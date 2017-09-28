public class ZebraMimic {    
    public static void main(String[] args){
        Settings settings = new Settings();
        settings.createCommandLineOptions(args); 
        ConnectionManager connectionManager = new ConnectionManager();       
        connectionManager.startServer();
    }

}