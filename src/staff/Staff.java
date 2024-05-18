package staff;

import java.util.Scanner;

import person.Person;

public abstract class Staff extends Person {
  
  private double salary;
  private double coefficient=1;

  //set&get
  public void setSalary(double salary) {
    this.salary = salary;
  }
  public double getSalary() {
    return this.salary;
  }

  public void setCoefficient(double number) {
    this.coefficient = number;
  }
  public double getCoefficient() {
    return this.coefficient;
  }

  //basic funcs
  public abstract double finalSalary();
  
  @Override
  public void setInfo(boolean autoID) {
    @SuppressWarnings("resource")
    Scanner scanner = new Scanner(System.in);

    super.setInfo(autoID);

    System.out.print("Salary: ");
    setSalary(scanner.nextDouble());
    System.out.print("Coefficient: ");
    setCoefficient(scanner.nextDouble());
  }
  
  public void setInfo(String id, String name, String email, String phone, double salary, double coefficient) {
    super.setInfo(id, name, email, phone);
    setSalary(salary);
    setCoefficient(coefficient);
  }

  @Override
  public void printInfo() {
    super.printInfo();
    System.out.println("Salary: " + getSalary());
    System.out.println("Coefficient: " + getCoefficient());
  }

}
