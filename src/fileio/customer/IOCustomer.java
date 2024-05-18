package fileio.customer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import customer.Address;
import customer.Customer;
import fileio.IO;

public class IOCustomer extends IO {
  
  public static final int ID=0, NAME=1, EMAIL=2, PHONE_NUMBER=3, DEFAULT_ADDRESS=4;

  //call funcs
  public IOCustomer(){}
  public IOCustomer(String filePath, String delimiter) {
    setDelimiter(delimiter);
    setFilePath(filePath);
  }
  public IOCustomer(String filePath, String delimiter, boolean append) {
    setFilePath(filePath);
    setDelimiter(delimiter);
    setAppend(append);
  }

  //write 
  public void store(Customer customer) {
    try(BufferedWriter writer = new BufferedWriter(new FileWriter(getFilePath(), getAppend()))) {
      //id # name # email # phone_number # default_address
      writer.write(customer.getID() + getDelimiter() + 
        customer.getName() + getDelimiter() + 
        customer.getEmail() + getDelimiter() + 
        customer.getPhone() + getDelimiter() + 
        customer.getAddress() + '\n'
      );

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //read
  public List<Customer> query() {
    List<Customer> customers = new ArrayList<>();

    try(BufferedReader reader = new BufferedReader(new FileReader(getFilePath()))) {

      String line;
      while((line=reader.readLine()) != null) {
        String[] customer = line.split(getDelimiter());
        Customer customerFormatted = new Customer(
          customer[ID], customer[NAME], customer[EMAIL], customer[PHONE_NUMBER], 
          customer[Address.HOUSE_NUMBER], customer[Address.STREET], customer[Address.WARD], 
          customer[Address.DISTRICT], customer[Address.CITY_PROVINCE]);

        customers.add(customerFormatted);
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

    return customers;
  }

  //delete
  public void delete(String id) {
    StringBuilder data = new StringBuilder();

    try(BufferedReader reader = new BufferedReader(new FileReader(getFilePath()))) {
      String line;
      while((line=reader.readLine()) != null) {
        String[] customer = line.split(getDelimiter());
        if(!customer[ID].equals(id)) { //not match->append
          data.append(line + '\n');
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

    try(FileWriter writer = new FileWriter(getFilePath())) {
      if(data.isEmpty()) { //case data is null
        writer.write(data.toString());
      } else {
        data.deleteCharAt(data.length() - 1);
        writer.write(data.toString() + '\n');
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  

}
