package search;

import steammer.SteammerHelper;

import java.util.ArrayList;

/**
 * Created by Adam on 2015-11-25.
 */
public class SearchEngine {
    private static SearchEngine instance;
    private ArrayList<String> keywords = new ArrayList<>();

    public SearchEngine() {
        init();
    }

    private void init() {
        generateKeywords();
    }

    private void generateKeywords() {
        if (keywords.size() == 0) {
            keywords = new SteammerHelper().run("keywords.txt");
            for (String keyword : keywords) {
                System.out.print(keyword + "\n");
            }
        }
    }

    public static SearchEngine getInstance() {
        if (instance == null) {
            instance = new SearchEngine();
        }
        return instance;
    }

    public SearchEngine searchPhrase(String s) {
        return this;
    }

    public void print() {
        System.out.println("jakiś tekst");
    }

}
