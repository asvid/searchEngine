package steammer;

import org.tartarus.martin.Stemmer;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class SteammerHelper {

    Stemmer s = new Stemmer();
    ArrayList<String> toReturn = new ArrayList<>();

    public ArrayList<String> runFromFile(String location) {

        System.out.println("runFromFile: " + location);
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            String file = classLoader.getResource(location).getFile();
            toReturn.clear();
            FileInputStream in = new FileInputStream(file);
            return generateOutput(in);
        } catch (Exception e) {
            System.out.println("file " + location + " not found");
        }
        return null;
    }

    public ArrayList<String> runFromString(String text) {
        InputStream is = new ByteArrayInputStream(text.getBytes());
        toReturn.clear();
        return generateOutput(is);
    }

    private ArrayList<String> generateOutput(InputStream in) {
        char[] w = new char[501];
        try {
            while (true) {
                int ch = in.read();
                if (Character.isLetter((char) ch)) {
                    int j = 0;
                    while (true) {
                        ch = Character.toLowerCase((char) ch);
                        w[j] = (char) ch;
                        if (j < 500) {
                            j++;
                        }
                        ch = in.read();
                        if (!Character.isLetter((char) ch)) {
                            for (int c = 0; c < j; c++)
                                s.add(w[c]);
                            s.stem();
                            {
                                toReturn.add(s.toString());
                            }
                            break;
                        }
                    }
                }
                if (ch < 0) {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("error reading " + in);
        }
        return toReturn;
    }
}