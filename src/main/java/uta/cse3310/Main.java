package uta.cse 3310;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class Main {
    public static void main(String[] args)
    {
        Word word = new Word();
        word.getWords();
        WordList wordist = new WordList();
        wordist.checkLetter();

    }
}
