import search.SearchEngine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Adam on 2015-11-25.
 */
public class Main {
    public static void main(String[] args) {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter something here : ");
        while (true) {
            try {
                String s = null;
                s = bufferRead.readLine();
                SearchEngine.getInstance().searchPhrase(s).print();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}