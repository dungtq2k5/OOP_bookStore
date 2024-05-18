package process;

import java.util.Scanner;

import feature.staff.*;
import utils.Utils;

public class ProcessStaff {

  private ProcessStaff() {
    throw new IllegalStateException("Process staff");
  }
    
  public static void showManagerMenu(boolean isLogin) {
    Utils.showHeading("Manager staff fucntions");
    
    System.out.println("0. Quit");

    if(isLogin) {
      System.out.println(
        "1. Logout\n" +
        "2. Show orders\n" +
        "3. Tracking parcels\n" + 
        "4. Add new product"
      );
    } else {
      System.out.println("1. Login");
    }
  }

  public static void showShipperMenu(boolean isLogin) {
    Utils.showHeading("Shipper functions");

    System.out.println("0. Quit");

    if(isLogin) {
      System.out.println(
        "1. Logout\n" +
        "2. Show shipments"
      );
    } else {
      System.out.println("1. Login");
    }

  }

  
  public static void processMananger(Scanner scanner) {
    ManagerFeature managerFeature = new ManagerFeature();
    while(true) {
      showManagerMenu(managerFeature.getIsLogin());
      System.out.print("\n-> Option: ");
      int option = scanner.nextInt();

      if(option == 0) {
        Utils.showHeading("Program end");
        break;
      }

      if(managerFeature.getIsLogin()) {
        switch(option) {
          case 1:
            managerFeature.logout();
            Utils.showHeading("Successfully logout");
            break;
          case 2:
            Utils.showHeading("Showing orders");
            managerFeature.processShowOrders();
            break;
          case 3:
            Utils.showHeading("Tracking packages");
            managerFeature.processTrackParcel();
            break;
          case 4:
            Utils.showHeading("Addd new product");
            managerFeature.processAddProduct();
            break;
          default:
            Utils.showInvalidInput();
        }
      } else if(!managerFeature.getIsLogin()) {
        if(option == 1) {
          Utils.showHeading("Indentify manager");
          managerFeature.processLogin();
        } else {
          Utils.showInvalidInput();
        }
      }

      Utils.pressToContinue();
      Utils.clearScreen();

    }
  }

  public static void processShipper(Scanner scanner) {
    ShipperFeature shipperFeature = new ShipperFeature();

    while(true) {
      showShipperMenu(shipperFeature.getIsLogin());
      System.out.print("\n-> Option: ");
      int option = scanner.nextInt();
  
      if(option == 0) { //quit
        Utils.showHeading("Program end");
        scanner.close();
        break;
      }
      
      if(shipperFeature.getIsLogin()) { //login
        switch(option) {
          case 1:
            shipperFeature.logout();
            Utils.showHeading("Successfully logout");
            break;
          case 2:
            Utils.showHeading("Your parcels to deliver");
            shipperFeature.processShowShipments();
            break;
          default:
            Utils.showInvalidInput();
        }
      } else if(!shipperFeature.getIsLogin()) { //not login
        if(option == 1) {
          Utils.showHeading("Indentify shipper");
          shipperFeature.processLogin();
        } else {
          Utils.showInvalidInput();
        }
      }

      Utils.pressToContinue();
      Utils.clearScreen();

    }
  }

}
