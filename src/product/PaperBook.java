package product;
import java.util.Scanner;

public class PaperBook extends Book {
  
  public static final int WIDTH=0, HEIGHT=1;

  private double[] size = new double[2]; //0->width, 1->height
  private String coverType;
  private int stock;


  //call funcs
  public PaperBook() {}
  public PaperBook(String id, String name, String author, String producer, String tenor, double price, int pages, double ratings, int comments, double width, double height, int stock, String coverType) {
    super.setInfo(id, name, author, producer, tenor, price, pages, ratings, comments);

    setSize(width, height);
    setStock(stock);
    setConverType(coverType);
  }


  //get&set
  public void setSize(double width, double height) {
    this.size[WIDTH] = width;
    this.size[HEIGHT] = height;
  }
  public double[] getSize() {
    return this.size;
  }

  public void setConverType(String type) {
    this.coverType = type;
  }
  public String getCoverType() {
    return this.coverType;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }
  public int getStock() {
    return this.stock;
  }

  //basic funcs
  @Override
  public void setInfo() {
    @SuppressWarnings("resource")
    Scanner scanner = new Scanner(System.in);

    super.setInfo();

    System.out.print("Width <space> Height: ");
    setSize(Double.parseDouble(scanner.next()), Double.parseDouble(scanner.next()));

    scanner.nextLine();
    System.out.print("Conver type: ");
    setConverType(scanner.nextLine());
    
    System.out.print("Stock: ");
    setStock(scanner.nextInt());
  }

  @Override
  public void printInfo() {
    super.printInfo();

    System.out.println("Size: " + getSize()[WIDTH] + " x " + getSize()[HEIGHT]);
    System.out.println("Conver type: " + getCoverType());
    System.out.println("Stock: " + getStock());
  }

}
