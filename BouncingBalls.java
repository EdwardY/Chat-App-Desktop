/**
 * [BouncingBalls.java]
 * Program is an object for balls
 * @author Edward Yang
 * @version 1.0
 * @since 2020.11.6
 */


public class BouncingBalls {
    /**The x coordinate of the ball */
    private double x;
    /**the y coordinate of the ball */
    private double y;
    /**the x velocity of the ball */
    private double xVel; 
    /**the y velocity of the ball */
    private double yVel;
    /**the diameter of the ball */
    private int diameter;
    /**Boolean checks if ball has collided */
    private boolean collided = false;


    /**
     * Constructs a point object with speed and coordinates in both x and y axis
     * @param x The x coordinate of the ball 
     * @param y the y coordinate of the ball
     * @param xVel The x velocity of ball
     * @param yVel y velocity of ball
     * @param yBound y boundary
     * @param xBound x boundary
     * @param diameter radius of ball

     */
    BouncingBalls(double x, double y, double xVel, double yVel, int diameter){
        this.x = x;
        this.y = y;
        this.xVel = xVel;
        this.yVel = yVel;

        this.diameter = diameter;

    }

    /**
     * @return returns the x position of the ball
     */
    public double getX(){

        return this.x;
    }

    /**
     * @return returns the y position of the ball
     */
    public double getY(){

        return this.y;
    }

    /**
     * @return returns xVel
     */
    public double getXVel(){

        return this.xVel;
    }

    /**
     * @return returns yVel
     */
    public double getYVel(){

        return this.yVel;
    }

    /**
     * Sets a new x coordinate value for object
     * @param x the x values to be set
     */
    public void setXCoord(double x){

        this.x = x;
    }

    /**
     * Sets a new y coordinate value for object
     * @param y the y values to be set
     */
    public void setYCoord(double y){

        this.y = y;
    }

    /**
     * 
     * @param xVel sets Object's xVel = xVel passed through
     */
    public void setXVel(double xVel){
        
        this.xVel = xVel;
    }

    /**
     * @param yVel sets Object's yVel = yVel passed through
     */
    public void setYVel(double yVel){

        this.yVel = yVel;
    }

    /** 
     * change the ball's x and y coodinate according to velocity
    */
    public void move(){

        this.x += this.xVel;
        this.y += this.yVel;
    }

    /**
     * detects if the ball hit the wall, if it does, it switches velocities 
     */
    public void wallCollision(){

    }


    /**
     * @return returns the radius of the ball
     */
    public int getDiameter(){
        return this.diameter;
    }

    /**
     * @return returns the x coordinate of center of the ball
     */
    public int getXCent(){
        return (int)(this.x + this.diameter/2);
    }

    /**
     * @return returns the y coordinate of center of ball
     */
    public int getYCent(){
        return (int)(this.y + this.diameter/2);
    }


    /**
     * method sets collided to true 
     */
    public void collided(){
        this.collided = true;
    }

    /**
     * method sets collided to false
     */
    public void uncollide(){
        this.collided = false;
    }

    /**
     * @return returns the collided value
     */
    public boolean checkCollide(){
        return this.collided;
    }
}
