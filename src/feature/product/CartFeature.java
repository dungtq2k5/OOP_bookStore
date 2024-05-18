package feature.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import customer.Cart;
import feature.customer.CustomerFeature;
import fileio.customer.IOCart;
import fileio.product.IOEBook;
import fileio.product.IOPaperBook;
import product.Book;
import product.EBook;
import product.PaperBook;
import utils.Utils;

public class CartFeature {
  
  private Scanner scanner = new Scanner(System.in);

  private CustomerFeature customerFeature;

  //call funcs
  public CartFeature(CustomerFeature customerFeature) {
    this.customerFeature = customerFeature;
  }


  //basic funcs
  public boolean add(String productID, int quantity) {
    IOCart ioCart = new IOCart(Utils.CART_FILE, Utils.FILE_DEIMITER);
    List<Cart> carts = ioCart.query();

    for(Cart cart : carts) {
      if(cart.getCustomerID().equals(customerFeature.getCustomer().getID()) && cart.getProductID().equals(productID)) { //already exist->quantity++
        cart.setProductQuantity(cart.getProductQuantity()+1);
        ioCart.update(customerFeature.getCustomer().getID(), productID, cart);
        return false;
      }
    }
    
    ioCart.store(customerFeature.getCustomer().getID(), productID, quantity);
    return true;
  }

  public void delete(String productID) {
    IOCart ioCart = new IOCart(Utils.CART_FILE, Utils.FILE_DEIMITER);
    ioCart.delete(customerFeature.getCustomer().getID(), productID);
  }

  public List<Book> showCart() {
    IOCart ioCart = new IOCart(Utils.CART_FILE, Utils.FILE_DEIMITER);
    IOPaperBook ioPaperBook = new IOPaperBook(Utils.PAPER_BOOK_FILE, Utils.FILE_DEIMITER);
    IOEBook ioeBook = new IOEBook(Utils.EBOOK_FILE, Utils.FILE_DEIMITER);
    
    List<Book> productInCart = new ArrayList<>();
    List<Cart> carts = ioCart.query(customerFeature.getCustomer().getID());
    int ordinal=0;
    for(Cart cart : carts) {
      ordinal++;
      System.out.println("\n- Ordinal: " + ordinal);

      //query paper book
      PaperBook paperBook = ioPaperBook.query(cart.getProductID());
      if(paperBook != null) {
        System.out.println("ID: " + paperBook.getID());
        System.out.println("Title: " + paperBook.getName());
        System.out.println("Price: " + paperBook.getPrice() + " - Quantity: " + cart.getProductQuantity()); 

        productInCart.add(paperBook);
      } else { //query ebook
        EBook eBook = ioeBook.query(cart.getCustomerID());
        System.out.println("ID "+ eBook.getID());
        System.out.println("Title: " + eBook.getName());
        System.out.println("Price: " + eBook.getPrice()); 
        
        productInCart.add(eBook);
      }
    }

    return productInCart;
  }

  
  //process funcs
  public void processOrderDel(ProductFeature productService, List<Book> products) {
    while(true) {
      System.out.print("\n-> 0.order | 1.delete | 2.quit: ");
      int option = scanner.nextInt();
      
      if(option == 0) { //order -> delete from cart
        System.out.print("\n-> Product ordinal to order: ");
        Book book = products.get(scanner.nextInt() - 1);

        if(book instanceof PaperBook) {
          productService.processOrderPaperBook((PaperBook) book);
        } else {
          productService.processOrderEBook((EBook) book);
        }

        delete(book.getID());

        Utils.showHeading("Successfully odered");
        Utils.pressToContinue("Press to confirm");
        break;

      } else if(option == 1) { //delete from cart
        System.out.print("\n-> Product ordinal to delete: ");
        Book book = products.get(scanner.nextInt() - 1);

        delete(book.getID());

        Utils.showHeading("Successfully deleted");
        Utils.pressToContinue("Press to confirm");
        break;

      } else {
        break;
      }
    }
  }

  public void processShowCart(ProductFeature productService) {
    processOrderDel(productService, showCart());
  }

}