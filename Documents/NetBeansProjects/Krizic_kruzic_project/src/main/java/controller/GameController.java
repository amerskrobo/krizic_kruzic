
package controller;

import java.util.concurrent.atomic.AtomicLong;
import jdk.nashorn.internal.ir.RuntimeNode;

import model.Game;
import static org.springframework.http.RequestEntity.method;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {
   
   private final AtomicLong counter = new AtomicLong();
   
   
   @RequestMapping(value="game/new",params={"first","second"},  method=GET )
   public Game game(@RequestParam("first")String first){
       return new Game(counter.incrementAndGet());
       
   }
   
   
}
