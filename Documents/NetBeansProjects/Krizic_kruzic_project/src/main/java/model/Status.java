
package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;


@JsonPropertyOrder({ "gameId", "status","gameStatus" })
public class Status {
    
    @JsonUnwrapped
    @JsonView(Views.StatusOnly.class)
    private long gameId;
    @JsonView(Views.StatusOnly.class)
    private String status;
    
    private int i;
    @JsonView(Views.SatsOnly.class)
    private int wins=0;
    @JsonView(Views.SatsOnly.class)
    private int loses=0;
    @JsonView(Views.SatsOnly.class)
    private int draws=0;
    
    @JsonView(Views.StatusOnly.class)
    
    private List gameStatus;
    
    
    
    
    public Status(long gameId,String status,List gameStatus){
        this.gameId=gameId;
        this.status=status;
        this.gameStatus=gameStatus;
    }
    
    
    
    
    public Status(){
        
    }
   
    
    public int getCountWins(){
        return wins;
    }
    
    public int getCountLoses(){
        return loses;
    }
    
    public int getCountDraws(){
        return draws;
    }
    @JsonProperty("game")
    public List getGameStatus(){
        //int i = (int)gameId;
       // return (String)gameStatus.get(i);
        
        return gameStatus;
        
    }
   
    public String getStatus(){
        return status;
    }
    
    @JsonProperty("gameId")
    public long getGame(){
        return gameId;
   }
    
    
    public void setStatus(String status){
        this.status=status;
    }
    public void setGame(long gameId){
      this.gameId= gameId;
    }
   
    
    public void setGameStatus(List gameStatus){
        this.gameStatus=gameStatus;
    }
    
    public void setWins(){
        
        wins++;
    }
    public void setLoses(){
        loses++;
    }
    public void setDraws(){
        
        draws++;
    }
    
   
}
