package Demo;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class InbuiltJava {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://www.google.com");
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "*/*");
        int statusCode = conn.getResponseCode();
        System.out.println(statusCode);
        InputStream inputstream = conn.getInputStream();
        InputStreamReader reader = new InputStreamReader(inputstream);
        BufferedReader br = new BufferedReader(reader);

        String data = null;
        while ((data = br.readLine()) != null) {
            System.out.println(data);
        }
    }
}