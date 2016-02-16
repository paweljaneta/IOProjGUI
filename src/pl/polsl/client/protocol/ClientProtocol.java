package pl.polsl.client.protocol;

import java.io.IOException;

/**
 *
 * @author Wojciech Dębski
 * @version 1.0
 */
public class ClientProtocol {

    ConnectionVariables connection;

    public ClientProtocol(ConnectionVariables connection) {
        this.connection = connection;
    }

    /**
     * Logowanie użytkownika.
     *
     * @param userName user's name
     * @param password user's password
     * @return null jeżeli operacja przebiegła nieprawidłowo (błąd połączenia
     * lub błędne dane logowania) w przeciwnym wypadku zwaraca typ zalogowanego
     * użytkownika
     */
    public String login(String userName, String password) {

        if (!sendWithConfirm("login")) {
            return null;
        }
        if (!sendWithConfirm(userName)) {
            return null;
        }
        if (!sendWithConfirm(password)) {
            return null;
        }
        if (!awaitOperationDone()) {
            return null;
        }
        return getResponse();
    }

    /**
     * Zwaraca listę transakcji wymagających autoryzacji w formacie: {"ID;Nazwa
     * firmy;Numer sali;Godzina;Data"}.
     *
     * @return tablicę z wartościami odseprowanymi średnikami gdy operacja się
     * powiedzie, w przeciwnym razie null
     */
    public String[] getTransactions() {
        Integer transactionsCounter;
        if (!sendWithConfirm("gettransactions")) {
            return null;
        }
        if (!awaitOperationDone()) {
            return null;
        }
        try {
            transactionsCounter = Integer.parseInt(getResponse());
        } catch (NumberFormatException exception) {
            return null;
        }
        String[] transactions = new String[transactionsCounter];
        for (int i = 0; i < transactionsCounter; i++) {
            String response = getResponse();
            if (response == null) {
                return null;
            }
            transactions[i] = response;
        }
        return transactions;
    }

    /**
     * Akceptacja transakcji przez menadżera.
     *
     * @param id
     * @return true gdy się uda, false gdy nie
     */
    public boolean acceptTransaction(String id) {
        if (!sendWithConfirm("accepttransaction")) {
            return false;
        }
        if (!sendWithConfirm(id)) {
            return false;
        }
        return awaitOperationDone();
    }

    /**
     * Odrzucenie transakcji przez menadżera.
     *
     * @param id
     * @return true gdy się uda, false gdy nie
     */
    public boolean refuseTransaction(String id) {
        if (!sendWithConfirm("refusetransaction")) {
            return false;
        }
        if (!sendWithConfirm(id)) {
            return false;
        }
        return awaitOperationDone();
    }

    /**
     * Pobranie obłożenia sal w formacie: {"Film;Numer Sali;Data
     * rozpoczęcia;Data zakończenia;Zarezerwowano"}.
     *
     * @return tablicę z wartościami odseprowanymi średnikami gdy operacja się
     * powiedzie, w przeciwnym razie null
     */
    public String[] getRoomsOccupancy() {
        Integer roomsOccupancyCounter;
        if (!sendWithConfirm("getroomsoccupancy")) {
            return null;
        }
        if (!awaitOperationDone()) {
            return null;
        }
        try {
            roomsOccupancyCounter = Integer.parseInt(getResponse());
        } catch (NumberFormatException exception) {
            return null;
        }
        String[] roomsOccupancy = new String[roomsOccupancyCounter];
        for (int i = 0; i < roomsOccupancyCounter; i++) {
            String response = getResponse();
            if (response == null) {
                return null;
            }
            roomsOccupancy[i] = response;
        }
        return roomsOccupancy;
    }

    /**
     * Przesłanie transakcji do potwierdzenia.
     *
     * @return true gdy się uda, false gdy nie
     */
    public boolean sendTransaction(String startDate, String endDate, String price,
            String companyName, String roomNumber, String type) {
        if (!sendWithConfirm("sendtransaction")) {
            return false;
        }
        if (!sendWithConfirm(startDate)) {
            return false;
        }
        if (!sendWithConfirm(endDate)) {
            return false;
        }
        if (!sendWithConfirm(price)) {
            return false;
        }
        if (!sendWithConfirm(companyName)) {
            return false;
        }
        if (!sendWithConfirm(roomNumber)) {
            return false;
        }
        if (!sendWithConfirm(type)) {
            return false;
        }
        return awaitOperationDone();
    }

    private String getResponse() {
        try {
            return connection.in.readLine();
        } catch (IOException exception) {
            return null;
        }
    }

    private boolean awaitConfirm() {
        try {
            if (connection.in.readLine().equals("OK")) {
                return true;
            }
        } catch (IOException exception) {
        }
        return false;
    }

    private boolean awaitOperationDone() {
        try {
            if (connection.in.readLine().equals("DONE")) {
                return true;
            }
        } catch (IOException exception) {
        }
        return false;
    }

    private boolean sendWithConfirm(String message) {
        try {
            this.connection.out.writeBytes(message + System.getProperty("line.separator"));
            if (!awaitConfirm()) {
                return false;
            }
        } catch (IOException exception) {
            return false;
        }
        return true;
    }
}
