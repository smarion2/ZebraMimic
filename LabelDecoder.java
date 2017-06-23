import java.util.Hashtable;
import java.util.Random;

public class LabelDecoder {
    private static int labelNumber = 0;
    public static String parseLabel(String label){        
        Hashtable<String, String> fieldNumberTable = new Hashtable<String, String>(); 
        boolean reportRfResults = false;
        String responseMessage = "";
        String epc = "";
        String tid = GenerateRandomTID();
        String hvString = "";
        labelNumber++;

        if (Settings.createTagImages){
            CreateTagImage.queryAPI(label, Integer.toString(labelNumber));
        }
        
        String[] messageGroups = label.split("\\^FS");
        for (int i = 0; i < messageGroups.length; i++){
            String[] messageLines = messageGroups[i].split("[\\^~]");
            for (int j = 0; j < messageLines.length; j++){
                //System.out.println(messageLines[j]); // log the lines of the label
                if (messageLines[j].contains("RVE")) { // RV is E nabled D RVD would be disabled so no return
                    // fake a chance of + or - and the amount of times it took. Max 3 attempts starting at 0
                    reportRfResults = true;
                }
                else if (messageLines[j].contains("RFW")) { // write or encode the tag
                    String[] rfWriteCommand = messageLines[j].split(",");                    
                    if (rfWriteCommand[4].equals("E")){
                        // should probably check if j + 1 isnt out of range exception but ya know....                        
                        epc = messageLines[j + 1].substring(2);
                        break;
                    }

                }
                else if (messageLines[j].contains("RFR")) { // read the tag
                    String[] rfReadCommand = messageLines[j].split(",");
                    if (rfReadCommand[4].equals("E")){
                        // want to read EPC
                        String index = messageLines[j + 1].substring(2);
                        fieldNumberTable.put(index, epc);

                    }
                    else if (rfReadCommand[4].equals("2")) {
                        // create fake TID to send
                        String index = messageLines[j + 1].substring(2);
                        fieldNumberTable.put(index, tid);
                    }
                }
                else if (messageLines[j].contains("HV")){
                    String[] verficationString = messageLines[j].split(",");
                    hvString += verficationString[2] + fieldNumberTable.get(verficationString[0].substring(2)) + verficationString[3];
                }
            }
        }
        if (reportRfResults == true){
            if (Settings.simulateTagVoids){
                responseMessage += SimulatePrintSuccess();
            }
            else {
                responseMessage += "0+,0";
            }
        }
        if (hvString != ""){
            responseMessage += hvString;
        }
        return responseMessage;
    }

    private static String GenerateRandomTID(){
        Random rand = new Random();
        String characters = "ABCDEF0123456789";
        String tid = "";
        for (int i = 0; i < 16; i++) {
            tid += characters.charAt(rand.nextInt(characters.length()));
        }
        return tid;
    }

    private static String SimulatePrintSuccess(){
        Random rand = new Random();
        String result = "";
        int roll = rand.nextInt(100) + 1;
        if (roll >= Settings.tagSuccessChance) {
            result = "0+,0";
        }
        else {
            result = "0-,3";
        }
        return result;
    }
}