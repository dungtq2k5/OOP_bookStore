package staff;

import java.util.Scanner;

public class PartTimeStaff extends Staff {
  
  private double workHours;

  //set&get
  public void setWorkHours(double hours) {
    this.workHours = hours;
  }
  public double getWorkHours() {
    return this.workHours;
  }

  //call funcs
  public PartTimeStaff(){}
  public PartTimeStaff(String id, String name, String email, String phone, double salary, double coefficient, double workHours) {
    setInfo(id, name, email, phone, salary, coefficient, workHours);
  }

  //basic funcs
  public double finalSalary() {
    return getSalary() * getCoefficient() * getWorkHours();
  }

  @Override
  public void setInfo(boolean autoID) {
    @SuppressWarnings("resource")
    Scanner scanner = new Scanner(System.in);

    super.setInfo(autoID);
    System.out.print("Work hours: ");
    setWorkHours(scanner.nextDouble());
  }
  public void setInfo(String id, String name, String email, String phone, double salary, double coefficient, double workHours) {
    super.setInfo(id, name, email, phone, salary, coefficient);
    setWorkHours(workHours);
  }

  @Override
  public void printInfo() {
    super.printInfo();
    System.out.println("Work hours: " + getWorkHours());
    System.out.println("Final salary: " + finalSalary());
  }

}
