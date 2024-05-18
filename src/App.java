//version 1
import java.util.Scanner;

import process.*;
import utils.Utils;

public class App {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    System.out.print("-> You are (0.customer | 1.manager | 2.shipper)?: ");
    int option = scanner.nextInt();

    switch(option) {
      case 0:
        ProcessCustomer.process(scanner);
        break;
      case 1:
        ProcessStaff.processMananger(scanner);
        break;
      case 2:
        ProcessStaff.processShipper(scanner);
        break;
      default:
        Utils.showInvalidInput();
    }

  }
}
