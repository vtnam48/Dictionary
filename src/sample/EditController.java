package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

public class EditController {
    @FXML
    private TextField textField;

    @FXML
    private HTMLEditor htmlEditor;

    public void setTextField(String target) {
        textField.setText(target);
    }

    public void setHtmlEditor(String explain) {
        htmlEditor.setHtmlText(explain);
    }

    public  void Submit(ActionEvent e) {
        String newTarget = textField.getText();
        String newExplain = htmlEditor.getHtmlText();

        if (Controller.getStatus() == 0) {
            DBController.updateEV(newTarget, newExplain);
        } else {
            DBController.updateVE(newTarget, newExplain);
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Edit message");
        alert.setHeaderText("Success!");
        alert.setContentText("Update word success!");
        alert.show();

        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }

    public void Cancel(ActionEvent e) {
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }
}
