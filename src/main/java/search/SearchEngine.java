package search;

import documents.Document;
import helpers.FileHelper;
import stemmer.StemmerHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Adam on 2015-11-25.
 */
public class SearchEngine {
    private static SearchEngine instance;
    private ArrayList<String> keywords = new ArrayList<>();
    private ArrayList<Document> documents = new ArrayList<>();

    private void init() {
        generateKeywords(null);
        analyseDocuments(null);
    }

    public void analyseDocuments(String path) {
        if (path == null) path = "documents-lab1.txt";
        SearchEngine.getInstance().setDocuments(new ArrayList<>());
        String rawDocument = new FileHelper().readFile(path);
        String[] rawArray = rawDocument.split("[\\n\\r|\\n|\\r]{2}");
        for (int i = 0; i < rawArray.length; i++) {
            documents.add(new Document(rawArray[i]));
        }
    }

    public void generateKeywords(String path) {
        if (keywords.size() == 0) {
            if (path == null) path = "keywords-lab1.txt";
            SearchEngine.getInstance().setKeywords(new ArrayList<>());
            keywords = new StemmerHelper().runFromFile(path);
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

    public LinkedHashMap<Document, Double> searchPhrase(String phrase) {
        System.out.println("serching...");
        ArrayList<String> searchedKeywords = new StemmerHelper().runFromString(phrase);
        HashMap<Document, Double> foundDocuments = new HashMap<>();
        for (Document doc : documents) {
            Double value = FactorCalculator.getSimilarity(searchedKeywords, doc);
            if (value > 0) {
                foundDocuments.put(doc, value);
            }
        }
        LinkedHashMap<Document, Double> sorted = new LinkedHashMap<>();
        sorted = foundDocuments.entrySet().stream().sorted(Map.Entry.<Document, Double>comparingByValue().reversed())
                .limit(10)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        if (foundDocuments.size() == 0) {
            System.out.println("sorry, nothing about '" + phrase + "'");
        }
        sorted.forEach((k, v) -> System.out.println(k.print() + " wynik: " + v));

        System.out.println("-------------------------\nLet me find stuff for you:");

        return sorted;
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

    public int getDocumentsWith(String keyword) {
        int counter = 0;
        for (Document doc : documents) {
            if (doc.getKeywords().contains(keyword)) {
                counter++;
            }
        }
        return counter;
    }
}
