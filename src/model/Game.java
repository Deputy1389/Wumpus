//Sean Gallagher

package model;

import java.util.Observable;

//This class handles the logic behind the game from setup to movements and death
public class Game extends Observable {
	private static int size = 10;									//the size of the board
	private static int LAST_ROW = size-1;							//last element
	private static int LAST_COL = size-1;							//last element
	public static char[][] board = new char[size][size];			//this is the game board
	public static char[][] hiddenboard = new char[size][size];		//this holds whether the room is hidden or not
	private static boolean dead;									//if you are dead
	private static boolean over;									//if the game is over
	private static boolean pit;										//if you fell down a put			
	private static boolean eaten;									//if you were eaten

	private static int currentRow;
	private static int currentCol;

	public Game(int column, int row) {
		if(row == 1000){
			initializeTestGame();									//initializes a predefined game for testing purposes
		}
		else
			initializeGame();										//initializes a random game
	} 
	
	//this initializes a predefined game for testing
	public static void initializeTestGame(){
		currentRow = 0;												//character starts at 0,0
		currentCol = 0;
		for(int i=0; i<10; i++)	
			for(int j=0; j<10; j++){
				board[i][j] = 'f';									//f is the ground
				hiddenboard[i][j] = 'x';							//x is a hidden coordinate
			}
		board[5][5] = 'w';											//places the wumpus at 5,5
		wumpusSetup(5,5);											//sets up the surrounding blood for the wumpus	
		board[2][2] = 'p';											//places a pit at 2,2
		pitSetup(2,2);												//sets up the slime around 2,2
		dead = false; 												//initializes the booleans
		over = false;
		pit = false;
		eaten = false;
	}
	
	//This method initializes the game
	public static void initializeGame(){
		int minimum = 0;
		int maximum = LAST_COL;
		int randomRow = minimum + (int)(Math.random() * maximum); 	//Creates a random number within the bounds of the board
		int randomCol = minimum + (int)(Math.random() * maximum);	//Creates a random number within the bounds of the board
		dead = false; 												//initializes the booleans
		over = false;
		pit = false;
		eaten = false;
		int randomI = 3 + (int)(Math.random() * 3);					//Creates a random number which is either 3, 4 or 5

		for(int i=0; i<10; i++)
			for(int j=0; j<10; j++){								
				board[i][j] = 'f';									//f is the ground
				hiddenboard[i][j] = 'x';							//x is a hidden room
			}

		for(int i=0; i<randomI; i++){								//iterates from 0 to 3, 4 or 5
			randomRow = minimum + (int)(Math.random() * maximum); 	//creates new random numbers
			randomCol = minimum + (int)(Math.random() * maximum); 	
			pitSetup(randomCol,randomRow);							//creates a pit at the random location
		}
		for(int i=0; i<size; i++){
			randomRow = (minimum) + (int)(Math.random() * maximum); //creates new random numbers
			randomCol = (minimum) + (int)(Math.random() * maximum);
			if(board[randomRow][randomRow] == 'f'){					//if the room is empty
				board[randomCol][randomRow] = 'w';					//adds a wumpus
				wumpusSetup(randomCol,randomRow);					//sets up the blood/goo around the wumpus
				break;												//breaks the loop so only 1 wumpus is ingame		
			}
		}
		for(int i=0; i<size;i++){
			randomRow = minimum + (int)(Math.random() * maximum); 	//creates new randoms
			randomCol = minimum + (int)(Math.random() * maximum); 
			if(board[randomCol][randomRow] == 'f'){					//if the room is empty
				currentRow = randomRow;								//adds the player to the location
				currentCol = randomCol;
//				board[randomCol][randomRow] = 'f';
				hiddenboard[randomCol][randomRow] = 'f';			//reveals the player location
				break;												//breaks the loop so there is only 1 revealed location at start
			}
		}
	}

	//This method takes two ints as parameter which are the pits location and sets up the area around the pit
	public static void pitSetup(int randomCol, int randomRow){
		board[randomCol][randomRow] = 'p';							// p is a pit
		int colplus1 = randomCol+1;									//creates ints for the squares around the pit
		int colmin1 = randomCol-1;
		int rowplus1 = randomRow+1;
		int rowmin1 = randomRow -1;
											
		if(randomRow == size-1)										//if the pit is at the edge of the board 
			rowplus1 = 0;											//allow wrap around
		if(randomCol == size-1)
			colplus1 = 0;
		if(randomRow == 0)
			rowmin1 = size-1;
		if(randomCol == 0)
			colmin1 = size-1;
		
		if(board[colplus1][randomRow] == 'f'){						//checks each coordinate N S E W of the pit for empty rooms
			board[colplus1][randomRow] = 's';						//adds slime to the empty room
		}
		if(board[colmin1][randomRow] == 'f'){
			board[colmin1][randomRow] = 's';
		}
		if(board[randomCol][rowplus1] == 'f'){
			board[randomCol][rowplus1] = 's';
		}
		if(board[randomCol][rowmin1] == 'f'){
			board[randomCol][rowmin1] = 's';
		}
	}

	//Sets up the wumpus surrounding, takes 2 int parameters that are the location of the wumpus
	public static void wumpusSetup(int x, int y){
		int yplus1 = y+1;											//creates ints for the squares around the wumpus
		int yplus2 = y+2;
		int ymin1 = y-1;
		int ymin2 = y-2;
		int xplus1 = x+1;
		int xplus2 = x+2;
		int xmin1 = x-1;
		int xmin2 = x-2;

		if(y == size-1){											//These if statements check for wrap around
			yplus1 = 0;												//then set the ints to the appropriate square to avoid compile time errors
			yplus2 = 1;
		}
		if(y == size-2)
			yplus2 = 0;
		if(x == size-1){
			xplus1 = 0;
			xplus2 = 1;
		}
		if(x == size-2)
			xplus2 = 0;

		if(y == 0){
			ymin1 = size-1;
			ymin2 = size-2;
		}
		if(y == 1)
			ymin2 = size-1;
		if(x == 0){
			xmin1 = size-1;
			xmin2 = size-2;
		}
		if(x == 1)
			xmin2 = size-1;
											
		if(board[x][yplus1] == 'f')									//checks the surroundings for empty squares 
			board[x][yplus1] = 'b';									//then adds blood to the empty room
		if(board[x][ymin1] == 'f')
			board[x][ymin1] = 'b';
		if(board[xplus1][y] == 'f')
			board[xplus1][y] = 'b';
		if(board[xmin1][y] == 'f')
			board[xmin1][y] = 'b';
		if(board[xmin1][yplus1] == 'f')
			board[xmin1][yplus1] = 'b';
		if(board[xplus1][yplus1] == 'f')
			board[xplus1][yplus1] = 'b';
		if(board[xplus1][ymin1] == 'f')
			board[xplus1][ymin1] = 'b';
		if(board[xmin1][ymin1] == 'f')
			board[xmin1][ymin1] = 'b';
		if(board[x][yplus2] == 'f')
			board[x][yplus2] = 'b';
		if(board[x][ymin2] == 'f')
			board[x][ymin2] = 'b';
		if(board[xplus2][y] == 'f')
			board[xplus2][y] = 'b';
		if(board[xmin2][y] == 'f')
			board[xmin2][y] = 'b';
		
		if(board[x][yplus1] == 's')									//then check rooms with slime in them 
			board[x][yplus1] = 'g';									//if a nearby room contains slime, the room now has goo
		if(board[x][ymin1] == 's')
			board[x][ymin1] = 'g';
		if(board[xplus1][y] == 's')
			board[xplus1][y] = 'g';
		if(board[xmin1][y] == 's')
			board[xmin1][y] = 'g';
		if(board[xmin1][yplus1] == 's')
			board[xmin1][yplus1] = 'g';
		if(board[xplus1][yplus1] == 's')
			board[xplus1][yplus1] = 'g';
		if(board[xplus1][ymin1] == 's')
			board[xplus1][ymin1] = 'g';
		if(board[xmin1][ymin1] == 's')
			board[xmin1][ymin1] = 'g';
		if(board[x][yplus2] == 's')
			board[x][yplus2] = 'g';
		if(board[x][ymin2] == 's')
			board[x][ymin2] = 'g';
		if(board[xplus2][y] == 's')
			board[xplus2][y] = 'g';
		if(board[xmin2][y] == 's')
			board[xmin2][y] = 'g';
	}


	//this method moves the player in the given direction
	public void movePlayer(Direction direction) {
		if(!isDead() || !isOver()){								//checks if the game is running
			if (direction == Direction.NORTH)					//Gets the direction given	
				currentRow--;									//moves the player accordingly
			if (direction == Direction.EAST)
				currentCol++;
			if (direction == Direction.SOUTH)
				currentRow++;
			if (direction == Direction.WEST)
				currentCol--;

			// Allow wrap around
			if(currentCol < 0) 
				currentCol = LAST_COL;
			if(currentCol > LAST_COL) 
				currentCol = 0;

			if(currentRow  < 0) 
				currentRow = LAST_ROW;
			if(currentRow > LAST_ROW) 
				currentRow = 0;

			hiddenboard[currentCol][currentRow]=board[currentCol][currentRow];	//if the player visits a square it is permanently revealed
		}
		if(board[currentCol][currentRow] == 'p'){					//if the player walks into a pit
			System.out.println("You fell");											
			fellInPit();											//kills the player
		}
		if(board[currentCol][currentRow] == 'w'){					//if the player walks into the wumpus room
			System.out.println("You have been eaten");
			eaten();												//the player is eaten
		}

		// With java.util.Observable, you must send yourself a setChanged
		// message.  If you don't, notifyObservers does nothing 
		setChanged();
		notifyObservers();
	}
	//This method fires the arrow in the given direction
	public void shootArrow(Direction direction) {
		for(int i = 0; i<size; i++){						
			if(direction == Direction.WEST || direction == Direction.EAST){	//The arrow wraps around so the east and west buttons are the same	
				if(board[i][currentRow]=='w'){								//checks all columns in the players row for the wumpus
					System.out.println("Hit");								//if the arrow hits the wumpus
					over = true;											//ends the game
					setChanged();											//notify oberservers
					notifyObservers();
					return;													//ends the method
				}			  
			}
			if(direction == Direction.SOUTH || direction == Direction.NORTH){
				if(board[currentCol][i]=='w'){								//checks all rows in the players column for the wumpus
					System.out.println("Hit");
					over = true;
					setChanged();
					notifyObservers();
					return;
				}
			}
		}
		missedShot();														//if it gets to get then the arrow missed
		setChanged();														//notify observers
		notifyObservers();
	}
	
	//this method returns the current row of the player
	public int getCurrentRow() {
		return currentRow;
	}
	
	//this method returns the current column of the player
	public int getCurrentColumn() {
		return currentCol;
	}
	
	//this method returns whether or not the game is over
	public boolean isOver(){
		if(isDead())
			over = true;	  
		return over;
	}
	//this method returns whether or not the player died
	public boolean isDead(){  
		return dead;
	}
	
	//If the player eaten
	public void eaten(){
		dead = true;		
		eaten = true;														
		over = true;													
	}
	//if the player fell into a pit
	public void fellInPit(){
		dead = true;
		pit = true;
		over = true;	
	}
	//if the arrow missed
	public void missedShot(){
		dead = true;
		over = true;
	}
	//this method returns the cause of death of the player
	public String causeOfDeath(){
		if(pit)
			return "pit";
		else if(eaten)
			return "eaten";
		else
			return "suicide";
	}
	//this method starts a new game
	public void startNewGame() {
		dead = false;
		over = false;
		initializeGame();
		notifyObservers();	// The state of this model just changed so tell any observer to update themselves

	}
}