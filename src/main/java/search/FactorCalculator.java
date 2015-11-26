package search;

import documents.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Adam on 2015-11-26.
 */
public class FactorCalculator {

    public static Double getQueryFactor(ArrayList<String> queryKeywords) {
        //log z = liczba dokumentów ogólnie : liczba dokumentów z konkretnym keyword
        for (String keyword : queryKeywords) {
            Double mathLog = Math.log(SearchEngine.getInstance().getDocuments().size() / SearchEngine.getInstance().getDocumentsWith(keyword));
        }


        HashMap<String, Integer> countedQuery = Helper.prepareKeywordsMatrix(queryKeywords);
        Double underRoot = 0.0;
        Iterator it = countedQuery.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            underRoot += Math.pow((Integer) pair.getValue(), 2);
            it.remove();
        }
        return Math.sqrt(underRoot);
    }

    public static Double getDocumentFactor(Document document) {
        HashMap<String, Integer> countedQuery = Helper.prepareKeywordsMatrix(document.getKeywords());
        Double underRoot = 0.0;
        Iterator it = countedQuery.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            underRoot += Math.pow((Integer) pair.getValue(), 2);
            it.remove();
        }
        return Math.sqrt(underRoot);
    }
}
