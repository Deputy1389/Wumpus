//Sean Gallagher

package view;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.Game;

//this class in an observer of game and displays a text representation of the game
public class TextView extends JPanel implements Observer {
	//this is needed to draw things in the right spot
	public static final int TILE_SIZE = 50;

	private Game game; 																	//needed to get the information to draw
	private int playerRow, playerColumn;												//these are the current row and column of the player

	//this is the text view, it takes the game as a parameter and initializes the view
	public TextView(Game game) {
		this.game = game; 																//initializes game
		playerColumn = game.getCurrentColumn(); 										//gets the player coordinates from game
		playerRow = game.getCurrentRow();
		// Draw the initial game with the player showing at a random location
		repaint();
	}

	//this updates this observer
	@Override
	public void update(Observable observable, Object extraParameter) {	
		playerRow = game.getCurrentRow();												//updates player location
		playerColumn = game.getCurrentColumn();
		repaint();																		//repaints the view
	}

	//this is called to update the view
	public void update() {
		playerRow = game.getCurrentRow();
		playerColumn = game.getCurrentColumn();
		repaint();
	}

	//this method is what actually does the painting
	public void paintComponent(Graphics g) {
		// Need to call this method to avoid incorrect drawing of this JPanel.
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		if(!game.isDead() && !game.isOver())											//if the game is still running
			for(int c = 0; c < 10; c++) {												//iterates over the board array
				for(int r = 0; r < 10; r++) {
					String text; 														//text is the string that will be drawn
					g2.setFont(new Font("TimesRoman", Font.PLAIN, 15));					//sets the font
					if(c == playerColumn && r == playerRow){							//sets text as an O at the current location
						text = "O";	//O is the player
					}
					else{																//if the player is not in this coordinate
						if(Game.hiddenboard[c][r] == 'f')								//if its an empty room
							text = "__";												//text is now an empty room
						else 															//if its not an empty room or is still hidden
							text = ""+Game.hiddenboard[c][r];							//text is hiddenboard[c][r] at that coordinate
					}

					g2.drawString(text, c * TILE_SIZE +20, r * TILE_SIZE+20);			//this display the text in the correct location

					//these are messages to the user based on the current location
					if(Game.hiddenboard[playerColumn][playerRow] == 's'){				//if you are in slime
						g2.setFont(new Font("TimesRoman", Font.PLAIN, 30));				//increases font size
						g2.drawString("I hear wind", 170, 500);							//display at the bottom the message
					}
					if(Game.hiddenboard[playerColumn][playerRow] == 'b'){				//if you are in blood
						g2.setFont(new Font("TimesRoman", Font.PLAIN, 30));				//increases font size
						g2.drawString("I smell something foul", 140, 500);				//display at the bottom the message
					}
					if(Game.hiddenboard[playerColumn][playerRow] == 'g'){				//if you are in a mix of slime and blood
						g2.setFont(new Font("TimesRoman", Font.PLAIN, 30));				//increase the font
						g2.drawString("Theres slime and blood everywhere", 60, 500);	//display the message at the bottom
					}
					//updates information
					update();
				}					
				if(game.isDead()){														//if the player is dead
					g2.setFont(new Font("TimesRoman", Font.PLAIN, 30));					//increase font
					g2.drawString("You have died", 150, 500);							//display at the bottom that you are dead
				}
			}
		//this displays all the nonempty rooms upon game over
		if(!game.isDead() && game.isOver()){											//if the player is not dead but the game is over
			g2.setFont(new Font("TimesRoman", Font.PLAIN, 30));							//increased font size
			g2.drawString("You have slain the beast!", 90, 500);						//displays kill message at the bottom
			for(int c = 0; c < 10; c++) {												//iterates over the board
				for(int r = 0; r < 10; r++) {
					String text;														//text will hold the board information
					g2.setFont(new Font("TimesRoman", Font.PLAIN, 15));					//lowered font
					if(Game.board[c][r]!='f'){											//if the room is not empty
						text = ""+Game.board[c][r];										//text = the nonempty room
						g2.drawString(text, c * TILE_SIZE +20, r * TILE_SIZE+20);		//display text at the coordinate
					}								
				}
			}
		}
		if(game.isDead()){																//if you are dead, displays all the hazards and cause of death
			String death = game.causeOfDeath();											//string dead is the cause of death
			g2.setFont(new Font("TimesRoman", Font.PLAIN, 30));							//increased font size
			if(death.compareTo("suicide")==0){											//if the cause of death is suicide
				g2.drawString("Your arrow missed!", 140, 500);							//display the message at the bottom
			}
			if(death.compareTo("pit")==0){												//if you fell down a pit
				g2.drawString("You have fallen down a pit!", 90, 500);					//displays the message at the bottom
			}	
			if(death.compareTo("eaten")==0){											//if the wumpus ate you
				g2.drawString("Your flesh fills my belly, human!", 70, 500);			//displays message at the bottom
			}
			for(int c = 0; c < 10; c++) {												//iterates over the board array
				for(int r = 0; r < 10; r++) {
					String text;											
					g2.setFont(new Font("TimesRoman", Font.PLAIN, 15));					//lowered font
					if(Game.board[c][r]!='f'){											//if the room is nonempty
						text = ""+Game.board[c][r];										//text = nonempty room
						g2.drawString(text, c * TILE_SIZE +20, r * TILE_SIZE+20);		//display the nonempty room at the coordinate
					}
				}
			}
		}
	}	
}
