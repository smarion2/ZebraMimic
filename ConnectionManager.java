import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

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
                    List<String> messageList = new ArrayList<>();
                    while((read = inputStream.read(buffer)) != -1){
                        String message = new String(buffer, "ASCII").trim();
                        messageList.add(message);
                        buffer = new byte[bufferSize];
                        if (read < bufferSize && read > 0){
                            System.out.println("*** EOM ***" + read);
                            String joinedMessage = String.join("", messageList);
                            //System.out.println(joinedMessage);
                            String returnMessage = MessageDecoder.parse(joinedMessage);
                            OutputStream outputStream = connectionSocket.getOutputStream();
                            OutputStreamWriter streamWriter = new OutputStreamWriter(outputStream);
                            BufferedWriter writer = new BufferedWriter(streamWriter);
                            System.out.println("Sending message: " + returnMessage);
                            writer.write(returnMessage);
                            writer.flush();
                            messageList.clear();
                        }
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
    }
}