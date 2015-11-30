import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import pojo.WordData;
import search.SearchEngine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adam on 2015-11-25.
 */
public class Main {
    public static void main(String[] args) {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Let me find stuff for you: ");
        while (true) {
            try {
                String s;
                s = bufferRead.readLine();
                SearchEngine.getInstance().searchPhrase(s);
                try {
                    Gson gson = new Gson();
                    HttpResponse<JsonNode> jsonResponse =
                            Unirest.get("http://wordventure.eti.pg.gda.pl/FindSenses?q=%25" + s).asJson();

                    ArrayList<WordData> list = new ArrayList<>();
                    list = gson.fromJson(jsonResponse.getBody().toString(),
                            new TypeToken<List<WordData>>() {}.getType());
                    System.out.println(list);
                } catch (UnirestException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}