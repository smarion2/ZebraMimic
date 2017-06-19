import java.io.BufferedInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStream;

public class ConnectionManager {

    private static final int timout = 3000;
    private static final int port = 9100;
    private static final boolean whileConnected = true; 
    private static final int bufferSize = 1024;
    private static InputStream inputStream = null;

    public static void main (String args[]){
        try {
            ServerSocket input = new ServerSocket(port);
            System.out.println("Starting server...");

            while (whileConnected){
                try { 
                    Socket connectionSocket = input.accept();
                    inputStream = new BufferedInputStream(connectionSocket.getInputStream());
                    int read;
                    if (connectionSocket.isConnected()){
                        System.out.println("Socket is connected!");
                    }
                    byte[] buffer = new byte[bufferSize];
                    while((read = inputStream.read(buffer)) != -1){
                        String message = new String(buffer, "ASCII").trim();
                        System.out.println(message);
                    }
                }
                catch (Exception e) {
                    System.out.println("Error sending message: " + e);
                }
                finally {
                    inputStream.close();
                }
            }            
        }
        catch (Exception e){
            System.out.println("Error: " + e);
        }
        finally {
            inputStream.close();
        }
    }
}