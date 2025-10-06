package Demo;

import okhttp3.*;

import java.io.IOException;

public class OkHttpPost {
    public static void main(String[] args) throws IOException {
        OkHttpClient client=new OkHttpClient();
        //--->Request Body
        String jsonBody="{\n" +
                "    \"username\": \"iamfd\",\n" +
                "    \"password\": \"password\"\n" +
                "}";

        RequestBody requestBody=RequestBody.create(jsonBody, MediaType.parse("application/json"));
        Request request=new Request.Builder().url("http://64.227.160.186:9000/v1/login").post(requestBody).header("Accept","*/*").build();
        Response response=client.newCall(request).execute();
        System.out.println(response.code());
        System.out.println(response.body().string());
        System.out.println(response.message());
    }
}
