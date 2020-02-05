package ohtu.util;

import java.util.ArrayList;
import java.util.List;

public class CreationStatus {
  private List<String> errors;

  public CreationStatus() {
    this.errors = new ArrayList<>();
  }

  public void addError(String error){
    this.errors.add(error);
  }
      
  public List<String> errors() {
    return this.errors;
  }

  public boolean isOk() {
    return this.errors.isEmpty();
  } 
}
