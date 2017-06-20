public class MessageDecoder {
    public static String parse(String message) {
        String response = "";
        if (message.contains("~HS")){
            // parse status message
            System.out.println("Generating Status Message...");
            response = generateStatus();
        }
        if (message.contains("^XA")){
            // result message
            //response = 
            LabelDecoder.parseLabel(message);
        }        
        return response;
    }

    private static String generateStatus(){
        String status = PrinterStatus.generateStatusMessage();
        return status;
    }
}