//Importing java.awt and javax.swing
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Arrow {
	//Declaring class variables
	private int width, height, x, y;
	private ImageIcon img;
//Constructor that allows to set the image, coordinates, and dimensions of the arrow
	public Arrow(ImageIcon arro, int xPos, int yPos, int aWidth, int aHeight)
	{
		img = arro;
		x= xPos;
		y=yPos;
		width = aWidth;
		height= aHeight;
	}
	//Returns a rectangle with the coordinates and dimensions of the arrow
	public Rectangle getBounds() {
		return new Rectangle(x,y,width,height);
	}
	//Allows to set the X position of the arrow
	public void setX(int xPos){
		x=xPos;
	}
	//Allows to set the Y position of the arrow
	public void setY(int yPos) {
		y= yPos;
	}
	//Method that returns an int representing the Y position of the arrow
	public int getY() {
		return y;
	}
	//Method that returns an int representing the Height of the arrow
	public int getHeight()
	{
		return height;
	}
//Method that returns an int representing the width of the arrow
	public int getWidth()
	{
		return width;
	}
	//Method that draws the arrow
	public void drawArrow(Graphics2D g2) {
		g2.drawImage(img.getImage(),x,y,width,height,null);
	}
}