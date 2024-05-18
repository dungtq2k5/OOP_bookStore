package feature.staff;

import fileio.customer.IOPurchases;
import fileio.product.IOEBook;
import fileio.product.IOPaperBook;
import fileio.staff.IOFullTimeStaff;
import fileio.staff.IOPartTimeStaff;
import fileio.staff.IOShipment;
import product.EBook;
import product.PaperBook;
import staff.FullTimeStaff;
import staff.PartTimeStaff;

import java.util.List;
import java.util.Scanner;

import customer.purchases.*;

import utils.Utils;

public class ManagerFeature {
  
  private Scanner scanner = new Scanner(System.in);
  private boolean isLogin;
  private FullTimeStaff manager;

  public ManagerFeature(){}


  //basic funcs
  private void setManager(FullTimeStaff staff) {
    this.manager = staff;
  }
  public FullTimeStaff getManager() {
    return this.manager;
  }

  private void setIsLogin(boolean isLogin) {
    this.isLogin = isLogin;
  }
  public boolean getIsLogin() {
    return this.isLogin;
  }

  public boolean login(String email, String phone) {
    IOFullTimeStaff ioFullTimeStaff = new IOFullTimeStaff(Utils.FULL_TIME_STAFF_FILE, Utils.FILE_DEIMITER);
    List<FullTimeStaff> staffs = ioFullTimeStaff.query();

    for(FullTimeStaff staff : staffs) {
      if(staff.getEmail().equals(email) && staff.getPhone().equals(phone)) {
        setManager(staff);
        setIsLogin(true);
        return true;
      }
    }

    return false;
  }

  public void logout() {
    setManager(null);
    setIsLogin(false);
  }

  public int showAvailableShipper() {
    IOPartTimeStaff ioPartTimeStaff = new IOPartTimeStaff(Utils.PART_TIME_STAFF_FILE, Utils.FILE_DEIMITER);
    List<PartTimeStaff> shippers =  ioPartTimeStaff.query();
    for(PartTimeStaff shipper : shippers) {
      System.out.println("ID: " + shipper.getID());
      System.out.println("Name: " + shipper.getName());
      System.out.println("Phone: " + shipper.getPhone());
      System.out.println("----\n");
    }

    return shippers.size();
  }

  public void handOverOder(Purchases order) { //change product' state to 'to ship'
    while(true) {
      System.out.print("\n-> 0.show shipper info | 1.hand over | 2.quit: ");
      int option = scanner.nextInt();
      
      if(option == 0) { //show shipper info->input shipper id->hander over
        System.out.printf("%n---- %d shippers available to handover ----%n", showAvailableShipper());
        Utils.pressToContinue("Press to confirm");
        Utils.clearScreen();

      } else if(option == 1) { //hand over
        IOPurchases ioPurchases = new IOPurchases(Utils.PURCHASES_FILE, Utils.FILE_DEIMITER);
        IOShipment ioShipment = new IOShipment(Utils.SHIPMENT_FILE, Utils.FILE_DEIMITER);

        System.out.print("-> Shipper's id to handover: ");
        String shipperID = scanner.next();

        ioPurchases.updateProductState(order.getID(), ProductState.TO_SHIP);
        ioShipment.store(shipperID, order.getID());

        Utils.showHeading("Successfully handover");
        Utils.pressToContinue("Press to confirm");
        break;

      } else {
        break;
      }
    }
  }
  public void handOverOder(List<Purchases> orders) {
    while(true) {
      System.out.print("\n-> 0.show shipper info | 1.hand over | 2.quit: ");
      int option = scanner.nextInt();
      
      if(option == 0) { //show shipper info->input shipper id->hander over
        System.out.printf("%n---- %d shippers available for handover ----%n", showAvailableShipper());
        Utils.pressToContinue("Press to confirm");
        Utils.clearScreen();

      } else if(option == 1) { //hand over
        IOShipment ioShipment = new IOShipment(Utils.SHIPMENT_FILE, Utils.FILE_DEIMITER);
        IOPurchases ioPurchases = new IOPurchases(Utils.PURCHASES_FILE, Utils.FILE_DEIMITER);

        System.out.print("-> Shipper's id to handover: ");
        String shipperID = scanner.next();
        
        for(Purchases order : orders) {
          ioPurchases.updateProductState(order.getID(), ProductState.TO_SHIP);
          ioShipment.store(shipperID, order.getID());
        }

        Utils.showHeading("Successfully handover");
        Utils.pressToContinue("Press to confirm");
        Utils.clearScreen();
        break;

      } else {
        break;
      }
    }
  }

  public void selectOrders(List<Purchases> orders) {
    while(true) {
      System.out.print("\n-> 0.select | 1.select range | 2.quit: ");
      int option = scanner.nextInt();

      if(option == 0) { //select
        System.out.print("-> Order ordinal: ");
        Purchases order = orders.get(scanner.nextInt()-1);
        handOverOder(order);
        break;

      } else if(option == 1) { //select between
        System.out.print("-> Order ordinal from <space> to: ");
        List<Purchases> list = orders.subList(scanner.nextInt()-1, scanner.nextInt());
        handOverOder(list);
        break;

      } else {
        break;
      }
    }
  }

  public List<Purchases> browsingOrders() {
    IOPurchases ioPurchases = new IOPurchases(Utils.PURCHASES_FILE, Utils.FILE_DEIMITER);
    List<Purchases> orders = ioPurchases.query();

    int ordinal = 0;
    for(Purchases order : orders) {
      if(order.getState() == ProductState.TO_PAY) {
        ordinal++;
        System.out.println("\n-> Ordinal: " + ordinal);
        order.printInfo();
      }
    }

    return orders;
  }

  public void showParcelState(int state) {
    IOShipment ioShipment = new IOShipment(Utils.SHIPMENT_FILE, Utils.FILE_DEIMITER);
    List<String[]> shipments = ioShipment.query();

    IOPurchases ioPurchases = new IOPurchases(Utils.PURCHASES_FILE, Utils.FILE_DEIMITER);

    for(String[] shipment : shipments) {
      Purchases parcel = ioPurchases.queryID(shipment[IOShipment.PURCHASE_ID]);

      if(parcel.getState() == state) {
        parcel.printInfo();
        System.out.println("Shipper's id: " + shipment[IOShipment.SHIPPER_ID]);
        System.out.println("----\n");
      }
    }

  }


  //process funcs
  public void processLogin() {
    System.out.print("-> Email: ");
    String email = scanner.next();
    System.out.print("-> Phone: ");
    String phone = scanner.next();

    if(login(email, phone)) {
      Utils.showHeading("Authenticated");
    } else {
      Utils.showHeading("Unauthenticated");
    }
  }

  public void processShowOrders() {
    selectOrders(browsingOrders());
  }

  public void processTrackParcel() {
    System.out.print("-> Track (0.on ship | 1.on receive | 2.complete)?: ");
    int option = scanner.nextInt();

    switch(option) {
      case 0:
        Utils.showHeading("On shipping");
        showParcelState(ProductState.TO_SHIP); 
        break;
      case 1:
        Utils.showHeading("On receiving");
        showParcelState(ProductState.TO_RECEIVE);
        break;
      default:
        Utils.showHeading("Completed");
        showParcelState(ProductState.TO_RATE);
    }
  }

  public void processAddProduct() {
    System.out.print("->Add (0.Book | 1.EBook)?: ");
    int option = scanner.nextInt();

    switch(option) {
      case 0:
        IOPaperBook ioPaperBook = new IOPaperBook(Utils.PAPER_BOOK_FILE, Utils.FILE_DEIMITER);
        PaperBook newBook = new PaperBook();
        newBook.setInfo();
        ioPaperBook.store(newBook);
        Utils.showHeading("Successfully added");
        break;
      case 1:
        IOEBook ioeBook = new IOEBook(Utils.EBOOK_FILE, Utils.FILE_DEIMITER);
        EBook newEBook = new EBook();
        newEBook.setInfo();
        ioeBook.store(newEBook);
        Utils.showHeading("Successfully added");
        break;
      default:
        Utils.showInvalidInput();
        break;
    }
  }

}
