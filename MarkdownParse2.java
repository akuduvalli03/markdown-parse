// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse2 {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        int currentIndex = 0, nextOpenBracket = 0, nextCloseBracket = 0, openParen = 0, closeParen = 0,nextLine;
        while(currentIndex < markdown.length()) {
            nextOpenBracket = markdown.indexOf("[", currentIndex);
            nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            openParen = markdown.indexOf("(", nextCloseBracket);
            closeParen = markdown.indexOf(")", openParen);
            nextLine = markdown.indexOf("\n", nextOpenBracket);
            //when one of this is less the 0 which means the char is not in the file 
            if (nextOpenBracket < 0 || nextCloseBracket < 0 || openParen < 0 || closeParen < 0) {
                break;
            }
            //check where tho start
            if (markdown.charAt(nextOpenBracket-1) != '[' || markdown.charAt(openParen - 1) != ']' || nextLine < closeParen) {
                currentIndex = closeParen + 1;
                continue;
            }

            toReturn.add(markdown.substring(openParen + 1, closeParen));
            currentIndex = closeParen + 1;
        }
        return toReturn;
    }
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}
