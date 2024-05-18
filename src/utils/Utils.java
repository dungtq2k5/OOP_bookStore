package utils;

import java.util.Scanner;
import java.util.UUID;

public class Utils {

  public static final String 
    FILE_DEIMITER = "# ",
    FILE_FORMAT = "txt",
    ROOT = "asset"
  ;

  public static final String 
    CART_FILE = String.format("%s/cart/carts.%s", ROOT, FILE_FORMAT),
    PURCHASES_FILE = String.format("%s/cart/purchases.%s", ROOT, FILE_FORMAT),

    CUSTOMER_FILE = String.format("%s/customers/customers.%s", ROOT, FILE_FORMAT),
    ADDRESS_FILE = String.format("%s/customers/addresses.%s", ROOT, FILE_FORMAT),

    RATING_FILE = String.format("%s/products/ratings/ratings.%s", ROOT, FILE_FORMAT),
    PAPER_BOOK_FILE = String.format("%s/products/paper_books.%s", ROOT, FILE_FORMAT),
    EBOOK_FILE = String.format("%s/products/ebooks.%s", ROOT, FILE_FORMAT),

    FULL_TIME_STAFF_FILE = String.format("%s/staffs/full_time_staffs.%s", ROOT, FILE_FORMAT),
    PART_TIME_STAFF_FILE = String.format("%s/staffs/part_time_staffs.%s", ROOT, FILE_FORMAT),

    SHIPMENT_FILE = String.format("%s/staffs/shipment.%s", ROOT, FILE_FORMAT)
  ;

  private Utils() {
    throw new IllegalStateException("Utility class");
  }

  public static String autoGenerateUUID() {
    return UUID.randomUUID().toString();
  }

  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public static void pressToContinue() {
    @SuppressWarnings("resource")
    Scanner scanner  = new Scanner(System.in);

    System.out.print("\nPress any keys to continue... ");
    scanner.nextLine();
  }

  public static void pressToContinue(String msg) {
    @SuppressWarnings("resource")
    Scanner scanner  = new Scanner(System.in);

    System.out.print("\n" + msg + "...");
    scanner.nextLine();
  }
  
  public static void showHeading(String msg) {
    System.out.println("\n---- " + msg + " ----\n");
  }

  public static void showSemiHeading(String msg) {
    System.out.println("\n~~~~ " + msg + " ~~~~\n");
  }

  public static void showWarning(String msg) {
    System.out.println("\n!!!! " + msg + " !!!!\n");
  }

  public static void showInvalidInput() {
    showWarning("Invalid input");
  }

}
