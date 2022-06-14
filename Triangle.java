/**
 * [Triangle.java]
 * Constructs a Triangle
 * @author Edward Yang
 * @version 1.0
 * Oct 8, 2020
 */  


import java.lang.Math; //Math class needed to calculate vertices

public class Triangle extends Shapes{

   /**serial Version UID */
   private static final long serialVersionUID = 1L;
    
    /**
   * Constructor to create object "Triangle"
   * @param name The name or type of the shape that is created
   * @param xCoordinates The x-values of the shape's coordinates
   * @param yCoordinates The y-values of the shape's coordinates
   */
    Triangle(String name, int[] xCoordinates, int[] yCoordinates,int color){
        super(name, xCoordinates, yCoordinates, color);
    }

    /**
     * @return the area of the shape
     */
     public double getArea(){

        /**side Lengths of the variables */
        double sideLength1;
        /**side Lengths of the variables */
        double sideLength2;
        /**side Lengths of the variables */
        double sideLength3;

        /**x coordinates of vertices */
        int[] xCoords = getXCoord();
        /**Ycoordinates of vertices */
        int[] yCoords = getYCoord(); 

        sideLength1 = Math.sqrt(Math.pow(xCoords[0]-xCoords[1],2) + Math.pow(yCoords[0]-yCoords[1],2));
        sideLength2 = Math.sqrt(Math.pow(xCoords[0]-xCoords[2],2) + Math.pow(yCoords[0]-yCoords[2],2));
        sideLength3 = Math.sqrt(Math.pow(xCoords[2]-xCoords[1],2) + Math.pow(yCoords[2]-yCoords[1],2));


        //calculate area using huron's formula
        /**total side length of triangle */
        double s = sideLength1 + sideLength2 + sideLength3;

        /**area of triangle */
        double area = Math.sqrt(s*(s - sideLength1)*(s - sideLength2)*(s - sideLength3));

        return area;

     }// end of method

    /**
     * @return the perimeter of the shape
     */

     public double getPerimeter(){

        //declaring the sides of the triangles
        double sideLength1;
        double sideLength2;
        double sideLength3;

        //getting the coordinates
        int[] xCoords = getXCoord();
        int[] yCoords = getYCoord(); 

        //calculating 3 side lengths
        sideLength1 = Math.sqrt(Math.pow(xCoords[0]-xCoords[1],2) + Math.pow(yCoords[0]-yCoords[1],2));
        sideLength2 = Math.sqrt(Math.pow(xCoords[0]-xCoords[2],2) + Math.pow(yCoords[0]-yCoords[2],2));
        sideLength3 = Math.sqrt(Math.pow(xCoords[2]-xCoords[1],2) + Math.pow(yCoords[2]-yCoords[1],2));

        return sideLength1 + sideLength2 + sideLength3;
     } //end of method

}//end of class
 