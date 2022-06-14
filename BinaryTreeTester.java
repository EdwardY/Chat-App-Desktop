//TEST PROGRAM
import java.util.Scanner;

class BinaryTreeTester { 

     public static void main(String[] args)  {            
        Scanner input = new Scanner(System.in);
      
        //Create BinaryTree to Store Strings
        BinaryTree<String> bt = new BinaryTree<String>(); 
       
        /*  Perform tree operations  */
        System.out.println("Binary Tree Test\n");          
        char ch;        
        
        do  {
            System.out.println("\nBinary Search Tree Operations\n");
            System.out.println("1. add ");
            System.out.println("2. contains");
            System.out.println("3. remove");
            System.out.println("4. count nodes");
            System.out.println("5. check empty");
 
            int choice = input.nextInt();            
            switch (choice)
            {
            case 1 : 
                System.out.println("Enter a String to add to the tree");
                bt.add( input.next() );                     
                break;                          
            case 2 : 
                System.out.println("Enter String to search");
                System.out.println("Search result : "+ bt.contains( input.next() )); 
                break;                                          
              case 3 : 
                System.out.println("Enter String to remove");
                System.out.println("Search result : "+ bt.remove( input.next() )); 
                break;   
              case 4 : 
                System.out.println("Number of items: "+ bt.size());
                break;     
              case 5 : 
                System.out.println("Empty status: "+ bt.isEmpty());
                break;           
            default : 
                System.out.println("Invalid Entry \n ");
                break;   
            }
           
            /*  Display tree  */ 
            System.out.print("\nDisplaying Tree In Order : ");
            bt.displayInOrder();

 
            System.out.println("\n\nDo you want to continue (Type y or n) \n");
            ch = input.next().charAt(0);        
            
    
          } while (ch == 'Y'|| ch == 'y');
          
          input.close();
    }

 }