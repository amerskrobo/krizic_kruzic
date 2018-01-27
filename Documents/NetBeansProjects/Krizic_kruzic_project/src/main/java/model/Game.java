
package model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.ArrayList;
import java.util.List;


@JsonIgnoreProperties({"computer","human"})
public class Game {
    
    
    private  long gameId;
    private static String computer="computer";
    private  String human;
    
    
    
    public Game(long gameId, String human){
        
        this.gameId=gameId;  
        this.human=human;
    }
    
   
    
    
    public Game(){   
    }
    
    
    public List getGameStatus(List gameStatus){
        return gameStatus;
    }
   
    @JsonProperty("gameId")
    public long getId(){
        return gameId;
    }
    
    public String getComputer(){
        return computer;
    }
    public String getHuman(){
        return human;
    }
    public void setId(long gameId){
        this.gameId=gameId;
    }
    public void setHuman(String human){
        this.human= human;
    }
   
   
   
    
   
    
  
    
}
