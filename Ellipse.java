/**
 * [Ellipse.java]
 * program creates an Ellipse object
 * @author Edward Yang
 * @version 1.0
 * Oct 8, 2020
 */ 

public class Ellipse extends Shapes{

    /**serial Version UID */
    private static final long serialVersionUID = 1L;

    /**height of the Ellipse */
    private int height;
    /**width of the ellipse */
    private int width;


    /**
     * Constructor to create object "Ellipse"
     * @param name Name of type of the object
     * @param xCoordinates the coordinates of the ellipse
     * @param yCoordinates the coordinates of the ellipse
     * @param width width of the ellipse
     * @param height height of the ellipse
     */
    Ellipse(String name,int[] xCoordinates, int[] yCoordinates, int width, int height,int color){

        //creates the object
        super(name, xCoordinates,yCoordinates,color);
        this.width = width;
        this.height = height;

    }

    /**
     * @return returns area of shape
     */
    public double getArea(){
        
        //returns the area of the ellipse
        return Math.PI*this.height*this.width;
    }


    /**

     * @return returns perimeter of shape
     */
    public double getPerimeter(){

        //this is only an estimation formula for an ellipse perimeter, as a real formula requires calculus
        return 2*Math.PI*Math.sqrt( (Math.pow(this.height,2) + Math.pow(this.width,2))/2 );
    }
    
    /**
     * @return return the width of the Ellipse
     */
    public int getWidth(){

        return this.width;
    }

    /**
     * @return returns the height of the Ellipse
     */
    public int getHeight(){

        return this.height;
    }
}
