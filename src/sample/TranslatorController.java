package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class TranslatorController {

    @FXML
    private TextField textFrom;

    @FXML
    private  TextField textTo;

    @FXML
    private Button button;
    private int status = 0;

    private static String translate(String langFrom, String langTo, String text) throws IOException {

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

    public void trans(ActionEvent e) throws IOException{
        String tFrom = textFrom.getText();
        String tTo = "";

        if (status == 0) {
            tTo = translate("en", "vi", tFrom);
        } else {

            tTo = translate("vi", "en", tFrom);
        }

        textTo.setText(tTo);
    }

    public void Cancel(ActionEvent e) {
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }

    public void changeStatus(ActionEvent e) {
        textTo.clear();
        textFrom.clear();

        if (status == 0){
            button.setText("V_E");
            status = 1;
        } else {
            button.setText("E_V");
            status = 0;
        }
    }
}