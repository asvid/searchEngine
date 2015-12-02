package search;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import pojo.WordData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adam on 2015-12-01.
 */
public class Wordnet {
    public static ArrayList<WordData> getExtendedQuery(String text) {
        ArrayList<WordData> list = new ArrayList<>();
        try {
            Gson gson = new Gson();
            HttpResponse<JsonNode> jsonResponse =
                    Unirest.get("http://wordventure.eti.pg.gda.pl/FindWords?q=%25" + text).asJson();
            list = gson.fromJson(jsonResponse.getBody().toString(), new TypeToken<List<WordData>>() {}.getType());
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return list;
    }
}
