//Sean Gallagher

package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.Game;


//this class in an observer of game and displays a graphic representation of the game
public class GraphicView extends JPanel implements Observer {

	// Need this to help compute where to draw the player
	public static final int TILE_SIZE = 50;

	// Instance variables needed in two or more methods
	private Game game;															//needed to get the information to draw
	private Image player, tile, pit, blood, goop, slime, wumpus, black;			//the images that are displayed
	private int playerRow, playerColumn;										//these are the current row and column of the player
	
	//this is the constructor for the view, it takes game as its parameter
	public GraphicView(Game game) {
		this.game = game;														//initializes game
		playerColumn = game.getCurrentColumn();									//gets the players location
		playerRow = game.getCurrentRow();

		//I got this try catch from the section lab a couple weeks ago
		//I just adapted it for this project
		//It loads in all the needed images
		try {
			player = ImageIO.read(new File("images/TheHunter.png"));
			tile = ImageIO.read(new File("images/Ground.png"));
			pit = ImageIO.read(new File("images/SlimePit.png"));
			blood = ImageIO.read(new File("images/Blood.png"));
			goop = ImageIO.read(new File("images/Goop.png"));
			slime = ImageIO.read(new File("images/Slime.png"));
			wumpus = ImageIO.read(new File("images/Wumpus.png"));
			black = ImageIO.read(new File("images/black.png"));
			System.out.println("read files");
		} catch(Exception e) {
			System.out.println("Error reading files");
			return;
		}

		// Draw the initial game with the player showing at the initial location
		repaint();
	}
	
	//this updates this observer
	@Override
	public void update(Observable observable, Object extraParameter) {
		playerRow = game.getCurrentRow();
		playerColumn = game.getCurrentColumn();
		repaint();
	}

	//this update is called within this class
	public void update() {
		playerRow = game.getCurrentRow();
		playerColumn = game.getCurrentColumn();
		repaint();
	}
	
	//paintcomponent displays all the images based on game
	public void paintComponent(Graphics g) {
		super.paintComponent(g);																// Need to call this method to avoid incorrect drawing of this JPanel.
		Graphics2D g2 = (Graphics2D) g;
		update();																				//updates it so the character is in the correct spot on new game
		for(int c = 0; c < 10; c++) {															//iterates over the array
			for(int r = 0; r < 10; r++) {	
				if(Game.board[c][r]=='f')														//if the room is empty
					g2.drawImage(tile, c * TILE_SIZE, r * TILE_SIZE, null);						//displays the empty room at that coordinate 
				if(Game.board[c][r]=='p'){														//if the room is a pit
					g2.drawImage(tile, c * TILE_SIZE, r * TILE_SIZE, null);						//display the tile under the pit image
					g2.drawImage(pit, c * TILE_SIZE, r * TILE_SIZE, null);
				}
				if(Game.board[c][r]=='g'){														//if the room is filled with goo
					g2.drawImage(tile, c * TILE_SIZE, r * TILE_SIZE, null);						//display a tile behind the goo image
					g2.drawImage(goop, c * TILE_SIZE, r * TILE_SIZE, null);
				}
				if(Game.board[c][r]=='b'){														//if the room is filled with blood
					g2.drawImage(tile, c * TILE_SIZE, r * TILE_SIZE, null);						//display a tile behind the blood image
					g2.drawImage(blood, c * TILE_SIZE, r * TILE_SIZE, null);
				}
				if(Game.board[c][r]=='s'){														//if the room is filled wiht slime
					g2.drawImage(tile, c * TILE_SIZE, r * TILE_SIZE, null);						//display the slime image on a tile image
					g2.drawImage(slime, c * TILE_SIZE, r * TILE_SIZE, null);
				}	
				if(Game.board[c][r]=='w'){														//if the room has the wumpus
					g2.drawImage(tile, c * TILE_SIZE, r * TILE_SIZE, null);						//display the wumpus standing on a tile
					g2.drawImage(wumpus, c * TILE_SIZE, r * TILE_SIZE, null);
				}
				if(Game.hiddenboard[c][r]=='x')													//if the room is hidden
					g2.drawImage(black, c * TILE_SIZE, r * TILE_SIZE, null);					//cover the hidden rooms with black tiles
			}
		}
		
		if(!game.isDead())																		//if you are not dead
			g2.drawImage(player, playerColumn * TILE_SIZE, playerRow * TILE_SIZE, null);		//display the player


		if(!game.isDead() && game.isOver()){													//if you are not dead but the game is over
			for(int c = 0; c < 10; c++) {														//iterate over the board array 
				for(int r = 0; r < 10; r++) {													//then draw all the non empty room elements
					if(Game.board[c][r]=='p'){													//pit
						g2.drawImage(pit, c * TILE_SIZE, r * TILE_SIZE, null);
					}
					if(Game.board[c][r]=='g'){													//goo
						g2.drawImage(goop, c * TILE_SIZE, r * TILE_SIZE, null);
					}
					if(Game.board[c][r]=='b'){													//blood
						g2.drawImage(blood, c * TILE_SIZE, r * TILE_SIZE, null);
					}
					if(Game.board[c][r]=='s'){													//slime
						g2.drawImage(slime, c * TILE_SIZE, r * TILE_SIZE, null);
					}
					if(Game.board[c][r]=='w'){													//wumpus
						g2.drawImage(wumpus, c * TILE_SIZE, r * TILE_SIZE, null);
					}
				}
			}
			g2.drawImage(player, playerColumn * TILE_SIZE, playerRow * TILE_SIZE, null);		//then draw the player
		}
		
		if(game.isDead()){																		//if you are dead(does not display the player because you are either in a pit or the wumpu)
			for(int c = 0; c < 10; c++) {														//iterate over the board array
				for(int r = 0; r < 10; r++) {													//then display all remaining nonempty rooms
					if(Game.board[c][r]=='p'){													//pit
						g2.drawImage(pit, c * TILE_SIZE, r * TILE_SIZE, null);
					}	
					if(Game.board[c][r]=='g'){													//goo
						g2.drawImage(goop, c * TILE_SIZE, r * TILE_SIZE, null);
					}
					if(Game.board[c][r]=='b'){													//blood
						g2.drawImage(blood, c * TILE_SIZE, r * TILE_SIZE, null);
					}
					if(Game.board[c][r]=='s'){													//slime
						g2.drawImage(slime, c * TILE_SIZE, r * TILE_SIZE, null);
					}
					if(Game.board[c][r]=='w'){													//wumpus
						g2.drawImage(wumpus, c * TILE_SIZE, r * TILE_SIZE, null);
					}
				}
			}
		}
	}

}
