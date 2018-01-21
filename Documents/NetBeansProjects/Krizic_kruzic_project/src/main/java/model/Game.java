
package model;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;



public class Game {
    
    
    private final long gameId;
    
    
    
    public Game(long gameId){
        this.gameId=gameId;  
    }

    @JsonProperty("gameId")
    public long getId(){
        return gameId;
    }
    
   
    
  
    
}
