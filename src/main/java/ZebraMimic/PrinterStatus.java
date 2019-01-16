package ZebraMimic;
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
    private static String _unused = "000,"; // this is always 000
    private static boolean _ramIsCorrupt = false;
    private static boolean _underTemp = false;
    private static boolean _overTemp = false;

    private static int _functionSettings = 000;
    private static boolean _printerHeadIsUp = false;
    private static boolean _ribbonIsOut = false;
    private static boolean _thermalTransferModeSelected = false;
    private static char _PrintMode = 'A';
    private static int _printWidthMode = 1;
    private static boolean _peelOffMode = false;
    private static int _labelsReminingInBatch = 0;
    private static int _numOfGraphicsStored = 0;
    private static String _password = "1234"; 
    private static boolean _staticRamIsInstalled = false;

    public static String generateStatusMessage() {
        String statusMessage = "";

        statusMessage += _communicationSettings;
        statusMessage += ",";
        statusMessage += _paperIsOut ? "1," : "0,"; 
        statusMessage += _printerIsPaused ? "1," : "0,";
        statusMessage += String.format("%04d", _labelLength);
        statusMessage += ",";
        statusMessage += String.format("%03d", _numberOfFormats);
        statusMessage += ",";
        statusMessage += _bufferIsFull ? "1," : "0,";
        statusMessage += _diagnosticMode ? "1," : "0,";
        statusMessage += _partialFormat ? "1," : "0,";
        statusMessage += _unused;
        statusMessage += _ramIsCorrupt ? "1," : "0,";
        statusMessage += _underTemp ? "1," : "0,";
        statusMessage += _overTemp ? "1," : "0,";
        statusMessage += Integer.toString(_functionSettings);
        statusMessage += ",";
        statusMessage += "0,";
        statusMessage += _printerHeadIsUp ? "1," : "0,";
        statusMessage += _ribbonIsOut ? "1," : "0,";
        statusMessage += _thermalTransferModeSelected ? "1," : "0,";
        statusMessage += _PrintMode;
        statusMessage += ",";
        statusMessage += Integer.toString(_printWidthMode);
        statusMessage += ",";
        statusMessage += _peelOffMode ? "1," : "0,";
        statusMessage += Integer.toString(_labelsReminingInBatch);
        statusMessage += ",0,";
        statusMessage += Integer.toString(_numOfGraphicsStored);
        statusMessage += ",";
        statusMessage += _password;
        statusMessage += ",";
        statusMessage += _staticRamIsInstalled ? "1" : "0";
        return statusMessage;
    }
}