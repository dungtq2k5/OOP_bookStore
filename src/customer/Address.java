package customer;
public interface Address {

  //housenumber - street - ward - district - city/province
  int 
    HOUSE_NUMBER = 0,
    STREET = 1,
    WARD = 2,
    DISTRICT = 3,
    CITY_PROVINCE = 4;

  void setHouseNumber(String number);
  String getHouseNumber();

  void setStreet(String name);
  String getStreet();

  void setWard(String name);
  String getWard();

  void setDistrict(String name);
  String getDistrict();

  void setCityProvince(String name);
  String getCityProvince();

  void setAddress();
  void setAddress(String houseNumber, String street, String ward, String district, String cityProvince);
  String getAddress();

}
