/**
 * [Rhombus.java]  
 * Programs creates a Rhombus object
 * @author Edward Yang
 * @version 1.0
 * Oct 8, 2020
 */
 
public class Rhombus extends Parrellelogram{
    
    /**serial Version UID */
    private static final long serialVersionUID = 1L;
    /**
     * Constructor for the object Rhombus
     * @param name name or type of object
     * @param xCoordinates x-coordinates of the rhombus
     * @param yCoordinates y-coordinates of the rhombus
     * @param width one side length/width of the rhombus
     */
    Rhombus(String name, int[] xCoordinates, int[] yCoordinates, int width ,int color){
        
        super(name, xCoordinates, yCoordinates, width, color);        
    }

}



