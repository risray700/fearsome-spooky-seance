import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.net.URL;

public class Book {

    private static final String VOWELS = "AEIOUaeiou";
    private String bookText;

    public Book(URL url) {
        this.bookText = readBook(url);
    }

    public Book(String text) {
        this.bookText = text;
    }

    private String readBook(URL url) {
        StringBuilder result = new StringBuilder();
        boolean reading = false;
        try (Scanner sc = new Scanner(url.openStream())) {
            while (sc.hasNextLine()) {
                String text = sc.nextLine();

                if (text.indexOf("*** END") > -1) reading = false;

                if (reading) result.append(text);

                if(text.indexOf("*** START") > -1) reading = true;
                
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result.toString();
    }

    public String pigLatin(String word) {
        if (word.isEmpty()) return word;

        StringBuilder punctuationFront = new StringBuilder();
        StringBuilder punctuationBack = new StringBuilder();
        int index1 = 0, index2 = word.length();

        // Front punctuation
        while (index1 < word.length() && !Character.isLetterOrDigit(word.charAt(index1))) {
            punctuationFront.append(word.charAt(index1++));
        }

        // Back punctuation
        while (index2 > index1 && !Character.isLetterOrDigit(word.charAt(index2 - 1))) {
            punctuationBack.insert(0, word.charAt(--index2));
        }

        String theWord = word.substring(index1, index2);
        if (theWord.isEmpty()) return word;

        boolean isAllCaps = theWord.equals(theWord.toUpperCase());
        boolean isCapitalized = Character.isUpperCase(theWord.charAt(0));

        int firstVowelIndex = -1;
        for (int i = 0; i < theWord.length(); i++) {
            if (VOWELS.indexOf(theWord.charAt(i)) >= 0) {
                firstVowelIndex = i;
                break;
            }
        }

        if (firstVowelIndex == -1) return word; // No vowels found

        String firstPart = theWord.substring(0, firstVowelIndex);
        String lastPart = theWord.substring(firstVowelIndex);

        String pigLatinWord = lastPart + firstPart + (isAllCaps ? "AY" : "ay");

        if (isCapitalized && !isAllCaps) {
            pigLatinWord = Character.toUpperCase(pigLatinWord.charAt(0)) + pigLatinWord.substring(1).toLowerCase();
        }

        return punctuationFront + pigLatinWord + punctuationBack;
    }

    public String translateString() {
        String[] words = bookText.split(" ");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            result.append(pigLatin(word)).append(" ");
        }

        return result.toString().trim();
    }

    public int countWords(String text) {
        return text.split("\\s+").length;
    }

    public String getText() {
        return bookText;
    }


  }
//