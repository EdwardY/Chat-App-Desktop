public class DoubleNode<E> {
    
    private DoubleNode<E> previous;
    private E item;
    private DoubleNode<E> next; 


    public DoubleNode(E item){
        
        
        this.item = item;
        this.next = null; 

    }

    public DoubleNode(E item, DoubleNode<E> next){
        this.item = item;
        this.next = next;
    }

    public void setNext(DoubleNode<E>next){
        this.next = next;

    }

    public DoubleNode<E> getNext(){
        return this.next;
    }

    public E getItem(){

        return this.item;
    }

}
