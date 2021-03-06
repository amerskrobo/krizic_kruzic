
package model;



import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import com.fasterxml.jackson.annotation.JsonView;
import java.util.ArrayList;


import java.util.List;
import java.util.Map;






@JsonPropertyOrder({ "gameId", "status","winner","gameStatus" })

public class Status extends Game {
    
    
    
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
    
    
    private List stats;
    
    @JsonInclude(Include.NON_EMPTY)
    @JsonView(Views.StatusFinished.class)
    private Map winner;
    
    
    public Status(long gameId,String status,List gameStatus){
        super(gameId);
        this.status=status;
        this.gameStatus=gameStatus;
        
    }
    
    public Status(){
    }
    
    
    public Status(String human,int wins,int loses,int draws){
        super(human);
        this.wins=wins;
        this.loses=loses;
        this.draws=draws;
        
    }
    
    
    @JsonInclude(Include.NON_EMPTY)
    public Object getWinner(){
        return  winner.get(getId());
    }
    public List getStats(){
        return stats;
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
    
    
    
    
    public void setStatus(String status){
        this.status=status;
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
    public void setStats(){
        stats = new ArrayList();
        
    }
    
    public void setWinner(Map winner){
        this.winner= winner;
    }
    
   
}
