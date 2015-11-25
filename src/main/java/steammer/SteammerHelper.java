package steammer;

import org.tartarus.martin.Stemmer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class SteammerHelper {
    public ArrayList<String> run(String location) {
        char[] w = new char[501];
        ArrayList<String> toReturn = new ArrayList<>();
        ClassLoader classLoader = getClass().getClassLoader();
        String file = classLoader.getResource(location).getFile();

        Stemmer s = new Stemmer();
        try {
            FileInputStream in = new FileInputStream(file);
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
                    //toReturn.add(s.toString());
                }
            } catch (IOException e) {
                System.out.println("error reading " + file);
            }
        } catch (FileNotFoundException e) {
            System.out.println("file " + file + " not found");
        }
        return toReturn;
    }
}