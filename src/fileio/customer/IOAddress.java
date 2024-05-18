package fileio.customer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fileio.IO;

public class IOAddress extends IO {
  
  public static final int CUSTOMER_ID=0, ADDRESS=1;

  //call funcs
  public IOAddress(){}
  public IOAddress(String path, String delimiter) {
    setFilePath(path);
    setDelimiter(delimiter);
  }
  public IOAddress(String path, String delimiter, boolean append) {
    setFilePath(path);
    setDelimiter(delimiter);
    setAppend(append);
  }

  //basic funcs
  public void store(String customer_id, String customer_address) {
    try(BufferedWriter writer = new BufferedWriter(new FileWriter(getFilePath(), getAppend()))) {
      writer.write(
        customer_id + getDelimiter() + 
        customer_address + '\n'
      );

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public List<String> query(String customer_id) {
    List<String> addresses = new ArrayList<>();

    try(BufferedReader reader = new BufferedReader(new FileReader(getFilePath()))) {

      String line;
      while((line = reader.readLine()) != null) {
        String[] data = line.split(getDelimiter());
        if(data[CUSTOMER_ID].equals(customer_id)) {
          addresses.add(data[ADDRESS]);
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
    
    return addresses;
  }
  
}
