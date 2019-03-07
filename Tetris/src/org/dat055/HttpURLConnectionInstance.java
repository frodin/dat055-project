package org.dat055;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Helper class for sending high score
 *
 * @author Philip Hellberg
 * @version 2019-03-06
 */
public class HttpURLConnectionInstance {

    private final String POST_URL = "http://localhost:8080/";

    /**
     * sends HTTP POST command
     *
     * @param jsonObject the new score to be added to the high scores
     * @throws IOException if connection error occurs
     */
    public void sendPOST(JsonObject jsonObject) throws IOException {
        URL obj = new URL(POST_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();

        os.write(jsonObject.toString().getBytes());
        os.close();

        int responseCode = con.getResponseCode();



    }

}




