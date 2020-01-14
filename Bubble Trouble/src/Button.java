//Importing java.awt and javax.swing
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Button {
	//Declaring class variables
	int x, y, width, height;
	ImageIcon img;
	//Constructor that allows to set coordinates, dimensions, and image of the button
	public Button(int xPos, int yPos, int bWidth, int bHeight, ImageIcon imag)
	{
		x = xPos;
		y=yPos;
		width =bWidth;
		height = bHeight;
		img = imag;
	}
	//Method that returns a rectangle with the coordinates and dimensions of the button 
	public Rectangle getBounds() {
		return new Rectangle(x,y,width,height);
	}
	//Method that allows to set the location of the button
	public void setLocation(int xPos, int yPos)
	{
		x = xPos;
		y=yPos;
	}
	//Method that draws the button
	public void drawButton(Graphics2D g2) {
		g2.drawImage(img.getImage(),x,y,width,height,null);
	}
}