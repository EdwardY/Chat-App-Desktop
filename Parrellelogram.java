/**
 * [Parrelelogram.java]
 * Program constructs a parrellelogram object
 * @author Edward Yang
 * @version 1.0
 * Oct 8, 2020
 */



public class Parrellelogram extends Shapes {

    /**serial Version UID */
    private static final long serialVersionUID = 1L;
    
    /**width of the Parrellelogram */
    private int width; 
    /**area of the parrellelogram */
    private double area;

    /**
     * Constructor creates a Parrellelogram object
     * @param name name and type of the object
     * @param xCoordinates x-coordinates of the object
     * @param yCoordinates y-coordinates of the the objects
     * @param width the width of the parrellelogram
     */
    Parrellelogram(String name, int[] xCoordinates, int[] yCoordinates, int width,int color){
        
        super(name, xCoordinates, yCoordinates, color);
        this.width = width;
        this.area = width* (yCoordinates[0] - yCoordinates[1]); //width*height = area, storing area because potential change in orientation will make it difficult to calculate area
    }

    /**
     * @return the area of the Parrellelogram
     */
    public double getArea(){
        return this.area;
    }

    /**
     * @return the perimeter of the Parrellelogram
     */
    public double getPerimeter(){

        //getting the coordinates of the parrallelogram
        int[] xCoords = getXCoord();
        int[] yCoords = getYCoord(); 


        double length1;
        double length2;
        double length3;
        double length4;

        length1 = Math.sqrt(Math.pow(xCoords[0]-xCoords[1],2) + Math.pow(yCoords[0]-yCoords[1],2));
        length2 = Math.sqrt(Math.pow(xCoords[1]-xCoords[2],2) + Math.pow(yCoords[1]-yCoords[2],2));
        length3 = Math.sqrt(Math.pow(xCoords[2]-xCoords[3],2) + Math.pow(yCoords[2]-yCoords[3],2));
        length4 = Math.sqrt(Math.pow(xCoords[3]-xCoords[0],2) + Math.pow(yCoords[3]-yCoords[0],2));

        return length1 + length2 + length3 + length4;//returns the 4 lengths added together
    }


    /**
     * @return return the width of the parrellelogram
     */
    public int getWidth(){

        return this.width;
    }

    
}
