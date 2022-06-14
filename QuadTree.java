/**
 * [QuadTree.java]
 * Program is the head of the subdivisons linked lists Aka quad tree, containg bouncingBalls objects to display balls and detect collisions between balls
 * @author Edward Yang
 * @version 1.0
 * @since 2020/11/6
 */


public class QuadTree {

    /** how many subdivisions can you make*/
    private int MAXDEPTH = 5;

    /**how many balls before a subdivision occurs */
    private int THRESHOLD = 4; 
    /**how many subdivision have been done/current one */
    private int currentDepth;
    /** children nodes (null if not subdivided) */
    private QuadTree[] children; 
    /**homemade linked list storing the balls */
    private SimpleLinkedList<BouncingBalls> ballList = new SimpleLinkedList<BouncingBalls>();
    /**rectangle object storing the lists */
    private Rectangle boundary;


    /**
     * Constructor for the QuadTree
     * @param r
     * @param currentDepth
     */
    QuadTree(Rectangle r,int currentDepth){
        this.boundary = r;
        this.currentDepth = currentDepth;
    }




    /**
     * Method checks if the current node is a leaf
     */
    public boolean isLeaf(){
       
        if (this.children == null){

            return true;

        }else{

            return false;
        }
        
    }     

    /**
     * Program pushes in a ball and pass it along to the children/leaves if necessary, also calls subdivid method to create children if necessary
     * @param ball The ball object being pushed into the tree
     * @see subdivide()
     */
    public void add(BouncingBalls ball){

        //check if max depth has been reached
        if(currentDepth <= MAXDEPTH){
            if(isLeaf() == false){ //is not a leaf, adds to children

                //adds item to children node
                children[findQuadrant(ball)].add(ball);
            
            }else if(ballList.size() < THRESHOLD){//is leaf and doesn't need dividing, adds to list

            //adds item to current list
                ballList.add(ball);

            }else{ //is a leaf and need dividing

                //creates children nodes
                subdivide();
                
                //adds each item to children nodes
                for(int i = 0; i < ballList.size(); i ++){

                    children[findQuadrant(ballList.get(i))].add(ballList.get(i));
                }

            }

        //adds to current list if max depth is reached    
        }else{

            ballList.add(ball);
        }

    }     

    /**
     * Program passes in a ball and checks for the quadrant that it's supposed to be in according to the children of current node
     * @param ball the ball object that is being checked
     * @return returns the quadrant of which the ball object belongs in, 0 is north west, 1 is north east, 2 is south east, 3 is south west
     */
    private int findQuadrant(BouncingBalls ball){

        //Uses if states to check the location of the ball's x and y values to determine which quadrant they belong in

        if( ball.getX() <= boundary.getX() + boundary.getWidth()/2 && ball.getY() <= boundary.getY() + boundary.getHeight()/2 ){

            return 0;
        }else if( ball.getX() >= boundary.getX() + boundary.getWidth()/2 && ball.getY() <= boundary.getY() + boundary.getHeight()/2 ){

            return 1;
        }else if( ball.getX() >= boundary.getX() + boundary.getWidth()/2 && ball.getY() >= boundary.getY() + boundary.getHeight()/2 ){

            return 2;
        }else{
            
            return 3;
        }


        


    }



    /**
     * Method creates a new rectangle object for the creation of subsequent children nodes for the quad tree
     */
    public void subdivide(){


        /**North west rectangle for division */
        Rectangle nw = new Rectangle(boundary.getX(), boundary.getY(), boundary.getHeight()/2, boundary.getWidth()/2);
        /** North east rectangle for division*/
        Rectangle ne = new Rectangle(boundary.getX() + boundary.getWidth()/2, boundary.getY(), boundary.getHeight()/2, boundary.getWidth()/2);
        /**South east rectangle for division */
        Rectangle se = new Rectangle(boundary.getX() + boundary.getWidth()/2, boundary.getY() + boundary.getHeight()/2, boundary.getHeight()/2, boundary.getWidth()/2);
        /**South west rectangle for division */
        Rectangle sw = new Rectangle(boundary.getX(), boundary.getY() + boundary.getHeight()/2, boundary.getHeight()/2, boundary.getWidth()/2);

        children = new QuadTree[4];
        //creates the children of the node
        this.children[0] = new QuadTree(nw, currentDepth + 1);
        this.children[1] = new QuadTree(ne, currentDepth + 1);
        this.children[2] = new QuadTree(se,  currentDepth + 1);
        this.children[3] = new QuadTree(sw,  currentDepth + 1);
    }

    /**
     * Method returns the rectangle object bounding the node
     * @return the rectangle object bounding the node
    */
    public Rectangle getBoundary(){

        return this.boundary;
    }


    /**
     * @return method returns the children of the node
     */
    public QuadTree[] getChildren(){

        return this.children;
    }

    /**
     * @return method returns the list of balls 
     */
    public SimpleLinkedList<BouncingBalls> getBouncingBalls(){

        return ballList;
    }
    

    /**
     * Method checks first if quad tree itself is a leaf node, if it is not, it pushes children nodes through leafCollision
     * @see leafCollision(QuadTree currentNode)
     */
    public void quadTreeCollision(){

        //temporary collided value to change collided 
        boolean tempCollide = false;


        
        if(isLeaf() == true){//if current node is leaf, check for collisions
            

            for(int i = 0; i < ballList.size(); i ++){

                for(int j = i +1; j < ballList.size(); j ++){

                    //x and y coordinates of the center of the balls
                    int x1 = ballList.get(i).getXCent();
                    int y1 = ballList.get(i).getYCent();
                    int x2 = ballList.get(j).getXCent();
                    int y2 = ballList.get(j).getYCent();

                    //delta values
                    int deltaX = x1 - x2;
                    int deltaY = y1 - y2;

                    //get mass of each item, for this program mass will equal to area
                    double mass1 = Math.PI * Math.pow(ballList.get(i).getDiameter(),2);
                    double mass2 = Math.PI * Math.pow(ballList.get(j).getDiameter(),2);
                    
                    //distance between the two centers
                    double distance = Math.sqrt( Math.pow( deltaX , 2 ) + Math.pow( deltaY , 2 ) );

                    
                    //if they are touching, change velocity values accordingly
                    if(ballList.get(i).checkCollide() == false && ballList.get(j).checkCollide() == false){
                        if(distance <= ballList.get(i).getDiameter()/2 + ballList.get(j).getDiameter()/2 ){
                            
                            ballList.get(i).collided();
                            ballList.get(j).collided();

                            tempCollide = true;

                            //initial velocities
                            double xVel1 = ballList.get(i).getXVel();
                            double yVel1 = ballList.get(i).getYVel();
                            double xVel2 = ballList.get(j).getXVel();
                            double yVel2 = ballList.get(j).getYVel();

                            //final velocities using elastic collision formula
                            double FXVel1 = xVel1*(mass1 - mass2)/(mass1+mass2) + xVel2*(2*mass2)/(mass1+mass2);
                            double FYVel1 = yVel1*(mass1 - mass2)/(mass1+mass2) + yVel2*(2*mass2)/(mass1+mass2);
                            double FXVel2 = xVel2*(mass2 - mass1)/(mass1+mass2) + xVel1*(2*mass1)/(mass1+mass2);
                            double FYVel2 = yVel2*(mass2 - mass1)/(mass1+mass2) + yVel1*(2*mass1)/(mass1+mass2);

                            //set velocities after change
                            ballList.get(i).setXVel(FXVel1);
                            ballList.get(i).setYVel(FYVel1);
                            ballList.get(j).setXVel(FXVel2);
                            ballList.get(j).setYVel(FYVel2);

                        //if one ball is already in collision
                        }

                    }else{
                        
                    }
                    

                }

                //if current ball is not collding with anything, set collided to false
                if(tempCollide == false){

                    ballList.get(i).uncollide();
                }

                //reset collide value to false
                tempCollide = false;
            }
        }else{

            

            //children nodes check for collision as well
            for(int i =0; i < 4; i ++){
                if(children[i] != null){
                    children[i].quadTreeCollision();
                }
            }

        }



    }


    
}
