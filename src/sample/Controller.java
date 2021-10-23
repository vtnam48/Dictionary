package sample;

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
    private static final String PATH_VE = "C:\\Users\\vuthe\\Desktop\\Dictionary\\src\\sample\\data\\V_E.txt";
    private static final String PATH_EV = "C:\\Users\\vuthe\\Desktop\\Dictionary\\src\\sample\\data\\E_V.txt";

    @FXML
    private ListView listView;

    @FXML
    private WebView webView;
    private WebEngine engine;

    @FXML
    private TextField textField;


    public void loadDataEtoV() throws IOException {
        dictionary.importData(PATH_EV);
    }

    public void loadDataVtoE() throws IOException {
        dictionary.importData(PATH_VE);
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
    }


    public void search() {
        String s = textField.getText();

        listView.getItems().clear();

        listView.getItems().addAll(dictionary.searchWord(s.toLowerCase()));
    }

    public void setSceneEtoV(ActionEvent e) throws IOException{
        listView.getItems().clear();

        loadDataEtoV();
        listView.getItems().addAll(dictionary.getData().keySet());
    }

    public void setSceneVtoE(ActionEvent e) throws IOException{
        listView.getItems().clear();

        loadDataVtoE();
        listView.getItems().addAll(dictionary.getData().keySet());
    }



    public void addNewWord(ActionEvent e) throws IOException{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxmlFile/addNewWord.fxml"));
        Parent newWord = loader.load();

        Scene scene = new Scene(newWord);

        stage.setScene(scene);
        stage.show();
    }

    public void edit(ActionEvent e) throws IOException{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxmlFile/edit.fxml"));
        Parent newWord = loader.load();

        Scene scene = new Scene(newWord);

        EditController newEdit = loader.getController();
        try {
            String editWord = listView.getSelectionModel().getSelectedItem().toString();
            Word word = dictionary.getData().get(editWord);
            dictionary.getData().remove(editWord);
            newEdit.setTextField(word.getWord_target());
            newEdit.setHtmlEditor(word.getWord_explain());
            stage.setScene(scene);
            stage.show();
        } catch (NullPointerException ex) {
//            ex.printStackTrace();
            System.out.println("Chua chon tu");
        }

    }

    public void del(ActionEvent e) {
        try {
            String delWord = listView.getSelectionModel().getSelectedItem().toString();
            dictionary.getData().remove(delWord);

        } catch (NullPointerException ex) {
            ex.printStackTrace();
            System.out.println(0);
        }
    }

    public void translate(ActionEvent e) throws IOException{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxmlFile/translate.fxml"));
        Parent newWord = loader.load();

        Scene scene = new Scene(newWord);

        stage.setScene(scene);
        stage.show();
        System.out.println(1);
    }
}
