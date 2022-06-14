/**
 * [ChatHistory.java]
 * contains the chat history between two people
 * 
 */

import java.util.*;

public class ChatHistory {
    
    /**The ArrayList storing the messages that were sent */
    private ArrayList<String> messages = new ArrayList<String>();
    /**using integers to return which users sent which message */
    private ArrayList<Integer> whichUser = new ArrayList<Integer>();    
    /**user 1 in the chat */
    private String user1 = "";
    /**user2 in the chat */
    private String user2 = "";

    /**ChatHistory
     * Constructor for ChatHistory
     * @param u1 sets user1 = u1
     * @param u2 sets user2 = u2
     */
    ChatHistory(String u1, String u2){
        user1 = u1;
        user2 = u2;
    
    }

    /**
     * Add something to the chat history
     * @param userFrom user sent the message between the 
     * @param message the actual message sent
     */
    public void add(String userFrom, String message){
        
        if(userFrom.equals(user1)){

            messages.add(message);
            whichUser.add(1);
        }else if(userFrom.equals(user2)){

            messages.add(message);
            whichUser.add(2);
        }else{
            System.out.println("An error has occured, the wrong usernames were inputed when adding to chatHistory");
        }

    }

    /**
     * getMessage
     * @return the ArrayList of messages that were sent
     */
    public ArrayList<String> getMessage(){

        return this.messages;
    }

    /**
     * getWhicheUser
     * @return the ArrayList of users indicated by integers, 1 for user1, 2 for user2.. etc up to 10
     */
    public ArrayList<Integer> getWhichUser(){

        return this.whichUser;
    }

    public String getUser1(){

        return this.user1;
    }

    /**
     * getUser2
     * @return String of user2
     */
    public String getUser2(){

        return this.user2;
    }

}