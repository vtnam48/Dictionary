package sample;

import com.sun.xml.internal.ws.api.pipe.Engine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

public class Controller implements Initializable {
    Dictionary dictionary = new Dictionary();

    @FXML
    private ListView listView;

    @FXML
    private WebView webView;
    private WebEngine engine;

    @FXML
    private TextField textField;

//    @FXML
//    private Button buttonEtoV;
//
//    @FXML
//    private Button buttonVtoE;

    public void loadDataEtoV() throws IOException {
        dictionary.importDataEtoV();
    }

    public void loadDataVtoE() throws IOException {
        dictionary.importDataVtoE();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            loadDataEtoV();
        } catch (IOException e) {
            e.printStackTrace();
        }
        listView.getItems().addAll(dictionary.getData().keySet());
        listView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    Word selectedWord = dictionary.getData().get(newValue);
                    if (selectedWord != null) {
                        String explain = selectedWord.getWord_explain();
                        engine = webView.getEngine();
                        engine.loadContent(explain, "text/html");
                    }

                }
        );

        System.out.println("2");
    }

    public void del(ActionEvent e) {
        listView.getItems().clear();
        listView.getSelectionModel().clearSelection();

    }

    public void search() {
        String s = textField.getText();

        listView.getItems().clear();
        listView.getSelectionModel().clearSelection();

        listView.getItems().addAll(dictionary.searchWord(s.toLowerCase()));
    }

    public void setSceneEtoV(ActionEvent e) throws IOException{
        listView.getItems().clear();
        listView.getSelectionModel().clearSelection();
        //engine.loadContent(null);

        loadDataEtoV();
        listView.getItems().addAll(dictionary.getData().keySet());
    }

    public void setSceneVtoE(ActionEvent e) throws IOException{
        listView.getItems().clear();
        listView.getSelectionModel().clearSelection();
        //engine.loadContent(null);

        loadDataVtoE();
        listView.getItems().addAll(dictionary.getData().keySet());

        System.out.println("1");
    }
}
