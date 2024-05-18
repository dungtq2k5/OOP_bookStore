package person;

import java.util.Scanner;

public abstract class Person {
  
  private String id;
  private String name;
  private String email;
  private String phone;

  //set&get
  public void setID(String id) {
    this.id = id;
  }
  public String getID() {
    return this.id;
  }

  public void setName(String name) {
    this.name = name;
  }
  public String getName() {
    return this.name;
  }

  public void setEmail(String email) {
    this.email = email;
  }
  public String getEmail() {
    return this.email;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }
  public String getPhone() {
    return this.phone;
  }

  //basic
  public void setInfo(boolean autoID) {
    @SuppressWarnings("resource")
    Scanner scanner = new Scanner(System.in);

    if(autoID) {
      setID(utils.Utils.autoGenerateUUID());
    } else {
      System.out.print("ID: ");
      setID(scanner.nextLine());
    }

    System.out.print("Name: ");
    setName(scanner.nextLine());
    System.out.print("Email: ");
    setEmail(scanner.next());
    System.out.print("Phone number: ");
    setPhone(scanner.next());
  }
  
  public void setInfo(String id, String name, String email, String phone) {
    setID(id);
    setName(name);
    setEmail(email);
    setPhone(phone);
  }
  
  public void printInfo() {
    System.out.println("ID: " + getID());
    System.out.println("Name: " + getName());
    System.out.println("Email: " + getEmail());
    System.out.println("Phone number: " + getPhone());
  }
  
}
