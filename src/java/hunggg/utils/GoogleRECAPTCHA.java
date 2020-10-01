/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hunggg.utils;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
/**
 *
 * @author giang
 */
public class GoogleRECAPTCHA {
    public static final String SITE_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";
    public static final String SECRET_KEY = "6LeBjqgZAAAAAHfwk-CkS17r4ryNAc0dh42dTiWP";
    public static final String USER_AGENT = "Chrome/83.0.4103.116";

    public static boolean verify(String gCaptchaResponse)
            throws IOException {
        if (gCaptchaResponse == null || gCaptchaResponse.length() == 0) {
            return false;
        }
        URL verificationURL = new URL(SITE_VERIFY_URL);

        //HttpsURLConnection conn = (HttpsURLConnection) verificationURL.openConnection();
        HttpURLConnection conn = (HttpURLConnection) verificationURL.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("User-Agent", USER_AGENT);
        conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String params = "secret=" + SECRET_KEY + "&response=" + gCaptchaResponse;

        conn.setDoOutput(true);
        DataOutputStream outputStream = new DataOutputStream(conn.getOutputStream());
        outputStream.writeBytes(params);
        outputStream.flush();
        outputStream.close();

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        String inputLine = "";
        StringBuilder buffer = new StringBuilder();

        while ((inputLine = reader.readLine()) != null) {
            buffer.append(inputLine);
        }
        reader.close();

        JsonReader jsonReader = Json.createReader(new StringReader(buffer.toString()));
        JsonObject jsonObject = jsonReader.readObject();
        jsonReader.close();

        return jsonObject.getBoolean("success");

    }
}
