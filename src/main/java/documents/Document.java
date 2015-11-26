package documents;

import search.SearchEngine;
import steammer.SteammerHelper;

import java.util.ArrayList;

/**
 * Created by Adam on 2015-11-25.
 */
public class Document {
    private String title;
    private String content;
    private String rawContent;
    private ArrayList<String> keywords = new ArrayList<>();

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
        ArrayList<String> stemmedContent = new SteammerHelper().runFromString(rawContent);
        for (String word : stemmedContent) {
            for (String keyword : SearchEngine.getInstance().getKeywords()) {
                if (keyword.equals(word)) {
                    keywords.add(keyword);
                }
            }
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

    @Override
    public String toString() {
        return title + " " + content;
    }
}
