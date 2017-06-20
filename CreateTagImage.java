import java.io.*;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.net.URL;
import java.io.OutputStream;

public class CreateTagImage {

    public static void queryAPI(String zpl) {
        String type = "application/x-www-form-urlencoded";
        String encodedData = URLEncoder.encode(zpl);
        // dont forget to change dpmm i forget what the printer uses right now.
        URL url = new URL("http://api.labelary.com/v1/printers/{dpmm}/labels/4x6/0/");
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", type);
        conn.setRequestProperty("Content-length", String.valueOf(encodedData.length()));
        OutputStream stream = conn.getOutputStream();
        steam.write(encodedData.getBytes());

        // convert bytes to image and save to folder.
    }
}