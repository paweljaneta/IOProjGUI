package pl.polsl.client.protocol;

import java.io.IOException;

/**
 * Class used to connect client with server, describes communication protocol
 *
 * @author Wojciech Dębski
 * @version 1.0
 */
public class ClientProtocol {

    /**
     * Field with connection variables object
     */
    ConnectionVariables connection;

    /**
     * Constructor
     *
     * @param connection ConnectionVariables object
     */
    public ClientProtocol(ConnectionVariables connection) {
        this.connection = connection;
    }

    /**
     * Method to login user
     *
     * @param userName user's name
     * @param password user's password
     * @return null if something goes wrong(connection error or wrong login
     * date) otherwise it returns user type
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
     * Get transaction list waiting for authorisation in format: {"ID;Company
     * name;Room number;Hour;Date"}.
     *
     * @return String[] array with transactions which are waiting for
     * authorisation
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
     * Method to accept transaction by manager
     *
     * @param id String with transaction id
     * @return true when everything goes alright
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
     * Transaction refusing by chief
     *
     * @param id String with transaction id
     * @return true when everything goes alright
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
     * Getting rooms occupancy from server in format{"Film;Room number;Start
     * date ;End date; reserver"}.
     *
     * @return String[] with values separeted with ';' when everything goes
     * alright
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
     * Method to send transaction to confirming
     *
     * @return true when everything goes alright
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

    /**
     * Waiting for confirmation
     *
     * @return true when connfirmation is approved
     */
    private boolean awaitConfirm() {
        try {
            if (connection.in.readLine().equals("OK")) {
                return true;
            }
        } catch (IOException exception) {
        }
        return false;
    }

    /**
     * Waiting for operation done confiration
     *
     * @return true when "DONE" was sent
     */
    private boolean awaitOperationDone() {
        try {
            if (connection.in.readLine().equals("DONE")) {
                return true;
            }
        } catch (IOException exception) {
        }
        return false;
    }

    /**
     * Method to send confirmation message
     *
     * @param message String with message
     * @return true when server confirms reciving the message
     */
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
