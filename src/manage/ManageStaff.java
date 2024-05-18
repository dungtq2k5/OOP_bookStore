package manage;

import java.util.ArrayList;
import java.util.List;

import staff.Staff;

public class ManageStaff {
  
  private List<Staff> list = new ArrayList<>();


  //call funcs
  public ManageStaff(){}
  public ManageStaff(List<Staff> list) {
    setList(list);
  }

  //set&get
  public void setList(List<Staff> list) {
    this.list = list;
  }
  public List<Staff> getList() {
    return this.list;
  }

  public int getQuantity() {
    return getList().size();
  }

  public Staff getStaff(String id) {
    for(Staff staff : getList()) {
      if(staff.getID().equals(id)) {
        return staff;
      }
    }
    return null;
  }

  //CRUD
  public void add(Staff newStaff) {
    getList().add(newStaff);
  }
  public void add(Staff newStaff, int pos) {
    getList().add(pos, newStaff);
  }

  public boolean delete(String id) {
    for(Staff staff : getList()) {
      if(staff.getID().equals(id)) {
        getList().remove(staff);
        return true;
      }
    }
    return false;
  }

  public boolean update(String id) {
    for(Staff staff : getList()) {
      if(staff.getID().equals(id)) {
        staff.setInfo(true);
        return true;
      }
    }
    return false;
  }

  public void printList() {
    for(Staff staff : getList()) {
      System.out.println("--------");
      staff.printInfo();
    }
  }
  public void printList(List<Staff> list) {
    for(Staff staff : list) {
      System.out.println("--------");
      staff.printInfo();
    }
  }

}
