/**
 * [SimeplLinkeList.java]
 * Program is a linked list for the program QuadTree
 * @see QuadTree.java
 * @author Edward Yang
 * @version 1.0
 * @since 2020.11.3
 * 
 */

public class SimpleLinkedList<E> {
    
    private Node<E> head;
    
    /**
     * Constructor of simple linked list
     */
    SimpleLinkedList(){
      this.head = null;
    }

    /**
     * 
     * @param item adds item to the list
     */
    public void add(E item) { 

        
        if (head == null) {
          head = new Node<E>(item,null);
          
        }else{
          
          Node<E> tempNode = head;

          while(tempNode.getNext()!=null) { 
            tempNode = tempNode.getNext();
          }
          
          tempNode.setNext(new Node<E>(item,null));
          
        }
          
      }

      /**
       * finds item at the index inputted
       * @param index 
       * @return the item at the index
       */
      public E get(int index) { 

        Node<E> tempNode = head;

        for(int i = 0; i < index; i ++){
          tempNode = tempNode.getNext();
        }

        return tempNode.getItem();
      }
      


      public int indexOf(E item) { 
        
        int index = -1;
        Node<E> tempNode = head;  //tempNode used to cycle through the LinkedList
        
        while(tempNode.getItem() != item){ //while loop cycles through the LinkedList
          //everytime the LinkedList cycles through a node, the size counter will increase  
          tempNode = tempNode.getNext();
          index ++;
        }

        if(index != -1){//since index started at -1, it'll be one off
          index ++;
        }

        return index;
      }
      

  
      public boolean remove(String item) { 
        
        return false;
      }
      
      public void clear() { 
      }
      
      /**
       * size is returned
       */
      public int size() { 

        int size = 0;

        Node<E> tempNode = head;  //tempNode used to cycle through the LinkedList
        
        while(tempNode != null){ //while loop cycles through the LinkedList
          //everytime the LinkedList cycles through a node, the size counter will increase  
          size++;  
          tempNode = tempNode.getNext();
        }

        return size;  //when the end of the LinkedList has been reached, return how many nodes the loop has cycled through
      }
      

        
    

}


