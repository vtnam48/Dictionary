package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ResourceBundle;

public class TranslatorController  implements Initializable {

    @FXML
    private TextField textFrom;

    @FXML
    private  TextField textTo;

    @FXML
    private RadioMenuItem itemev;

    @FXML
    private RadioMenuItem itemve;

    private boolean EV = true;

    public static void main(String[] args) throws IOException {
        String text = "i love you";
        //Translated text: Hallo Welt!
        System.out.println("Translated text: " + translate("en", "vi", text));
    }

    private static String translate(String langFrom, String langTo, String text) throws IOException {
        // INSERT YOU URL HERE
        String urlStr = "https://script.google.com/macros/s/AKfycbwX1NA9ZOYgS8xUuwpb-VzdigKT3j9IUvpTT-QoD-ipgr4tGbiJmxeonwltCVQZZ4zN6A/exec" +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + langTo +
                "&source=" + langFrom;
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    public void setEV(ActionEvent e) {
        EV = true;
    }

    public void setVE(ActionEvent e) {
        EV = false;
    }

    public void trans(ActionEvent e) throws IOException{
        String tFrom = textFrom.getText();
        String tTo = "";

        if (itemev.isSelected()) {
            tTo = translate("en", "vi", tFrom);
        }
        if (itemve.isSelected()){
            tTo = translate("vi", "en", tFrom);
        }

        textTo.setText(tTo);
    }

    public void Cancel(ActionEvent e) {
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}