package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddController implements Initializable {

    @FXML
    private TextField target;

    @FXML
    private TextField explain;

    public  void Submit(ActionEvent e) {
        String newTarget = target.getText();
        String newExplain = explain.getText();

        Controller.dictionary.addNewWord(newTarget, newExplain);

        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }

    public void Cancel(ActionEvent e) {
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
