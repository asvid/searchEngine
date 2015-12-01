import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Adam on 2015-11-25.
 */
public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Let me find stuff for you: ");
        while (true) {
            try {
                String s;
                s = bufferRead.readLine();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Search Engine");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }
}