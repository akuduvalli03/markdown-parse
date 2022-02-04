import static org.junit.Assert.*;
import org.junit.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;

public class MarkdownParseTest {
    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void testDefaultLinkMethod() throws IOException {
        Path fileName = Path.of("testfiles/test-file.md");
        String contents = Files.readString(fileName);
        assertEquals(List.of("https://something.com", "some-page.html"), MarkdownParse3.getLinks(contents));
    }
    @Test
    public void testOneLinkMethod() throws IOException {
        Path fileName = Path.of("testfiles/markdown-new-test.md");
        String contents = Files.readString(fileName);
        assertEquals(List.of("thiswillbreak.com", "thisisover.com", "peasy.html"), MarkdownParse3.getLinks(contents));
    }
    @Test
    public void testTwoLinkMethod() throws IOException {
        Path fileName = Path.of("testfiles/markdown-test-two.md");
        String contents = Files.readString(fileName);
        assertEquals(List.of("thiswillbreak.com", "thisisover.com", "peasy.html"), MarkdownParse3.getLinks(contents));
    }
    @Test
    public void testThreeLinkMethod() throws IOException {
        Path fileName = Path.of("testfiles/markdown-test-three.md");
        String contents = Files.readString(fileName);
        assertEquals(List.of("thiswillbreak.com", "thisisover.com", "peasy.html"), MarkdownParse3.getLinks(contents));
    }

    @Test 
    public void purposefullyFailedTest() throws IOException {
        assertEquals(List.of("error.com","something.com"), 
            MarkdownParse3.getLinks(getContents("testfiles/failing-test.md")));
    } 

    //Tests from Joe's new repos
    @Test
    public void joesNewTestTwo() throws IOException {
        assertEquals(List.of("https://something.com", "some-page.html"), 
            MarkdownParse3.getLinks(getContents("testfiles/test-file2.md")));
    }
    @Test
    public void joesNewTestThree() throws IOException {
        assertEquals(List.of(), 
            MarkdownParse3.getLinks(getContents("testfiles/test-file3.md")));
    }
    @Test
    public void joesNewTestFour() throws IOException {
        assertEquals(new ArrayList<String>(), 
            MarkdownParse3.getLinks(getContents("testfiles/test-file4.md")));
    }
    @Test
    public void joesNewTestFive() throws IOException {
        assertEquals(List.of("page.com"), 
            MarkdownParse3.getLinks(getContents("testfiles/test-file5.md")));
    }
    @Test
    public void joesNewTestSix() throws IOException {
        assertEquals(List.of("page.com"), 
            MarkdownParse3.getLinks(getContents("testfiles/test-file6.md")));
    }
    @Test
    public void joesNewTestSeven() throws IOException {
        assertEquals(new ArrayList<String>(), 
            MarkdownParse3.getLinks(getContents("testfiles/test-file7.md")));
    }
    @Test
    public void joesNewTestEight() throws IOException {
        assertEquals(new ArrayList<String>(), 
            MarkdownParse3.getLinks(getContents("testfiles/test-file8.md")));
    }
    

    public static String getContents(String filePath) throws IOException {
        Path fileName = Path.of(filePath);
        String contents = Files.readString(fileName);
        return contents;
    }
}