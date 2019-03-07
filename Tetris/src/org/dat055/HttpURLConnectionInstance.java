package org.dat055;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpURLConnectionInstance {

    private final String POST_URL = "http://localhost:8080/";

    public void sendPOST(JsonObject jsonObject) throws IOException {
        URL obj = new URL(POST_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();

        os.write(jsonObject.toString().getBytes());
        os.close();

        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);


    }

}




