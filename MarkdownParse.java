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
        /*
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

        System.out.println(markdown);*/


        
        while(currentIndex < markdown.length()) {
            /*int nextOpenBracket = markdown.indexOf("[", currentIndex);
            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);*/
            int nextOpenBracket = indexOfTheFollowing(markdown, currentIndex);
            //System.out.println(nextOpenBracket);
            int nextCloseBracket = indexOfTheFollowing(markdown, nextOpenBracket+1);
            System.out.println(nextCloseBracket);
            /*int openParen = markdown.indexOf("(", nextCloseBracket+1);
            int closeParen = markdown.indexOf(")", openParen+1);*/
            int openParen = indexOfTheFollowing(markdown, nextCloseBracket+1);
            int closeParen = indexOfTheFollowing(markdown, openParen+1);
            toReturn.add(markdown.substring(openParen + 1, closeParen));
            currentIndex = closeParen + 1;
            
        }
        return toReturn;
    }

    /** finds the indexOf one of the following enclosers, whichever comes first. 
     * @param str searching through str
     * @param start to search after
     * @return the index, -1 if none in str. 
     */
    public static int indexOfTheFollowing(String str, int start) {
        ArrayList<String> linkLabelEnclosers = new ArrayList<String>();
        linkLabelEnclosers.add("(");
        linkLabelEnclosers.add("]");
        linkLabelEnclosers.add("[");
        linkLabelEnclosers.add(")");
        int firstIndex = str.length();
        for(int i = 0;i<linkLabelEnclosers.size();i++) {
            int temp = str.indexOf(linkLabelEnclosers.get(i),start);
            if(temp != -1 && temp < firstIndex) {
                firstIndex = temp;
            }
        }
        System.out.println();
        if(firstIndex == str.length())
            return -1; //no results
        return firstIndex;

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