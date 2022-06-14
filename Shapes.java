/**
 * [Shapes.java]
 * an abstract class template for different shapes
 * @author Edward Yang
 * @version 1.0
 * Oct 8, 2020
 */ 
    
import java.io.Serializable;

public abstract class Shapes implements ShapeTranslation, Serializable{

    /**serial Version UID */
    private static final long serialVersionUID = 1L;
    /**Name of the shape */
    private String name;
    /**x coordinates of the shape */
    private int[] xCoordinates;
    /**y coordinates of the shape */
    private int[] yCoordinates;
    /**color of the shape */
    private int color;


     /**
   * Constructor to create object "Ellipse"
   * @param name The name or type of the shape that is created
   * @param xCoordinates The x-values of the shape's coordinates
   * @param yCoordinates The y-values of the shape's coordinates
   */
    Shapes(String name, int[] xCoordinates, int[] yCoordinates,int color){
        this.name = name;
        this.xCoordinates = xCoordinates;
        this.yCoordinates = yCoordinates;
        this.color=color;

    }


    /**
     * @return the area of the shape
     */
    abstract double getArea();

    /**
     * @return the perimeter of the shape
     */
    abstract double getPerimeter();

    /**
     * @return the the x-coordinates of the shape
     */
     public int[] getXCoord(){
         return this.xCoordinates;
     }

     /**
     * @return the the y-coordinates of the shape
     */
    public int[] getYCoord(){
        return this.yCoordinates;
    }

    /**
     * @return returns the name and type of the object
     */
    public String getName(){
        return this.name;
    }

    /**
     * @return the number corresponds to color 
     *  1. Black
        2. Blue
        3. Red
        4. Purple
        5. Green
        6. Yellow
     */
    public int getColor(){
        return this.color;
    }


    /**
     * Method translates the shapes's coordinates
     * @param x translates the shape's x-coordinate by x amount
     * @param y translates the shape's y-coordinate by y amount
     */

    public void translate(int x,int y){
        for(int i = 0; i < xCoordinates.length; i++){

            xCoordinates[i] += x;
            yCoordinates[i] += y; //this adjusts for the fact that 0,0 is in the top left corner in java


        }

    }
    


    
}
    
