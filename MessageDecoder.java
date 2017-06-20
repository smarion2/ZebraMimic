import java.io.*;
import java.util.*;
public class MessageDecoder {
    public static String parse(List<String> message) {
        String response = "";
        int messageLength = message.size();
        for (int i = 0; i < messageLength; i++) {
            String messageIndex = message.get(i);
            if (messageIndex.contains("~HS")){
                // parse status message
                response = generateStatus();
            }
            if (messageIndex.contains("~RVE")){
                // result message
                response = generatePrintResult();
            }
        }
        return response;
    }

    private static String generateStatus(){
        String status = "";
        return status;
    }

    private static String generatePrintResult(){
        String printResult = "";
        return printResult;
    }


}