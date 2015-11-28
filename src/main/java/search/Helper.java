package search;

import java.util.*;

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

    public static void print(String str) {
        System.out.println(str);
    }

    public static LinkedHashMap sortHashMapByValuesD(HashMap passedMap) {
        List mapKeys = new ArrayList(passedMap.keySet());
        List mapValues = new ArrayList(passedMap.values());
        Collections.sort(mapValues);

        LinkedHashMap sortedMap = new LinkedHashMap();

        Iterator valueIt = mapValues.iterator();
        while (valueIt.hasNext()) {
            Object val = valueIt.next();
            Iterator keyIt = mapKeys.iterator();

            while (keyIt.hasNext()) {
                Object key = keyIt.next();
                String comp1 = passedMap.get(key).toString();
                String comp2 = val.toString();

                if (comp1.equals(comp2)){
                    passedMap.remove(key);
                    mapKeys.remove(key);
                    sortedMap.put((String)key, (Double)val);
                    break;
                }

            }

        }
        return sortedMap;
    }
}
