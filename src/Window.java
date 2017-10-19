import javax.swing.JFrame;


public class Window extends JFrame {
  
  // The width and height of the window (in pixels).
  public static final int WIDTH = 640;
  public static final int HEIGHT = 640;
  
  // The constructor for the Window class.
  public Window(String title) {
    setTitle(title);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    setSize(WIDTH, HEIGHT);
    setLocationRelativeTo(null);
    setResizable(false);
  }
  
}