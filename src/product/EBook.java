package product;
import java.util.Scanner;

public class EBook extends Book {

  private boolean PDF;
  private boolean EPUB;
  private double size; //KB, MB,...

  //call funcs
  public EBook() {}
  public EBook(String id, String name, String author, String producer, String tenor, double price, int pages, double ratings, int comments, boolean PDF, boolean EPUB, double size) {
    super.setInfo(id, name, author, producer, tenor, price, pages, ratings, comments);
  
    setType(PDF, EPUB);
    setSize(size);
  }
    

  //get&set
  public void setPDF(boolean isPDF) {
    this.PDF = isPDF;
  }
  public boolean getPDF() {
    return this.PDF;
  }

  public void setEPUB(boolean isEPUB) {
    this.EPUB = isEPUB;
  }
  public boolean getEPUB() {
    return this.EPUB;
  }

  public void setType(boolean PDF, boolean EPUB) {
    setPDF(PDF);
    setEPUB(EPUB);
  }
  public String getType() {
    return "PDF: " + getPDF() + '\n' + "EPUB: " + getEPUB();
  }

  public void setSize(double size) {
    this.size = size;
  }
  public double getSize() {
    return this.size;
  }


  //basic funcs
  @Override
  public void setInfo() { 
    @SuppressWarnings("resource")
    Scanner scanner = new Scanner(System.in);
    
    super.setInfo();

    System.out.print("PDF (true/false)?: ");
    setPDF(scanner.nextBoolean());
    System.out.print("EPUB (true/false)?: ");
    setEPUB(scanner.nextBoolean());
    System.out.print("Size: ");
    setSize(scanner.nextDouble());
  }
  
  @Override
  public void printInfo() {
    super.printInfo();

    System.out.println("PDF: " + getPDF());
    System.out.println("EPUB: " + getEPUB());
    System.out.println("Size: " + getSize());
  }

}
