package customer.purchases;

public interface ProductState {

  String[] STATES = {"To Pay", "To ship", "To Receive", "To rate", "Completed"};
  int 
    TO_PAY = 0, 
    TO_SHIP = 1, 
    TO_RECEIVE = 2, 
    TO_RATE = 3,
    COMPLETED = 4
  ;
  
  void setState(int i);
  int getState();

} 
