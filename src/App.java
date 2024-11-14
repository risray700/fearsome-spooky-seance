import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.net.URL;

class App {
  public static void main(String[] args) throws java.net.MalformedURLException, FileNotFoundException{
    
       Book book = new Book(new URL("https://www.gutenberg.org/cache/epub/74652/pg74652.txt"));
       String translatedBook = book.translateString();
       String OGbook = book.getText();
       
  try (PrintWriter out = new PrintWriter("filenameOG.txt")) { //saves untranslated book to txt
       out.println(OGbook);
 } 

  try (PrintWriter out = new PrintWriter("filename.txt")) { //saves translated book to txt
    out.println(translatedBook);
  } 
       System.out.println(book.translateString());
    }
}
//url:https://www.gutenberg.org/cache/epub/74652/pg74652.txt
