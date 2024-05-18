package staff;

public class FullTimeStaff extends Staff {
  
  //call funcs
  public FullTimeStaff(){}
  public FullTimeStaff(String id, String name, String email, String phone, double salary, double coefficient) {
    setInfo(id, name, email, phone, salary, coefficient);
  }

  //basic func
  public double finalSalary() {
    return getSalary() * getCoefficient();
  }

  @Override
  public void printInfo() {
    super.printInfo();
    System.out.println("Final salary: " + finalSalary());
  }

}
