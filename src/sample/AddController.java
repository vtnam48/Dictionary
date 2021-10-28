package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddController{

    @FXML
    private TextField target;

    @FXML
    private TextField explain;

    public  void Submit(ActionEvent e) {
        String newTarget = target.getText();
        String newExplain = explain.getText();

        int status = Controller.getStatus();

        if (newTarget.trim().equals("") || newExplain.trim().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Add message");
            alert.setHeaderText("Error!");
            alert.setContentText("New word target or New word explain is empty!");
            alert.show();
        } else if ((status==0 && DBController.checkExitEV(newTarget))
                    || ((status==1 && DBController.checkExitVE(newTarget)))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Add message");
            alert.setHeaderText("Error!");
            alert.setContentText("Word already exists!");
            alert.show();
        } else {
            if (status == 0) {
                Controller.dictionary.addNewWordEV(newTarget, newExplain);
            } else {
                Controller.dictionary.addNewWordVE(newTarget, newExplain);
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Add message");
            alert.setHeaderText("Success!");
            alert.setContentText("Add new word success!");
            alert.show();
        }

        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }

    public void Cancel(ActionEvent e) {
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }
}
