/**
 * [Circle.java]
 * program creates circle object
 * @author Edward Yang
 * @version 1.0
 * Oct 8, 2020
 */

public class Circle extends Ellipse{

    /**serial Version UID */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor to create object "circle"
     * @param name Name of type of the object
     * @param xCoordinate the coordinates of the circle
     * @param yCoordinate the coordinates of the circle
     * @param height height of the circle
     */
    Circle(String name, int[] xCoordinate, int[] yCoordinate, int height,int color){
            
        super(name, xCoordinate, yCoordinate, height, height, color);
    }
    
}
