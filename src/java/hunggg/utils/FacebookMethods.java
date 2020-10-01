/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hunggg.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.types.User;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

/**
 *
 * @author giang
 */
public class FacebookMethods {

    public static String getFacebookToken(final String code) throws IOException {
        String link = String.format(FacebookConstants.FACEBOOK_LINK_GET_TOKEN, FacebookConstants.FACEBOOK_APP_ID, FacebookConstants.FACEBOOK_APP_SECRET, FacebookConstants.FACEBOOK_REDIRECT_URL, code);
        String response = Request.Get(link).execute().returnContent().asString();
        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    public static User getFacebookUserInfo(String accessToken) {
        FacebookClient facebookClient = new DefaultFacebookClient(accessToken, FacebookConstants.FACEBOOK_APP_SECRET, Version.LATEST);
        return facebookClient.fetchObject("me", User.class);
    }
}
