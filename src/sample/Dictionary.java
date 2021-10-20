package sample;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;


public class Dictionary {
    private HashMap<String, Word> data = new HashMap<>();

    public HashMap<String, Word> getData() {
        return data;
    }


    public void importData() throws IOException {
        FileReader fileReader = new FileReader("C:\\Users\\vuthe\\Desktop\\Dictionary\\src\\sample\\data.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            String[] word = str.split("<html>");
            String key = word[0];
            String word_ex = word[1];
            Word newWord = new Word(key, word_ex);

            data.put(key, newWord);
        }
    }

    public void showAllWord() {
        for (String key : data.keySet()) {
            Word a = data.get(key);
            a.printWord();
        }
    }

    public void addNewWord() {
        Scanner scanner = new Scanner(System.in);
        String newTg = scanner.nextLine();
        String newEx = scanner.nextLine();

        Word newWord = new Word(newTg, newEx);
        data.put(newTg, newWord);
    }

//    public void findWord(String key) {
//        //Word[] words = new Word[]{data.get(key)};
//
////        for (Word i : words) {
////            i.printWord();
////        }
//        System.out.println(data.get(key));
//    }

}
