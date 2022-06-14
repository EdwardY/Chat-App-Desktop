//BINARY TREE TEMPLATE
//Complete the methods as specified
//Note - the extends Comparable ensures that items added to the tree should be Comparable

class BinaryTree<T extends Comparable<T>> { 
  private BinaryTreeNode<T> root;
 

  //************ METHODS TO BE COMPLETED BELOW  ************
  
  //Displays all the items in the tree IN ORDER to the console
  //not a typical method - but very useful for testing/debugging
  //use a recursove helper method help method
  public void displayInOrder() { 

    recursiveDisplayInOrder(root);
  }
  
  private void recursiveDisplayInOrder(BinaryTreeNode<T> currentNode) { 
     //hint 1. recurse left, 2. display current item, 2. recurse right

    
    // just make sure the currentNode is not null!    
    if(currentNode.getItem() != null){
      recursiveDisplayInOrder(currentNode.getLeft());
      System.out.print(" " + currentNode.getItem() + " ");
      recursiveDisplayInOrder(currentNode.getRight());
    }


  }
  
  
  //adds an item to the tree in the correct place (this is a Binary Search Tree - sorted)
  public void add(T item)  { 

    recursiveAdd(this.root, item);    

  }

  //helper recursive method to add an object

  private void recursiveAdd(BinaryTreeNode<T> currentNode, T item){

    if(currentNode.getItem().equals(null)){
      currentNode.setItem(item);
    }else if (currentNode.getItem().compareTo(item) == 0){
      //do nothing

    }else if(currentNode.getItem().compareTo(item) > 0){

      recursiveAdd(currentNode.getLeft(),item);
    }else{
      recursiveAdd(currentNode.getRight(), item);

    }


  }
  
  
  //traverses the tree to find an item and returns true if it exists (use CompareTo)
  //hint - use a private recursive helper method
  public boolean contains(T item) { 

    searchTree(this.root,item);
    return false;
  }

  private boolean searchTree(BinaryTreeNode<T> currentNode, T item){
    
    boolean returnVal = false;//value for searching the 
    if(currentNode.getItem().equals(null)){
      returnVal = false; 

    }else if (currentNode.getItem().compareTo(item) == 0){
      returnVal = true;
    }else if(currentNode.getItem().compareTo(item) > 0){

      returnVal = searchTree(currentNode.getLeft(),item);

    }else{

      returnVal = searchTree(currentNode.getRight(),item);
    }

    return returnVal;
  }


  //challenge - remove an item while keeping the tree in order
  //three possibilites:
  //1 - removing a leaf is straight forward
  //2 - remove a node with one child. The child takes the place of the one removed
  //3 - two chilren - find the larget item in the left subtree to take the place of the removed node
  public boolean remove(T item) { 
    

     recursiveRemove(this.root, item);
    return false;
  }
  

  private void recursiveRemove(BinaryTreeNode<T> currentNode,T item){
    
    if(currentNode.getItem().compareTo(item) > 0){

      recursiveRemove(currentNode.getLeft(), item);

    }else if( currentNode.getItem().compareTo(item) < 0){

      recursiveRemove(currentNode.getRight(), item);

    }else if( currentNode.getItem().compareTo(item) == 0){
      //remove item
    }




  }


  //************ METHODS TO BE COMPLETED ABOVE  ************
  

  /* size
   * returns the number of items in the tree
   * @return an integer representing the number of items stored in the tree
   */
  public int size() { 
    return sizeRecursive(root);
  }
  
  //recursive helper method for size()
  private int sizeRecursive(BinaryTreeNode<T> currentNode) { 
    if (currentNode == null) { 
      return 0;
    } else { 
      int numberOfChildNodes = 0;
      numberOfChildNodes+=sizeRecursive(currentNode.getLeft());
      numberOfChildNodes+=sizeRecursive(currentNode.getRight());
      return numberOfChildNodes + 1; //add the current node to the count
    }
  }
  
  /** isEmpty
    * Determines if the binary tree is empty, no data exists
    * @return true if the binary tree contains no data, otherwise false
    */
  public Boolean isEmpty() { 
    return (root==null);
  }
  
} //end of BinaryTree



//**********************************
//    Binary Tree Node Class
//**********************************

/**
 * BinaryTreeNode
 * A Node class for a Binary Tree
 * @author Mangat
 * @version 1.0 2020
 */
class BinaryTreeNode<E> {
  private E item;
  private BinaryTreeNode<E> left;
  private BinaryTreeNode<E> right;
  
  BinaryTreeNode(E i, BinaryTreeNode<E> l,BinaryTreeNode<E> r){
    
    this.item=i;
    this.left=l;
    this.right=r;


  }

  
  public void setLeft(BinaryTreeNode<E> n)  {
    this.left = n;
  }
  
  public void setRight(BinaryTreeNode<E> n)  {
     this.right = n;
  }

  public BinaryTreeNode<E> getLeft() {
    return this.left;
  }

  public BinaryTreeNode<E> getRight() {
    return this.right;
  }

  public void setItem(E d){
     this.item = d;
  }

  public E getItem()  {
    return this.item;
  }     
    
  /** isLeaf
    * determines if the the current node is a leaf
    * @returns true if the current node has no children, otherwise false
    */
  public boolean isLeaf() { 
    if (this.left == null && this.right == null) { 
      return true;
    }
      return false;
  }
  
}