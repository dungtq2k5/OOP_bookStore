package manage;
import java.util.ArrayList;
import java.util.List;

import customer.Customer;

public class ManageCustomer {
  
  List<Customer> list = new ArrayList<>();

  //call funcs
  public ManageCustomer(){}
  public ManageCustomer(List<Customer> list) {
    setList(list);
  }
  

  //set&get
  public void setList(List<Customer> list) {
    this.list = list;
  }
  public List<Customer> getList() {
    return this.list;
  }

  public int getQuantity() {
    return getList().size();
  }

  public Customer getCustomer(String id) {
    for(Customer customer : getList()) {
      if(customer.getID().equals(id)) {
        return customer;
      }
    }
    return null;
  }


  //CRUD
  public void add(Customer customer) {
    getList().add(customer);
  }
  public void add(Customer customer, int pos) {
    getList().add(pos, customer);
  }

  public boolean delete(String id) {
    for(Customer customer : getList()) {
      if(customer.getID().equals(id)) {
        getList().remove(customer);
        return true;
      }
    }

    return false;
  }

  public boolean update(String id) {
    for(Customer customer : getList()) {
      if(customer.getID().equals(id)) {
        customer.setInfo(true);
        return true;
      }
    }

    return false;
  }

  public void printInfo() {
    for(Customer customer : getList()) {
      System.out.println("--------");
      customer.printInfo();
    }
  }
  public void printInfo(List<Customer> list) {
    for(Customer customer : list) {
      System.out.println("--------");
      customer.printInfo();
    }
  }

}
