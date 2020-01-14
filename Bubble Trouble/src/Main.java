//import javax.swing and java.awt
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
//Declaring Main class with JPanel and implementing action, mouse and key listeners
public class Main extends JPanel implements KeyListener, ActionListener, MouseListener {
	//Declaring class variables
	Player alien;
	Arrow arro;
	ImageIcon  imgBall, imgAr, gameover;
	ImageIcon imgLeft, imgRight, imgAlive;
	ImageIcon backie, grass, loading;
	ImageIcon play1, play2, controls, quit, imgSorry, controller, victorious;
	ImageIcon fire, keys, goodbye, question;
	Button player1Button, player2Button , controlsButton, quitButton;
	Timer arrowTimer;
	int dirX0,dirY0,dirX1, dirY1, dirX2,dirY2, dirX3, dirY3, dirX4,dirY4 ,dirX5, dirY5, dirX6, dirY6,time, passed, hits;
	int lives =3;
	boolean split, game;
	int counter;
	//Declaring array of balls
	Ball[] b;
	public static void main(String[] args) {		
		new Main();
	}

	public Main(){		
		//Initializing boolean variables
		split = false;
		game = false;
		//Initializing int variables to store timer, amount of time passed, and number of times the ball is hit
		hits=0;
		time=30;
		passed=0;
		//Initializing ImageIcons
		imgSorry = new ImageIcon("sorry3.png");
		Image imgS = imgSorry.getImage();
		imgS = imgS.getScaledInstance(75, 75, Image.SCALE_SMOOTH);
		imgSorry = new ImageIcon(imgS);
		imgBall = new ImageIcon("Bubble Trouble Ball.png");
		imgAlive = new ImageIcon("DefaultPlay.png");
		imgRight = new ImageIcon("Rite.png");
		backie = new ImageIcon("_Background.png");
		grass= new ImageIcon("grasss.png");
		imgLeft = new ImageIcon("Moving left.png");
		loading = new ImageIcon("BT back.jpg");
		controls = new ImageIcon("Controls.jpg");
		play1 = new ImageIcon("1Player.jpg");
		play2 = new ImageIcon("2 Player.jpg");
		quit = new ImageIcon("quit.jpg");
		gameover = new ImageIcon("gameover.png");
		fire = new ImageIcon("fire.gif");
		controller = new ImageIcon("controller.png");
		goodbye = new ImageIcon("goodbye.png");
		Image imgBye = goodbye.getImage();
		imgBye = imgBye.getScaledInstance(75, 75, Image.SCALE_SMOOTH);
		goodbye = new ImageIcon(imgBye);
		victorious = new ImageIcon("kimjong.gif");
		question = new ImageIcon("Question-Mark.png");
		imgAr = new ImageIcon("Arrow.png");
		//Initializing array of balls
		b = new Ball[] {new Ball  (200, 400, 100,100,imgBall),
				new Ball(-1000, 400, 50,50,imgBall),
				new Ball(-1000, 400, 50,50,imgBall),
				new Ball(-1000,400,25,25,imgBall),
				new Ball(-1000,400,25,25,imgBall),
				new Ball(-1000,400,25,25,imgBall),
				new Ball(-1000,400,25,25,imgBall)};
		//Initializing Buttons
		player1Button = new Button(120,315,210,70, play1);
		player2Button= new Button(120,387,210,70, play2);
		controlsButton = new Button(120,459,210,70,controls);
		quitButton = new Button(120,531,210,70, quit);
		//Initializing the alien player
		alien = new Player(imgAlive, 0,480, 40,65);
		alien.setImage(imgAlive);		
		//Initializing direction for the first ball
		dirX0= 2;
		dirY0= 8;
		//Initialzing the arrow/harpoon
		arro = new Arrow(imgAr, (int) alien.getX() ,-1000,50,600);

		// Set properties of the JPanel
		setLayout(null);
		setBackground(Color.WHITE);
		setFocusable(true);
		addKeyListener(this);
		addMouseListener(this);
		//Declaring and initializing JFrame
		JFrame frame = new JFrame();
		//Setting properties of the JFrame
		frame.setContentPane(this);
		frame.setSize(900,700);
		frame.setTitle("Bubble Trouble");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		//Starting the timer
		arrowTimer = new Timer(25,this);
		arrowTimer.start();
		//Making JFrame visible
		frame.setVisible(true);
	}
	//Paint component
	public void  paintComponent(Graphics g) {
		// Repaints the frame and its components
		super.paintComponent(g);
		// Declare and initialize a Graphics2D object
		Graphics2D g2 = (Graphics2D) g;
		//If the game has not started yet. Paint the "home screen " with its buttons
		if(game == false)
		{
			g2.drawImage(loading.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
			player1Button.drawButton(g2);	
			player2Button.drawButton(g2);
			controlsButton.drawButton(g2);
			quitButton.drawButton(g2);
			g2.drawImage(fire.getImage(), 20,32,35 ,150, null);
			g2.drawImage(fire.getImage(), 840,32,35 ,150, null);
			repaint();
		}
		else {
			//If the game has started make game=true and draw the game screen
			game =true;
			g2.drawImage(backie.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
			arro.drawArrow(g2);
			g2.drawImage(grass.getImage(), 0, 545, this.getWidth(), this.getHeight(), this);
			g2.setFont(new Font ("Stencil", Font.BOLD, 34));
			g2.drawString("Time remaining: "+ time+" s", 0, 34);
			//Draw the player and the balls
			alien.drawPlayer(g2);
			for(int i =0; i<7;i++) {
				b[i].drawBall(g2);
				g2.drawString("Lives remaining: "+ lives, 500, 625);
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//If the game is running
		if(game==true)
		{
			//if user presses right arrow
			if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			{
				//move the alien to the right by 5 pixels and replace the image with the one of him walking right
				alien.setImage(imgRight);
				alien.setX(alien.getX() + 5);
			}
			//if the user presses left arrow
			if (e.getKeyCode() == KeyEvent.VK_LEFT)
			{
				//move the alien to the left by 5 pixels and replace the image with the one of him walking left
				alien.setImage(imgLeft);
				alien.setX(alien.getX() - 5);
			}
			//if the user presses spacebar and the arrow is not on the the screen already place the arrow at the alien's position
			if (e.getKeyCode() == KeyEvent.VK_SPACE && (arro.getY()<0|| arro.getY()> this.getHeight()))
			{
				arro.setX((int) alien.getX());
				arro.setY((int)alien.getY());
			}		
			//if user tries to move alien off the screen do not let him
			if(alien.getX()<=0)
			{
				alien.setX(0);
			}
			if( (alien.getX()+alien.getWidth() )>=  this.getWidth())
			{
				alien.setX(this.getWidth()-alien.getWidth());
			}
			//repaint
			repaint();
		}
	}

	@Override
	//when the user releases the key
	public void keyReleased(KeyEvent e) {
		//if the game is running
		if(game == true)
		{
			//set the image back to the default image
			alien.setImage(imgAlive);
			//repaint
			repaint();
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//If the game is not running
		if(game == false)
		{
			//reinitialize lives, time, and number of hits
			lives=3;
			time=30;
			hits=0;
			//set player back to the left of the screen
			alien.setX(1);
			//reset the first ball to the centre 
			b[0].centre(this.getWidth(),this.getHeight());
			//set split to false
			split=false;

		}
		//if they still have time and lives remaining
		if(lives>0&&time>0)
		{
			//if the game is running
			if(game == true) {
				//store the time that has passed
				passed+=25;	
				//every second or so, subtract the timer by 1
				if( passed%1000 == 0)
				{
					time-=1;
				}
				//keep the arrow moving by 1- pixels upwards, as long as the game is running
				arro.setY(arro.getY()-10);
				//If the arrows top left touches the top of the screen, move the whole arrow offscreen
				if(arro.getY()<=0)
				{
					arro.setY(-1000);
				}
				//if split is false
				if(split==false)
				{
					//move ball 0 by dirX0 and dirY0
					b[0].setX( b[0].getX()+dirX0);
					b[0].setY( b[0].getY()+dirY0);
					//if  ball 0 hits the right of the screen, multiply dirX by a negative so that it does not go off screen
					if(b[0].getX()+b[0].getWidth()>=(this.getWidth()))
					{
						dirX0=-dirX0;
					}
					//if ball 0 hits the left of the screen, multiply dirX by a negative so that it does not go off screen
					if(b[0].getX()<=0)
					{
						dirX0=-dirX0;
					}
					//if ball 0 hits the part  of the screen where the alien's feet are, multiply dirY by a negative so that it does not go below the ground
					if(( b[0].getY()+b[0].getHeight() )>= alien.getY()+ alien.getHeight())
					{
						dirY0=-dirY0;
					}
					//if ball 0  hits y=200, multiply dirX by a negative to simulate the bouncing of a real ball
					if(b[0].getY()<=200)
					{
						dirY0=-dirY0;
					}
				}
				//if ball 0 has been hit
				if(split== true)
				{
					//move ball 1 and ball 2 by dirX1 ,dirX2  and by dirY1 and dirY2 respectively
					b[1].setX(b[1].getX()+dirX1);
					b[1].setY(b[1].getY()+dirY1);
					b[2].setX(b[2].getX()+dirX2);
					b[2].setY(b[2].getY()+dirY2);
					//if ball 1 hits the right of the screen, multiply dirX1 by a negative so that it stays on the screen
					if(b[1].getX()+b[1].getWidth()>=(this.getWidth()))
					{
						dirX1=-dirX1;
					}
					//if ball 1 hits the left of the screen, multiply dirX1 by a negative so that it stays on the screen
					if(b[1].getX()<=0)
					{
						dirX1=-dirX1;
					}
					//if ball 1 hits the line at which the player's feet are multiply dirY1  by a negative so it does not go below the ground
					if(( b[1].getY()+b[1].getHeight() )>= alien.getY()+ alien.getHeight())
					{
						dirY1=-dirY1;
					}
					//if ball 1 hits the line y =150,  multiply dirY1 by a negative to simulate the bouncing of a real ball
					if(b[1].getY()<=150)
					{
						dirY1=-dirY1;
					}	
					//if ball 2 hits the right of the screen, multiply dirX2 by a negative
					if(b[2].getX()+b[2].getWidth()>=(this.getWidth()))
					{
						dirX2=-dirX2;
					}
					//if ball 2 hits the left of the screen, multiply dirX2 by a negative
					if(b[2].getX()<=0)
					{
						dirX2=-dirX2;
					}
					//if ball 1 hits the line at which the player's feet are multiply dirY1  by a negative so it does not go below the ground
					if(( b[2].getY()+b[2].getHeight() )>= alien.getY()+alien.getHeight())
					{
						dirY2=-dirY2;
					}
					// if ball 2 hits y =150
					if(b[2].getY()<=150)
					{
						dirY2=-dirY2;
					}	
					//if ball 1 is off screen(because it has been hit)
					if(b[1].isOffScreen(this.getWidth(),this.getHeight())==1) {
						//move ball 3 BY dirX3 and dirY3 and move ball 4 by dirX4 and dirX4 
						b[3].setX(b[3].getX()+dirX3);
						b[3].setY(b[3].getY()+dirY3);
						b[4].setX(b[4].getX()+dirX4);
						b[4].setY(b[4].getY()+dirY4);
						//if ball 3 hits the right of the screen, multiply dirX3 by a negative to keep it onscreen
						if(b[3].getX()+b[3].getWidth()>=(this.getWidth()))
						{
							dirX3=-dirX3;
						}
						//if ball 3 hits the left of the screen multiply dirX3 by a negative to keep it on screen
						if(b[3].getX()<=0)
						{
							dirX3=-dirX3;
						}
						//if ball 3 hits the line at which the player's feet are multiply dirY3  by a negative so it does not go below the ground
						if(( b[3].getY()+b[3].getHeight() )>= alien.getY()+ alien.getHeight())
						{
							dirY3=-dirY3;
						}
						//if ball 3 hits the line y=125, multiply it by a negative to simulate a real ball bouncing
						if(b[3].getY()<=125)
						{
							dirY3=-dirY3;
						}	
						//if ball 4 hits the right of the screen, multiply dirY4 by a negative so that it stays on screen
						if(b[4].getX()+b[4].getWidth()>=(this.getWidth()))
						{
							dirX4=-dirX4;
						}
						//if ball 4 hits the left of the screen, multiply dirY4 by a negative so that it stays on screen
						if(b[4].getX()<=0)
						{
							dirX4=-dirX4;
						}
						//if ball 4 hits the line at which the player's feet are multiply dirY4  by a negative so it does not go below the ground
						if((b[4].getY()+b[4].getHeight() )>= alien.getY()+alien.getHeight())
						{
							dirY4=-dirY4;
						}
						//if ball 4 hits the line y=125, multiply it by a negative to simulate a real ball bouncing
						if(b[4].getY()<=125)
						{
							dirY4=-dirY4;
						}	
					}
					//if ball 2 is off screen(because it has been hit)
					if(b[2].isOffScreen(this.getWidth(),this.getHeight())==1) {
						//move ball 5 BY dirX5 and dirY5 and move ball 6 by dirX6 and dirX6 
						b[5].setX(b[5].getX()+dirX5);
						b[5].setY(b[5].getY()+dirY5);
						b[6].setX(b[6].getX()+dirX6);
						b[6].setY(b[6].getY()+dirY6);
						//if ball 5 hits the right of the screen, multiply dirX5 by a negative to keep it onscreen
						if(b[5].getX()+b[5].getWidth()>=(this.getWidth()))
						{
							dirX5=-dirX5;
						}
						//if ball 5 hits the left of the screen, multiply dirY5 by a negative so that it stays on screen
						if(b[5].getX()<=0)
						{
							dirX5=-dirX5;
						}
						//if ball 5 hits the line at which the player's feet are multiply dirY5  by a negative so it does not go below the ground
						if(( b[5].getY()+b[5].getHeight() )>= alien.getY()+ alien.getHeight())
						{
							dirY5=-dirY5;
						}
						//if ball 5 hits the line y=125, multiply it by a negative to simulate a real ball bouncing
						if(b[5].getY()<=125)
						{
							dirY5=-dirY5;
						}	
						//if ball 6 hits the right of the screen, multiply dirX6 by a negative to keep it onscreen
						if(b[6].getX()+b[6].getWidth()>=(this.getWidth()))
						{
							dirX6=-dirX6;
						}
						//if ball 6 hits the left of the screen, multiply dirY6 by a negative so that it stays on screen

						if(b[6].getX()<=0)
						{
							dirX6=-dirX6;
						}
						//if ball 6 hits the line at which the player's feet are multiply dirY6  by a negative so it does not go below the ground
						if((b[6].getY()+b[6].getHeight() )>= alien.getY()+alien.getHeight())
						{
							dirY6=-dirY6;
						}
						//if ball 6 hits the line y=125, multiply it by a negative to simulate a real ball bouncing
						if(b[6].getY()<=125)
						{
							dirY6=-dirY6;
						}	
					}
				}
			
				//if split is false, place all balls off screen, EXCEPT ball 0,
				if(split==false)
				{
					b[1].setLocation(-1000,-1000);
					b[2].setLocation(-1000, -1000);
					b[3].setLocation(-1000,-1000);
					b[4].setLocation(-1000, -1000);
					b[5].setLocation(-1000,-1000);
					b[6].setLocation(-1000,-1000);
				}
			}
			//check collision
			checkCollision();
		}
		//if they run out of lives
		if(lives==0)
		{
			//repaint, tell them they ran out lives, and set game to false
			repaint();
			JOptionPane.showMessageDialog(null,  "You have run "
					+ "out of lives...\nBetter luck next time!", "Game Over!", 
					JOptionPane.INFORMATION_MESSAGE , gameover);
			game = false;
		}
		//if they run out of time
		if(time==0)
		{
			//repaint, tell them they ran out times, and set game to false
			repaint();
			JOptionPane.showMessageDialog(null,  "You have run "
					+ "out of time...\nBetter luck next game!", "Game Over!", 
					JOptionPane.INFORMATION_MESSAGE, gameover);
			game = false;
		}
		//if they have hit all possible balls
		if(hits== b.length) {
			//repaint, congratulate them for winning, and set game to false
			repaint();
			JOptionPane.showMessageDialog(null,  "Well done, you beat Bubble Trouble\n"
					+ "in "+(30 - time)+ " seconds! 	Be sure to  play again\n to try and beat your best time!","Congrats!", 
					JOptionPane.INFORMATION_MESSAGE, victorious);
			game=false;
		}
		//repaint
		repaint();
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		//if the first button is clicked, start the game
		if(player1Button.getBounds().contains(e.getX(), e.getY()))
		{
			game=true;
		}
		//if the second button is clicked, apologize and tell them that this game mode is coming soon (in a JOptionPane)
		if(player2Button.getBounds().contains(e.getX(), e.getY()))
		{
			JOptionPane.showMessageDialog(null,  "This game mode is not available yet...\n "
					+ "Stay tuned for future updates!", "Coming Soon!", 
					JOptionPane.INFORMATION_MESSAGE, imgSorry);
		}
		//if the 3rd button is clicked, tell them the controls and how to win (in a JOptionPane)
		if(controlsButton.getBounds().contains(e.getX(), e.getY()))
		{
			JOptionPane.showMessageDialog(null,  " Use left and right arrows keys to move the alien.\n"
					+ " Press spacebar to shoot his harpoon gun at the balls.\n Destroy all the balls within the time limit "
					+ "without\n getting hit more than 3 times to emerge victiorious. \n                                Good Luck!",
					"Controls and Instructions", 
					JOptionPane.INFORMATION_MESSAGE, controller);
		}
		//if they click the 4th button, ask them if they are sure they want to quit (in JOptionPane). If they say yes, thank them for playing and end the program. Otherwise just continue
		if(quitButton.getBounds().contains(e.getX(), e.getY()))
		{
			Object[] option= { "Continue Having Fun", "Exit Bubble Trouble"};
			int choice = JOptionPane.showOptionDialog(null,"Are you sure you would like to exit \nBubble"
					+ "Trouble or would you like\nto stick around for a bit longer?","Bubble Trouble",JOptionPane.DEFAULT_OPTION,
					JOptionPane.PLAIN_MESSAGE, question, 
					option, option[0]);

			if(choice== 1) {
				JOptionPane.showMessageDialog(null,  "Thank you for playing Bubble Trouble!\n We hope you enjoyed. Goodbye!", "Farewell", 
						JOptionPane.INFORMATION_MESSAGE, goodbye);
				System.exit(0);
			}
		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	private void checkCollision() {
//if arrow hits ball 0
		if( arro.getBounds().intersects(b[0].getBounds())) {
			//add 1 to variable storing number of hits
			hits++;
			//if ball is moving left
			if(dirX0 <0) {
				//spawn ball 1 on the right and ball 2 on the left (where ball 0 once was)
				b[1].setX(b[0].getX());
				b[1].setY(b[0].getY());
				b[2].setX(b[0].getX()-b[2].getWidth());
				b[2].setY(b[0].getY());
			}
			//if ball is mo
			else {
				//spawn ball 2 on the right and ball 1 on the left (where ball 0 once was)
				b[2].setX(b[0].getX());
				b[2].setY(b[0].getY());
				b[1].setX(b[0].getX()-b[1].getWidth());
				b[1].setY(b[0].getY());
			}
			//Check if ball 1 or ball 2 try to spawn  off screen or hit the left side of the screen. If one of them does, then set it's X position to 1
			for(int c=1;c<=2;c++){
				if(b[c].getX()<=0)
				{
					b[c].setX(1);
				}
			}
			//Check if ball 1 or ball 2 try to spawn  off screen or hit the right side of the screen. If one of them does, then set it's X position to 1 less than the width of the frame
			for(int c=1;c<=2;c++){
				if(b[c].getX()+b[c].getWidth()>=this.getWidth())
				{
					b[c].setX(this.getWidth()-(b[c].getWidth()+1));
				};
			}
			//set split to true
			split = true;
			// place ball 0 off screen and place the arrow off screen
			b[0].setX(this.getWidth()*1000);
			b[0].setY(this.getHeight()*1000);
			arro.setY(-1000);
			//set dirX1 and dirY1 to the opposite of dirX0 and dirY0 at that moment
			dirX1=-dirX0;
			dirY1=-dirY0;
			//set dirX2 to the opposite of dirX1 and dirY2 to the same value as dirY1 at that moment
			dirX2= - dirX1;
			dirY2=dirY1;
		}
		//If any of the balls hit the alien, spawn that ball in the center and the alien at x=1
		for(int i=0; i<7;i++) {
			if(alien.getBounds().intersects(b[i].getBounds())) {
				lives-=1;
				b[i].centre(this.getWidth(), this.getHeight());
				alien.setX(1);
			}
		}
//If the arrow hits ball 1
		if(arro.getBounds().intersects(b[1].getBounds())) {			
			//add 1 to the variable storing the number of hits
			hits++;
			//set the arrow off the screen
			arro.setY(-1000);
			//if ball 1 is moving to the left
			if(dirX1 <0) {
				//spawn ball 4 to the left of ball 3, but at the position of ball1
				b[3].setX(b[1].getX());
				b[3].setY(b[1].getY());
				b[4].setX(b[1].getX()-b[4].getWidth());
				b[4].setY(b[1].getY());
			}
			//if ball 2 is moving to the right
			else {
				//spawn ball 3 to the left of ball 4, but at the position of ball1
				b[4].setX(b[1].getX());
				b[4].setY(b[1].getY());
				b[3].setX(b[1].getX()-b[3].getWidth());
				b[3].setY(b[1].getY());
			}
			//set dirX3 to the opposite of dirX1 and set dirY3 to the opposite of dirY1, at that moment
			dirX3=-dirX1;
			dirY3=-dirY1;
			//set dirX4 to the opposite of dirX3 and set dirY4 to the same as dirY3, at that moment
			dirX4= - dirX3;
			dirY4=dirY3;

			//check if ball 3 or 4 are spawning to or hitting the left side of the screen. If any of them does, then place it's X position at X=1
			for(int c=3;c<=4;c++){
				if(b[c].getX()<=0)
				{
					b[c].setX(1);
				}
			}
			//check if ball 3 or 4 are spawning to or hitting the right side of the screen. If any of them does, then place it's X position at 1 less than the width of the frame
			for(int c=3;c<=4;c++){
				if(b[c].getX()+b[c].getWidth()>=this.getWidth())
				{
					b[c].setX(this.getWidth()-(b[c].getWidth()+1));
				};
			}
			//put ball 1 off the screen
			b[1].setX(this.getWidth()*1000);
			b[1].setY(this.getHeight()*1000);
		}
		//if the arrow hit ball 2
		if(arro.getBounds().intersects(b[2].getBounds())) {			
			//add 1 to the variable storing the number of hits
			hits++;
			//place the arrow off the screen
			arro.setY(-1000);
			//if the ball is moving to the left
			if(dirX2 <0) {
				//set ball 6 to the left of ball 5 (where ball 2 once was)
				b[5].setX(b[2].getX());
				b[5].setY(b[2].getY());
				b[6].setX(b[2].getX()-b[6].getWidth());
				b[6].setY(b[2].getY());
			}
			//if the ball is moving to the right
			else {
				//set ball 5 to the left of ball 6 (where ball 2 once was)
				b[6].setX(b[2].getX());
				b[6].setY(b[2].getY());
				b[5].setX(b[2].getX()-b[5].getWidth());
				b[5].setY(b[2].getY());
			}
			//Set dirX5 to the opposite of dirX2 and dirY5 to the opposite of dirY2
			dirX5=-dirX2;
			dirY5=-dirY2;
			//Set dirX6 to the opposite of dirX5 and dirY6 to the same as dirY5
			dirX6= - dirX5;
			dirY6=dirY5;
			//check if ball 5 or 6 are spawning to or hitting the left side of the screen. If any of them does, then place it's X position at X=1
			for(int c=5;c<=6;c++){
				if(b[c].getX()<=0)
				{
					b[c].setX(1);
				}
			}
			//check if ball 5 or 6 are spawning to or hitting the right side of the screen. If any of them does, then place it's X position at X=1
			for(int c=5;c<=6;c++){
				if(b[c].getX()+b[c].getWidth()>=this.getWidth())
				{
					b[c].setX(this.getWidth()-(b[c].getWidth()+1));
				}
			}
			//place ball 2 off the screen
			b[2].setX(this.getWidth()*1000);
			b[2].setY(this.getHeight()*1000);
		}
		// if the arrow hits any of balls 3-->6, set it off screen. Add 1 to hits. Place the arrow off screen
		for(int i=3;i<=6;i++) {
			if (arro.getBounds().intersects(b[i].getBounds())){
				b[i].setLocation(-1000, -1000);
				arro.setY(-1000);
				hits++;
			}
		}
	}
	}