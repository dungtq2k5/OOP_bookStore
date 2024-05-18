package fileio.customer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fileio.IO;
import customer.Cart;

public class IOCart extends IO {
  
  public static final int CUSTOMER_ID=0, PRODUCT_ID=1, PRODUCT_QUANTITY=2;

  //calls funcs
  public IOCart(){}
  public IOCart(String filePath, String delimiter) {
    setFilePath(filePath);
    setDelimiter(delimiter);
  }
  public IOCart(String filePath, String delimiter, boolean append) {
    setFilePath(filePath);
    setDelimiter(delimiter);
    setAppend(append);
  }


  //basic funcs
  public void store(String customer_id, String product_id, int product_quantity) {
    try(BufferedWriter writer = new BufferedWriter(new FileWriter(getFilePath(), getAppend()))) {
      writer.write(
        customer_id + getDelimiter() +
        product_id + getDelimiter() + 
        Integer.toString(product_quantity) + '\n'
      );

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public List<Cart> query() {
    List<Cart> carts = new ArrayList<>();

    try(BufferedReader reader = new BufferedReader(new FileReader(getFilePath()))) {
      String line;
      while((line=reader.readLine()) != null) {
        String[] cart = line.split(getDelimiter());
        Cart cartFormatted = new Cart(cart[CUSTOMER_ID], cart[PRODUCT_ID], Integer.parseInt(cart[PRODUCT_QUANTITY]));
        carts.add(cartFormatted);
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

    return carts;
  }
  public List<Cart> query(String customer_id) {
    List<Cart> carts = new ArrayList<>();

    try(BufferedReader reader = new BufferedReader(new FileReader(getFilePath()))) {
      String line;
      while((line=reader.readLine()) != null) {
        String[] cart = line.split(getDelimiter());
        if(cart[CUSTOMER_ID].equals(customer_id)) {
          Cart cartFormatted = new Cart(cart[CUSTOMER_ID], cart[PRODUCT_ID], Integer.parseInt(cart[PRODUCT_QUANTITY]));
          carts.add(cartFormatted);
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

    return carts;
  }

  public void update(String customer_id, String product_id, Cart updateCart) {
    StringBuilder data = new StringBuilder();

    try(BufferedReader reader = new BufferedReader(new FileReader(getFilePath()))) { //read data
      String line;
      while((line=reader.readLine()) != null) {
        String[] cart = line.split(getDelimiter());
        if(cart[CUSTOMER_ID].equals(customer_id) && cart[PRODUCT_ID].equals(product_id)) { //update
          data.append(
            updateCart.getCustomerID() + getDelimiter() + 
            updateCart.getProductID() + getDelimiter() + 
            updateCart.getProductQuantity() + '\n'
          );
        } else {
          data.append(line + '\n');
        }
      }
      data.deleteCharAt(data.length()-1); //remove '\n' at last
    } catch (IOException e) {
      e.printStackTrace();
    }

    try(FileWriter writer = new FileWriter(getFilePath())) { //update
      writer.write(data.toString() + '\n');
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void delete(String customer_id, String product_id) {
    StringBuilder data = new StringBuilder();

    try(BufferedReader reader = new BufferedReader(new FileReader(getFilePath()))) {
      String line;
      while((line=reader.readLine()) != null) {
        String[] cart = line.split(getDelimiter());
        if(!cart[CUSTOMER_ID].equals(customer_id) || !cart[PRODUCT_ID].equals(product_id)) {
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
