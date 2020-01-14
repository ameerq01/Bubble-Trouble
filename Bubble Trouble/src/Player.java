//importing java.awt and javax.swing
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Player {
	//declaring class variables
	private double xPos, yPos, width, height;
	private ImageIcon imgPlayer;
	//Constructor that allows the player to  set the image, coordinates and dimensions of the player
	public Player(ImageIcon img, int x,  int y, int pWidth, int pHeight) {
		xPos = x;
		yPos = y;
		imgPlayer = img;
		width = pWidth;
		height = pHeight;
	}
	//Method that returns a rectangle with the dimensions and coordinates of the player
	public Rectangle getBounds() {
		return new Rectangle((int)xPos, (int)yPos,
				(int)width, (int)height);
	}
	//Method that allows to set the X position of the player
	public void setX(double x) {
		xPos = x;
	}
	//Method that allows to set the Y position of the player
	public void setY(double y) {
		yPos = y;
	}
	//Method that allows to set the image of the player
	public void setImage(ImageIcon img) {
		imgPlayer = img;
	}
	//Method that allows to set the with of the player
	public void setWidth(double playerWidth) {
		width = playerWidth;
	}
	//Method that allows to set the height of the player
	public void setHeight(double playerHeight) {
		height = playerHeight;
	}
	//Method that returns an int representing the X position of the player
	public double getX() {
		return xPos;
	}
	//Method that returns an int representing the Y position of the ball	
	public double getY() {
		return yPos;
	}
	//Method that returns an int representing the width of the ball	
	public double getWidth() {
		return width;
	}
	//Method that returns an int representing the height of the ball	
	public double getHeight() {
		return height;
	}
	//Method that returns an the ImageIcon used for the ball	
	public ImageIcon getImage() {
		return imgPlayer;
	}
	//Method that draws the player
	public void drawPlayer(Graphics2D g2) {
		g2.drawImage(imgPlayer.getImage(), (int) xPos,  	(int) yPos, (int) width, (int) height, null);
	}
}
