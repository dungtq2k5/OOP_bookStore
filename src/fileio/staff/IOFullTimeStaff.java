package fileio.staff;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import staff.FullTimeStaff;

public class IOFullTimeStaff extends IOStaff {
  
  public IOFullTimeStaff(){}
  public IOFullTimeStaff(String filePath, String delimeter) {
    setFilePath(filePath);
    setDelimiter(delimeter);
  }
  public IOFullTimeStaff(String filePath, String delimeter, boolean append) {
    setFilePath(filePath);
    setDelimiter(delimeter);
    setAppend(append);
  }

  public void store(FullTimeStaff staff) {
    try(BufferedWriter writer = new BufferedWriter(new FileWriter(getFilePath(), getAppend()))) {
      writer.write(
        staff.getID() + getDelimiter() + 
        staff.getName() + getDelimiter() +
        staff.getEmail() + getDelimiter() + 
        staff.getPhone() + getDelimiter() + 
        String.valueOf(staff.getSalary()) + getDelimiter() + 
        String.valueOf(staff.getCoefficient()) + getDelimiter() + '\n'
      );

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public List<FullTimeStaff> query() {
    List<FullTimeStaff> staffs = new ArrayList<>();

    try(BufferedReader reader = new BufferedReader(new FileReader(getFilePath()))) {
      String line;
      while((line=reader.readLine()) != null) {
        String[] staff = line.split(getDelimiter());
        FullTimeStaff staffFormatted = new FullTimeStaff(
          staff[ID],
          staff[NAME],
          staff[EMAIL],
          staff[PHONE],
          Double.parseDouble(staff[SALARY]),
          Double.parseDouble(staff[COEFFICIENT])
        );

        staffs.add(staffFormatted);
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

    return staffs;
  }

}
