package fileio;

public abstract class IO {

  private String delimiter;
  private String filePath;
  private boolean append = true;

  //set&get
  public void setDelimiter(String delimiter) {
    this.delimiter = delimiter;
  }
  public String getDelimiter() {
    return this.delimiter;
  }

  public void setFilePath(String path) {
    this.filePath = path;
  }
  public String getFilePath() {
    return this.filePath;
  }

  public void setAppend(boolean append) {
    this.append = append;
  }
  public boolean getAppend() {
    return this.append;
  }
  
}
