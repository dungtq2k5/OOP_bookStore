package customer.purchases;

import java.util.Scanner;

public class Purchases implements PaymentMethod, ProductState {

  private String id;
  private String buyerID;
  private String productID;
  private int productQuantity;
  private String deliveryAddress;
  private double total;
  private int paymentMethod;
  private int productState; 

  public Purchases() {}
  public Purchases(String id, String buyerID, String productID, int productQuantity, String deliveryAddress, double total, int paymentMethod, int productState) {
    setInfo(id, buyerID, productID, productQuantity, deliveryAddress, total, paymentMethod, productState);
  }

  //set&get
  public void setID(String id) {
    this.id = id;
  }
  public String getID() {
    return this.id;
  }

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

  public void setProductQuantity(int amount) {
    this.productQuantity = amount;
  }
  public int getProductQuantity() {
    return this.productQuantity;
  }

  public void setDeliveryAddress(String address) {
    this.deliveryAddress = address;
  }
  public String getDeliveryAddress() {
    return this.deliveryAddress;
  }

  public void setTotal(double amount) {
    this.total = amount;
  }
  public double getTotal() {
    return this.total;
  }

  public void setPaymentMethod(int i) {
    this.paymentMethod = i;
  }
  public int getPaymentMethod() {
    return this.paymentMethod;
  }

  public void setState(int i) {
    this.productState = i;
  }
  public int getState() {
    return this.productState;
  }

  public void setInfo() {
    @SuppressWarnings("resource")
    Scanner scanner = new Scanner(System.in);

    System.out.print("ID: ");
    setID(scanner.nextLine());

    System.out.print("Buyer's id: ");
    setBuyerID(scanner.nextLine());

    System.out.print("Product's id: ");
    setProductID(scanner.nextLine());

    System.out.print("Product quantity: ");
    setProductQuantity(Integer.parseInt(scanner.nextLine()));

    System.out.print("Delivery address: ");
    setDeliveryAddress(scanner.nextLine());

    System.out.print("Total: ");
    setTotal(scanner.nextDouble());

    System.out.print("Payment method (0.cash, 1.debit card, 2.credit card): ");
    setPaymentMethod(scanner.nextInt());

    System.out.print("Product's state (0.to pay, 1.to ship, 2.to receive, 3.to rate): ");
    setState(scanner.nextInt());
  }
  public void setInfo(String id, String buyerID, String productID, int productQuantity, String deliveryAddress, double total, int paymentMethod, int productState) {
    setID(id);
    setBuyerID(buyerID);
    setProductID(productID);
    setProductQuantity(productQuantity);
    setDeliveryAddress(deliveryAddress);
    setTotal(total);
    setPaymentMethod(paymentMethod);
    setState(productState);
  }

  public void printInfo() {
    System.out.println("ID: " + getID());
    System.out.println("Buyer's id: " + getBuyerID());
    System.out.println("Product's id: " + getProductID());
    System.out.println("Product quantity: " + getProductQuantity());
    System.out.println("Devilery address: " + getDeliveryAddress());
    System.out.println("Total: " + getTotal());
    System.out.println("Payment method: " + PAYMENT_METHODS[getPaymentMethod()]);
    System.out.println("Product's state: " + STATES[getState()]);
  }

}
