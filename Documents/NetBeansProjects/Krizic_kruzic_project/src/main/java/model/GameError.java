
package model;

import java.util.List;
import org.springframework.http.HttpStatus;

public class GameError {
  private HttpStatus status;
  private String message;
  private List<String> errors;
  
  public GameError(HttpStatus status,String message,List<String>errors){
      this.status=status;
      this.message=message;
      this.errors=errors;
  }
  public GameError(HttpStatus status,String message){
      this.status=status;
      this.message=message;
      
  }
  
  public HttpStatus getStatus(){
      return status;
  }
  public String getMessage(){
      return message;
  }
  
}
