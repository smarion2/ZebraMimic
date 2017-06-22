import java.net.URL;
import java.net.URLConnection;
import java.net.MalformedURLException;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;

public class CreateTagImage {

    public static void queryAPI(String zpl, String tagName) {
        try {
            URLConnection conn = new URL("http://api.labelary.com/v1/printers/12dpmm/labels/4x6/0/").openConnection();
            conn.setDoOutput(true);
            //conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-length", String.valueOf(zpl.length()));
            OutputStream stream = conn.getOutputStream();
            try {
                stream.write(zpl.getBytes());
            }
            catch (Exception e) {
                System.out.println("Error sending message: " + e);
            }
            finally {
                stream.close();
            }
            InputStream response = conn.getInputStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            while (true) {
                byte[] buffer = new byte[2048];
                int r = response.read(buffer);
                if (r == -1) {
                    break;
                }
                out.write(buffer, 0, r);
            }

            byte[] tagImageBytes = out.toByteArray();
            String dir = System.getProperty("user.dir");
            String filePath = dir + "\\TagImages\\" + tagName + ".png";
            OutputStream saveFile = new FileOutputStream(filePath);
            saveFile.write(tagImageBytes);
            saveFile.close();
        }
        catch (Exception ex) {
            System.out.println("URL malformed check if API is down Error: " + ex);
        }

        // convert bytes to image and save to folder.
    }
}