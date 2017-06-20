import java.io.*;

public class PrinterStatus {
    private static String _communicationSettings = "000"; // 3 characters
    private static boolean _paperIsOut = false;
    private static boolean _printerIsPaused = false;
    private static int _labelLength = 1000; // 4 characters
    private static int _numberOfFormats = 0; // 3 characters
    private static boolean _bufferIsFull = false;
    private static boolean _diagnosticMode = false;
    private static boolean _partialFormat = false;
    private static String _unused = "000"; // this is always 000
    private static boolean _ramIsCorrupt = false;
    private static boolean _underTemp = false;
    private static boolean _overTemp = false;

    public static String GenerateStatusMessage() {
        String statusMessage = "";

        statusMessage += _communicationSettings + ",";
        statusMessage += _paperIsOut ? "1," : "0,"; 
        statusMessage += _printerIsPaused ? "1," : "0,";
        statusMessage += String.format("|%04d|", Integer.toString(_labelLength)) + ",";
        statusMessage += String.format("|%03d|", Integer.toString(_numberOfFormats)) + ",";
        statusMessage += _bufferIsFull ? "1," : "0,";
        statusMessage += _diagnosticMode ? "1," : "0,";
        statusMessage += _partialFormat ? "1," : "0,";
        statusMessage += _unused;
        statusMessage += _ramIsCorrupt ? "1," : "0,";
        statusMessage += _underTemp ? "1," : "0,";
        statusMessage += _overTemp ? "1," : "0";
        
        return statusMessage;
    }
}