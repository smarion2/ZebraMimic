package ZebraMimic;
public class ZebraMimic {    
    public static void main(String[] args){
        Settings settings = new Settings();
        ArgumentParser parser = new ArgumentParser();
        settings = parser.createCommandLineOptions(args); 
        ConnectionManager connectionManager = new ConnectionManager(settings);       
        connectionManager.startServer();
    }

}