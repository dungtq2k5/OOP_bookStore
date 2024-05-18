package customer;
import java.util.Scanner;

import person.Person;

public class Customer extends Person implements Address {
  
  private String[] address = new String[5]; //housenumber - street - ward - district - city/province

  //set&get
  public void setHouseNumber(String number) {
    this.address[HOUSE_NUMBER] = number;
  }
  public String getHouseNumber() {
    return this.address[HOUSE_NUMBER];
  }
 
  public void setStreet(String name) {
    this.address[STREET] = name;
  }
  public String getStreet() {
    return this.address[STREET];
  }

  public void setWard(String name) {
    this.address[WARD] = name;
  }
  public String getWard() {
    return this.address[WARD];
  }
 
  public void setDistrict(String name) {
    this.address[DISTRICT] = name;
  }
  public String getDistrict() {
    return this.address[DISTRICT];
  }
 
  public void setCityProvince(String name) {
    this.address[CITY_PROVINCE] = name;
  }
  public String getCityProvince() {
    return this.address[CITY_PROVINCE];
  }
 
  public void setAddress() {
    @SuppressWarnings("resource")
    Scanner scanner = new Scanner(System.in);
    
    System.out.println("---- Set up address ----");
    System.out.print("House number: ");
    setHouseNumber(scanner.nextLine());
    System.out.print("Street: ");
    setStreet(scanner.nextLine());
    System.out.print("Ward: ");
    setWard(scanner.nextLine());
    System.out.print("District: ");
    setDistrict(scanner.nextLine());
    System.out.print("City/Province: ");
    setCityProvince(scanner.nextLine());
  }
  public void setAddress(String houseNumber, String street, String ward, String district, String cityProvince) {
    setHouseNumber(houseNumber);
    setStreet(street);
    setWard(ward);
    setDistrict(district);
    setCityProvince(cityProvince);
  }
  public String getAddress() {
    return getHouseNumber() + " " + getStreet() + ", " + getWard() + ", " + getDistrict() + ", " + getCityProvince();
  }

  //call funcs
  public Customer(){}
  public Customer(String id, String name, String email, String phone, String houseNumber, String street, String ward, String district, String cityProvince) {
    super.setInfo(id, name, email, phone);
    setAddress(houseNumber, street, ward, district, cityProvince);
  }

  //basic
  @Override
  public void setInfo(boolean autoID) {
    super.setInfo(autoID);
    setAddress();
  }
  
  @Override
  public void printInfo() {
    super.printInfo();
    System.out.println("Address: " + getAddress());
  }

}
