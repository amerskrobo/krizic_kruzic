
package controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import jdk.nashorn.internal.ir.RuntimeNode;
import junit.framework.Assert;
import static junit.framework.Assert.assertEquals;

import model.Game;
import model.Moves;
import model.Status;
import model.Views;
import org.springframework.http.HttpStatus;
import static org.springframework.http.RequestEntity.method;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import static org.junit.Assert.*;


@RestController
public class GameController {
   
   private final AtomicLong counter = new AtomicLong();
   private Game game;
   private String[][] gameMoves;
   List stats = new ArrayList();
   private Status status;
   
   @RequestMapping(value="game/new", method=GET )
   public Game game(@RequestParam(value="first",defaultValue="computer", required=false) String first,@RequestParam(value="second",required=false) String second){
       if(first.equals("computer")&& !second.isEmpty()){
           
           game = new Game(counter.incrementAndGet(),second);
           gameMoves = new String[3][3];
           
           return game;
          
       }else{
            
           return new Game(counter.incrementAndGet(),first);
       }   
   }
   
   @RequestMapping(value="game/status", method=GET)
   @JsonView(Views.StatusOnly.class)
   public Status status(@RequestParam(value="gameId")long gameId){
       if(gameId==game.getId()){
       
       
       status.setGame(gameId);
       status.setStatus("active");
       status.setGameStatus(getStatus());
       
       return status;
       }else{
           
           Status status = new Status(gameId,"finished",getStatus());
           return status;
       }
       
   }
   
   @RequestMapping(value="game/play", method=GET)
   public void gamePlay(@RequestParam(value="gameId", required=true)long gameId,@RequestParam(value="row",required=true)int row,@RequestParam(value="column",required=true)int column,@RequestParam(value="value",required=true)String value){
       if(gameId==game.getId()){
           playHuman(row,column,value);
           playComputer(row,column,value);
           if(checkWinner()!=null){
               
               if(checkWinner().equals(value)){
                   System.out.println("Winner is "+game.getHuman());
                   status.setWins();
               }else{
                   System.out.println("Winner is computer");
                   status.setLoses();
               }
           }
       }
       
   }
   
   @RequestMapping(value="game/stats", method=GET) 
   @JsonView(Views.SatsOnly.class)
   public Map getStatss(){
       
      
       stats.add(0, status);
       HashMap map = new HashMap();
       
       map.put("stats", stats );
       return map;
       
   }
   
   public void playHuman(int row,int column,String value){
       
       for(int i=0;i<3;i++){
           for(int j=0;j<3;j++){
               if(i==row-1 && j==column-1){
                   gameMoves[i][j]=value;
                   System.out.println( game.getHuman() +" play "+"row: "+i+" column:"+j+" value:"+gameMoves[i][j]);
                   
                   
               }
           }
       }
       
   }
   
   public void playComputer(int row,int column, String value){
       for(int i=0;i<3;i++){
           for(int j=0;j<3;j++){
               if(gameMoves[i][j]==null){
                   if (value.equals("x")){
                       gameMoves[i][j]="o";
                      System.out.println("Computer play "+"row: "+i+" column:"+j+": "+"value: "+gameMoves[i][j]);
                      return;
                   }
                   else{
                      
                       gameMoves[i][j]="x";
                       System.out.println("Computer play"+"row: "+i+" column:"+j+": "+"value:"+gameMoves[i][j]);
                       return;
                   }
                   
               }
               
               
           }
       }
   }
   public String checkWinner(){
       String winner = null;
       
       
       if(gameMoves[0][0]!=null && gameMoves[0][1]!=null && gameMoves[0][2]!=null){
       if (gameMoves[0][0].equals(gameMoves[0][1]) && gameMoves[0][0].equals(gameMoves[0][2])){
           winner= gameMoves[0][0];
           return winner;
       }
       }
        if(gameMoves[2][0]!=null && gameMoves[2][1]!=null && gameMoves[2][2]!=null){
         if (gameMoves[2][0].equals(gameMoves[2][1]) && gameMoves[2][0].equals(gameMoves[2][2])){
           winner= gameMoves[2][0];
           return winner;
       }
       }
         if(gameMoves[1][0]!=null && gameMoves[1][1]!=null && gameMoves[1][2]!=null){
        if (gameMoves[1][0].equals(gameMoves[1][1]) && gameMoves[1][0].equals(gameMoves[1][2])){
           winner= gameMoves[1][0];
           return winner;
       }
         }
        if(gameMoves[0][0]!=null && gameMoves[1][0]!=null && gameMoves[2][0]!=null){
        if (gameMoves[0][0].equals(gameMoves[1][0]) && gameMoves[0][0].equals(gameMoves[2][0])){
           winner= gameMoves[0][0];
           return winner;
       }
        }
         if(gameMoves[0][0]!=null && gameMoves[1][0]!=null && gameMoves[2][0]!=null){
         if (gameMoves[0][0].equals(gameMoves[1][0]) && gameMoves[0][0].equals(gameMoves[2][0])){
           winner= gameMoves[0][0];
           return winner;
       }
         }
          if(gameMoves[0][1]!=null && gameMoves[1][1]!=null && gameMoves[2][1]!=null){
          if (gameMoves[0][1].equals(gameMoves[1][1]) && gameMoves[0][1].equals(gameMoves[2][1])){
           winner= gameMoves[0][1];
           return winner;
       }
          }
       if(gameMoves[0][2]!=null && gameMoves[1][2]!=null && gameMoves[2][2]!=null){
           if (gameMoves[0][2].equals(gameMoves[1][2]) && gameMoves[0][2].equals(gameMoves[2][2])){
           winner= gameMoves[0][0];
           return winner;
       }
           }
       if(gameMoves[0][0]!=null && gameMoves[1][1]!=null && gameMoves[2][2]!=null){
            if (gameMoves[0][0].equals(gameMoves[1][1]) && gameMoves[0][0].equals(gameMoves[2][2])){
           winner = gameMoves[0][0];
           return winner;
       }
       }
        if(gameMoves[2][2]!=null && gameMoves[1][1]!=null && gameMoves[0][2]!=null){
             if (gameMoves[2][2].equals(gameMoves[1][1]) && gameMoves[2][2].equals(gameMoves[0][2])){
           winner= gameMoves[2][2];
           return winner;
       }
        }
            
            return winner;
   }
   
   @JsonView(Views.StatusOnly.class)
   public List getStatus(){
       
       List getStatus = new ArrayList<Moves>();
       
       for(int i=0;i<3;i++){
           for(int j=0;j<3;j++){
               
               if(gameMoves[i][j]!=null){
                   getStatus.add(new Moves(i,j,gameMoves[i][j]));
                  
               }
               
           }
       }
       
       getStatus.add("...");
       return getStatus;
   }
   
   
   
}
