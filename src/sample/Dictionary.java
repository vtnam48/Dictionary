package sample;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class Dictionary {
    private HashMap<String, Word> data = new HashMap<>();

    public HashMap<String, Word> getData() {
        return data;
    }


    public void importDataEtoV() throws IOException {
        data.clear();

        FileReader fileReader = new FileReader("C:\\Users\\vuthe\\Desktop\\Dictionary\\src\\sample\\E_V.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            String[] word = str.split("<html>");
            String key = word[0];
            String word_ex = word[1];
            Word newWord = new Word(key, word_ex);

            data.put(key, newWord);
        }

        System.out.println("Success1");
    }

    public void importDataVtoE() throws IOException {
        data.clear();

        FileReader fileReader = new FileReader("C:\\Users\\vuthe\\Desktop\\Dictionary\\src\\sample\\V_E.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            String[] word = str.split("<html>");
            String key = word[0];
            String word_ex = word[1];
            Word newWord = new Word(key, word_ex);

            data.put(key, newWord);
        }

        System.out.println("Success2");
    }

    public void showAllWord() {
        for (String key : data.keySet()) {
            Word a = data.get(key);
            a.printWord();
        }
    }

    public void addNewWord(String newTg, String newEx) {
        Word newWord = new Word(newTg, newEx);
        data.put(newTg, newWord);
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

    public void show() {
//        for (int i = 0; i < 5; i++) {
//            data
//        }
    }

//    public static void main(String[] args) throws IOException{
//        Dictionary dictionary = new Dictionary();
//        dictionary.importDataVtoE();
//        dictionary.showAllWord();
//    }
}
