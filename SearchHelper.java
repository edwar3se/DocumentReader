import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Provides helper methods for the Duplicate Search class.
 * 
 * @author Sydney Edwards
 * @version 12/2015
 *
 */
public class SearchHelper
{
    private ArrayList<String> doc1, doc2;
    private HashMap<String, ArrayList<Integer>> doc2Words = 
            new HashMap<String, ArrayList<Integer>>();
    private int minLength;
    private double doc1Position, doc2Position;
    private ArrayList<String> matchingWords = new ArrayList<String>();
    private double matchLength;

    /**
     * Turns two files into strings then turns them into a hashmap if a minimum
     * length is not given.
     * 
     * @param doc1 - name of the first document.
     * @param doc2 - name of the second document.
     * @throws FileNotFoundException - if file cannot be found.
     */
    public SearchHelper(String doc1, String doc2) throws FileNotFoundException
    {

        this(doc1, doc2, "3");
    }

    /**
     * Turns two files into string arrays then turns the second file into a
     * hashmap with the given minimum match length.
     * 
     * @param doc1 - the name of the first document.
     * @param doc2 - the name of the second document.
     * @param length - the minimum length of matches.
     * @throws FileNotFoundException - if file cannot be found.
     */
    public SearchHelper(String doc1, String doc2, String length) throws FileNotFoundException
    {
        this.doc1 = DocumentReader.readDocument(doc1);
        this.doc2 = DocumentReader.readDocument(doc2);

        minLength = Integer.parseInt(length);
        matchLength = 0;

        for (int place = 0; place < this.doc2.size(); place++)
        {

            String tempWord = this.doc2.get(place) + " ";

            for (int length2 = 1; length2 < minLength; length2++)
            {
                if (place + length2 < this.doc2.size())
                {
                    tempWord += this.doc2.get(place + length2);

                    if (length2 < minLength - 1)
                    {
                        tempWord += " ";
                    }

                }
            }

            if (doc2Words.containsKey(tempWord))
            {
                ArrayList<Integer> locations = doc2Words.get(tempWord);

                doc2Words.remove(this.doc2.get(place));

                locations.add(place);

                doc2Words.put(tempWord, locations);

            }

            else
            {
                ArrayList<Integer> locations = new ArrayList<Integer>();

                locations.add(place);

                doc2Words.put(tempWord, locations);

            }

        }

    }

    /**
     * Find the longest match and stores all the information.
     */
    public void solveLongestTwo()
    {
        int tempDoc1Location;
        int tempDoc2Location;
        int tempMatchLength;

        for (int place = 0; place < doc1.size(); place++)
        {
            tempDoc1Location = place;
            String tempWord = doc1.get(place) + " ";

            for (int length = 1; length < minLength; length++)
            {
                if (place + length < doc1.size())
                {
                    tempWord += doc1.get(place + length);

                    if (length < minLength - 1)
                    {
                        tempWord += " ";
                    }

                }
            }

            if (doc2Words.containsKey(tempWord))
            {
                ArrayList<Integer> places = new ArrayList<Integer>();
                places = doc2Words.get(tempWord);

                for (Integer thisLocation : places)
                {
                    // System.out.println(thisLocation);
                    // ERROR HERE
                    tempDoc2Location = thisLocation;
                    // tempDoc2Location = doc2.indexOf(originalTempWord);
                    tempMatchLength = minLength;
                    ArrayList<String> tempMatch = new ArrayList<String>();
                    tempMatch.add(tempWord);

                    int thisdoc2place, thisdoc1place;
                    thisdoc2place = tempDoc2Location + minLength;
                    thisdoc1place = tempDoc1Location + minLength;

                    while (doc2 != null && doc1 != null && thisdoc2place < doc2.size()
                            && thisdoc1place < doc1.size()
                            && doc2.get(thisdoc2place).equals(doc1.get(thisdoc1place)))
                    {

                        tempMatch.add(doc2.get(thisdoc2place));
                        thisdoc1place++;
                        thisdoc2place++;
                        tempMatchLength++;

                    }

                    if (tempMatchLength > matchLength)
                    {
                        doc1Position = tempDoc1Location;
                        doc2Position = tempDoc2Location;
                        matchLength = tempMatchLength;
                        matchingWords = tempMatch;
                    }

                }
            }
        }
    }

    /**
     * Returns the position of the match found in doc1.
     * 
     * @return double - the position in doc1.
     */
    public double getDoc1Position()
    {
        return doc1Position;
    }

    /**
     * Returns the position of the match found in doc2.
     * 
     * @return double - the position in doc2.
     */
    public double getDoc2Position()
    {
        return doc2Position;
    }

    /**
     * Returns the matching words.
     * 
     * @return ArrayList<String> - the matching words.
     */
    public ArrayList<String> getMatchingWords()
    {
        return matchingWords;
    }

    /**
     * Returns the length of the match.
     * 
     * @return double - the match length.
     */
    public double getMatchLength()
    {
        return matchLength;
    }

    /**
     * Returns the minimum length of matches.
     * 
     * @return int - minimum match length.
     */
    public int getMinLength()
    {
        return minLength;
    }

}

/*
 * References and Acknowledgments: I received no outside help with this
 * programming assignment.
 */
