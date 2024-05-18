package feature.customer;

import java.util.List;
import java.util.Scanner;

import fileio.customer.IOAddress;
import fileio.customer.IOCustomer;
import customer.Customer;
import utils.Utils;

public class CustomerFeature {
  
  private Scanner scanner = new Scanner(System.in);

  private boolean isLogin;
  private Customer customer;

  //call funcs
  public CustomerFeature(){}
  public CustomerFeature(Customer customer) {
    setCustomer(customer);
  }
  
  //set&get
  private void setIsLogin(boolean isLogin) {
    this.isLogin = isLogin;
  }
  public boolean getIsLogin() {
    return this.isLogin;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }
  public Customer getCustomer() {
    return this.customer;
  }


  //basic funcs
  public boolean login(String email, String phone) {
    IOCustomer ioCustomer = new IOCustomer(Utils.CUSTOMER_FILE, Utils.FILE_DEIMITER);
    List<Customer> customers = ioCustomer.query();

    for(Customer cus : customers) {
      if(cus.getEmail().equals(email) && cus.getPhone().equals(phone)) {
        setCustomer(cus);
        setIsLogin(true);
        return true;
      }
    }

    return false;
  }

  public boolean register(Customer newCustomer) {
    IOCustomer ioCustomer = new IOCustomer(Utils.CUSTOMER_FILE, Utils.FILE_DEIMITER);
    List<Customer> customers = ioCustomer.query();
    
    for(Customer cus : customers) {
      if(cus.getPhone().equals(newCustomer.getPhone()) || cus.getEmail().equals(newCustomer.getEmail())) {
        return false;
      }
    }
    
    IOAddress ioAddress = new IOAddress(Utils.ADDRESS_FILE, Utils.FILE_DEIMITER);
    ioAddress.store(newCustomer.getID(), newCustomer.getAddress());
    ioCustomer.store(newCustomer);
    setIsLogin(true);
    setCustomer(newCustomer);
    return true;
  }
  
  public void logout() {
    setCustomer(null);
    setIsLogin(false);
  }


  //process funcs
  public boolean processLogin() {
    Utils.showHeading("Login");
    System.out.print("Email: ");
    String email = scanner.next();
    System.out.print("Phone number: ");
    String phone = scanner.next();
    
    if(login(email, phone)) {
      Utils.showHeading("Successfully login");
      return true;
    }

    Utils.showWarning("Login fail");
    return processRegister();    
  }
  
  public boolean processRegister() {
    Utils.showHeading("Register");
    Customer newCustomer = new Customer();
    newCustomer.setInfo(true);

    if(register(newCustomer)) {
      Utils.showHeading("Successfully register");
      return true;
    }

    Utils.showWarning("Register fail");
    return processLogin();
  }
  
  public void processLoginRegister() {
    while(true) {
      System.out.print("\n-> 0.login | 1.register | 2.quit: ");
      int option = scanner.nextInt();
  
      if(option == 0) {
        processLogin();
        break;
      } else if(option == 1) {
        processRegister();
        break;
      } else {
        break;
      }
    }
  }
  
  public void processAddAddress() {
    IOAddress ioAddress = new IOAddress(Utils.ADDRESS_FILE, Utils.FILE_DEIMITER);

    getCustomer().setAddress();
    ioAddress.store(getCustomer().getID(), getCustomer().getAddress());
  }

}



