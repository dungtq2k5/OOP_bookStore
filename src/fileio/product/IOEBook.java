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
import product.EBook;

public class IOEBook extends IO implements IOBook {
  
  public static final int PDF=9, EPUB=10, SIZE=11;

  public IOEBook(){}
  public IOEBook(String filePath, String delimiter) {
    setFilePath(filePath);
    setDelimiter(delimiter);
  }
  public IOEBook(String filePath, String delimiter, boolean append) {
    setFilePath(filePath);
    setDelimiter(delimiter);
    setAppend(append);
  }

  public void store(EBook book) {
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

        String.valueOf(book.getPDF()) + getDelimiter()  +
        String.valueOf(book.getEPUB()) + getDelimiter() +
        String.valueOf(book.getSize()) + '\n'
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
        Book bookFormmatted = new EBook(
          book[ID], book[NAME], book[AUTHOR], book[PRODUCER], book[TENOR],
          Double.parseDouble(book[PRICE]),
          Integer.parseInt(book[PAGES]),
          Double.parseDouble(book[RATINGS]),
          Integer.parseInt(book[COMMENTS]),

          Boolean.parseBoolean(book[PDF]),
          Boolean.parseBoolean(book[EPUB]),
          Double.parseDouble(book[SIZE])
        );

        books.add(bookFormmatted);
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

    return books;
  }

  public EBook query(String id) {
    try(BufferedReader reader = new BufferedReader(new FileReader(getFilePath()))) {
      String line;
      while((line=reader.readLine()) != null) {
        String[] book = line.split(getDelimiter());
        if(book[ID].equals(id)) {
          return new EBook(
            book[ID], book[NAME], book[AUTHOR], book[PRODUCER], book[TENOR],
            Double.parseDouble(book[PRICE]),
            Integer.parseInt(book[PAGES]),
            Double.parseDouble(book[RATINGS]),
            Integer.parseInt(book[COMMENTS]),
  
            Boolean.parseBoolean(book[PDF]),
            Boolean.parseBoolean(book[EPUB]),
            Double.parseDouble(book[SIZE])
          );
  
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }
 
}
