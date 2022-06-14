/**
 * [Trapezoid.java]
 * Program creates a trapezoid object
 * @author Edward Yang
 * @version 1.0
 * Oct 8, 2020
 */

public class Trapezoid extends Shapes {

    /**serial Version UID */
    private static final long serialVersionUID = 1L;
    
    /** lower width of the trapezoid */
    private double lowerWidth;
    /**higher width of the trapezoid */
    private double higherWidth;



    /**
     * Constructor creates a trapezoid object from specified parameters
     * @param name name or type of object
     * @param xCoordinates x-coordinates of the objects 
     * @param yCoordinates y-coordinates of the objects
     * @param lowerWidth lower width of the Trapezoid
     * @param higherWidth higher width of the Trapezoid
     */
    Trapezoid(String name, int[] xCoordinates, int[] yCoordinates, int lowerWidth, int higherWidth,int color){
        super(name, xCoordinates, yCoordinates,color);

        this.lowerWidth = lowerWidth;
        this.higherWidth = higherWidth;
    }


    /**
     * Method calculates area of trapezoid object
     * @return the area of the Trapezoid
     */
    public double getArea(){

        //copy yCoordinates to find height of trapezoid
        int[] yCoord = getYCoord();

        return (this.lowerWidth + this.higherWidth)*(yCoord[0] - yCoord[1])/2;//Use trapezoid formula to calculate area
    }

    /**
     * @return returns the perimeter of the trapezoid
     */
    public double getPerimeter(){
        
        double unknownLength1;
        double unknownLength2;
        
        //getting the coordinates
        int[] xCoords = getXCoord();
        int[] yCoords = getYCoord(); 

        //calculate two lengths unknown in the trapezoid
        unknownLength1 = Math.sqrt(Math.pow(xCoords[0]-xCoords[1],2) + Math.pow(yCoords[0]-yCoords[1],2));
        unknownLength2 = Math.sqrt(Math.pow(xCoords[2]-xCoords[3],2) + Math.pow(yCoords[2]-yCoords[3],2));

        return this.lowerWidth + this.higherWidth + unknownLength1 + unknownLength2;
    }

    /**
     * @return returns the lower width of the trapezoid
     */
    public double getLowerWidth(){

        return this.lowerWidth;
    }

    /**
     * @return returns the higher width of the trapezoid
     */
    public double getHigherWidth(){

        return this.higherWidth;
    }



     
}
