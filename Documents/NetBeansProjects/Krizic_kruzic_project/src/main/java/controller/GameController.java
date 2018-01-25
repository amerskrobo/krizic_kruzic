
package controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import jdk.nashorn.internal.ir.RuntimeNode;

import model.Game;
import model.Status;
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

@RestController
public class GameController {
   
   private final AtomicLong counter = new AtomicLong();
   Game game;
   String[][] gameMoves = new String[3][3];
   
   
   @RequestMapping(value="game/new", method=GET )
   public Game game(@RequestParam(value="first",defaultValue="computer", required=false) String first,@RequestParam(value="second",required=false) String second){
       if(first.equals("computer")&& !second.isEmpty()){
           
           game = new Game(counter.incrementAndGet(),second);
           return game;
          
       }else{
            
           return new Game(counter.incrementAndGet(),first);
       }   
   }
   @RequestMapping(value="game/status", method=GET)
   public Status status(@RequestParam(value="gameId")long gameId){
       if(gameId==game.getId()){
       Status status = new Status(gameId,"active",getStatus());
       
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
       }
       
   }
   
   
   public List getStatus(){
       List getStatus = new ArrayList();
       getStatus.add(0, "{row:1,column:1,value:x}");
       getStatus.add(1, "{row:1,column:1,value:0}");
       
       return getStatus;
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
   
   
   
}
