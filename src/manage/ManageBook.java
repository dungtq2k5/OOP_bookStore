package manage;
import java.util.ArrayList;
import java.util.List;

import product.*;

public class ManageBook {
  
  private List<Book> list = new ArrayList<>();

  //call
  public ManageBook(){}
  public <T extends Book> ManageBook(List<T> list) {
    setList(list);
  }

  //set&get
  @SuppressWarnings("unchecked")
  public <T extends Book> void setList(List<T> list) {
    this.list = (List<Book>) list;
  }
  public List<Book> getList() {
    return this.list;
  }

  public int getQuantity() {
    return getList().size();
  }

  public Book getBook(String id) {
    for(Book book : getList()) {
      if(book.getID().equals(id)) {
        return book;
      }
    }
    return null;
  }

  public List<EBook> getEBookList() {
    List<EBook> eBooks = new ArrayList<>();
    for(Book book : getList()) {
      if(book instanceof EBook) {
        EBook ebook = (EBook) book;
        eBooks.add(ebook);
      }
    }

    return eBooks;
  }

  public List<PaperBook> getPaperBookList() {
    List<PaperBook> books = new ArrayList<>();
    for(Book book : getList()) {
      if(book instanceof PaperBook) {
        PaperBook paperBook = (PaperBook) book;
        books.add(paperBook);
      }
    }

    return books;
  }

  //sort 
  public void sortByID() {
    getList().sort((a, b) -> a.getID().compareTo(b.getID()));
  }
  public void sortByName() {
    getList().sort((a, b) -> a.getName().compareTo(b.getName()));
  }
  public void sortByAuthor() {
    getList().sort((a, b) -> a.getAuthor().compareTo(b.getAuthor()));
  }
  public void sortByProducer() {
    getList().sort((a, b) -> a.getProducer().compareTo(b.getProducer()));
  }
  public void sortByTenor() {
    getList().sort((a, b) -> a.getTenor().compareTo(b.getTenor()));
  }
  public void sortByPrice() {
    getList().sort((a, b) -> Double.compare(a.getPrice(), b.getPrice()));
  }
  public void sortByPages() {
    getList().sort((a, b) -> Integer.compare(a.getPages(), b.getPages()));
  }

  //CRUD
  public void add(Book newBook) {
    getList().add(newBook);
  }
  public void add(Book newBook, int pos) {
    getList().add(pos, newBook);
  }

  public boolean delete(String id) {
    for(Book book : getList()) {
      if(book.getID().equals(id)) {
        getList().remove(book);
        return true;
      }
    }
    return false;
  }

  public boolean update(String id) {
    for(Book book : getList()) {
      if(book.getID().equals(id)) {
        book.setInfo();
        return true;
      }
    }
    return false;
  }
  
  public void printList() {
    for(Book book : getList()) {
      System.out.println("--------");
      if(book instanceof PaperBook) {
        PaperBook paperBook = (PaperBook) book;
        paperBook.printInfo();
      } else if(book instanceof EBook) {
        EBook eBook = (EBook) book;
        eBook.printInfo();
      }
    }
  }
  public void printList(List<Book> list) {
    for(Book book : list) {
      System.out.println("--------");
      if(book instanceof PaperBook) {
        PaperBook paperBook = (PaperBook) book;
        paperBook.printInfo();
      } else if(book instanceof EBook) {
        EBook eBook = (EBook) book;
        eBook.printInfo();
      }
    }
  }

  public void printList(boolean ordinal) {
    if(ordinal) {
      printListWithOrdinal();
    } else {
      printList();
    }
  }

  private void printListWithOrdinal() {
    int number = 1;
    for(Book book : getList()) {
      System.out.println("\n- Product ordinal: " + number);
      number++;
      if(book instanceof PaperBook) {
        PaperBook paperBook = (PaperBook) book;
        paperBook.printInfo();
      } else if(book instanceof EBook) {
        EBook eBook = (EBook) book;
        eBook.printInfo();
      }
    }
  }

  //filter 
  public List<Book> filterName(String name) {
    List<Book> filterList = new ArrayList<>();

    for(Book book : getList()) {
      if(book.getName().toLowerCase().contains(name)) {
        filterList.add(book);
      }
    }

    return filterList;
  }
  public List<Book> filterName(List<Book> list, String name) {
    ArrayList<Book> filterList = new ArrayList<>();

    for(Book book : list) {
      if(book.getName().toLowerCase().contains(name)) {
        filterList.add(book);
      }
    }

    return filterList;
  }
  
  public List<Book> filterAuthor(String author) {
    List<Book> filterList = new ArrayList<>();

    for(Book book : getList()) {
      if(book.getAuthor().toLowerCase().contains(author)) {
        filterList.add(book);
      }
    }

    return filterList;
  }
  public List<Book> filterAuthor(List<Book> list, String author) {
    List<Book> filterList = new ArrayList<>();

    for(Book book : list) {
      if(book.getAuthor().toLowerCase().contains(author)) {
        filterList.add(book);
      }
    }

    return filterList;
  }
  
  public List<Book> filterProducer(String producer) {
    List<Book> filterList = new ArrayList<>();

    for(Book book : getList()) {
      if(book.getProducer().toLowerCase().contains(producer)) {
        filterList.add(book);
      }
    }

    return filterList;
  }
  public List<Book> filterProducer(List<Book> list, String producer) {
    List<Book> filterList = new ArrayList<>();

    for(Book book : list) {
      if(book.getProducer().toLowerCase().contains(producer)) {
        filterList.add(book);
      }
    }

    return filterList;
  }
  
  public List<Book> filterPrice(double min, double max) {
    ArrayList<Book> filterList = new ArrayList<>();

    for(Book book : getList()) {
      if(book.getPrice() >= min && book.getPrice() <= max) {
        filterList.add(book);
      }
    }

    return filterList;
  }
  public List<Book> filterPrice(List<Book> list, double min, double max) {
    List<Book> filterList = new ArrayList<>();

    for(Book book : list) {
      if(book.getPrice() >= min && book.getPrice() <= max) {
        filterList.add(book);
      }
    }

    return filterList;
  }

}
