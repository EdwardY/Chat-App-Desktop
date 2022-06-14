/**
* [QuadTree.java]
* program displays a quadtree collision detection
* @author Edward Yang
* @version 1.0
* @since 2020/11/6
*/

//Graphics &GUI imports
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Color;

//Keyboard imports
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//random number generation
import java.util.Random;



@SuppressWarnings("serial")

class QuadTreeDisplay extends JFrame { 

   
   /**The quadTree data structure for detecting the balls collision */
   static QuadTree tree; 
   /**storing the balls linked list */
   static SimpleLinkedList<BouncingBalls> ballList = new SimpleLinkedList<BouncingBalls>();
   /**Game panel variable for graphics */
   static GameAreaPanel gamePanel;  
   /**random to generate randome variables and position */
   static Random r = new Random();
   /**screen size/boundary of biggest quad tree */
   static int screenSize = 512;
   /**rectangle object to construct the quadTree */
   static  Rectangle rect = new Rectangle(0,0,screenSize,screenSize);
  
   public static void main(String[] args) { 
     new QuadTreeDisplay ();



   }
   
   /**
    * Constructor for the display function
    */
  QuadTreeDisplay () { 
    
    super("QuadTree Fun!");      
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
    gamePanel = new GameAreaPanel();
    this.add(new GameAreaPanel());    
    MyKeyListener keyListener = new MyKeyListener();
    this.addKeyListener(keyListener);
    this.requestFocusInWindow();    
    this.setVisible(true);
    
    //***** Initialize the Tree here *****
    //new QuadTree etc..
    tree = new QuadTree(rect,1);
    
    //Start the game loop in a separate thread (yikes)

    Thread t = new Thread(new Runnable() { public void run() { animate(); }}); //start the gameLoop 
    t.start();
   
  } //End of Constructor


  /**
   * method is the main game loop
   */
  public void animate() { 

    while(true){
    try{ Thread.sleep(50);} catch (Exception exc){exc.printStackTrace();}  //delay
      this.repaint();
    }    
  }
  
  /** --------- INNER CLASSES ------------- **/
  
  // Inner class for the the game area - This is where all the drawing of the screen occurs
  private class GameAreaPanel extends JPanel {

    
    public void paintComponent(Graphics g) {   
       super.paintComponent(g); //required
       setDoubleBuffered(true); 
       g.setColor(Color.BLUE); //There are many graphics commands that Java can use
      
      //create new tree
      tree = new QuadTree(rect,1);

      //adds balls from ballList to QuadTree
      for(int i = 0; i < ballList.size(); i ++){

        tree.add(ballList.get(i));
      }
      


      //calls command to draw QuadTree subdivision rectangles
       recursiveDrawRect(g,tree);
       
      
       ///***** draw some stuff on the screen *****
     
       for(int i = 0; i < ballList.size(); i ++){

          g.setColor(Color.black);  
          //draw individual circles
          g.fillOval((int)Math.round(ballList.get(i).getX()), (int)Math.round(ballList.get(i).getY()),ballList.get(i).getDiameter(),ballList.get(i).getDiameter());
       }

    
      //detects wall collisions
      for(int i = 0; i < ballList.size(); i ++){

        //if x values go out of bounds, reverse the velocity
        if(ballList.get(i).getX() + ballList.get(i).getDiameter() > screenSize){

           ballList.get(i).setXVel(-1.0* Math.abs(ballList.get(i).getXVel() ) );

        }else if(ballList.get(i).getX() +  ballList.get(i).getDiameter()< 0){

           ballList.get(i).setXVel(Math.abs(ballList.get(i).getXVel() ));
           
        }else{

        }

        //if y values go out of bounds, reverse the velocity 
        if(ballList.get(i).getY() + ballList.get(i).getDiameter() > screenSize){

          ballList.get(i).setYVel(-1.0* Math.abs(ballList.get(i).getYVel() ) );

       }else if(ballList.get(i).getY() +  ballList.get(i).getDiameter()< 0){

          ballList.get(i).setYVel(Math.abs(ballList.get(i).getYVel() ));
          
       }else{

       }
        
      
        
        
      }

      //calls upon 
      tree.quadTreeCollision();

      //move every ball
      for(int i = 0; i < ballList.size(); i ++){

        ballList.get(i).move();

      }



      
     
      
       
       
      
    }

    /**
     * Program draws the quad tree division by recursive through each QuadTree node and their children
     * @param g Graphics to draw
     * @param tree Passing throught the "head" of the QuadTree
     * @see Graphics
     */
    public void recursiveDrawRect(Graphics g, QuadTree tree){

      //draw the rectangle of current node
      g.drawRect(tree.getBoundary().getX(), tree.getBoundary().getY(), tree.getBoundary().getWidth(), tree.getBoundary().getHeight());

      //when not a leaf, recurse to children and draw their rectangles
      if(tree.isLeaf() == false){

        for(int i = 0; i < 4; i ++){
          recursiveDrawRect(g,tree.getChildren()[i]);
        }
      }
    }
  }
 
   // -----------  Inner class for the keyboard listener - this detects key presses and runs the corresponding code
    private class MyKeyListener implements KeyListener {
  
      public void keyTyped(KeyEvent e) {}
      public void keyReleased(KeyEvent e) {}
      public void keyPressed(KeyEvent e) {
       
        if (KeyEvent.getKeyText(e.getKeyCode()).equals("A")) {  

          
          //add a random ball
          BouncingBalls tempBall;
          
          int num1 = r.nextInt(screenSize - 19)+10; //these are to ensure that the edge of the ball spawns inside the box
          int num2 = r.nextInt(screenSize - 19) +10 ;
          double xVel = 2.0*r.nextDouble(); //variable for random velocities
          double yVel = 2.0*r.nextDouble();
          int diameter; //variable for random radius

          // sets yVelocity to a random value positive and negative
          if(xVel >= 1.0){
            xVel = 10*(xVel-1);
          }else{
            xVel = -10*xVel;
          }


          // sets yVelocity to a random value positive and negative
          if(yVel >= 1.0){
            yVel = 10.0*(yVel-1);
          }else{
            yVel = -10.0*yVel;
          }
          

          
          
          //generate random radius
          diameter = 20; //generate random radius from 10 - 25

          //creating a new ball 
          tempBall = new BouncingBalls(num1,num2,xVel,yVel, diameter);

          //adding a new ball
          ballList.add(tempBall);
          
          System.out.println("Adding Ball!");
          System.out.println("There is now " + ballList.size() + " balls on your screen");

          //escape key exists program
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) { 
          System.out.println("YIKES ESCAPE KEY!"); 
          System.exit(0);
        } 
      }              
    }  
}


  


