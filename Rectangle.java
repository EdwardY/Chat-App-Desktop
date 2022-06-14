/**
 * [Rectangle.java]
 * program creates a rectangle object, remade the rectangle object from Shapes OOP project into one better suited for QuadTree
 * @author Edward Yang
 * @version 2.0
 * @since 2020/11/5
 */

public class Rectangle{


    /**height of the rectangle */
    private int height;
    /**width of the rectangle */
    private int width;
    /**x coordinate of top left of rectangle */
    private int x;
    /**y coordinate of top left of rectangle */
    private int y;


    /**
     * Contructor for the rectangle object
     * @param x  
     * @param y
     * @param height
     * @param width 
     */
    Rectangle(int x, int y, int height, int width){
        
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;
    }

    /**
     * 
     * @return returns the x coordinate value of rectangle's top left corner
     */
    public int getX(){
        return this.x;
    }

    /**
     * 
     * @return returns the y coordinate value of rectangle's top left corner
     */
    public int getY(){
        return this.y;
    }

    /**
     * @return return the width of the rectangle
     */
    public int getWidth(){

        return this.width;
    }

    /**
     * @return returns the height of the rectangle
     */
    public int getHeight(){

        return this.height;
    }
}
