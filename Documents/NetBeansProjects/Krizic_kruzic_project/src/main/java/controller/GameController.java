
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
   
   
   public List getStatus(){
       List getStatus = new ArrayList();
       getStatus.add(0, "{row:1,column:1,value:x}");
       getStatus.add(1, "{row:1,column:1,value:0}");
       
       return getStatus;
   }
   
   
   
}
