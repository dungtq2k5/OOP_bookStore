package fileio.staff;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import staff.PartTimeStaff;

public class IOPartTimeStaff extends IOStaff {

  public static final int WORKHOURS=6;

  public IOPartTimeStaff(){}
  public IOPartTimeStaff(String filePath, String delimeter) {
    setFilePath(filePath);
    setDelimiter(delimeter);
  }
  public IOPartTimeStaff(String filePath, String delimeter, boolean append) {
    setFilePath(filePath);
    setDelimiter(delimeter);
    setAppend(append);
  }

  public void store(PartTimeStaff staff) {
    try(BufferedWriter writer = new BufferedWriter(new FileWriter(getFilePath(), getAppend()))) {
      writer.write(
        staff.getID() + getDelimiter() + 
        staff.getName() + getDelimiter() +
        staff.getEmail() + getDelimiter() + 
        staff.getPhone() + getDelimiter() + 
        String.valueOf(staff.getSalary()) + getDelimiter() + 
        String.valueOf(staff.getCoefficient()) + getDelimiter() +
        String.valueOf(staff.getWorkHours()) + '\n'
      );

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public List<PartTimeStaff> query() {
    List<PartTimeStaff> staffs = new ArrayList<>();

    try(BufferedReader reader = new BufferedReader(new FileReader(getFilePath()))) {
      String line;
      while((line=reader.readLine()) != null) {
        String[] staff = line.split(getDelimiter());
        PartTimeStaff staffFormatted = new PartTimeStaff(
          staff[ID],
          staff[NAME],
          staff[EMAIL],
          staff[PHONE],
          Double.parseDouble(staff[SALARY]),
          Double.parseDouble(staff[COEFFICIENT]),
          Double.parseDouble(staff[WORKHOURS])
        );

        staffs.add(staffFormatted);
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

    return staffs;
  }

}
