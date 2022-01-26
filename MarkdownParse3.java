// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse3 {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            if(nextOpenBracket == -1) {
                break;
            }
            int openParen = markdown.indexOf("(", nextCloseBracket);
            if(openParen > markdown.indexOf("\n", nextCloseBracket) && markdown.indexOf("\n", nextCloseBracket) != -1) {
                openParen = -1;
            }
            if(openParen == -1) {
                openParen = nextCloseBracket;
            }

            int closeParen = markdown.indexOf(")", openParen);
            if(closeParen > markdown.indexOf("\n", openParen) && markdown.indexOf("\n", openParen) != -1) {
                closeParen = -1;
            }
            if(closeParen == -1) {
                closeParen = markdown.indexOf("\n", openParen);
            }
            toReturn.add(markdown.substring(openParen + 1, closeParen));
            // System.out.println("NOB: " + nextOpenBracket + " NCB: " + nextCloseBracket + " OP: " + openParen + " CP: " + closeParen);
            currentIndex = closeParen + 1;
        }
        return toReturn;
    }
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        // System.out.println(contents);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}