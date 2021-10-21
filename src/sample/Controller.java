package sample;

import com.sun.xml.internal.ws.api.pipe.Engine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public static Dictionary dictionary = new Dictionary();

    @FXML
    private ListView listView;

    @FXML
    private WebView webView;
    private WebEngine engine;

    @FXML
    private TextField textField;


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
        //listView.getSelectionModel().clearSelection();

        listView.getItems().addAll(dictionary.searchWord(s.toLowerCase()));
    }

    public void setSceneEtoV(ActionEvent e) throws IOException{
        listView.getItems().clear();
        //engine.loadContent(null);

        loadDataEtoV();
        listView.getItems().addAll(dictionary.getData().keySet());
    }

    public void setSceneVtoE(ActionEvent e) throws IOException{
        listView.getItems().clear();
        //engine.loadContent(null);

        loadDataVtoE();
        listView.getItems().addAll(dictionary.getData().keySet());
    }

    public void test(ActionEvent e) {
        dictionary.addNewWord("123","123");
        listView.getItems().add(dictionary.getData().get("123").getWord_target());
    }


    public void addNewWord(ActionEvent e) throws IOException{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addNewWord.fxml"));
        Parent newWord = loader.load();

        Scene scene = new Scene(newWord);
//        StudentDetailController controller = loader.getController();
//        Student selected = table.getSelectionModel().getSelectedItem();
//        controller.setStudent(selected);

        stage.setScene(scene);
        stage.show();
    }
}
