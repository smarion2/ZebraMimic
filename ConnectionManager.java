import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionManager {

    private static final int timout = 3000;
    private static final int Port = 6880;
    private static final boolean WhileConnected = true; 

    public static void main (String args[]){
        try {
            ServerSocket input = new ServerSocket(Port);
            System.out.println("Starting server...");
            while (WhileConnected){
                try (
                    Socket connectionSocket = input.accept();
                    BufferedReader iptFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                    DataOutputStream optToClient = new DataOutputStream(connectionSocket.getOutputStream());
                ) {
                    String TCPclientMess = iptFromClient.readLine();
                    System.out.println("Sending message: " + TCPclientMess);
                    optToClient.writeBytes(TCPclientMess);
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