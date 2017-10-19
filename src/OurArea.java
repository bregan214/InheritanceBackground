import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class OurArea extends Area implements KeyListener{
	
	//whether the user is controlling the ship or not
	private boolean isShip = false;
  
 // number of trees that will be on the island 
  private int numTrees;
  // The constructor for OurArea. 
  public OurArea() {
    super(); 
    //Generates 0-22 trees
    numTrees = (int)(Math.random() * 23);
    // x, y are coordinates for a tree
    // p is a location in the ArrayList treeCoord
    int x, y ,p;
     
    // an array to store the tree tiles
    layer1 = new Tile[numTrees];
    //an array to store the background tiles (wall, grass, water)
    tiles = new Tile[numTilesX][numTilesY];
    //Initialize the background tiles
    for (int i = 0; i < numTilesX; i++) {
      for (int j = 0; j < numTilesY; j++) {
        // Creates the wall border
    	  	if (i ==0 || j == 9 || i == 9 || j == 0 ) {
    	  		tiles[i][j] = new Wall(i,j);
    	  	}
    	  	//creates the main island
    	  	else if ((j>=3 && j<=6)&& (i>=3 && i<=6)) {
    	  		tiles[i][j] = new Grass(i,j);
    	  // Adds grass tiles to treeCoord 
    	  			treeCoord.add(tiles[i][j]);
    	  	}
    	  	// Fills in the remaining tiles with water
    	  	else {
        tiles[i][j] = new Water(i, j);
    	  	}
    	  
  
      }
    }
    // Adds more tiles to the island
	tiles[2][2] = new Grass(2,2);
  	tiles[2][3] = new Grass(2,3);
  	tiles[3][2] = new Grass(3,2);
  	tiles[7][7] = new Grass(7,7);
  	tiles[7][6] = new Grass(7,6);
  	tiles[6][7] = new Grass(6,7);
  	tiles[4][4] = new Grass(4,4);
  	// Adds island extension to ArrayList of grass tiles (tree locations) 
  	treeCoord.add(tiles[2][2]);
  	treeCoord.add(tiles[3][2]);
	treeCoord.add(tiles[7][6]);
	treeCoord.add(tiles[2][3]);
  	treeCoord.add(tiles[7][7]);
	treeCoord.add(tiles[6][7]);
	

  	// picks a random grass tile from the ArrayList of grass tiles
	// and plants the tree there
  	for (int i = 0; i<numTrees; i++) {
  	   p = (int)(Math.random() * treeCoord.size());
  	   x = treeCoord.get(p).get_x();
  	   y = treeCoord.get(p).get_y();
  	   
    layer1[i] = new ShortTree(x, y);
    // removes location from ArrayList so only one tree is
    //planted per grass tile
    treeCoord.remove(p);
  
  }
  	//Initialize the character and ship
  	hero = new Hero(5,4);
  	ship = new Ship(2,4);
  }
  
  // This function must draw each tree to the screen.
  protected void drawLayer1() {
    // Draws the trees.
    for (int i = 0; i < numTrees; i++) {
    			drawLayer_1(i);
    			
    		}
      
    }
    //*/
  
  
  protected void drawTiles() {
    //Draws the background tiles
    for (int i = 0; i < numTilesX; i++) {
      for (int j = 0; j < numTilesY; j++) {
    	
       drawTile(i,j);
  
      }
    }
    //*/
  }
  
  
  

@Override
public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void keyPressed(KeyEvent e) {
	//coordinates of the character and ship
	int x,y;
	//Only controls the ship when it should be controlled 
	if (!isShip) {	
		x = hero.get_x();
		y = hero.get_y();
	}
	else {
		x = ship.get_x();
		y = ship.get_y();
	}
	// The number of the key pressed
	int key = e.getKeyCode();
	// Different conditions for each arrow key press
	    switch( key ) { 
	  // Up arrow pressed
	        case KeyEvent.VK_UP:
	        	// limits the character to the area in the walls
		        	if (y==1) {
		        		System.out.println("You've hit a wall!");
		        	}
		    // Moves the appropriate character up
		        	else {
			        	y--; // Changes the coordinate for the move
			  //Move the ship when appropriate
			        	if ((x == ship.get_x() && y == ship.get_y()) || isShip) {
			        		isShip = true;//Sets control to ship
			        // If the ship hits grass, return control to hero
			        		if ((y>=3 &&y<=6) &&(x<7&&x>2) || (y==2 && (x ==3||x ==2)
				        			|| (y==7 && (x ==6||x ==7))
				        			||(x==2 && (y ==2||y ==3) )
				        			|| (x==7 && (y ==6||y ==7)))) {
			        			isShip = false;
			        		}
			        	// Otherwise keep moving the ship
			        		else {
			        			hero.set_location(20, 20);
			        			ship.set_location(x, y);
			        			repaint();
			        		}
			        	}
			 //Move the hero when appropriate
			        //Use the hero image when on grass
			        	if (!isShip && ((y>=3 &&y<=6) &&(x<7&&x>2) || (y==2 && (x ==3||x ==2)
			        			|| (y==7 && (x ==6||x ==7))
			        			||(x==2 && (y ==2||y ==3) )
			        			|| (x==7 && (y ==6||y ==7))))) {
				    		hero.set_location(x, y);
			        		hero.setImage("Hero.png");
				    		repaint();
			        	}
			        	// Use the fish image and move the fish
			        	else {
			        		if (!isShip) {
				        		hero.set_location(x, y);
				        		hero.setImage("Fish.png");
				        		repaint();
				        		}
			        	}
		        	}
	        break;
	        //Down arrow pressed
	        case KeyEvent.VK_DOWN:
	        	//Limits the character to the area inside the walls
		        	if (y==8) {
		        		System.out.println("You've hit a wall!");
		        	}
		        	//Moves the character down
		        	else {
			        	y++; // Changes the coordinate to move
			        	//Moves the ship when appropriate
			        	if ((x == ship.get_x() && y == ship.get_y()) || isShip) {
			        		isShip = true;
			        		if ((y>=3 &&y<=6) &&(x<7&&x>2) || (y==2 && (x ==3||x ==2)
				        			|| (y==7 && (x ==6||x ==7))
				        			||(x==2 && (y ==2||y ==3) )
				        			|| (x==7 && (y ==6||y ==7)))) {
			        			isShip = false;
			        		}	
			        		else {
			        		hero.set_location(20, 20);
			        		ship.set_location(x, y);
			        		repaint();
			        		}
			        	}
			        	//Moves the hero when appropriate with the proper image
			        	if (!isShip && ((y>=3 &&y<=6) &&(x<7&&x>2) || (y==2 && (x ==3||x ==2)
			        			|| (y==7 && (x ==6||x ==7))
			        			||(x==2 && (y ==2||y ==3) )
			        			|| (x==7 && (y ==6||y ==7))))) {
				    		hero.set_location(x, y);	        		
				    		hero.setImage("Hero.png");
				    		repaint();
			        	}
			        	//Uses fish image and moves the fish 
			        	else {
			        		if (!isShip) {
			        		hero.set_location(x, y);
			        		hero.setImage("Fish.png");
			        		repaint();
			        		}
			        		
			        	}
		        	}
	        break;
	        case KeyEvent.VK_LEFT:
	         	//Limits the character to the area inside the walls
		        	if (x==1) {
		        		System.out.println("You've hit a wall!");
		        	}
		        // Moves the character left
		        	else {
		        		
			        	x--;// Changes coordinate to move
			        	//Moves the ship when appropriate
			        	if ((x == ship.get_x() && y == ship.get_y()) || isShip) {
			        		isShip = true;
			        		if (((x>=3 &&x<=6) &&(y<7&&y>2) || (y==2 && (x ==3||x ==2)
				        			|| (y==7 && (x ==6||x ==7))
				        			||(x==2 && (y ==2||y ==3) )
				        			|| (x==7 && (y ==6||y ==7))))) {
			        			isShip = false;
			        		}
			        		else {
			        		hero.set_location(20, 20);
			        		ship.set_location(x, y);
			        		repaint();
			        		}
			        	}
			        //Moves the hero with the proper image
			        	if (!isShip && ((x>=3 &&x<=6) &&(y<7&&y>2) || (y==2 && (x ==3||x ==2)
			        			|| (y==7 && (x ==6||x ==7))
			        			||(x==2 && (y ==2||y ==3) )
			        			|| (x==7 && (y ==6||y ==7))))) {
				    		hero.set_location(x, y);
			        		hero.setImage("Hero.png");
				    		repaint();
			        	}
			        //Uses fish image and moves the fish 
			        	else {
			        		if (!isShip) {
				        		hero.set_location(x, y);
				        		hero.setImage("Fish.png");
				        		repaint();
				        		}
			        	}
		        	}
	        break;
	        case KeyEvent.VK_RIGHT :
	        	//Limits the character to the area inside the walls
	        	if (x==8) {
	        		System.out.println("You've hit a wall!");
	        	}
	        	//Moves the character right
	        	else {
	        	x++;// Changes coordinate to move
	        	//Moves the ship when appropriate
	        	if ((x == ship.get_x() && y == ship.get_y()) || isShip) {
	        		isShip = true;
	        	 	if ((x>=3 &&x<=6) &&(y<7&&y>2) || (y==2 && (x ==3||x ==2)
		        			|| (y==7 && (x ==6||x ==7))
		        			||(x==2 && (y ==2||y ==3) )
		        			|| (x==7 && (y ==6||y ==7)))) {
	        	 		isShip = false;
	        	 	}
	        	 	else {
	        	 		hero.set_location(20, 20);
		        		ship.set_location(x, y);
		        		repaint();
	        	 	}
	        	}
	        	//Moves the hero when appropriate with the proper image
	        	if (!isShip && ((x>=3 &&x<=6) &&(y<7&&y>2) || (y==2 && (x ==3||x ==2)
	        			|| (y==7 && (x ==6||x ==7))
	        			||(x==2 && (y ==2||y ==3) )
	        			|| (x==7 && (y ==6||y ==7))))) {
		    		hero.set_location(x, y);
	        		hero.setImage("Hero.png");
		    		repaint();
	        	}
	        	//Uses fish image and moves the fish
	        	else {
	        		if (!isShip) {
		        		hero.set_location(x, y);
		        		hero.setImage("Fish.png");
		        		repaint();
		        		}
	        	}
	        	}
	        break;
	     }
	}

	  
	

@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub

	  
}
  
  
}
