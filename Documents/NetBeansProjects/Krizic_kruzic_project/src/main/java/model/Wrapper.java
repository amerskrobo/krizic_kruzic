
package model;

import com.fasterxml.jackson.annotation.JsonView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Wrapper {
    
   @JsonView(Views.SatsOnly.class)
    private  Map map = new HashMap();
   
   
   
   public  Map getMap(){
        return map;
    }
   public  void setMap(List stats){
        this.map.put("stats", stats);
        
    }
   
  
}
