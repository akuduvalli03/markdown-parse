// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        markdown = markdown.replace("\n(","\n[");
        markdown = markdown.replace(")(","](");
        //for when there aren't parentheses around the link
        if(markdown.indexOf("]",currentIndex)!= markdown.indexOf("](",currentIndex)) {
            //when there isn't parentheses starting the link
            markdown = markdown.replace("]","](");
        }
        if(markdown.trim().charAt(markdown.trim().length()-1) != ')') {
            markdown = markdown.trim() + ")";
        }
        else if(!markdown.substring(currentIndex,markdown.indexOf("\n")).trim().endsWith(")")) {
            markdown = markdown.substring(0,currentIndex) + 
            markdown.substring(currentIndex,markdown.indexOf("\n")).trim() + ")" +
            markdown.substring(markdown.indexOf("\n",currentIndex));

        }

        System.out.println(markdown);
        while(currentIndex < markdown.length()) {
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            int openParen = markdown.indexOf("(", nextCloseBracket);
            int closeParen = markdown.indexOf(")", openParen);
            toReturn.add(markdown.substring(openParen + 1, closeParen));
            currentIndex = closeParen + 1;
            System.out.println(currentIndex);
            
        }
        return toReturn;
    }
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        //System.out.println(contents);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
    //[error](error.com)
    //[error](error.com)

}