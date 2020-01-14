//importing java.awt and javax.swing
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import javax.swing.ImageIcon;

public class Ball {

	// Declare class variables
	private int width, height, x, y;
	private Color col;
	private ImageIcon imgBall;
	//constructor that allows to enter the coordinates, dimensions, and image of the ball
	public Ball(int xPos, int yPos, int ballWidth, int ballHeight,ImageIcon img) {		
		x = xPos;
		y = yPos;
		width = ballWidth;
		height = ballHeight;
		imgBall = img;
	}
	//constructor that allows to enter the coordinates and all other values will be set to default 
	public Ball(int xPos, int yPos) {
		x = xPos;
		y = yPos;
		width = 10;
		height = 10;
		col = Color.PINK;
		imgBall = null;
	}

//Method that allows to set the width of the ball
	public void setWidth(int ballWidth) {
		width = ballWidth;
	}
	//Method that returns an int representing the width of the ball
	public int getWidth() {
		return width;
	}
	//Method that allows to set the image of the ball
	public void setImage(String file) {
		imgBall = new ImageIcon(file);
		width = imgBall.getIconWidth();
		height = imgBall.getIconHeight();
	}
//Method that allows to set the height of the ball
	public void setHeight(int ballHeight) {

		height = ballHeight;
	}
	//Method that returns an int representing the height of the ball
	public int getHeight() {
		return height;
	}
	
//Method that allows to set color of the ball
	public void setColor(Color ballColor) {
		col = ballColor;
	}
//Method that draws the ball
	public void drawBall(Graphics2D g2) {
		g2.drawImage(imgBall.getImage(), x, y,width, height, null);
	}
	//Method that fills the ball
	public void fillBall(Graphics2D g2) {
		g2.setColor(col);
		g2.fill(new Ellipse2D.Double(x, y, width, height));
	}
	//Method that allows to change the X position
	public void setX(int xPos) {
		x = xPos;
	}
	//Method that allows to set the Y position
	public void setY(int yPos) {
		y = yPos;
	}
	//Method that allows to set the height and width of the ball
	public void setDimensions(int bwidth , int bheight){
		width= bwidth;
		height = bheight;
	}
//Method that returns an int representing the X  position of the ball	
	public int getX() {
		return x;
	}
	//Method that returns an int representing the Y position of the ball	
	public int getY()
	{
		return y;
	}
	//Method that returns a rectangle with the same dimensions as the ball and the coordinates of the ball
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	//Method that allows to set both X and Y position
	public void setLocation(int xPos, int yPos) {
		x = xPos;
		y = yPos;
	}
	//Method that allows to center the ball
	public void centre(int frameWidth, int frameHeight) {
		x = frameWidth / 2 - width / 2;
		y = frameHeight / 2 - height / 2;
	}
	//Checks if ball is off screen and returns an int accordingly
	public int isOffScreen(int frameWidth, int frameHeight) {
		if(x<frameWidth|| x>frameWidth||y>frameHeight||y<frameHeight) {
		return 1;
		}
		else {
			return 0;
		}
		}
}
  