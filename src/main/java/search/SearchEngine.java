package search;

import documents.Document;
import helpers.FileHelper;
import steammer.SteammerHelper;

import java.util.ArrayList;

/**
 * Created by Adam on 2015-11-25.
 */
public class SearchEngine {
    private static SearchEngine instance;
    private ArrayList<String> keywords = new ArrayList<>();
    private ArrayList<Document> documents = new ArrayList<>();

    private void init() {
        generateKeywords();
        analyseDocuments();
    }

    private void analyseDocuments() {
        String rawDocument = new FileHelper().readFile("documents.txt");
        String[] rawArray = rawDocument.split("[\\n\\r|\\n|\\r]{2}");
        for (int i = 0; i < rawArray.length; i++) {
            documents.add(new Document(rawArray[i]));
        }
    }

    private void generateKeywords() {
        if (keywords.size() == 0) {
            keywords = new SteammerHelper().runFromFile("keywords.txt");
            for (String keyword : keywords) {
                //System.out.print(keyword + "\n");
            }
        }
    }

    public static SearchEngine getInstance() {
        if (instance == null) {
            instance = new SearchEngine();
            instance.init();
        }
        return instance;
    }

    public SearchEngine searchPhrase(String s) {
        ArrayList<String> searchedKeywords = new SteammerHelper().runFromString(s);
        System.out.println(searchedKeywords);
        System.out.println(FactorCalculator.getQueryFactor(searchedKeywords));


        return this;
    }

    public ArrayList<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(ArrayList<String> keywords) {
        this.keywords = keywords;
    }

    public ArrayList<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(ArrayList<Document> documents) {
        this.documents = documents;
    }

    public void print() {
        System.out.println("jakiś tekst");
    }

    public int getDocumentsWith(String keyword) {
        int counter = 0;
        for (Document doc : documents) {
            for (String docKeyword : doc.getKeywords()) {
                if (docKeyword.equals(keyword)) {
                    counter++;
                }
            }
        }
        return counter;
    }
}
