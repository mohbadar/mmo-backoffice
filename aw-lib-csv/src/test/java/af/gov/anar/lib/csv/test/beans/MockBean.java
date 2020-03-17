package af.gov.anar.lib.csv.test.beans;


public class MockBean {
  private String name;
  private String id;
  private String orderNumber;
  private int num;
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getOrderNumber() {
    return orderNumber;
  }
  public void setOrderNumber(String orderNumber) {
    this.orderNumber = orderNumber;
  }
  public int getNum() {
    return num;
  }
  public void setNum(int num) {
    this.num = num;
  }
  
  @Override
  public String toString() {
    return String.format("[MockBean id: %s; name: %s; orderNumber: %s; num: %d]", 
        id, name, orderNumber, num);
  }
}