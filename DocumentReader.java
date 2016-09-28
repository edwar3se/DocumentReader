import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The DocumentReader class includes utilities for extracting words from text
 * documents and returning them in an ArrayList. <br>
 * 
 * @author Nathan Sprague
 * @version 11/2015
 */
public class DocumentReader
{

    /**
     * This method opens a plain-text file and reads its contents into an
     * ArrayList. All punctuation and whitespace is discarded. All words are
     * converted to lower-case. The words in the ArrayList are stored in the
     * order they appear in the document.
     * 
     * @param fileName the name of a plain-text file
     * @throws FileNotFoundException - if file cannot be found.
     * 
     * @return an arraylist containing the ordered list of words
     */
    public static ArrayList<String> readDocument(String fileName) throws FileNotFoundException
    {
        Scanner scanner;
        ArrayList<String> words = new ArrayList<String>();

        scanner = new Scanner(new File(fileName));
        scanner.useDelimiter("[\\W]"); // all non-word characters
        while (scanner.hasNext())
        {
            String cur = scanner.next();
            if (!cur.equals(""))
            {
                words.add(cur.toLowerCase());
            }
        }
        scanner.close();
        return words;
    }

}
