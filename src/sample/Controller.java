package sample;

import com.sun.xml.internal.ws.api.pipe.Engine;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
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

    public void loadData() throws IOException {
        dictionary.importData();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            loadData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        listView.getItems().addAll(dictionary.getData().keySet());
        listView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    Word selectedWord = dictionary.getData().get(newValue);
                    String explain = selectedWord.getWord_explain();
                    engine = webView.getEngine();
                    engine.loadContent(explain, "text/html");
                }
        );
    }


}
