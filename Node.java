/**
 * [Node.java]
 * Program is a node class for simple linked lists
 * @author Edward Yang
 * @version 1.0
 * @since 2020/11/5
 */
 

public class Node<E> {
    
    //item stored
    private E item;
    //reference to next item
    private Node<E> next;

    /**
     * Constructor for the class sets reference to next item null, indicating that it is the end of the list
     * @param item takes in stored value and stores it
     */
    public Node(E item, Node<E> previous){
       
        this.item = item;
        this.next = null;


    }

    /**
     * Constructor for the class takes in stored value and sets reference to next item in the list
     * @param item Item value to be store
     * @param next reference to next item in list
     */
    public Node(E item,Node<E> next, Node<E> previous){

        this.item = item;
        this.next = next;

    }

    /**
     * @param next sets reference to next item in list
     */
    public void setNext(Node<E>next){
        this.next = next;

    }

    /**
     * @return returns the reference to the next item in the list
     */
    public Node<E> getNext(){
        return this.next;
    }

    /**
     * @return returns the current item stored in the node
     */
    public E getItem(){

        return this.item;
    }

    /**
     * @param item set current stored value = item
     */
    public void setItem(E item){

        this.item = item;
    }

}
