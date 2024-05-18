package customer;

import java.util.Scanner;

public class Cart {

  private String customerID;
  private String productID;
  private int productQuantity;

  //call funcs
  public Cart(){}
  public Cart(String customerID, String productID, int productQuantity) {
    setInfo(customerID, productID, productQuantity);
  }

  //set&get
  public void setCustomerID(String id) {
    this.customerID = id;
  }
  public String getCustomerID() {
    return this.customerID;
  }

  public void setProductID(String id) {
    this.productID = id;
  }
  public String getProductID() {
    return this.productID;
  }

  public void setProductQuantity(int quantity) {
    this.productQuantity = quantity;
  }
  public int getProductQuantity() {
    return this.productQuantity;
  }


  //basic funcs
  public void setInfo() {
    @SuppressWarnings("resource")
    Scanner scanner = new Scanner(System.in);

    System.out.print("Customer's id: ");
    setCustomerID(scanner.nextLine());
    System.out.print("Product's id: ");
    setProductID(scanner.nextLine());
    System.out.print("Product quantity: ");
    setProductQuantity(scanner.nextInt());
  }
  public void setInfo(String customerID, String productID, int productQuantity) {
    setCustomerID(customerID);
    setProductID(productID);
    setProductQuantity(productQuantity);
  }

  public void printInfo() {
    System.out.println("Customer's id: " + getCustomerID());
    System.out.println("Product's id: " + getProductID());
    System.out.println("Product quantity: " + getProductQuantity());
  }

}
