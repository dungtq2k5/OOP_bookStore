package fileio.product;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fileio.IO;
import product.Book;
import product.PaperBook;

public class IOPaperBook extends IO implements IOBook {

  public static final int WIDTH=9, HEIGHT=10, STOCK=11, COVER_TYPE=12; 
  
  //call funcs
  public IOPaperBook(){}
  public IOPaperBook(String filePath, String delimiter) {
    setFilePath(filePath);
    setDelimiter(delimiter);
  }
  public IOPaperBook(String filePath, String delimiter, boolean append) {
    setFilePath(filePath);
    setDelimiter(delimiter);
    setAppend(append);
  }
  
  //basic funcs
  public void store(PaperBook book) {
    try(BufferedWriter writer = new BufferedWriter(new FileWriter(getFilePath(), getAppend()))) {
      writer.write(
        book.getID() + getDelimiter() +
        book.getName() + getDelimiter() + 
        book.getAuthor() + getDelimiter() + 
        book.getProducer()  + getDelimiter() + 
        book.getTenor() + getDelimiter() + 
        String.valueOf(book.getPrice()) + getDelimiter() +
        String.valueOf(book.getPages()) + getDelimiter() + 
        String.valueOf(book.getRatings()) + getDelimiter() +
        String.valueOf(book.getComments()) + getDelimiter() +

        String.valueOf(book.getSize()[PaperBook.WIDTH]) + getDelimiter()  +
        String.valueOf(book.getSize()[PaperBook.HEIGHT]) + getDelimiter() +
        String.valueOf(book.getStock()) + getDelimiter() +
        book.getCoverType() + '\n'
      );

    } catch(IOException e) {
      e.printStackTrace();
    }
  }
  
  public List<Book> query() {
    List<Book> books = new ArrayList<>();

    try(BufferedReader reader = new BufferedReader(new FileReader(getFilePath()))) {
      String line;
      while((line=reader.readLine()) != null) {
        String[] book = line.split(getDelimiter());
        Book bookFormatted = new PaperBook(
          book[ID], book[NAME], book[AUTHOR], book[PRODUCER], book[TENOR],
          Double.parseDouble(book[PRICE]),
          Integer.parseInt(book[PAGES]),
          Double.parseDouble(book[RATINGS]),
          Integer.parseInt(book[COMMENTS]),

          Double.parseDouble(book[WIDTH]),
          Double.parseDouble(book[HEIGHT]),
          Integer.parseInt(book[STOCK]),
          book[COVER_TYPE]
        );

        books.add(bookFormatted);
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

    return books;
  }
  
  public PaperBook query(String id) {
    try(BufferedReader reader = new BufferedReader(new FileReader(getFilePath()))) {
      String line;
      while((line=reader.readLine()) != null) {
        String[] book = line.split(getDelimiter());
        if(book[ID].equals(id)) {
          return new PaperBook(
            book[ID], book[NAME], book[AUTHOR], book[PRODUCER], book[TENOR],
            Double.parseDouble(book[PRICE]),
            Integer.parseInt(book[PAGES]),
            Double.parseDouble(book[RATINGS]),
            Integer.parseInt(book[COMMENTS]),
  
            Double.parseDouble(book[WIDTH]),
            Double.parseDouble(book[HEIGHT]),
            Integer.parseInt(book[STOCK]),
            book[COVER_TYPE]
          );
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }

}

