
package model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import java.util.List;


@JsonIgnoreProperties({"computer"})
public class Game {
    
    @JsonView(Views.GameIdOnly.class)
    private long gameId;
    
    private static String computer="computer";
    
    @JsonView(Views.SatsOnly.class)
    private String human;
    
    
    
    public Game(long gameId, String human){
        
        this.gameId=gameId;  
        
        this.human=human;
    }
    
   public Game(long gameId){
       this.gameId=gameId;
   }
   
    public Game(String human){
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
    @JsonProperty("name")
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
