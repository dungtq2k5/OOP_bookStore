package process;

import java.util.Scanner;

import feature.customer.CustomerFeature;
import feature.product.CartFeature;
import feature.product.ProductFeature;
import feature.product.PurchaseFeature;
import utils.Utils;

public class ProcessCustomer {

  private ProcessCustomer() {
    throw new IllegalStateException("Process customer");
  }

  public static void showMenu(boolean isLogin) {
    Utils.showHeading("Customer functions");

    System.out.println(
      "0. Quit\n" +
      "1. Browsing\n" +
      "2. Searching"
    );
    
    if(isLogin) {
      System.out.println(
        "3. Logout\n" + 
        "4. Add delivery address\n" + 
        "5. My Cart\n" + 
        "6. My Purchases"
      );
    } else {
      System.out.println(
        "3. Login\n" + 
        "4. Register"
      );
    }
  }

  public static void process(Scanner scanner) {
    CustomerFeature customerFeature = new CustomerFeature();
    CartFeature cartFeature = new CartFeature(customerFeature);
    PurchaseFeature purchaseFeature = new PurchaseFeature(customerFeature);
    ProductFeature productFeature = new ProductFeature(customerFeature);
    
    while(true) {
      showMenu(customerFeature.getIsLogin());
      System.out.print("\n-> Option: ");
      int option = Integer.parseInt(scanner.next());
  
      if(option == 0) {
        Utils.showHeading("Program end");
        scanner.close();
        break;
      } else if(option == 1) {
        Utils.showHeading("Browsing");
        productFeature.processBrowsing(cartFeature);
      } else if(option == 2) {
        Utils.showHeading("Searching");
        productFeature.processSearching(cartFeature);
  
      } else if(customerFeature.getIsLogin()) { 
        switch(option) {
          case 3: 
            customerFeature.logout();
            Utils.showHeading("Successfully logout");
            break;
          case 4: 
            Utils.showHeading("Adding delivery address");
            customerFeature.processAddAddress();
            Utils.showHeading("Successfully added");
            break;
          case 5: 
            Utils.showHeading("Your cart");
            cartFeature.processShowCart(productFeature);
            break;
          case 6: 
            Utils.showHeading("Your purchases");
            purchaseFeature.processShowPurchases(productFeature);
            break;
          default:
            Utils.showInvalidInput();
            break;
        }
  
      } else if(!customerFeature.getIsLogin()) {
        switch(option) {
          case 3: 
            customerFeature.processLogin();
            break;
          case 4: 
            customerFeature.processRegister();
            break;
          default:
            Utils.showInvalidInput();
            break;
        }
      }
    
      Utils.pressToContinue();
      Utils.clearScreen();
    }
  }
  
}
