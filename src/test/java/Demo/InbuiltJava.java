package Demo;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
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
    public static int brokenLink(String url) throws IOException {
        HttpURLConnection conn=(HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("HEAD");
        conn.connect();
        return conn.getResponseCode();
    }
    //Head http verb is similar to get but it wont get or wait for response body to download it will return the response header like status code and content Type.
    //in Get we will get the response header along with response body and it will take more time to download the response body and response time is more.
}