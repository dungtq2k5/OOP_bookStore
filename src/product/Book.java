package product;
import java.util.Scanner;

public abstract class Book {

  private String id;
  private String name;
  private String author;
  private String producer;
  private String tenor;
  private double price;
  private int pages;
  
  private double ratings; //avg
  private int comments; //total

  //get&set
  public void setID(String id) {
    this.id = id;
  }
  public String getID() {
    return this.id;
  }

  public void setName(String name) {
    this.name = name;
  }
  public String getName() {
    return this.name;
  }

  public void setAuthor(String author) {
    this.author = author;
  }
  public String getAuthor() {
    return this.author;
  }
  
  public void setProducer(String producer) {
    this.producer = producer;
  }
  public String getProducer() {
    return this.producer;
  }

  public void setTenor(String tenor) {
    this.tenor = tenor;
  }
  public String getTenor() {
    return this.tenor;
  }

  public void setPrice(double price) {
    this.price = price;
  }
  public double getPrice() {
    return this.price;
  }

  public void setPages(int pages) {
    this.pages = pages;
  }
  public int getPages() {
    return this.pages;
  }
  
  public void setRatings(double number) {
    this.ratings = number;
  }
  public double getRatings() {
    return this.ratings;
  }

  public void setComments(int number) {
    this.comments = number;
  }
  public int getComments() {
    return this.comments;
  }


  //basic funcs
  public void setInfo() {
    @SuppressWarnings("resource")
    Scanner scanner = new Scanner(System.in);

    setID(utils.Utils.autoGenerateUUID());

    System.out.print("Name: ");
    setName(scanner.nextLine());

    System.out.print("Author: ");
    setAuthor(scanner.nextLine());

    System.out.print("Producer: ");
    setProducer(scanner.nextLine());

    System.out.print("Tenor: ");
    setTenor(scanner.nextLine());

    System.out.print("Price: ");
    setPrice(scanner.nextDouble());

    System.out.print("Pages: ");
    setPages(scanner.nextInt());
  }

  public void setInfo(String id, String name, String author, String producer, String tenor, double price, int pages, double ratings, int comments) {
    setID(id);
    setName(name);
    setAuthor(author);
    setProducer(producer);
    setTenor(tenor);
    setPrice(price);
    setPages(pages);
    setRatings(ratings);
    setComments(comments);
  }


  public void printInfo() {
    System.out.println("ID: " + getID());
    System.out.println("Book's name: " + getName());
    System.out.println("Author: " + getAuthor());
    System.out.println("Producer: " + getProducer());
    System.out.println("Tenor: \"" + getTenor() + "\"");
    System.out.println("Price: " + getPrice());
    System.out.println("Pages: " + getPages());
    System.out.println("Ratings: " + getRatings());
    System.out.println("Comments: " + getComments());
  }

} 