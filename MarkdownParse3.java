// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MarkdownParse3 {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        ArrayList<Integer> periodList = new ArrayList<Integer>();
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            int nextPeriodIndex = markdown.indexOf(".", currentIndex + 1);
            if(nextPeriodIndex != -1) {
                periodList.add(nextPeriodIndex);
                currentIndex = nextPeriodIndex;
            } else {
                break;
            }
        }
        for(int periodIndex: periodList) {
            int startIndex = periodIndex;
            int endIndex = periodIndex;
            ArrayList<Character> stopCharacters = new ArrayList<Character>();
            stopCharacters.add('(');
            stopCharacters.add(')');
            stopCharacters.add('[');
            stopCharacters.add(']');
            stopCharacters.add('\n');
            stopCharacters.add(' ');
            while(startIndex >= 0) {
                if(stopCharacters.contains(markdown.charAt(startIndex))) {
                    startIndex++;
                    break;
                }
                startIndex--;
            }
            while(endIndex < markdown.length()) {
                if(stopCharacters.contains(markdown.charAt(endIndex))) {
                    endIndex--;
                    break;
                }
                endIndex++;
            }
            toReturn.add(markdown.substring(startIndex, endIndex + 1));
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