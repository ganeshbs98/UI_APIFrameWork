package Demo;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class OkHttpClientLib {

    public static void main(String[] args) throws IOException {
        OkHttpClient okclient=new OkHttpClient();
        Request request=new Request.Builder().url("https://www.google.com").get().header("Accpet","*/*").build();
        Response response=okclient.newCall(request).execute();
        System.out.println(response.code());
        System.out.println(response.body().string());
        System.out.println(response.message());
    }
}
