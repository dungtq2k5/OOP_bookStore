package feature.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import customer.purchases.PaymentMethod;
import customer.purchases.ProductState;
import customer.purchases.Purchases;
import feature.customer.CustomerFeature;
import fileio.customer.IOPurchases;
import utils.Utils;

public class PurchaseFeature {

  private Scanner scanner = new Scanner(System.in);
  private CustomerFeature customerFeature;

  //call
  public PurchaseFeature(CustomerFeature customerFeature) {
    this.customerFeature = customerFeature;
  }


  //basic funcs
  public void printForm(Purchases purchase) {
    System.out.println(
      "Product's ID: " + purchase.getProductID() + '\n' +
      "Quantity: " + purchase.getProductQuantity() + '\n' +
      "Order total: " + purchase.getTotal() + '\n' +
      "Payment method: " + PaymentMethod.PAYMENT_METHODS[purchase.getPaymentMethod()] + '\n' +
      "Delivery address: " + purchase.getDeliveryAddress()
    );
  }

  public List<Purchases> showPurchases() {
    IOPurchases ioPurchases = new IOPurchases(Utils.PURCHASES_FILE, Utils.FILE_DEIMITER);
    List<Purchases> purchases = ioPurchases.query(customerFeature.getCustomer().getID());
    
    List<Purchases> toPay = new ArrayList<>();
    List<Purchases> toShip = new ArrayList<>();
    List<Purchases> toReceive = new ArrayList<>();
    List<Purchases> toRate = new ArrayList<>();
    List<Purchases> completed = new ArrayList<>();
    
    for(Purchases purchase : purchases) { //seperate state
      int productState = purchase.getState();

      if(productState == ProductState.TO_PAY) {
        toPay.add(purchase);
      } else if (productState == ProductState.TO_SHIP) {
        toShip.add(purchase);
      } else if(productState == ProductState.TO_RECEIVE) {
        toReceive.add(purchase);
      } else if(productState == ProductState.TO_RATE) {
        toRate.add(purchase);
      } else {
        completed.add(purchase);
      } 
    }

    int ordinal = 0;
    Utils.showSemiHeading("To Pay");
    for(Purchases purchase : toPay) {
      ordinal++;
      System.out.println("\n- Ordinal: " + ordinal);
      printForm(purchase);
    }

    Utils.showSemiHeading("To Ship");
    for(Purchases purchase : toShip) {
      ordinal++;
      System.out.println("\n- Ordinal: " + ordinal);
      printForm(purchase);
    }

    Utils.showSemiHeading("To Receive");
    for(Purchases purchase : toReceive) {
      ordinal++;
      System.out.println("\n- Ordinal: " + ordinal);
      printForm(purchase);
    }

    Utils.showSemiHeading("To Rate");
    for(Purchases purchase : toRate) {
      ordinal++;
      System.out.println("\n- Ordinal: " + ordinal);
      printForm(purchase);
    }

    Utils.showSemiHeading("Completed");
    for(Purchases purchase : completed) {
      ordinal++;
      System.out.println("\n- Ordinal: " + ordinal);
      printForm(purchase);
    }

    return purchases;
  }


  //process funcs
  public void proccessCancelRating(ProductFeature productService, List<Purchases> purchases) {
    while(true) {
      System.out.print("\n-> 0.cancel order | 1.rating | 2.quit: ");
      int option = scanner.nextInt();

      if(option == 0 || option == 1) {
        System.out.print("-> Product ordinal: ");
        Purchases purchase = purchases.get(scanner.nextInt() - 1);
        int state = purchase.getState();
        scanner.nextLine();

        if(option==0 && state==ProductState.TO_PAY) { //can cancel order
          IOPurchases ioPurchases = new IOPurchases(Utils.PURCHASES_FILE, Utils.FILE_DEIMITER);
          ioPurchases.delete(purchase.getBuyerID(), purchase.getProductID());
  
          Utils.showHeading("Successfully cancel");
          Utils.pressToContinue("Press to confirm");
          break;
  
        } else if(option==1 && state==ProductState.TO_RATE) { //can rating
          IOPurchases ioPurchases = new IOPurchases(Utils.PURCHASES_FILE, Utils.FILE_DEIMITER);
          System.out.print("Rating (0-5): ");
          int rate = Integer.parseInt(scanner.nextLine());
          System.out.print("Comment: ");
          String comment = scanner.nextLine();
  
          productService.rating(purchase.getProductID(), rate, comment);
          ioPurchases.updateProductState(purchase.getID(), ProductState.COMPLETED);
  
          Utils.showHeading("Successfully rating");
          Utils.pressToContinue("Press to confirm");
          break;

        } else {
          Utils.showWarning("Permission denied");
        }

      } else {
        break;
      }

    }
  }

  public void processShowPurchases(ProductFeature productService) {
    proccessCancelRating(productService, showPurchases());
  } 

}
