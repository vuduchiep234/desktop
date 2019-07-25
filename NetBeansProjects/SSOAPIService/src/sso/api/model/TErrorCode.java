
package sso.api.model;

public enum TErrorCode {
  EC_OK(0),
  EC_SYSTEM(1),
  EC_PARAM_ERROR(2),
  EC_INEXISTENT_DATA(3),
  EC_CONNECTION_REFUSED(4);


  private final int value;

  private TErrorCode(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
  
  public static TErrorCode findByValue(int value) { 
    switch (value) {
      case 0:
        return EC_OK;
      case 1:
        return EC_SYSTEM;
      case 2:
        return EC_PARAM_ERROR;
      case 3:
        return EC_INEXISTENT_DATA;
      case 4:
        return EC_CONNECTION_REFUSED;
      default:
        return null;
    }
  }
}
