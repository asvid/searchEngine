package documents;

import search.Helper;
import search.SearchEngine;
import stemmer.StemmerHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Adam on 2015-11-25.
 */
public class Document {
    private String title;
    private String content;
    private String rawContent;
    private int maxKeyword = 0;
    private ArrayList<String> keywords = new ArrayList<>();
    private HashMap<String, Integer> keywordsMatrix = new HashMap<>();
    private HashMap<String, Double> TF = new HashMap<>();

    public Document(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Document(String rawContent) {
        createDocument(rawContent);
    }

    private void createDocument(String text) {
        this.rawContent = text;
        this.content = text.substring(text.indexOf("\n") + 1);
        this.title = text.split("\\n")[0];
        getKeyWords();
    }

    public String getRawContent() {
        return rawContent;
    }

    public void setRawContent(String rawContent) {
        this.rawContent = rawContent;
    }

    public ArrayList<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(ArrayList<String> keywords) {
        this.keywords = keywords;
    }

    private void getKeyWords() {
        ArrayList<String> stemmedContent = new StemmerHelper().runFromString(rawContent);
        for (String word : stemmedContent) {
            keywords.addAll(SearchEngine.getInstance().getKeywords().stream().filter(keyword -> keyword.equals(word))
                    .collect(Collectors.toList()));
        }
        keywordsMatrix = Helper.prepareKeywordsMatrix(keywords);
        maxKeyword = keywordsMatrix.entrySet().stream()
                .max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getValue();
        Helper.countDuplicates(keywords).forEach((k, v) -> TF.put(k, v.doubleValue() / maxKeyword));
        checkTFvalues();
    }

    private void checkTFvalues() {
        Iterator it = TF.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void checkKeywords(ArrayList<String> keywords) {
        this.keywords = keywords;
    }

    public int getMaxKeyword() {
        return maxKeyword;
    }

    public void setMaxKeyword(int maxKeyword) {
        this.maxKeyword = maxKeyword;
    }

    public HashMap<String, Integer> getKeywordsMatrix() {
        return keywordsMatrix;
    }

    public void setKeywordsMatrix(HashMap<String, Integer> keywordsMatrix) {
        this.keywordsMatrix = keywordsMatrix;
    }

    public HashMap<String, Double> getTF() {
        return TF;
    }

    public void setTF(HashMap<String, Double> TF) {
        this.TF = TF;
    }

    @Override
    public String toString() {
        return title + " " + content;
    }

    public String print() {
        return ("Title: " + title + " ");
    }
}
