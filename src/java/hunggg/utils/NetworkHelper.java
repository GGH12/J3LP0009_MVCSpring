/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hunggg.utils;
import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
/**
 *
 * @author giang
 */
public class NetworkHelper {
    
    public static String getResult(String url) throws ClientProtocolException, IOException {
        String result = Request.Get(url)
                .setHeader("Accept-Charset", "utf-8")
                .execute().returnContent().asString();
        return result;
    }
}
