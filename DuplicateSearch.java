import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Find the longest match in two specified files.
 * 
 * @author Sydney Edwards
 * @version 12/2015
 *
 */
public class DuplicateSearch
{

    /**
     * Finds the longest match and prints out the information about the match.
     * 
     * @param args - The name of two files and possibly a minimum match length.
     * @throws FileNotFoundException - If the file cannot be found.
     */
    public static void main(String[] args) throws FileNotFoundException
    {
        SearchHelper thisSearch = null;
        ArrayList<String> matchingWords;
        DecimalFormat format = new DecimalFormat("0.#");

        if (args.length == 2)
        {
            thisSearch = new SearchHelper(args[0], args[1]);
        }

        else if (args.length == 3)
        {
            thisSearch = new SearchHelper(args[0], args[1], args[2]);
        }

        if (thisSearch != null)
        {

            thisSearch.solveLongestTwo();

            if (thisSearch.getMinLength() <= thisSearch.getMatchLength())
            {

                System.out.println();
                System.out.println("Match length: " + (format.format(thisSearch.getMatchLength())));
                System.out.println("Position in " + args[0] + ": "
                        + (format.format(thisSearch.getDoc1Position())));
                System.out.println("Position in " + args[1] + ": "
                        + (format.format(thisSearch.getDoc2Position())));
                System.out.println();
                System.out.println("Matching words:");
                matchingWords = thisSearch.getMatchingWords();

                if (matchingWords != null)
                {

                    for (int place = 0; place < matchingWords.size(); place++)
                    {

                        if (place < matchingWords.size() - 1)
                        {
                            System.out.print(matchingWords.get(place) + " ");
                        }

                        else
                        {
                            System.out.print(matchingWords.get(place));
                        }
                    }

                    System.out.println();
                }

            }

            else
            {
                System.out.print("\nNo matches found.\n");
            }

        }
    }

}



/*
 * References and Acknowledgments: I received no outside help with this
 * programming assignment.
 */
