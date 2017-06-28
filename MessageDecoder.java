public class MessageDecoder {
    public static String parse(String message) {
        String response = "";
        if (message.contains("~HS")){
            // parse status message
            System.out.println("Generating Status Message...");
            response = PrinterStatus.generateStatusMessage();
        }
        if (message.contains("^XA")){
            // result message
            try {
                response = LabelDecoder.parseLabel(message);
            }
            catch (Exception e) {
                System.out.println("Could not parse label");
            }
        }        
        return response;
    }
}