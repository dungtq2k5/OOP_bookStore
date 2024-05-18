package feature.staff;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import customer.purchases.ProductState;
import customer.purchases.Purchases;
import fileio.customer.IOPurchases;
import fileio.staff.IOPartTimeStaff;
import fileio.staff.IOShipment;
import staff.PartTimeStaff;
import utils.Utils;

public class ShipperFeature {

  private Scanner scanner = new Scanner(System.in);
  private boolean isLogin;
  private PartTimeStaff shipper;

  //call
  public ShipperFeature(){}
  public ShipperFeature(PartTimeStaff shipper){
    setShipper(shipper);
  }


  //basic
  public void setShipper(PartTimeStaff shipper) {
    this.shipper = shipper;
  }
  public PartTimeStaff getShipper() {
    return this.shipper;
  }

  private void setIsLogin(boolean isLogin) {
    this.isLogin = isLogin;
  }
  public boolean getIsLogin() {
    return this.isLogin;
  }

  public boolean login(String email, String phone) {
    IOPartTimeStaff ioPartTimeStaff = new IOPartTimeStaff(Utils.PART_TIME_STAFF_FILE, Utils.FILE_DEIMITER);
    List<PartTimeStaff> staffs = ioPartTimeStaff.query();
    for(PartTimeStaff staff : staffs) {
      if(staff.getEmail().equals(email) && staff.getPhone().equals(phone)) {
        setShipper(staff);
        setIsLogin(true);
        return true;
      }
    }

    return false;
  }

  public void logout() {
    setShipper(null);
    setIsLogin(false);
  }

  public void updateParcelState(List<Purchases> parcels) {
    while(true) {
      System.out.print("\n-> 0.select | 1.select range | 2.quit: ");
      int option = scanner.nextInt();

      if(option == 0) {
        System.out.print("-> Parcel ordinal: ");
        Purchases parcel = parcels.get(scanner.nextInt()-1);
        System.out.print("-> State (0.to ship | 1.to receive | 2.complete)?: ");
        int parcelState = scanner.nextInt() + 1;

        IOPurchases ioPurchases = new IOPurchases(Utils.PURCHASES_FILE, Utils.FILE_DEIMITER);
        ioPurchases.updateProductState(parcel.getID(), parcelState);

        Utils.showHeading("Successfully updated");
        Utils.pressToContinue("Press to confirm");
        break;

      } else if(option == 1) {
        System.out.print("-> Parcel ordinal from <space> to: ");
        List<Purchases> parcelList = parcels.subList(scanner.nextInt()-1, scanner.nextInt());
        System.out.print("-> State (0.to ship | 1.to receive | 2.complete)?: ");
        int parcelState = scanner.nextInt() + 1;

        for(Purchases parcel : parcelList) {
          IOPurchases ioPurchases = new IOPurchases(Utils.PURCHASES_FILE, Utils.FILE_DEIMITER);
          ioPurchases.updateProductState(parcel.getID(), parcelState);
        }

        Utils.showHeading("Successfully updated");
        Utils.pressToContinue("Press to confirm");
        break;

      } else {
        break;
      }

    }
  }

  public List<Purchases> showParcel() {
    IOShipment ioShipment = new IOShipment(Utils.SHIPMENT_FILE, Utils.FILE_DEIMITER);
    List<String[]> shipments =  ioShipment.query(getShipper().getID());

    IOPurchases ioPurchases = new IOPurchases(Utils.PURCHASES_FILE, Utils.FILE_DEIMITER);
    List<Purchases> parcels = new ArrayList<>();
    int ordinal = 0;
    for(String[] shipment : shipments) {
      Purchases parcel = ioPurchases.queryID(shipment[IOShipment.PURCHASE_ID]);
      
      if(parcel.getState()!=ProductState.TO_RATE && parcel.getState()!=ProductState.COMPLETED) {
        ordinal++;
        System.out.println("- Parcel ordinal: " + ordinal);
        parcel.printInfo();
        System.out.println("----\n");

        parcels.add(parcel);
      }

    }

    return parcels;
  }


  //process
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

  public void processShowShipments() {
    updateParcelState(showParcel());
  }

}
