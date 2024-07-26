//-----------For Getting words-------------

package uta.cse3310;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;

//do "WordList wordlist = new WordList();" access to Wordlist file
//then "wordlist.gatherwords();" to gather words

    
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
            String w = readWordsFromFile("src/main/resources/words.txt");//2
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
}

