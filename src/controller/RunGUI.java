//Sean Gallagher

package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicArrowButton;

import model.Direction;
import model.Game;
import view.GraphicView;
import view.TextView;

//This class sets up and runs the gui and the game
public class RunGUI extends JFrame {

	public static void main(String[] args) {
		RunGUI window = new RunGUI(0,0);								//creates an instance of RunGUI	the parameter 0,0 just means it isn't a test if it was 1000,1000 it would be a test
		window.setVisible(true);										//sets the gui to visible					
	}

	private JPanel currentView;											//the jpanel that is currently displayed
	public TextView textView;											//the text view, an observer
	public GraphicView graphicView;										//the graphics view, an observer
	public Game game;													//the observable
	private BasicArrowButton[] playerButton;							//buttons that move the player
	private BasicArrowButton[] arrowButton;								//buttons that fire the arrow
	
	//Constructor for the gui, takes parameter that is meaningless unless the row is 1000, then its a test game
	public RunGUI(int x, int y) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			//when you exit the window the program ends
		setSize(518, 640);												//sets the window size		
		setupMenus();													//initializes the menus
		game = new Game(x, y);											//creates a new game
		initializeButtons();											//initializes the player and arrow buttons
		addKeyListener(new ArrowKeyListener());							//creates a key listener so you can move with the arrow keys
		textView = new TextView(game);									//creates the textView observer
		graphicView = new GraphicView(game);   							//creates the graphicView observer
		addObservers();													//adds the observers to the observable
		setViewTo(graphicView);											//sets the initial view to graphicView
	}
	//This method sets the views to observe the game
	private void addObservers() {						
		game.addObserver(textView);										//adds the textView observer
		game.addObserver(graphicView);									//adds the graphicView observer
	}
	//This method creates the menu system
	private void setupMenus() {	
		JMenuItem menu = new JMenu("Options");							//creates the top layer menu labeled Options
		JMenuItem newGame = new JMenuItem("New Game");					//Creates a menu item labeled New Game
		menu.add(newGame);												//Adds the New Game option to the Options tab

		JMenuItem Views = new JMenu("Views");							//Creates a menu item labeled Views
		menu.add(Views);												//Adds the Views tab to Options
		
		JMenuItem JText = new JMenuItem("Text View");					//Creates a menu item labeled Text View
		Views.add(JText);												//Adds Text View to Views

		JMenuItem JGraphic = new JMenuItem("Graphic View");				//Creates a menu item labeled Graphic View
		Views.add(JGraphic);											//Adds Graphic View to Views

		JMenuBar menuBar = new JMenuBar();								//Set the menu bar
		setJMenuBar(menuBar);
		menuBar.add(menu);												//Adds the menu to the menu bar

																		// Add the same listener to all menu items requiring action
		MenuItemListener menuListener = new MenuItemListener();
		newGame.addActionListener(menuListener);
		JText.addActionListener(menuListener);
		JGraphic.addActionListener(menuListener);
	}
	
	//This method initializes the player and arrow buttons
	public void initializeButtons(){ 
		playerButton = new BasicArrowButton[4];							//Creates an array of BasicArrowButtons
		arrowButton = new BasicArrowButton[4];							//Creates an array of BasicArrowButtons
		ButtonListener buttonListener = new ButtonListener();			//Creates a new ButtonListener
		JPanel container = new JPanel();								//Creates a container JPanel
		container.setSize(500,500);										//Sets the size of the container						
		JPanel panelOne = new JPanel();									//Create two Jpanels
		JPanel panelTwo = new JPanel();
		panelTwo.setBackground(Color.RED);								//Set the backgrounds to red and orange
		panelOne.setBackground(Color.ORANGE);
		panelOne.setLayout(new FlowLayout());							//sets the layout to flowLayout
		panelTwo.setLayout(new FlowLayout());

		panelTwo.add(new JLabel("Arrow"));								//Labels the two jpanels
		panelOne.add(new JLabel("Player"));

		playerButton[0] = new BasicArrowButton(BasicArrowButton.WEST);	//Adds North South East and West buttons the each button array
		playerButton[1] = new BasicArrowButton(BasicArrowButton.EAST);
		playerButton[2] = new BasicArrowButton(BasicArrowButton.NORTH);
		playerButton[3] = new BasicArrowButton(BasicArrowButton.SOUTH);
		arrowButton[0] = new BasicArrowButton(BasicArrowButton.WEST);
		arrowButton[1] = new BasicArrowButton(BasicArrowButton.EAST);
		arrowButton[2] = new BasicArrowButton(BasicArrowButton.NORTH);
		arrowButton[3] = new BasicArrowButton(BasicArrowButton.SOUTH);


		for(int i = 0; i<4; i++){
			arrowButton[i].addActionListener(buttonListener);			//adds an action listener to each button in each array
			playerButton[i].addActionListener(buttonListener);
			panelOne.add(playerButton[i]);								//adds the player buttons to panelOne
			panelTwo.add(arrowButton[i]);								//adds the arrow buttons to panelTwo
		}

		container.setLayout(new BorderLayout());						//sets the container layout to BorderLayout
		container.add(panelOne, BorderLayout.CENTER);					//Adds panelOne and panelTwo to the container
		container.add(panelTwo, BorderLayout.PAGE_END);
		this.add(container, BorderLayout.SOUTH);						//adds the container to the frame
	}
	
	private class MenuItemListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
													
			String text = ((JMenuItem) e.getSource()).getText();		// Find out the text of the JMenuItem that was just clicked
			if (text.equals("Text View")){								//If Text VIew is selected, sets the view to Text View
				System.out.println("Text");
				setViewTo(textView);
			}
			if (text.equals("Graphic View")){							//If Graphic VIew is selected, sets the view to Graphic View
				System.out.println("Graphic");
				setViewTo(graphicView);
			}
			if (text.equals("New Game")) {								//If New Game is selected, creates a new game and updates the display
				System.out.println("New Game");
				game.startNewGame();
				repaint();
			}
		}
	}

	//this method takes a JPanel as its parameter and sets the current view to that new JPanel
	public void setViewTo(JPanel newView) {
		if (currentView != null)										
			remove(currentView);
		currentView = newView;											//currentView is  now the newVIew
		add(currentView);												//adds currentView to the display
		currentView.repaint();											//repaints the display
		validate();
	}

	//This method is the action listener for the player and arrow buttons
	private class ButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton buttonClicked = (JButton) e.getSource();			//gets the button that was clicked
			if(!game.isDead() && !game.isOver())						//if the game is still running
				for(int i=0; i<4; i++){										
					if(playerButton[i] == buttonClicked){				//checks which array and which button was clicked
						if(i==0){
							game.movePlayer(Direction.WEST);			//moves the player in the correct direction
						}
						else if(i==1){
							game.movePlayer(Direction.EAST);
						}
						else if(i==2){
							game.movePlayer(Direction.NORTH);
						}
						else if(i==3){
							game.movePlayer(Direction.SOUTH);
						}
					}
					if(arrowButton[i] == buttonClicked){
						if(i==0){
							game.shootArrow(Direction.WEST);			//fires the arrow in the correct direction
						}
						else if(i==1){
							game.shootArrow(Direction.EAST);
						}
						else if(i==2){
							game.shootArrow(Direction.NORTH);
						}
						else if(i==3){
							game.shootArrow(Direction.SOUTH);
						}
					}
				}
		}
	}

	//This method listens to keys to move the player
	private class ArrowKeyListener implements KeyListener {
		@Override
		public void keyPressed(KeyEvent key) {
			if(!game.isDead() && !game.isOver()){						//if the game is still runing
				if(key.getKeyCode() == KeyEvent.VK_UP)					//if the key pressed is an arrow key
					game.movePlayer(Direction.NORTH);					//move in the direction of the arrow key
				else if(key.getKeyCode() == KeyEvent.VK_DOWN)
					game.movePlayer(Direction.SOUTH);
				else if(key.getKeyCode() == KeyEvent.VK_LEFT)
					game.movePlayer(Direction.WEST);
				else if(key.getKeyCode() == KeyEvent.VK_RIGHT)
					game.movePlayer(Direction.EAST);
			}
		}
		@Override
		public void keyTyped(KeyEvent e) {
		}
		@Override
		public void keyReleased(KeyEvent e) {
		}
	}

}