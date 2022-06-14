/* [CompareBubbleSelection.java]
 * This program is a template for a comparing sorting algorithms
 * Random numbers are generated and tested with various sorting algorithms
 */

import java.util.Random;
import java.util.Arrays;

class CompareSorts {
  
   /** Main method *******************************************
     * @param arguements
     */ 
   public static void main(String[] args) {     
     
     int data[] = generateNumberArray(10);
     int[] tempArray; // a temp holder for data as it is passed to methods
     long startTime, endTime;
     double elapsedTime;
     
     System.out.println("Array generated: ");
     displayArray(data);
     
     
     //Testing Selection Sort -----------------
     System.out.println("\nSorting with Selection sort:");
     tempArray = Arrays.copyOf(data,data.length); //to keep original arrays safe from modification
     startTime = System.nanoTime();     //start time
     
     tempArray = selectionSort(tempArray);
     
     endTime = System.nanoTime();  //end time
     elapsedTime = (endTime - startTime) / 1000000.0;
     
     displayArray(tempArray);
     System.out.println("The sort took: " + elapsedTime + "ms");
     
     
     //Testing Bubble Sort -----------------
     System.out.println("\nSorting with Bubble sort:");  
     tempArray = Arrays.copyOf(data,data.length); //to keep original arrays safe from modification
     startTime = System.nanoTime();     //start time
     
     tempArray = bubbleSort(tempArray);
     
     endTime = System.nanoTime();  //end time
     elapsedTime = (endTime - startTime) / 1000000.0;
     
     displayArray(tempArray);
     System.out.println("The sort took: " + elapsedTime + "ms");
     
      //Testing Insertion Sort -----------------
     System.out.println("\nSorting with Insertion sort:");  
     tempArray = Arrays.copyOf(data,data.length); //to keep original arrays safe from modification
     startTime = System.nanoTime();     //start time
     
     tempArray = insertionSort(tempArray);
     
     endTime = System.nanoTime();  //end time
     elapsedTime = (endTime - startTime) / 1000000.0;
     
     displayArray(tempArray);
     System.out.println("The sort took: " + elapsedTime + "ms");
     
      //Testing Merge Sort -----------------
     System.out.println("\nSorting with Merge sort:");  
     tempArray = Arrays.copyOf(data,data.length); //to keep original arrays safe from modification
     startTime = System.nanoTime();     //start time
     
     tempArray = mergeSort(tempArray);
     
     endTime = System.nanoTime();  //end time
     elapsedTime = (endTime - startTime) / 1000000.0;
     
     displayArray(tempArray);
     System.out.println("The sort took: " + elapsedTime + "ms");
     
      //Testing Quick Sort -----------------
     System.out.println("\nSorting with Quick sort:");  
     tempArray = Arrays.copyOf(data,data.length); //to keep original arrays safe from modification
     startTime = System.nanoTime();     //start time
     
     tempArray = quickSort(tempArray);
     
     endTime = System.nanoTime();  //end time
     elapsedTime = (endTime - startTime) / 1000000.0;
     
     displayArray(tempArray);
     System.out.println("The sort took: " + elapsedTime + "ms");
     
          
     //Testing Arrays.sort -----------------
     System.out.println("\nSorting with Arrays.sort sort:");
     tempArray = Arrays.copyOf(data,data.length);  //to keep original arrays safe from modification
     startTime = System.nanoTime();     //start time
     
     tempArray = javaBuiltInSort(tempArray);
    
     endTime = System.nanoTime();  //end time
     elapsedTime = (endTime - startTime) / 1000000.0;
     
     displayArray(tempArray);
     System.out.println("The sort took: " + elapsedTime + "ms");
     
   } //end of main method
   
   
   /** generateNumberArray *******************************************
     * Creates a random array on integers
     * @param size of array
     * @return the generated integer array
     */
   public static int[] generateNumberArray(int numOfElements) { 
     
     int[] generated = new int[numOfElements];
     
     //add unique numbers into array in order
     for (int i = 0 ; i< generated.length;i++)
       generated[i]=i;
     
     //shuffle the numbers randomly
     Random randomNumber = new Random();
     for (int i = 0 ; i< generated.length;i++) { 
       //swap to random positions
       int temp;       
       int first = randomNumber.nextInt(generated.length);
       int second = randomNumber.nextInt(generated.length);
       temp = generated[first];
       generated[first]=generated[second];
       generated[second]=temp;
     }
     
     return generated;
   } //end of generateNumberArray
   
   
     /** displayArray *******************************************
     * Sorts a random array on integers using selection sort
     * @param the  integer array
     */
   public static void displayArray(int[] numbers) { 
     for (int i = 0 ; i< numbers.length;i++) {
       System.out.print(numbers[i]+" ");
     }
     System.out.println("");
   }
   
   /** selectionSort *******************************************
     * Sorts a random array on integers using selection sort
     * @param the unsorted integer array
     * @return the sorted integer array
     */
   public static int[] selectionSort(int[] numbers) { 
      //******* Your code here *************

        int minVal;

        for(int i = 0; i < 10; i++) {
            minVal = numbers[i];
            
            for(int j = i+1; j < 10; j ++){
                if( minVal > numbers[j]){

                    //switches the values in order
                    numbers[i] = numbers[j];
                    numbers[j] = minVal;
                    minVal = numbers[i];
                
                }

            }

        }
    
     return numbers;
    
   }
   
   /** bubbleSort *******************************************
     * Sorts a random array on integers using bubble sort
     * @param the unsorted integer array
     * @return the sorted integer array
     */
   public static int[] bubbleSort(int[] numbers) {    
     //******* Your code here *************

     int tempVal;
    boolean swap = false;
    
    for(int i = 0; i <10;i++){
      
      for(int j = 0; j<9; j ++){
        
   
        if(numbers[j] > numbers[j+1]){
          tempVal = numbers[j];
          numbers[j] = numbers[j+1];
          numbers[j+1] = tempVal;
          swap = true;
          
        }
        
        
      }
      if(swap == false){
        i = 10;
      }
      
    }
     return numbers;     
   }

   /** insertionSort *******************************************
     * Sorts a random array on integers using bubble sort
     * @param the unsorted integer array
     * @return the sorted integer array
     */
   public static int[] insertionSort(int[] numbers) {    
     //******* Your code here *************

    int tempVar;
    boolean indexFound = false;
    int index; 
    
    
    for(int i = 0; i<9; i++){
      
      tempVar = numbers[i+1];
      index = i;
      
      while(!indexFound && index!=-1){
        

        if (tempVar<= numbers[index]){
          numbers[index+1] = numbers[index];
          numbers[index] = tempVar;
          index --;
          
        }else{
          indexFound = true;
          
        }
        
        
      }
      
      indexFound = false;
      
    }
     return numbers;     
   }
   
   /** mergeSort *******************************************
     * Sorts a random array on integers using bubble sort
     * @param the unsorted integer array
     * @return the sorted integer array
     */
   public static int[] mergeSort(int[] numbers) {    
     //******* Your code here *************
     return numbers;     
   }
   
   /** quickSort *******************************************
     * Sorts a random array on integers using bubble sort
     * @param the unsorted integer array
     * @return the sorted integer array
     */
   public static int[] quickSort(int[] numbers) {    
     
    //******* Your code here *************
    


     return numbers;     
   }
   
   /** javaBuiltInSort *******************************************
     * Sorts a random array on integers using Arrays.sort
     * @param the unsorted integer array
     * @return the sorted integer array
     */
   public static int[] javaBuiltInSort( int[] numbers) { 
     Arrays.sort(numbers);  //sort
     return numbers;
   }
   
}