public class LabelDecoder {
    public static void parseLabel(String label){
        private static boolean hostVerificationEnabled = false;
        private static boolean reportRfResults = false;


        String[] messageLines = label.split("[\\^~]");
        for (int i = 0; i < messageLines.length; i++){
            if (messageLines[i] == "RVE") { // RV is E nabled D RVD would be disabled so no return
                // fake a chance of + or - and the amount of times it took. Max 3 attempts starting at 0
            }
            
            System.out.println(messageLines[i]);
        }
    }
}