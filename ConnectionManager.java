import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionManager {

    private static final int timout = 3000;
    private static final int port = 6880;
    private static final boolean whileConnected = true; 

    public static void main (String args[]){
        try {
            ServerSocket input = new ServerSocket(port);
            System.out.println("Starting server...");
            while (whileConnected){
                try (
                    Socket connectionSocket = input.accept();
                    BufferedReader iptFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                    DataOutputStream optToClient = new DataOutputStream(connectionSocket.getOutputStream());
                ) {
                    String tcpClientMess = iptFromClient.readLine();
                    System.out.println("Sending message: " + tcpClientMess);
                    optToClient.writeBytes(tcpClientMess);
                }
                catch (Exception e) {
                    System.out.println("Error sending message: " + e);
                }
            }            
        }
        catch (Exception e){
            System.out.println("Error: " + e);
        }
    }
}