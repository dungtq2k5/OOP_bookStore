package product.rating;

import java.util.Scanner;

public class RatingProduct implements Rating {
  
  private String buyerID;
  private String productID;
  private int rating;
  private String comment;

  //call funcs
  public RatingProduct(){}
  public RatingProduct(String buyerID, String productID, int rating, String comment) {
    setInfo(buyerID, productID, rating, comment);
  }

  //set&get funcs
  public void setBuyerID(String id) {
    this.buyerID = id;
  }
  public String getBuyerID() {
    return this.buyerID;
  }

  public void setProductID(String id) {
    this.productID = id;
  }
  public String getProductID() {
    return this.productID;
  }

  public void setRating(int level) {
    this.rating = level;
  }
  public int getRating() {
    return this.rating;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }
  public String getComment() {
    return this.comment;
  }

  public void setInfo() {
    @SuppressWarnings("resource")
    Scanner scanner = new Scanner(System.in);

    System.out.print("Buyer's ID: ");
    setBuyerID(buyerID);
    System.out.print("Product's ID: ");
    setProductID(scanner.nextLine());
    System.out.print("Buyer's rating (0 to 5): ");
    setRating(Integer.parseInt(scanner.nextLine()));
    System.out.print("Buyer's comment: ");
    setComment(scanner.nextLine());
  }
  public void setInfo(String buyerID, String productID, int rating, String comment) {
    setBuyerID(buyerID);
    setProductID(productID);
    setRating(rating);
    setComment(comment);
  }

  //basic funcs
  public void printInfo() {
    System.out.println("Buyer's ID: " + getBuyerID());
    System.out.println("Product's ID: "+ getProductID());
    System.out.println("Buyer's rating (0 to 5): " + RATING_LEVEL[getRating()]);
    System.out.println("Buyer's comment: " + getComment());
  }
  

}
