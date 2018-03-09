package Huffman;

import com.company.Huffman.HuffTree;
import org.junit.*;

public class HuffmanTest {

    private final String Empty = "";
    private final String EvenNumberCharacter = "Some";
    private final String OddNumberCharacters = "Someday";
    private final String EvenWithSpacesAndNonAscii = "some some#";
    private final String OddWithSpacesAndNonAscii = "someday someday&";
    private final String Sentence = "If you're reading this, you've been in a coma for almost 20 years now. We're trying a new technique. We don't know where this message will end up in your dream, but we hope it works. Please wake up, we miss you.\n";
    private final String Terms = "The site is provided on an “as-is” and “as available” basis, and company and our suppliers expressly disclaim any and all warranties and conditions of any kind, whether express, implied, or statutory, including all warranties or conditions of merchantability, fitness for a particular purpose, title, quiet enjoyment, accuracy, or non-infringement.  We and our suppliers make not guarantee that the site will meet your requirements, will be available on an uninterrupted, timely, secure, or error-free basis, or will be accurate, reliable, free of viruses or other harmful code, complete, legal, or safe.  If applicable law requires any warranties with respect to the site, all such warranties are limited in duration to ninety (90) days from the date of first use.\n" +
            "\n" +
            "Some jurisdictions do not allow the exclusion of implied warranties, so the above exclusion may not apply to you. Some jurisdictions do not allow limitations on how long an implied warranty lasts, so the above limitation may not apply to you. ";
    private final String Emoji = "\uD83D\uDC35 \uD83D\uDE48 \uD83D\uDE49 \uD83D\uDE4A";
    private final String RightToLeft = "مُنَاقَشَةُ سُبُلِ اِسْتِخْدَامِ اللُّغَةِ فِي النُّظُمِ الْقَائِمَةِ وَفِيم يَخُصَّ التَّطْبِيقَاتُ الْحاسُوبِيَّةُ، ";
    private final String Font = "\uD835\uDCE3\uD835\uDCF1\uD835\uDCEE \uD835\uDCFA\uD835\uDCFE\uD835\uDCF2\uD835\uDCEC\uD835\uDCF4 \uD835\uDCEB\uD835\uDCFB\uD835\uDCF8\uD835\uDD00\uD835\uDCF7 \uD835\uDCEF\uD835\uDCF8\uD835\uDD01 \uD835\uDCF3\uD835\uDCFE\uD835\uDCF6\uD835\uDCF9\uD835\uDCFC \uD835\uDCF8\uD835\uDCFF\uD835\uDCEE\uD835\uDCFB \uD835\uDCFD\uD835\uDCF1\uD835\uDCEE \uD835\uDCF5\uD835\uDCEA\uD835\uDD03\uD835\uDD02 \uD835\uDCED\uD835\uDCF8\uD835\uDCF0";
    private final String Zalgo = "Ṱ̺̺̕o͞ ̷i̲̬͇̪͙n̝̗͕v̟̜̘̦͟o̶̙̰̠kè͚̮̺̪̹̱̤ ̖t̝͕̳̣̻̪͞h̼͓̲̦̳̘̲e͇̣̰̦̬͎ ̢̼̻̱̘h͚͎͙̜̣̲ͅi̦̲̣̰̤v̻͍e̺̭̳̪̰-m̢iͅn̖̺̞̲̯̰d̵̼̟͙̩̼̘̳ ̞̥̱̳̭r̛̗̘e͙p͠r̼̞̻̭̗e̺̠̣͟s̘͇̳͍̝͉e͉̥̯̞̲͚̬͜ǹ̬͎͎̟̖͇̤t͍̬̤͓̼̭͘ͅi̪̱n͠g̴͉ ͏͉ͅc̬̟h͡a̫̻̯͘o̫̟̖͍̙̝͉s̗̦̲.̨̹͈̣\n" +
            "̡͓̞ͅI̗̘̦͝n͇͇͙v̮̫ok̲̫̙͈i̖͙̭̹̠̞n̡̻̮̣̺g̲͈͙̭͙̬͎ ̰t͔̦h̞̲e̢̤ ͍̬̲͖f̴̘͕̣è͖ẹ̥̩l͖͔͚i͓͚̦͠n͖͍̗͓̳̮g͍ ̨o͚̪͡f̘̣̬ ̖̘͖̟͙̮c҉͔̫͖͓͇͖ͅh̵̤̣͚͔á̗̼͕ͅo̼̣̥s̱͈̺̖̦̻͢.̛̖̞̠̫̰\n" +
            "̗̺͖̹̯͓Ṯ̤͍̥͇͈h̲́e͏͓̼̗̙̼̣͔ ͇̜̱̠͓͍ͅN͕͠e̗̱z̘̝̜̺͙p̤̺̹͍̯͚e̠̻̠͜r̨̤͍̺̖͔̖̖d̠̟̭̬̝͟i̦͖̩͓͔̤a̠̗̬͉̙n͚͜ ̻̞̰͚ͅh̵͉i̳̞v̢͇ḙ͎͟-҉̭̩̼͔m̤̭̫i͕͇̝̦n̗͙ḍ̟ ̯̲͕͞ǫ̟̯̰̲͙̻̝f ̪̰̰̗̖̭̘͘c̦͍̲̞͍̩̙ḥ͚a̮͎̟̙͜ơ̩̹͎s̤.̝̝ ҉Z̡̖̜͖̰̣͉̜a͖̰͙̬͡l̲̫̳͍̩g̡̟̼̱͚̞̬ͅo̗͜.̟\n" +
            "̦H̬̤̗̤͝e͜ ̜̥̝̻͍̟́w̕h̖̯͓o̝͙̖͎̱̮ ҉̺̙̞̟͈W̷̼̭a̺̪͍į͈͕̭͙̯̜t̶̼̮s̘͙͖̕ ̠̫̠B̻͍͙͉̳ͅe̵h̵̬͇̫͙i̹͓̳̳̮͎̫̕n͟d̴̪̜̖ ̰͉̩͇͙̲͞ͅT͖̼͓̪͢h͏͓̮̻e̬̝̟ͅ ̤̹̝W͙̞̝͔͇͝ͅa͏͓͔̹̼̣l̴͔̰̤̟͔ḽ̫.͕\n" +
            "Z̮̞̠͙͔ͅḀ̗̞͈̻̗Ḷ͙͎̯̹̞͓G̻O̭̗̮\n";
    /*
      ***********************      CONSTRUCTOR Tests   *******************************
    */

    @Test(expected = IllegalArgumentException.class)
    public void NullConstructorTest() {
        new HuffTree(null);
    }

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
    public void EmojiStringConstructorTest() {
        new HuffTree(Emoji);
    }

    @Test
    public void RightToLeftStringConstructorTest() {
        new HuffTree(RightToLeft);
    }

    @Test
    public void ZalgoStringConstructorTest() {
        new HuffTree(Zalgo);
    }

    @Test
    public void FontStringConstructorTest() {
        new HuffTree(Font);
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

    @Test
    public void EmojiStringToStringTestt() {
        HuffTree huffTree = new HuffTree(Emoji);

        Assert.assertEquals(Emoji, huffTree.toString());
    }

    @Test
    public void RightToLeftStringToStringTestt() {
        HuffTree huffTree = new HuffTree(RightToLeft);

        Assert.assertEquals(RightToLeft, huffTree.toString());
    }

    @Test
    public void ZalgoStringToStringTestt() {
        HuffTree huffTree = new HuffTree(Zalgo);

        Assert.assertEquals(Zalgo, huffTree.toString());
    }

    @Test
    public void FontStringToStringTestt() {
        HuffTree huffTree = new HuffTree(Font);

        Assert.assertEquals(Font, huffTree.toString());
    }


}
