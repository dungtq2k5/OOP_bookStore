package fileio.product;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import fileio.IO;
import product.rating.Rating;

public class IORating extends IO {

  public static final int RATING_AVG=0, TOTAL_COMMENT=1;
  public static final int CUSTOMER_ID=0, PRODUCT_ID=1, RATING=2, COMMENT=3;
  
  //call funcs
  public IORating(){}
  public IORating(String filePath, String delimiter) {
    setFilePath(filePath);
    setDelimiter(delimiter);
  }
  public IORating(String filePath, String delimiter, boolean append) {
    setFilePath(filePath);
    setDelimiter(delimiter);
    setAppend(append);
  }

  //basic funcs
  public void store(String buyerID, String productID, int rating, String comment) {
    try(BufferedWriter writer = new BufferedWriter(new FileWriter(getFilePath(), getAppend()))) {
      writer.write(
        buyerID + getDelimiter() +
        productID + getDelimiter() +
        String.valueOf(rating) + getDelimiter() + 
        comment + '\n'
      );

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public double getRatings(String productID) { //average rating
    double avg=0;

    try (BufferedReader reader = new BufferedReader(new FileReader(getFilePath()))) {
      double sum=0, count=0;
      String line;
      while((line=reader.readLine()) != null) {
        String[] rating = line.split(getDelimiter());
        if(rating[PRODUCT_ID].equals(productID)) {
          sum += Rating.RATING_LEVEL[Integer.parseInt(rating[RATING])];
          count++;
        }
      }

      if(count != 0) { //case there's no rating yet
        avg = Double.parseDouble(String.format("%.1f", sum/count));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return avg;
  }

  public int getComments(String productID) {
    int sum=0;

    try (BufferedReader reader = new BufferedReader(new FileReader(getFilePath()))) {
      String line;
      while((line=reader.readLine()) != null) {
        String[] rating = line.split(getDelimiter());
        if(rating[PRODUCT_ID].equals(productID)) {
          sum++;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return sum;
  }

}
