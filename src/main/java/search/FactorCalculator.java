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
        Double underRoot = 0.0;
        for (String keyword : queryKeywords) {
            int docWithKeyword = SearchEngine.getInstance().getDocumentsWith(keyword);
            if (docWithKeyword > 0) {
                underRoot += Math.pow(Math.log10((double) SearchEngine.getInstance().getDocuments().size() /
                        SearchEngine.getInstance().getDocumentsWith(keyword)), 2);
            }

        }
        return Math.sqrt(underRoot);
    }

    public static HashMap<String, Double> getQueryFactorMatrix(ArrayList<String> queryKeywords) {
        HashMap<String, Double> returnMatrix = new HashMap<>();
        for (String keyword : queryKeywords) {
            int docWithKeyword = SearchEngine.getInstance().getDocumentsWith(keyword);
            if (docWithKeyword > 0) {
                Helper.print(" query matrix : ->>> " + SearchEngine.getInstance().getDocuments().size() + " / " + SearchEngine.getInstance().getDocumentsWith(keyword));
                returnMatrix.put(keyword, Math.log10((double) SearchEngine.getInstance().getDocuments().size() /
                        (double) SearchEngine.getInstance().getDocumentsWith(keyword)));
            }
        }
        return returnMatrix;
    }

    public static Double getDocumentFactor(Document document) {
        HashMap<String, Integer> countedQuery = Helper.prepareKeywordsMatrix(document.getKeywords());
        Double underRoot = 0.0;
        Iterator it = countedQuery.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            int docWithKeyword = SearchEngine.getInstance().getDocumentsWith((String)pair.getKey());
            if (docWithKeyword > 0 && document.getTF().get(pair.getKey()) != null) {
                underRoot += Math.pow(document.getTF().get(pair.getKey())*Math.log10((double) SearchEngine.getInstance().getDocuments().size() /
                        (double) SearchEngine.getInstance().getDocumentsWith((String)pair.getKey())),2);
            }
            it.remove();
        }
        return Math.sqrt(underRoot);
    }

    public static Double getSimilarity(ArrayList<String> querryList, Document document) {
        HashMap<String, Double> queryMatrix = getQueryFactorMatrix(querryList);
        HashMap<String, Double> documentMatrix = document.getTF();
        Double licznik = 0.0;
        Iterator it = queryMatrix.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            //System.out.println("docMatrix: " + documentMatrix.get(pair.getKey()) + " / mianownik: " + mianownik);
            if (documentMatrix.get(pair.getKey()) != null &&
                    !documentMatrix.get(pair.getKey()).toString().equals("-Infinity") &&
                    !documentMatrix.get(pair.getKey()).toString().equals("Infinity")) {
                licznik += (Double) pair.getValue() * documentMatrix.get(pair.getKey()) * queryMatrix.get(pair.getKey());
                it.remove();
            }
        }
        double mianownik = getQueryFactor(querryList) * getDocumentFactor(document);

        if (mianownik > 0) {
            Helper.print(licznik + " / " + mianownik);
            return licznik / mianownik;
        } else {
            //System.out.println("Noting found..." + querryList + " / " + document.getKeywords());
            return 0.0;
        }
    }
}
