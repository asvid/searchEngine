package helpers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Adam on 2015-11-26.
 */
public class FileHelper {

    public String readFile(String fileLocation) {
        ClassLoader classLoader = getClass().getClassLoader();
        String file;
        try {
            file = classLoader.getResource(fileLocation).getFile();
        } catch (Exception e) {

            file = fileLocation;
        }

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));

            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            br.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
