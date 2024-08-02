//-----------For Getting words-------------

package uta.cse3310;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
/*------------------------------------IMPORTANT!!!!!!!!!!!!!!!--------------------------
MAIN FUNCTION WOULD BE LIKE THIS

public class Main {
    public static void main(String[] args) 
    {
        //access to Wordlist file
        WordList wordlist = new WordList();
        Word words = new Word();
       
        //gathering the words
        wordlist.gatherwords();
        ArrayList<String> wordsforgame = new ArrayList<>(wordlist.getArrList());//storing the words in a new array list 
        
        //printing out the words for verification
        for (String word : wordsforgame)    
        {
            System.out.println("word(s) selected: " + word); 
        }        



        //gathering letters
        HashSet<Character> lettersinword = wordlist.findletters(wordsforgame);

        //showing letters
        System.out.print("letters to guess: ");
        for (char letters : lettersinword) 
        {
            System.out.print(letters);
        }
        System.err.println();


        //simple guessing functionality
        HashSet<Character> lettersguessed = new HashSet<>();
        HashSet<Character> correctguesses = new HashSet<>();
        int i = 17;
        Scanner scanner = new Scanner(System.in);
        
        while ( i > 0)
        {
            System.out.print("Enter a letter: ");
            String guess = scanner.next(); 
            char letter = guess.charAt(0);
            words.checksolutionandreveal(wordsforgame, letter, lettersinword, lettersguessed, correctguesses);
            i--;
        }   
        scanner.close();
    }
}
*/
public class WordList {

    ArrayList<String> randomwords = new ArrayList<>();  



    public void printwords() 
    {       
        for (String word : randomwords)
        {
            System.out.println("word selected " + word); 
        }
    }    



    public void gatherwords() //1
    {       
        Random random = new Random();   
        int randomNumber = random.nextInt(3) + 1;
        System.out.println("I am selecting " + randomNumber + " words");  
        
        for( int i = 0; i < randomNumber; i++) 
        {
            String w = readWordsFromFile("cse3310_su24_group_5\\src\\main\\resources\\words");//2
            randomwords.add(w);
        }
    }
    


    public static String readWordsFromFile(String filePath)//2
    {
        ArrayList<String> words = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) 
        {
            String word;
            while ((word = br.readLine()) != null) 
            {
                words.add(word); 
            }
        } 
        catch (IOException e) 
        {
            System.err.println("abort abort failed!!!");
        }        
        return isValidWord(words); //3
    }



    public static String isValidWord(ArrayList<String> words)//3
    {
        String validword = "";
        while(validword == "")
        {
            Random rand = new Random();
            int randomIndex = rand.nextInt(words.size());
            String testword = words.get(randomIndex);
            String rule = "^[a-z]+$";
            if(testword.matches(rule) && testword.length() >= 3 && testword.length() <= 12)
            {
                    validword = testword;                             
            }
        }
        return validword;
    }



    public ArrayList<String> getArrList() 
    {
        return randomwords;
    }



    public static HashSet<Character> findletters(ArrayList<String> wordsforgame) {
        
        HashSet<Character> letters = new HashSet<>();

        for (String word : wordsforgame) {
            for (int i = 0; i < word.length(); i++) {
                char letter = word.charAt(i);
                letters.add(letter);
            }
        }
        return letters;
    }

}

