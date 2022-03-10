/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apis;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;



/**
 *
 * @author mohamedabbes
 */
public class Gameapi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, JSONException {

OkHttpClient client = new OkHttpClient();

Request request = new Request.Builder()
	.url("https://valorant-esports.p.rapidapi.com/leagues")
	.get()
	.addHeader("x-rapidapi-host", "valorant-esports.p.rapidapi.com")
	.addHeader("x-rapidapi-key", "d3fb3d6a81msh0bf8c2d0abaada3p1bd3f3jsn77e625cc7347")
	.build();

        Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                    final String result = response.body().string();
                        JSONObject obj = new JSONObject(result);
                    System.out.println(obj);
            }
    }
    
}
