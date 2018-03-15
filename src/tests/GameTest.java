//Sean Gallagher

package tests;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import controller.RunGUI;
import model.Direction;
import model.Game;

//This class tests the game
public class GameTest {

  @Test
  public void testGetters() {
    Game game = new Game(1000, 1000);
    assertEquals(0, game.getCurrentRow());
    assertEquals(0, game.getCurrentColumn());
  }

  @Test
  public void testMoveNorth() {
    Game game = new Game(1000, 1000);
    game.movePlayer(Direction.NORTH);
    assertEquals(9, game.getCurrentRow());
    assertEquals(0, game.getCurrentColumn());
  }

  @Test
  public void testMoveEast() {
	    Game game = new Game(1000, 1000);
	    game.movePlayer(Direction.EAST);
	    assertEquals(0, game.getCurrentRow());
	    assertEquals(1, game.getCurrentColumn());
  }

  @Test
  public void testMoveSouth() {
	    Game game = new Game(1000, 1000);
	    game.movePlayer(Direction.SOUTH);
	    assertEquals(1, game.getCurrentRow());
	    assertEquals(0, game.getCurrentColumn());
  }
  
  @Test
  public void testMoveDownMapWrap() {
	    Game game = new Game(1000, 1000);
	    game.movePlayer(Direction.SOUTH);
	    game.movePlayer(Direction.SOUTH);
	    game.movePlayer(Direction.SOUTH);
	    game.movePlayer(Direction.SOUTH);
	    game.movePlayer(Direction.SOUTH);
	    game.movePlayer(Direction.SOUTH);
	    game.movePlayer(Direction.SOUTH);
	    game.movePlayer(Direction.SOUTH);
	    game.movePlayer(Direction.SOUTH);
	    game.movePlayer(Direction.SOUTH);
	    game.movePlayer(Direction.NORTH);
	    assertEquals(9, game.getCurrentRow());
	    assertEquals(0, game.getCurrentColumn());
  }
  
  @Test
  public void testMoveAcrossMapWrap() {
	    Game game = new Game(1000, 1000);
	    game.movePlayer(Direction.EAST);
	    game.movePlayer(Direction.EAST);
	    game.movePlayer(Direction.EAST);
	    game.movePlayer(Direction.EAST);
	    game.movePlayer(Direction.EAST);
	    game.movePlayer(Direction.EAST);
	    game.movePlayer(Direction.EAST);
	    game.movePlayer(Direction.EAST);
	    game.movePlayer(Direction.EAST);
	    game.movePlayer(Direction.EAST);
	    game.movePlayer(Direction.WEST);
	    assertEquals(0, game.getCurrentRow());
	    assertEquals(9, game.getCurrentColumn());
  }

  @Test
  public void testMoveWest() {
	    Game game = new Game(1000, 1000);
	    game.movePlayer(Direction.WEST);
	    assertEquals(0, game.getCurrentRow());
	    assertEquals(9, game.getCurrentColumn());
  }
  @Test
  public void testPit() {
	  	RunGUI window = new RunGUI(1000,1000);
	    window.setVisible(true);
	    Game game = window.game;
	    game.movePlayer(Direction.SOUTH);
	    assertEquals(1, game.getCurrentRow());
	    assertEquals(0, game.getCurrentColumn());
	    game.movePlayer(Direction.SOUTH);
	    assertEquals(2, game.getCurrentRow());
	    assertEquals(0, game.getCurrentColumn());
	    game.movePlayer(Direction.EAST);
	    assertEquals(2, game.getCurrentRow());
	    assertEquals(1, game.getCurrentColumn());
	    game.movePlayer(Direction.EAST);
	    assertEquals(2, game.getCurrentRow());
	    assertEquals(2, game.getCurrentColumn());
	    
	    assertEquals(true, game.isDead());    
	    assertEquals("pit", game.causeOfDeath());
  }
  
  @Test
  public void testWumpus() {
	    RunGUI window = new RunGUI(1000,1000);
	    window.setVisible(true);
	    Game game = window.game;
	    game.movePlayer(Direction.SOUTH);
	    assertEquals(1, game.getCurrentRow());
	    assertEquals(0, game.getCurrentColumn());
	    game.movePlayer(Direction.SOUTH);
	    assertEquals(2, game.getCurrentRow());
	    assertEquals(0, game.getCurrentColumn());
	    game.movePlayer(Direction.EAST);
	    assertEquals(2, game.getCurrentRow());
	    assertEquals(1, game.getCurrentColumn());
	    game.movePlayer(Direction.SOUTH);
	    assertEquals(3, game.getCurrentRow());
	    assertEquals(1, game.getCurrentColumn());
	    game.movePlayer(Direction.SOUTH);
	    game.movePlayer(Direction.SOUTH);
	    assertEquals(5, game.getCurrentRow());
	    assertEquals(1, game.getCurrentColumn());
	    game.movePlayer(Direction.EAST);
	    game.movePlayer(Direction.EAST);
	    game.movePlayer(Direction.EAST);
	    game.movePlayer(Direction.EAST);
	    assertEquals(5, game.getCurrentRow());
	    assertEquals(5, game.getCurrentColumn());
	    
	    assertEquals(true, game.isDead());  
	    assertEquals("eaten", game.causeOfDeath()); 
  }
  @Test
  public void testPitText() {
	  	RunGUI window = new RunGUI(1000,1000);
	    window.setVisible(true);
	    Game game = window.game;
	    window.setViewTo(window.textView);
	    game.movePlayer(Direction.SOUTH);
	    assertEquals(1, game.getCurrentRow());
	    assertEquals(0, game.getCurrentColumn());
	    game.movePlayer(Direction.SOUTH);
	    assertEquals(2, game.getCurrentRow());
	    assertEquals(0, game.getCurrentColumn());
	    game.movePlayer(Direction.EAST);
	    assertEquals(2, game.getCurrentRow());
	    assertEquals(1, game.getCurrentColumn());
	    game.movePlayer(Direction.EAST);
	    assertEquals(2, game.getCurrentRow());
	    assertEquals(2, game.getCurrentColumn());
	    
	    assertEquals(true, game.isDead());  
	    assertEquals("pit", game.causeOfDeath());
  }
  
  @Test
  public void testWumpusText() {
	    RunGUI window = new RunGUI(1000,1000);
	    window.setVisible(true);
	    Game game = window.game;
	    window.setViewTo(window.textView);
	    game.movePlayer(Direction.SOUTH);
	    assertEquals(1, game.getCurrentRow());
	    assertEquals(0, game.getCurrentColumn());
	    game.movePlayer(Direction.SOUTH);
	    assertEquals(2, game.getCurrentRow());
	    assertEquals(0, game.getCurrentColumn());
	    game.movePlayer(Direction.EAST);
	    assertEquals(2, game.getCurrentRow());
	    assertEquals(1, game.getCurrentColumn());
	    game.movePlayer(Direction.SOUTH);
	    assertEquals(3, game.getCurrentRow());
	    assertEquals(1, game.getCurrentColumn());
	    game.movePlayer(Direction.SOUTH);
	    game.movePlayer(Direction.SOUTH);
	    assertEquals(5, game.getCurrentRow());
	    assertEquals(1, game.getCurrentColumn());
	    game.movePlayer(Direction.EAST);
	    game.movePlayer(Direction.EAST);
	    game.movePlayer(Direction.EAST);
	    game.movePlayer(Direction.EAST);
	    assertEquals(5, game.getCurrentRow());
	    assertEquals(5, game.getCurrentColumn());
	    
	    assertEquals(true, game.isDead());    
	    assertEquals("eaten", game.causeOfDeath());  
  }
  
  @Test
  public void testWumpusShot() {
	    RunGUI window = new RunGUI(1000,1000);
	    window.setVisible(true);
	    Game game = window.game;
	    game.movePlayer(Direction.SOUTH);
	    assertEquals(1, game.getCurrentRow());
	    assertEquals(0, game.getCurrentColumn());
	    game.movePlayer(Direction.SOUTH);
	    assertEquals(2, game.getCurrentRow());
	    assertEquals(0, game.getCurrentColumn());
	    game.movePlayer(Direction.EAST);
	    assertEquals(2, game.getCurrentRow());
	    assertEquals(1, game.getCurrentColumn());
	    game.movePlayer(Direction.SOUTH);
	    assertEquals(3, game.getCurrentRow());
	    assertEquals(1, game.getCurrentColumn());
	    game.movePlayer(Direction.SOUTH);
	    game.movePlayer(Direction.SOUTH);
	    assertEquals(5, game.getCurrentRow());
	    assertEquals(1, game.getCurrentColumn());
	    game.movePlayer(Direction.EAST);
	    game.movePlayer(Direction.EAST);
	    game.movePlayer(Direction.EAST);
	    assertEquals(5, game.getCurrentRow());
	    assertEquals(4, game.getCurrentColumn());
	    game.shootArrow(Direction.EAST);
	    
	    assertEquals(true, game.isOver());   
	    assertEquals(false, game.isDead());
  }
  
  @Test
  public void testWumpusShotText() {
	    RunGUI window = new RunGUI(1000,1000);
	    window.setVisible(true);
	    Game game = window.game;
	    window.setViewTo(window.textView);
	    game.movePlayer(Direction.SOUTH);
	    assertEquals(1, game.getCurrentRow());
	    assertEquals(0, game.getCurrentColumn());
	    game.movePlayer(Direction.SOUTH);
	    assertEquals(2, game.getCurrentRow());
	    assertEquals(0, game.getCurrentColumn());
	    game.movePlayer(Direction.EAST);
	    assertEquals(2, game.getCurrentRow());
	    assertEquals(1, game.getCurrentColumn());
	    game.movePlayer(Direction.SOUTH);
	    assertEquals(3, game.getCurrentRow());
	    assertEquals(1, game.getCurrentColumn());
	    game.movePlayer(Direction.SOUTH);
	    game.movePlayer(Direction.SOUTH);
	    assertEquals(5, game.getCurrentRow());
	    assertEquals(1, game.getCurrentColumn());
	    game.movePlayer(Direction.EAST);
	    game.movePlayer(Direction.EAST);
	    game.movePlayer(Direction.EAST);
	    assertEquals(5, game.getCurrentRow());
	    assertEquals(4, game.getCurrentColumn());
	    game.shootArrow(Direction.EAST);
	    
	    assertEquals(true, game.isOver());   
	    assertEquals(false, game.isDead());
  }
  
  @Test
  public void testWumpusShotNewGame() {
	    RunGUI window = new RunGUI(1000,1000);
	    window.setVisible(true);
	    Game game = window.game;
	    game.movePlayer(Direction.SOUTH);
	    assertEquals(1, game.getCurrentRow());
	    assertEquals(0, game.getCurrentColumn());
	    game.movePlayer(Direction.SOUTH);
	    assertEquals(2, game.getCurrentRow());
	    assertEquals(0, game.getCurrentColumn());
	    game.movePlayer(Direction.EAST);
	    assertEquals(2, game.getCurrentRow());
	    assertEquals(1, game.getCurrentColumn());
	    game.movePlayer(Direction.SOUTH);
	    assertEquals(3, game.getCurrentRow());
	    assertEquals(1, game.getCurrentColumn());
	    game.movePlayer(Direction.SOUTH);
	    game.movePlayer(Direction.SOUTH);
	    assertEquals(5, game.getCurrentRow());
	    assertEquals(1, game.getCurrentColumn());
	    game.movePlayer(Direction.EAST);
	    game.movePlayer(Direction.EAST);
	    game.movePlayer(Direction.EAST);
	    assertEquals(5, game.getCurrentRow());
	    assertEquals(4, game.getCurrentColumn());
	    game.shootArrow(Direction.EAST);
	    
	    assertEquals(true, game.isOver());   
	    assertEquals(false, game.isDead());
	    
	    game.startNewGame();
	    assertEquals(false, game.isOver());   
	    assertEquals(false, game.isDead());
}
  @Test
  public void testSuicide() {
	    RunGUI window = new RunGUI(1000,1000);
	    window.setVisible(true);
	    Game game = window.game;
	    game.movePlayer(Direction.SOUTH);
	    assertEquals(1, game.getCurrentRow());
	    assertEquals(0, game.getCurrentColumn());
	    game.movePlayer(Direction.SOUTH);
	    assertEquals(2, game.getCurrentRow());
	    assertEquals(0, game.getCurrentColumn());
	    game.movePlayer(Direction.EAST);
	    assertEquals(2, game.getCurrentRow());
	    assertEquals(1, game.getCurrentColumn());
	    game.movePlayer(Direction.SOUTH);
	    assertEquals(3, game.getCurrentRow());
	    assertEquals(1, game.getCurrentColumn());
	    game.movePlayer(Direction.SOUTH);
	    game.movePlayer(Direction.SOUTH);
	    assertEquals(5, game.getCurrentRow());
	    assertEquals(1, game.getCurrentColumn());
	    game.movePlayer(Direction.EAST);
	    game.movePlayer(Direction.EAST);
	    game.movePlayer(Direction.EAST);
	    assertEquals(5, game.getCurrentRow());
	    assertEquals(4, game.getCurrentColumn());
	    game.shootArrow(Direction.NORTH);
	    
	    assertEquals(true, game.isOver());   
	    assertEquals(true, game.isDead());
	    assertEquals("suicide", game.causeOfDeath());  
}
  
  @Test
  public void testWumpusShotSOUTH() {
	    RunGUI window = new RunGUI(1000,1000);
	    window.setVisible(true);
	    Game game = window.game;
	    game.movePlayer(Direction.SOUTH);
	    assertEquals(1, game.getCurrentRow());
	    assertEquals(0, game.getCurrentColumn());
	    game.movePlayer(Direction.SOUTH);
	    assertEquals(2, game.getCurrentRow());
	    assertEquals(0, game.getCurrentColumn());
	    game.movePlayer(Direction.EAST);
	    assertEquals(2, game.getCurrentRow());
	    assertEquals(1, game.getCurrentColumn());
	    game.movePlayer(Direction.SOUTH);
	    assertEquals(3, game.getCurrentRow());
	    assertEquals(1, game.getCurrentColumn());
	    game.movePlayer(Direction.SOUTH);

	    game.movePlayer(Direction.EAST);
	    game.movePlayer(Direction.EAST);
	    game.movePlayer(Direction.EAST);
	    game.movePlayer(Direction.EAST);
	    
	    game.shootArrow(Direction.SOUTH);
	    
	    assertEquals(true, game.isOver());   
	    assertEquals(false, game.isDead());
  }
  
  
  
  @Test
  public void testWumpusShotNewGameText() {
	    RunGUI window = new RunGUI(1000,1000);
	    window.setVisible(true);
	    window.setViewTo(window.textView);
	    Game game = window.game;
	    
	    game.movePlayer(Direction.SOUTH);
	    assertEquals(1, game.getCurrentRow());
	    assertEquals(0, game.getCurrentColumn());
	    game.movePlayer(Direction.SOUTH);
	    assertEquals(2, game.getCurrentRow());
	    assertEquals(0, game.getCurrentColumn());
	    game.movePlayer(Direction.EAST);
	    assertEquals(2, game.getCurrentRow());
	    assertEquals(1, game.getCurrentColumn());
	    game.movePlayer(Direction.SOUTH);
	    assertEquals(3, game.getCurrentRow());
	    assertEquals(1, game.getCurrentColumn());
	    game.movePlayer(Direction.SOUTH);
	    game.movePlayer(Direction.SOUTH);
	    assertEquals(5, game.getCurrentRow());
	    assertEquals(1, game.getCurrentColumn());
	    game.movePlayer(Direction.EAST);
	    game.movePlayer(Direction.EAST);
	    game.movePlayer(Direction.EAST);
	    assertEquals(5, game.getCurrentRow());
	    assertEquals(4, game.getCurrentColumn());
	    game.shootArrow(Direction.EAST);
	    
	    assertEquals(true, game.isOver());   
	    assertEquals(false, game.isDead());
	    
	    game.startNewGame();
	    assertEquals(false, game.isOver());   
	    assertEquals(false, game.isDead());
}
  @Test
  public void testWumpusShotWESTNewGameText() {
	    RunGUI window = new RunGUI(1000,1000);
	    window.setVisible(true);
	    window.setViewTo(window.textView);
	    Game game = window.game;
	    
	    game.movePlayer(Direction.SOUTH);
	    assertEquals(1, game.getCurrentRow());
	    assertEquals(0, game.getCurrentColumn());
	    game.movePlayer(Direction.SOUTH);
	    assertEquals(2, game.getCurrentRow());
	    assertEquals(0, game.getCurrentColumn());
	    game.movePlayer(Direction.EAST);
	    assertEquals(2, game.getCurrentRow());
	    assertEquals(1, game.getCurrentColumn());
	    game.movePlayer(Direction.SOUTH);
	    assertEquals(3, game.getCurrentRow());
	    assertEquals(1, game.getCurrentColumn());
	    game.movePlayer(Direction.SOUTH);
	    game.movePlayer(Direction.SOUTH);
	    assertEquals(5, game.getCurrentRow());
	    assertEquals(1, game.getCurrentColumn());
	    game.movePlayer(Direction.EAST);
	    game.movePlayer(Direction.EAST);
	    game.movePlayer(Direction.EAST);
	    assertEquals(5, game.getCurrentRow());
	    assertEquals(4, game.getCurrentColumn());
	    game.shootArrow(Direction.WEST);
	    
	    assertEquals(true, game.isOver());   
	    assertEquals(false, game.isDead());
	    
	    game.startNewGame();
	    assertEquals(false, game.isOver());   
	    assertEquals(false, game.isDead());
}
  @Test
  public void testSuicideText() {
	    RunGUI window = new RunGUI(1000,1000);
	    window.setVisible(true);
	    window.setViewTo(window.textView);
	    Game game = window.game;
	   
	    game.movePlayer(Direction.SOUTH);
	    assertEquals(1, game.getCurrentRow());
	    assertEquals(0, game.getCurrentColumn());
	    game.movePlayer(Direction.SOUTH);
	    assertEquals(2, game.getCurrentRow());
	    assertEquals(0, game.getCurrentColumn());
	    game.movePlayer(Direction.EAST);
	    assertEquals(2, game.getCurrentRow());
	    assertEquals(1, game.getCurrentColumn());
	    game.movePlayer(Direction.SOUTH);
	    assertEquals(3, game.getCurrentRow());
	    assertEquals(1, game.getCurrentColumn());
	    game.movePlayer(Direction.SOUTH);
	    game.movePlayer(Direction.SOUTH);
	    assertEquals(5, game.getCurrentRow());
	    assertEquals(1, game.getCurrentColumn());
	    game.movePlayer(Direction.EAST);
	    game.movePlayer(Direction.EAST);
	    game.movePlayer(Direction.EAST);
	    assertEquals(5, game.getCurrentRow());
	    assertEquals(4, game.getCurrentColumn());
	    game.shootArrow(Direction.NORTH);
	    
	    assertEquals(true, game.isOver());   
	    assertEquals(true, game.isDead());
	    assertEquals("suicide", game.causeOfDeath());  
}
  
  @Test
  public void testWumpusShotSOUTHText() {
	    RunGUI window = new RunGUI(1000,1000);
	    window.setVisible(true);
	    window.setViewTo(window.textView);
	    Game game = window.game;
	    
	    game.movePlayer(Direction.SOUTH);
	    assertEquals(1, game.getCurrentRow());
	    assertEquals(0, game.getCurrentColumn());
	    game.movePlayer(Direction.SOUTH);
	    assertEquals(2, game.getCurrentRow());
	    assertEquals(0, game.getCurrentColumn());
	    game.movePlayer(Direction.EAST);
	    assertEquals(2, game.getCurrentRow());
	    assertEquals(1, game.getCurrentColumn());
	    game.movePlayer(Direction.SOUTH);
	    assertEquals(3, game.getCurrentRow());
	    assertEquals(1, game.getCurrentColumn());
	    game.movePlayer(Direction.SOUTH);

	    game.movePlayer(Direction.EAST);
	    game.movePlayer(Direction.EAST);
	    game.movePlayer(Direction.EAST);
	    game.movePlayer(Direction.EAST);
	    
	    game.shootArrow(Direction.SOUTH);
	    
	    assertEquals(true, game.isOver());   
	    assertEquals(false, game.isDead());
  }
  
  
  
}
