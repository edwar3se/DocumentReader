import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

/* method for testing terminal output from:
 * http://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
 */

/**
 * Web-CAT test for CS159 PA6.
 * 
 * @author Nathan Sprague
 * @version 11/15
 */
public class DuplicateSearchOneTest
{

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUp()
    {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    public void testFromPAPage() throws FileNotFoundException
    {
        DuplicateSearch.main(new String[] {"A.txt", "B.txt", "2"});
        String expected = "\nMatch length: 9\n" + "Position in A.txt: 5\n"
                          + "Position in B.txt: 6\n\n" + "Matching words:\n"
                          + "every unhappy family is unhappy in its own way\n";

        assertEquals(expected, outContent.toString());
    }

}