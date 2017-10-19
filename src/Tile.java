import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.io.IOException;
import javax.swing.JPanel;
import javax.imageio.ImageIO;


public class Tile {

	 private int x;
	  private int y;
	  
	  
	  // The size of the tree. For our purposes, this is
	  // known to be the size of the tree image.
	  public static final int WIDTH = 64;
	  public static final int HEIGHT = 64;
	  
	  // The image of the tree.
	  protected BufferedImage tileImage;
	  
	  // The constructor for the Tree class. This allows
	  // the user to specify the location of a tree.
	  public Tile(int x, int y, String fileName) {
	    try {
	     tileImage = ImageIO.read(new URL("file:" + fileName));
	    } catch (IOException e) {
	      System.out.println("Failed to load" + fileName + "image.");
	    }
	    
	    set_location(x, y);
	  }
	  
		  
	public void setImage(String imageName) {
		 try {
			    tileImage = ImageIO.read(new URL("file:" + imageName));
			 } catch (IOException e) {
			     System.out.println("Failed to load" + imageName + "image.");
			 }
	}
	  // Set the location of the tree.
	  public void set_location(int x, int y) {
	    this.x = x;
	    this.y = y;
	  }
	  
	  // Get the x-axis location of the tree.
	  public int get_x() {
	    return this.x;
	  }
	  
	  // Get the y-axis location of the tree.
	  public int get_y() {
	    return this.y;
	  }
	  
	  // Draw the tree at its location in the window.
	  public void draw(Graphics2D g2) {
	    g2.drawImage(tileImage, null, x*WIDTH, y*HEIGHT);
	  }
	  
	}


