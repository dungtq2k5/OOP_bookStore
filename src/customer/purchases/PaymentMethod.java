package customer.purchases;

public interface PaymentMethod {

  String[] PAYMENT_METHODS = {"cash", "debit card", "credit card"};
  String[] EPAYMENT_METHODS = {"debit card", "credit card"};

  int 
    BY_CASH = 0,
    BY_DEBIT_CARD = 1,
    BY_CREDIT_CARD = 2
  ;

  void setPaymentMethod(int i);
  int getPaymentMethod();

}