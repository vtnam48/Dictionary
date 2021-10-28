package sample;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static sample.DBController.*;


public class Dictionary {
    private HashMap<String, Word> data = new HashMap<>();

    public HashMap<String, Word> getData() {
        return data;
    }


    public void importDataEV() {
        data.clear();
        String query = "SELECT word_target, word_explain FROM entovie";

        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String key = rs.getString("word_target");
                String word_ex = rs.getString("word_explain");
                Word newWord = new Word(key, word_ex);

            data.put(key, newWord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Success load database EV");
    }

    public void importDataVE(){
        data.clear();
        String query = "SELECT word_target, word_explain FROM vietoen";

        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String key = rs.getString("word_target");
                String word_ex = rs.getString("word_explain");
                Word newWord = new Word(key, word_ex);

                data.put(key, newWord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Success load database VE");
    }

    public void addNewWordVE(String newTg, String newEx) {
        insertVE(newTg, newEx);
    }

    public void addNewWordEV(String newTg, String newEx) {
        insertEV(newTg, newEx);
    }


    public Set<String> searchWord(String s) {
        Set<String> findWord = new HashSet<>();
         s.trim();
         s.toLowerCase();
        for (String key : data.keySet()) {
            String word = key.toLowerCase();
            if (word.startsWith(s)) {
                findWord.add(key);
            }
        }

        return findWord;
    }

}
