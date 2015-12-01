package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import pojo.WordData;
import search.SearchEngine;
import search.Wordnet;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Adam on 2015-12-01.
 */
public class Controller implements Initializable {
    @FXML
    private Button search;
    @FXML
    private TextArea result;
    @FXML
    private TextField query;
    @FXML
    private VBox linkList;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SearchEngine.getInstance().searchPhrase(query.getText())
                        .forEach((k, v) -> result.appendText(k.print() + " wynik: " + v + "\n"));

                for (WordData wordData : Wordnet.getExtendedQuery(query.getText())) {
                    linkList.getChildren().add(new Hyperlink(wordData.getSense()));
                }
            }
        });
    }
}
