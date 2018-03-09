package Huffman;

import com.company.Huffman.HuffTree;
import org.junit.*;

public class HuffmanTest {

    private final String Empty = "";
    private final String EvenNumberCharacter = "Some";
    private final String OddNumberCharacters = "Someday";
    private final String EvenWithSpacesAndNonAscii = "some some#";
    private final String OddWithSpacesAndNonAscii = "someday someday&";
    private final String Sentence = "The centipede is a predator.";
    private final String Terms = "The site is provided on an “as-is” and “as available” basis, and company and our suppliers expressly disclaim any and all warranties and conditions of any kind, whether express, implied, or statutory, including all warranties or conditions of merchantability, fitness for a particular purpose, title, quiet enjoyment, accuracy, or non-infringement.  We and our suppliers make not guarantee that the site will meet your requirements, will be available on an uninterrupted, timely, secure, or error-free basis, or will be accurate, reliable, free of viruses or other harmful code, complete, legal, or safe.  If applicable law requires any warranties with respect to the site, all such warranties are limited in duration to ninety (90) days from the date of first use.\n" +
            "\n" +
            "Some jurisdictions do not allow the exclusion of implied warranties, so the above exclusion may not apply to you. Some jurisdictions do not allow limitations on how long an implied warranty lasts, so the above limitation may not apply to you. ";

    /*
      ***********************      CONSTRUCTOR Tests   *******************************
    */
    @Test
    public void EmptyStringConstructorTest() {
        new HuffTree(Empty);
    }

    @Test
    public void EvenStringConstructorTest() {
        new HuffTree(EvenNumberCharacter);
    }
    @Test
    public void OddStringConstructorTest() {
        new HuffTree(OddNumberCharacters);
    }

    @Test
    public void EvenSpacesStringConstructorTest() {
        new HuffTree(EvenWithSpacesAndNonAscii);
    }
    @Test
    public void OddSpacesStringConstructorTest() {
        new HuffTree(OddWithSpacesAndNonAscii);
    }

    @Test
    public void SentenceStringConstructorTest() {
        new HuffTree(Sentence);
    }

    @Test
    public void TermsStringConstructorTest() {
        new HuffTree(Terms);
    }

    /*
     ***********************      ToString Tests   *******************************
     */

    @Test
    public void EmptyStringToStringTest() {
        HuffTree huffTree = new HuffTree(Empty);

        Assert.assertEquals(Empty, huffTree.toString());
    }

    @Test
    public void EvenStringToStringTest() {
        HuffTree huffTree = new HuffTree(EvenNumberCharacter);

        Assert.assertEquals(EvenNumberCharacter, huffTree.toString());
    }
    @Test
    public void OddStringToStringTest() {
        HuffTree huffTree = new HuffTree(OddNumberCharacters);

        Assert.assertEquals(OddNumberCharacters, huffTree.toString());
    }

    @Test
    public void EvenSpacesStringToStringTest() {
        HuffTree huffTree = new HuffTree(EvenWithSpacesAndNonAscii);

        Assert.assertEquals(EvenWithSpacesAndNonAscii, huffTree.toString());
    }
    @Test
    public void OddSpacesStringToStringTest() {
        HuffTree huffTree = new HuffTree(OddWithSpacesAndNonAscii);

        Assert.assertEquals(OddWithSpacesAndNonAscii, huffTree.toString());
    }

    @Test
    public void SentenceStringToStringTestt() {
        HuffTree huffTree = new HuffTree(Sentence);

        Assert.assertEquals(Sentence, huffTree.toString());
    }

    @Test
    public void TermsStringToStringTestt() {
        HuffTree huffTree = new HuffTree(Terms);;

        Assert.assertEquals(Terms, huffTree.toString());
    }
}
