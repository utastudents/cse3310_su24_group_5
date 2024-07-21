import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordList {

    public static void main(String[] args) 
    {
        Random random = new Random();   //for nextInt
        int randomNumber = random.nextInt(3) + 1;  //getting random number btw 1 and 3
        ArrayList<String> randomwords = new ArrayList<>();  //array to store our random words
        System.out.println("I am selecting " + randomNumber + " words");  //temporary, how many words we will be selecting
        
        for( int i = 0; i < randomNumber; i++)  //for loop to store x number of words
        {
            String w = readRandomWordFromFile("words.txt"); //calling function
            randomwords.add(w); //adding words to arraylist
        }

        for (String word : randomwords)    //for each loop
        {
            System.out.println("word selected " + word);  //printing out the words we have stored

        }
    }

    public static String readRandomWordFromFile(String filePath) 
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

        }        
        return isValidWord(words);
    }


    public static String isValidWord(ArrayList<String> words)
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
}

    