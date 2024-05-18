package fileio.staff;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fileio.IO;

public class IOShipment extends IO {

  public static final int SHIPPER_ID=0, PURCHASE_ID=1;
  
  public IOShipment(){}
  public IOShipment(String filePath, String delimiter) {
    setFilePath(filePath);
    setDelimiter(delimiter);
  }
  public IOShipment(String filePath, String delimiter, boolean append) {
    setFilePath(filePath);
    setDelimiter(delimiter);
    setAppend(append);
  }

  public void store(String shipperID, String parcelID) {
    try(BufferedWriter writer = new BufferedWriter(new FileWriter(getFilePath(), getAppend()))) {
      writer.write(
        shipperID + getDelimiter() +
        parcelID + '\n'
      );

    } catch(IOException e) {
      e.printStackTrace();
    }
  }

  public List<String[]> query() {
    List<String[]> list = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(getFilePath()))) {
      String line;
      while((line=reader.readLine()) != null) {
        String[] data = line.split(getDelimiter());
        list.add(data);
      }

    } catch(Exception e) {
      e.printStackTrace();
    }

    return list;
  }

  public List<String[]> query(String shipperID) {
    List<String[]> list = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(getFilePath()))) {
      String line;
      while((line=reader.readLine()) != null) {
        String[] data = line.split(getDelimiter());
        if(data[SHIPPER_ID].equals(shipperID)) {
          list.add(data);
        }
      }

    } catch(Exception e) {
      e.printStackTrace();
    }

    return list;
  }


  public String queryShipperID(String parcelID) {
    try(BufferedReader reader = new BufferedReader(new FileReader(getFilePath()))) {
      String line;
      while((line=reader.readLine()) != null) {
        String[] data = line.split(getDelimiter());
        if(data[PURCHASE_ID].equals(parcelID)) {
          return data[SHIPPER_ID];
        }
      }

    } catch(IOException e) {
      e.printStackTrace();
    }

    return null;
  }


}
