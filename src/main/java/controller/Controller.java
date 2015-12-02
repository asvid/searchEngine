package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import pojo.WordData;
import search.SearchEngine;
import search.Wordnet;

import java.net.URL;
import java.util.ArrayList;
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
    private FlowPane linkList;
    @FXML
    private CheckBox showSuggestions;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                result.clear();
                SearchEngine.getInstance().searchPhrase(query.getText())
                        .forEach((k, v) -> result.appendText(v + " | " + k.print() + "\n"));
                String[] queryArray = query.getText().split(" ");
                linkList.getChildren().clear();
                if (showSuggestions.isSelected()) {
                    if (queryArray.length > 1) {
                        for (String queryWord : queryArray) {
                            ArrayList<WordData> extendedQuery = Wordnet.getExtendedQuery(queryWord);
                            if (extendedQuery.size() > 0) {
                                int counter = extendedQuery.size() > 2 ? extendedQuery.size() : 0;
                                for (int i = 0; i < counter; i++) {
                                    String str = extendedQuery.get(i).getWord();
                                    Hyperlink hyperLink = new Hyperlink(str + " | ");
                                    //hyperLink.setPrefWidth(100);
                                    hyperLink.setWrapText(true);
                                    hyperLink.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent mouseEvent) {
                                            if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                                                query.setText(str);
                                            }
                                        }
                                    });
                                    linkList.getChildren().add(hyperLink);
                                }
                            }
                        }
                    }
                }
            }
        });
    }
}
