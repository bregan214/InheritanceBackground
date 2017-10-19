import java.awt.*;
import javax.swing.JPanel;
import java.util.*;


public class Area extends JPanel {

 
 // ArrayList of grass tiles (a.k.a. suitable location for trees) 
  protected ArrayList<Tile> treeCoord = new ArrayList<Tile>();
  // The area tile map.
  protected Tile tiles[][];
  //Array that hold the trees
  protected Tile layer1[];
  //Variable for the hero
  protected Tile hero;
  //Variable for the ship
  protected Tile ship;
  
 
  
  // To hide this parameter from being passed around.
  private Graphics2D g2;
  
  // Define some constants.
 
  protected static final int numTilesX = Window.WIDTH / 64;
  protected static final int numTilesY = Window.HEIGHT / 64;
  
  
  
  // The constructor for the Area class.
  public Area() {
    
    
    
    g2 = null;
    
    setBackground(Color.BLACK);
    setPreferredSize(new Dimension(Window.WIDTH, Window.HEIGHT));
  }
  
  
  
 
  
  // Overridden function from JPanel, which allows us to
  // write our own paint method which draws our area.
  @Override
  public void paint(Graphics g) {
    // This calls JPanel's paintComponent method to handle
    // the lower-level details of drawing in a window.
    super.paint(g);
    
    g2 = (Graphics2D)g;
    //Draw Background tiles (water,grass,wall)
    drawTiles();
    //Draw the Ship
    drawShip();
    //Draw the character
    drawHero();
    //Draw the trees
    drawLayer1();
    
    // Sync for cross-platform smooth rendering.
    Toolkit.getDefaultToolkit().sync();
  }
  
  protected void drawTiles() {
    // TODO: Implement in a child class.
  }
  
  protected void drawLayer1() {
    // TODO: Implement in a child class.
	  
  }
  
  
  protected void drawLayer_1(int i) {
	  if (layer1[i] !=null) {
		  layer1[i].draw(g2);
	  }
  }
  protected void drawTile(int i,int j) {
	  if (tiles != null) {
		  tiles[i][j].draw(g2);
		  
	  }
  }
  protected void drawHero() {
	  if (hero != null) {
		  hero.draw(g2);
	  }
  }
  protected void drawShip() {
	  if (ship != null) {
		  ship.draw(g2);
	  }
  }
  
}