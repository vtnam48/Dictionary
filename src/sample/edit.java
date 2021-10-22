package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

public class edit {
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

        Controller.dictionary.getData().remove(newTarget);
        Controller.dictionary.addNewWord(newTarget, newExplain);

    }

    public void Cancel(ActionEvent e) {
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }
}
