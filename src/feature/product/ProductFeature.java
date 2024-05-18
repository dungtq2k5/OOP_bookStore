package feature.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import customer.purchases.PaymentMethod;
import feature.customer.CustomerFeature;
import fileio.customer.IOAddress;
import fileio.customer.IOPurchases;
import fileio.product.IOEBook;
import fileio.product.IOPaperBook;
import fileio.product.IORating;
import manage.ManageBook;
import product.Book;
import product.EBook;
import product.PaperBook;
import utils.Utils;

public class ProductFeature {
  
  private Scanner scanner = new Scanner(System.in);

  private CustomerFeature customerFeature;

  //call funcs
  public ProductFeature(CustomerFeature customerFeature) {
    this.customerFeature = customerFeature;
  }


  //basic funcs
  public void order(Book book, int quantity, String deliveryAddress, int paymentMethod) {
    IOPurchases ioPurchase = new IOPurchases(Utils.PURCHASES_FILE, Utils.FILE_DEIMITER);
    ioPurchase.store(customerFeature.getCustomer().getID(), book, quantity, deliveryAddress, paymentMethod);
  }

  public List<Book> browsingBook(boolean browsEBook) {
    List<Book> books;
    if(browsEBook) { //brows eBook
      IOEBook ioeBook = new IOEBook(Utils.EBOOK_FILE, Utils.FILE_DEIMITER);
      books = ioeBook.query();
    } else { //brows book
      IOPaperBook ioPaperBook = new IOPaperBook(Utils.PAPER_BOOK_FILE, Utils.FILE_DEIMITER);
      books = ioPaperBook.query();
    }
    
    List<Book> bookBrowsedList = new ArrayList<>();
    IORating ioRating = new IORating(Utils.RATING_FILE, Utils.FILE_DEIMITER);
    int ordinal = 0;
    for(Book book : books) {
      ordinal++;
      System.out.println("\n- Product ordinal: " + ordinal);
      book.setRatings(ioRating.getRatings(book.getID()));
      book.setComments(ioRating.getComments(book.getID()));
      
      if(book instanceof PaperBook) {
        PaperBook paperBook = (PaperBook) book;
        paperBook.printInfo();
      } else {
        EBook eBook = (EBook) book;
        eBook.printInfo();
      }

      bookBrowsedList.add(book);

      System.out.print("-> Enter any key to continue browsing or 'q' to quit: ");
      if(scanner.next().equalsIgnoreCase("q")) { //stop
        break;
      }
    }

    return bookBrowsedList;
  }

  public void rating(String productID, int rating, String comment) { // product's state = delivered
    IORating ioRating = new IORating(Utils.RATING_FILE, Utils.FILE_DEIMITER);
    ioRating.store(customerFeature.getCustomer().getID(), productID, rating, comment);
  }

  public List<Book> searchBook(boolean qEBook) {
    ManageBook manageBook = new ManageBook();
    if(qEBook) {
      IOEBook ioeBook = new IOEBook(Utils.EBOOK_FILE, Utils.FILE_DEIMITER);
      manageBook.setList(ioeBook.query());
    } else {
      IOPaperBook ioPaperBook = new IOPaperBook(Utils.PAPER_BOOK_FILE, Utils.FILE_DEIMITER);
      manageBook.setList(ioPaperBook.query());
  }

  System.out.print("\n-> Search by (0.name | 1.author | 2.producer)?: ");
  int option = scanner.nextInt(); 
  scanner.nextLine();

  List<Book> filterList;
  switch(option) {
    case 0:
      System.out.print("-> Name: ");
      String name = scanner.nextLine();
      filterList =  manageBook.filterName(name);
      break;
    case 1:
      System.out.print("-> Author: ");
      String author = scanner.nextLine();
      filterList = manageBook.filterAuthor(author);
      break;
    default:
      System.out.print("-> Producer: ");
      String producer = scanner.nextLine();
      filterList = manageBook.filterProducer(producer);
      break;
  }
  
  ManageBook manageFilterBook = new ManageBook(filterList);
  System.out.printf("%n---- %d related results ----%n", manageFilterBook.getQuantity());
  manageFilterBook.printList(true);

  return manageFilterBook.getList();
}


  //process funcs
  public void processOrderPaperBook(PaperBook book) {
    //select quant
    System.out.print("\n-> Quantity: ");
    int quantity = scanner.nextInt();

    //select delivery address
    int i=0;
    IOAddress ioAddress = new IOAddress(Utils.ADDRESS_FILE, Utils.FILE_DEIMITER);
    List<String> addresses = ioAddress.query(customerFeature.getCustomer().getID());
    System.out.println("\n- Select your addressess: ");
    for(String address : addresses) {
      System.out.println("\t" + i + ". " + address);
      i++;
    }
    System.out.print("-> Select: ");
    String deliveryAddress = addresses.get(scanner.nextInt());

    //select payment method
    i=0;
    System.out.println("\n- Select your payment method: ");
    for(String method : PaymentMethod.PAYMENT_METHODS) {
      System.out.println("\t" + i + ". " + method);
      i++;
    }
    System.out.print("-> Select: ");
    int paymentMethod = scanner.nextInt();

    order(book, quantity, deliveryAddress, paymentMethod);
  }

  public void processOrderEBook(EBook book) {
    int i=0;
    System.out.println("\n- Select your payment method: ");
    for(String method : PaymentMethod.EPAYMENT_METHODS) {
      System.out.println("\t" + i + ". " + method);
      i++;
    }
    System.out.print("-> Select: ");
    int paymentMethod = scanner.nextInt();
    
    book.setPrice(0);

    order(book, 1, customerFeature.getCustomer().getID(), paymentMethod);
  }

  public void processAddBuy(CartFeature cartFeature, List<Book> bookList) {
    while(true) {
      System.out.print("\n-> 0.add to cart | 1.order | 2.quit: ");
      int option = scanner.nextInt();

      if((option == 0 || option == 1) && customerFeature.getIsLogin()) { //when login
        System.out.print("-> Product ordinal: ");
        Book book = bookList.get(scanner.nextInt() - 1);

        if(option == 0) { //add to cart
          cartFeature.add(book.getID(), 1);
          Utils.showHeading("Successfully added");
        } else { //order
          if(book instanceof PaperBook) {
            processOrderPaperBook((PaperBook) book);
          } else {
            processOrderEBook((EBook) book);
          }
          Utils.showHeading("Successfully ordered");
        }

      } else if((option == 0 || option == 1) && !customerFeature.getIsLogin()) {
        Utils.showWarning("You have to login first");
        customerFeature.processLoginRegister();
        
      } else {
        break;
      }
    }
  }

  public void processBrowsing(CartFeature cartFeature) {
    System.out.print("-> Browsing (0.book | 1.ebook)?: ");
    int option = scanner.nextInt();

    switch(option) {
      case 0:
        Utils.showHeading("Browsing Book");
        processAddBuy(cartFeature, browsingBook(false));
        break;

      case 1:
        Utils.showHeading("Browsing EBook");
        processAddBuy(cartFeature, browsingBook(true));
        break;
        
      default:
        Utils.showInvalidInput();
    }
  }

  public void processSearching(CartFeature carFeature) {
    System.out.print("-> Search (0.book | 1.ebook)?: ");
    int option = scanner.nextInt();

    switch(option) {
      case 0:
        Utils.showHeading("Searching Book");
        processAddBuy(carFeature, searchBook(false));
        break;

      case 1:
        Utils.showHeading("Searching EBook");
        processAddBuy(carFeature, searchBook(true));
        break;

      default:
        Utils.showInvalidInput();
    }
  }

}
