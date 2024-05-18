package fileio.customer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import customer.purchases.ProductState;
import customer.purchases.Purchases;
import fileio.IO;
import product.Book;

public class IOPurchases extends IO {
 
  public static final int ID=0, BUYER_ID=1, PRODUCT_ID=2, PRODUCT_QUANTITY=3, DELIVERY_ADDRESS=4, TOTAL=5, PAYMENT_METHOD=6, PRODUCT_STATE=7;

  //call funcs
  public IOPurchases(){}
  public IOPurchases(String filePath, String delimiter) {
    setFilePath(filePath);
    setDelimiter(delimiter);
  }
  public IOPurchases(String filePath, String delimiter, boolean append) {
    setFilePath(filePath);
    setDelimiter(delimiter);
    setAppend(append);
  }

  //basic funcs
  public void store(String buyerID, Book product, int productQuantity, String deliveryAddress, int paymentMethod) {
    try(BufferedWriter writer = new BufferedWriter(new FileWriter(getFilePath(), getAppend()))) {
      writer.write(
        utils.Utils.autoGenerateUUID() + getDelimiter() +
        buyerID + getDelimiter() +
        product.getID() + getDelimiter() +
        productQuantity + getDelimiter() +
        deliveryAddress + getDelimiter() +
        String.valueOf(product.getPrice()*productQuantity) + getDelimiter() +
        String.valueOf(paymentMethod) + getDelimiter() +
        String.valueOf(ProductState.TO_PAY) + '\n' //default state when order
      ); 

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public List<Purchases> query() {
    List<Purchases> purchases = new ArrayList<>();

    try(BufferedReader reader = new BufferedReader(new FileReader(getFilePath()))) {
      String line;
      while((line=reader.readLine()) != null) {
        String[] purchase = line.split(getDelimiter());
        Purchases purchaseFormatted = new Purchases(
          purchase[ID],
          purchase[BUYER_ID], 
          purchase[PRODUCT_ID], 
          Integer.parseInt(purchase[PRODUCT_QUANTITY]),
          purchase[DELIVERY_ADDRESS], 
          Double.parseDouble(purchase[TOTAL]),
          Integer.parseInt(purchase[PAYMENT_METHOD]), 
          Integer.parseInt(purchase[PRODUCT_STATE])
        );

        purchases.add(purchaseFormatted);
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

    return purchases;
  }
  public List<Purchases> query(String buyerID) {
    List<Purchases> purchases = new ArrayList<>();

    try(BufferedReader reader = new BufferedReader(new FileReader(getFilePath()))) {
      String line;
      while((line=reader.readLine()) != null) {
        String[] purchase = line.split(getDelimiter());
        if(purchase[BUYER_ID].equals(buyerID)) {
          Purchases purchaseFormatted = new Purchases(
            purchase[ID],       
            purchase[BUYER_ID], 
            purchase[PRODUCT_ID], 
            Integer.parseInt(purchase[PRODUCT_QUANTITY]),
            purchase[DELIVERY_ADDRESS], 
            Double.parseDouble(purchase[TOTAL]), 
            Integer.parseInt(purchase[PAYMENT_METHOD]), 
            Integer.parseInt(purchase[PRODUCT_STATE])
          );

          purchases.add(purchaseFormatted);
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

    return purchases;
  }
  public Purchases queryID(String id) {
    try(BufferedReader reader = new BufferedReader(new FileReader(getFilePath()))) {
      String line;
      while((line=reader.readLine()) != null) {
        String[] purchase = line.split(getDelimiter());
        if(purchase[ID].equals(id)) {
          return new Purchases(
            purchase[ID],       
            purchase[BUYER_ID], 
            purchase[PRODUCT_ID], 
            Integer.parseInt(purchase[PRODUCT_QUANTITY]),
            purchase[DELIVERY_ADDRESS], 
            Double.parseDouble(purchase[TOTAL]), 
            Integer.parseInt(purchase[PAYMENT_METHOD]), 
            Integer.parseInt(purchase[PRODUCT_STATE])
          );
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }

  public void updateProductState(String id, int productState) { //funcs for staff
    StringBuilder data = new StringBuilder();

    try(BufferedReader reader = new BufferedReader(new FileReader(getFilePath()))) {
      String line;
      while((line=reader.readLine()) != null) {
        String[] purchase = line.split(getDelimiter());
        if(purchase[ID].equals(id)) { //update
          purchase[PRODUCT_STATE] = String.valueOf(productState);
          data.append(
            purchase[ID] + getDelimiter() +
            purchase[BUYER_ID] + getDelimiter() + 
            purchase[PRODUCT_ID] + getDelimiter() +
            purchase[PRODUCT_QUANTITY] + getDelimiter() +
            purchase[DELIVERY_ADDRESS] + getDelimiter() + 
            purchase[TOTAL] + getDelimiter() +
            purchase[PAYMENT_METHOD] + getDelimiter() +
            purchase[PRODUCT_STATE]
          ).append('\n');
        } else {
          data.append(line + '\n');
        }
      }
      
      data.deleteCharAt(data.length()-1);
    } catch (IOException e) {
      e.printStackTrace();
    }

    try(FileWriter writer = new FileWriter(getFilePath())) {
      writer.write(data.toString() + '\n');
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void delete(String buyerID, String productID) {
    StringBuilder data = new StringBuilder();

    try(BufferedReader reader = new BufferedReader(new FileReader(getFilePath()))) {
      String line;
      while((line = reader.readLine()) != null) {
        String[] purchase = line.split(getDelimiter());
        if(!purchase[BUYER_ID].equals(buyerID) || !purchase[PRODUCT_ID].equals(productID)) {
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
        data.deleteCharAt(data.length()-1);
        writer.write(data.toString() + '\n');
      }
    } catch(IOException e) {
      e.printStackTrace();
    }
  }

}
