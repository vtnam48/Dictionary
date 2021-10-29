package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;



import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public static Dictionary dictionary = new Dictionary();

//    status  = 0 Tiếng Anh
//    status  = 1 Tiếng Việt
    public static int status = 0;

    @FXML
    private ListView listView;

    @FXML
    private WebView webView;
    private WebEngine engine;

    @FXML
    private TextField textField;

    @FXML
    private Button button;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loadDataEtoV();
        loadWord();
    }

    public static int getStatus() {
        return status;
    }

    public void loadDataEtoV() {
        dictionary.importDataEV();
        status = 0;
        button.setText("E_V");
    }

    public void loadDataVtoE() {
        dictionary.importDataVE();
        status = 1;
        button.setText("V_E");
    }

    public void loadWord() {
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

    public void setScene(ActionEvent e){
        listView.getItems().clear();
        if (status == 0) {
            loadDataVtoE();
        } else {
            loadDataEtoV();
        }
        listView.getItems().addAll(dictionary.getData().keySet());

    }

    public void addNewWord(MouseEvent e) throws IOException{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxmlFile/addNewWord.fxml"));
        Parent newWord = loader.load();

        Scene scene = new Scene(newWord);

        stage.setScene(scene);
        stage.show();
    }

    public void edit(MouseEvent e) throws IOException{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxmlFile/edit.fxml"));
        Parent newWord = loader.load();

        Scene scene = new Scene(newWord);

        EditController newEdit = loader.getController();
        try {
            String editWord = listView.getSelectionModel().getSelectedItem().toString();
            Word word = dictionary.getData().get(editWord);
            newEdit.setTextField(word.getWord_target());
            newEdit.setHtmlEditor(word.getWord_explain());
            stage.setScene(scene);
            stage.show();
        } catch (NullPointerException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Edit message");
            alert.setHeaderText("Error!");
            alert.setContentText("Word is not selected!");
            alert.show();
        }

    }

    public void delete(MouseEvent e) {
        try {

            Alert alertConfirm = new Alert(Alert.AlertType.CONFIRMATION);
            alertConfirm.setTitle("Delete message");
            alertConfirm.setHeaderText("Confirmation.");
            alertConfirm.setContentText("You want to delete this word?");

            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            alertConfirm.getButtonTypes().setAll(yes, no);

            Optional<ButtonType> result = alertConfirm.showAndWait();

            if (result.get()==yes) {
                String delWord = listView.getSelectionModel().getSelectedItem().toString();
                if (status == 0) {
                    DBController.deleteEV(delWord);
                } else {
                    DBController.deleteVE(delWord);
                }

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Delete message");
                alert.setHeaderText("Success!");
                alert.setContentText("Delete word success!");
                alert.show();
            }

        } catch (NullPointerException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Delete message");
            alert.setHeaderText("Error!");
            alert.setContentText("Word is not selected!");
            alert.show();
        }
    }

    public void translate(MouseEvent e) throws IOException{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxmlFile/translate.fxml"));
        Parent newWord = loader.load();

        Scene scene = new Scene(newWord);

        stage.setScene(scene);
        stage.show();
    }

    public void TTS(MouseEvent e) {
        try {
            String word = listView.getSelectionModel().getSelectedItem().toString();

            TextToSpeechController.tts(word);
        } catch (NullPointerException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Delete message");
            alert.setHeaderText("Error!");
            alert.setContentText("Word is not selected!");
            alert.show();
        }
    }

    public void refresh(MouseEvent e){
        //Refresh list view
        listView.getItems().clear();

        //Refresh search text field
        textField.clear();

        //Refresh webview
        engine = webView.getEngine();
        engine.loadContent("", "text/html");

        //Refresh database
        if (status == 0) {
            loadDataEtoV();
        } else {
            loadDataVtoE();
        }

        //Reload
        loadWord();
    }

    public void about(MouseEvent e) throws IOException{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxmlFile/about.fxml"));
        Parent newWord = loader.load();

        Scene scene = new Scene(newWord);

        stage.setScene(scene);
        stage.show();
    }

}
