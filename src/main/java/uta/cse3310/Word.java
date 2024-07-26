//--------------------------for checking if match and revealing word------------------------------------

package uta.cse3310;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
/*
-------IMPORTANT------
Do "Word words = new Word();"
And "ArrayList<String> wordsforgame = new ArrayList<>(wordlist.getArrList());" in main function to fill arr
And "words.checksolutionandreveal(wordsforgame);" to call this file 
*/

public class Word {


    public static int iscorrect(char letter, HashSet<Character> lettersinword)
    {
        int i;

        if(lettersinword.contains(letter))
        {
            i = 1;
        }
        else
        {
            i = 0;
        }
        return i;
    }



    public static void checksolutionandreveal(ArrayList<String> wordsforgame, char letter, HashSet<Character> lettersinword, HashSet<Character> lettersguessed, HashSet<Character> correctguesses) 
    {

        lettersguessed.add(letter);



        int i = iscorrect(letter, lettersinword );    
        if(i == 1)
        {
            correctguesses.add(letter);
            System.out.println("correct");
            //revealletter(letter );
            //            
            //need correctly guessed letters
            //the index of each letter
            //the orrigiall letters
        }
        if(correctguesses.equals(lettersinword))
        {
            i = 0;
            System.out.println("you guessed all the letters!"); 
            winner(wordsforgame);              
        }


/*-------------------
        Map<Character, List<Integer>> letteridx = new HashMap<>(); //good

        for (int i = 0; i < newstring.length(); i++) { //must
            char ch = newstring.charAt(i);
            if (!letteridx.containsKey(ch)) {
                letteridx.put(ch, new ArrayList<>());
            }
            letteridx.get(ch).add(i);
        }
        //print it put to verify(temporary)
        System.out.println("\n----------printing hasmap--------------");
        //for (Map.Entry<Character, List<Integer>> entry : letteridx.entrySet()) {
        //    System.out.print(entry.getKey());
        for( int i = 0; i < letteridx.size(); i++)
        {
            System.out.println(letteridx.get(i));
        }
        //cannont access a specific index in a hashmap
        //}
        System.out.println(letteridx.values());
        //System.out.println(letteridx.());
        System.out.println("\n----------done printing hasmap---------");

*/      
        
    }

/* 
    public static void revealletter(char letter)
    {
        //show the current board + any new letters
    }

    public static void board()
    {
        //show the current letters in the board

        //need to be able to do this multiple times and at diffrent indexs
        //do for entire array
        if( != )
        System.out.print(" ");
        if(==)
        System.out.print(letter);
        if(tansitioning letters)
        System.out.print(" ");
    }
*/

    //good
    public static void winner(ArrayList<String> wordsforgame)
    {
        System.out.print("\nWinner the word(s) were:");
        for (String word : wordsforgame)    
        {
            System.out.print(" " + word); 
        }
    }
}
      
        
    //--------------------add ons--------------------------
    //check (if) that letter has already been guessed
    //if it has been guessed already game over(break), or give error message and another chance
    //(if) not a duplicate check (if) any letter in the letterarraylist matches that letter
    //if match (go) to correct, pass arrays
    //set has guess = 1, and update the arrays
    //(else) not match/ set has guess = 0 


    
    


















/*
public class Word {

    private String[] words;
    private String selectedWord;
    private boolean[] guessedLetters;

    public Word(String[] wordList) {
        this.words = wordList;
        selectRandomWord();
    }

    private void selectRandomWord() {
        Random rand = new Random();
        selectedWord = words[rand.nextInt(words.length)];
        guessedLetters = new boolean[selectedWord.length()];
    }

    public String getSelectedWord() {
        return selectedWord;
    }

    public boolean guessLetter(char letter) {
        boolean found = false;
        for (int i = 0; i < selectedWord.length(); i++) {
            if (selectedWord.charAt(i) == letter) {
                guessedLetters[i] = true;
                found = true;
            }
        }
        return found;
    }

    public String getWordProgress() {
        StringBuilder progress = new StringBuilder();
        for (int i = 0; i < selectedWord.length(); i++) {
            if (guessedLetters[i]) {
                progress.append(selectedWord.charAt(i));
            } else {
                progress.append('_');
            }
        }
        return progress.toString();
    }

    public boolean isFullyGuessed() {
        for (boolean guessed : guessedLetters) {
            if (!guessed) {
                return false;
            }
        }
        return true;
    }

    public void reset() {
        selectRandomWord();
    }
}
*/

