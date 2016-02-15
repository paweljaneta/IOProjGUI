package pl.polsl.client.protocol;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Simple class aggregate to forward connection data in easy way.
 *
 * @author Wojciech Dębski
 * @version 1.0
 */
public class ConnectionVariables {

    /**
     * Socket connection with server.
     */
    public Socket socket;

    /**
     * DataOutputStream to server.
     */
    public DataOutputStream out;

    /**
     * BufferedReader from server.
     */
    public BufferedReader in;

    /**
     * Creates connection with serwer using socket and prepares output stream
     * and input reader.
     *
     * @param clientSocket socket to communicate with server
     * @throws IOException when there is no possibility to connect
     */
    public ConnectionVariables(Socket clientSocket) throws IOException {
        this.socket = clientSocket;
        this.out = new DataOutputStream(this.socket.getOutputStream());
        this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
    }
}
