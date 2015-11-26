package search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Adam on 2015-11-26.
 */
public class Helper {
    public static ArrayList<String> removeDuplicates(ArrayList<String> al) {
        Set<String> hs = new HashSet<>();
        hs.addAll(al);
        al.clear();
        al.addAll(hs);
        return al;
    }

    public static HashMap<String, Integer> countDuplicates(ArrayList<String> al) {
        HashMap<String, Integer> douplicatesCounted = new HashMap<>();
        for (String key : al) {
            if (!douplicatesCounted.containsKey(key)) {
                douplicatesCounted.put(key, 1);
            } else {
                douplicatesCounted.put(key, douplicatesCounted.get(key).intValue() + 1);
            }
        }
        return douplicatesCounted;
    }

    public static HashMap<String, Integer> prepareKeywordsMatrix(ArrayList<String> al) {
        HashMap<String, Integer> keyMatrix = new HashMap<>();
        for (String key : SearchEngine.getInstance().getKeywords()) {
            keyMatrix.put(key, 0);
        }
        for (String key : al) {
            if (!keyMatrix.containsKey(key)) {
                keyMatrix.put(key, 1);
            } else {
                keyMatrix.put(key, keyMatrix.get(key).intValue() + 1);
            }
        }
        return keyMatrix;
    }
}
