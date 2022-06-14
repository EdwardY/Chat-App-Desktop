/* [ChatServer.java]
 * You will need to modify this so that received messages are broadcast to all clients
 * @author Mangat
 * @ version 1.0a
 */

//imports for network communication
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
 
class ChatServer {
  
  /** queue for bufferng incoming and outgoing messages */
  static Queue<ClientMsg> messageQue = new LinkedList<ClientMsg>();
  /**ConcurrentHashMap for keeping track of users who are online */
  static ConcurrentHashMap<String, OnlineUser> userOnline = new ConcurrentHashMap<>();
  /**ConcurrentHashMap for keeping track of users who are online */
  static ConcurrentHashMap<String, String> chatHistory = new ConcurrentHashMap<>(); 

  /**Socket for detecting and connecting users */
  ServerSocket serverSock;// server socket for connection

  static Boolean running = true;  // controls if the server is accepting clients
  static Scanner myScanner = new Scanner(System.in);

  /**remove for final */
  static boolean test = true;

   /** Main
    * @param args parameters from command line
    */
  public static void main(String[] args) { 
    new ChatServer().go(); //start the server
  }
  
  /** Go
    * Starts the server
    */
  public void go() { 
    
    System.out.println("Waiting for a clients connection...");
    
    Socket client = null;//hold the client connection

    MessageSend msgSend = new MessageSend();//creates sending the messages
    Thread sendInfo = new Thread(msgSend);

    sendInfo.start();//starts the loop that will eventuall send users information
    /**remember to close the server */

    try {
      serverSock = new ServerSocket(5000);  //assigns an port to the server
      
      //serverSock.setSoTimeout(15000);  //15 second timeout

      while(running) {  //this loops to accept multiple clients

        client = serverSock.accept();  //wait for connection
        System.out.println("A new Client connected with the " + client.toString());
                  
        //Note: you might want to keep references to all clients if you plan to broadcast messages
        //Also: Queues are good tools to buffer incoming/outgoing messages

        Thread t = new Thread(new ConnectionHandler(client)); //create a thread for the new client and pass in the socket
        t.start(); //start the new thread
      }

    }catch(Exception e) { 
      System.out.println("Error accepting connection");
      System.out.println(e);
      //print out error

      //close all and quit
      try {

        client.close();
        System.out.println("Socket has been closed");

      }catch (Exception e1) { 
        System.out.println("Failed to close socket");
      }
      System.exit(-1);
    }
  }
  
  //***** Inner class - thread for client connection
  class ConnectionHandler implements Runnable { 
    
    private PrintWriter output; //assign printwriter to network stream
    private BufferedReader input; //Stream for network input
    private Socket client;  //keeps track of the client socket
    private boolean running;
    private String username; //username of the client connected to this thread
    private String userTalkingTo = ""; //the user that the current client is talking to 


    /* ConnectionHandler
     * Constructor
     * @param the socket belonging to this client connection
     */    
    ConnectionHandler(Socket s) { 
      this.client = s;  //constructor assigns client to this    
      try {  //assign all connections to client

        this.output = new PrintWriter(client.getOutputStream());
        InputStreamReader stream = new InputStreamReader(client.getInputStream());
        this.input = new BufferedReader(stream);

      }catch(IOException e) {
        e.printStackTrace();        
      }            
      running=true;
      
    } //end of constructor
  
    
    /* run
     * executed on start of thread
     */
    public void run() {  

      //Get a message from the client
      String msg = "";
        
      

      //Get the username from the user 
      while(running){ 
        try{
          if(input.ready()){ //captures the first message received and sets it as username
            
            this.username = input.readLine();///receive the username
            this.client.setSoTimeout(300000); //add a 5 minute timeout

            output.println(username);//echo back the username
            output.flush();

            /**TBD */
            checkNameTaken(username);
            running = false;//makes sure this loop only runs after username is received
          }
        }catch(IOException ioe){
          System.out.println("Failed to receive msg from the client");
          ioe.printStackTrace();
        }
      }


      //construct an OnlineUser 
      userOnline.put(username, new OnlineUser(username, "password", this.client));
      /** */
      System.out.println(username + " logged on with the " +  userOnline.get(username).getSocket());
      
      running = true;

      //Get a message from the client
      while(running) {  // loop unit a message is received        
        try {
          this.client.setSoTimeout(300000); //add a 5 minute timeout
          
          if (input.ready()) { //check for an incoming messge
            msg = input.readLine();  //get a message from the client
            
            if(msg.indexOf('\\') == 0){ 
              
              if(msg.equals("\\q")){ //if user enters \q it means they want to quit
                running = false;

              }else if(msg.startsWith("\\u")){ //receives who client is talking to
                
                userTalkingTo = msg.substring(2);
                
                sendChatHistory(username + userTalkingTo);

              }else{//user sends a normal message
                
                msgToQue(msg, username);
                
                addToHistory (msg,username);
                /**change? for final */
                System.out.println("Received: " + msg); //prints to console message is received

                output.println("\\s" + msg); //echo the message back to the client 
                output.flush(); 

              }
            }else{//user sends a normal message
              msgToQue(msg, username);
              addToHistory (msg,username);

              /**remove for final */
              System.out.println("Received: " + msg); //prints to console message is received

              output.println("\\s" + msg); //echo the message back to the client 
              output.flush(); 

            }              
          }
        }catch (IOException e) { 
          System.out.println("Failed to receive msg from the client"); //detect and print error
          e.printStackTrace();
        }
      }    
    
      //Send a message to the client
      output.println("We got your message! Goodbye.");
      output.flush(); 
      
      //close the socket
      try {
        input.close();
        output.close();
        client.close();

        userOnline.remove(username); //removing the user from the list of ppl online
        System.out.println(username + " has disconnected");

      }catch (Exception e) { 
        System.out.println("Failed to close socket");
      }
    } // end of run()

    /**
     * receiveMsg
     * adds a received msg to queue/buffer
     */
    public void msgToQue(String msg,String username){ //adds the message to the message queue/buffer

      System.out.println("userFrom:" + username);
      System.out.println("userTo: " + userTalkingTo);
      System.out.println(msg);

      //add message to the que
      messageQue.add(new ClientMsg(userOnline.get(username), userOnline.get(userTalkingTo), msg));

    } //end of msgToQue method

    /**
     * checkNameTake
     * checks if the username that user wanted is taken
     * @return true if name is already taken, false if name is not taken and available
     */
    public boolean checkNameTaken(String name){
      boolean taken = false;
      /**TBD */

      return taken;
    }

    /**sendChatHistory
     * 
     * @param key the combination of keys of the 2 users used to refer their chatHistory
     */
    public void sendChatHistory(String key){
      //if a chatHistory between the two clients don't already exist, create one
      if(chatHistory.get(key) == null){

        chatHistory.put(username + userTalkingTo, "\\c"); //create a chatHistory using both orders of username
        chatHistory.put(userTalkingTo + username, "\\c");//Both users can access the same chat history

      }else{ //send chatHistory to current client
        
        try{
          //send the messages
          output.println(chatHistory.get(username + userTalkingTo));

        }catch(Exception e){
          System.out.println("There was an error sending the chatHistory to the user");
          e.printStackTrace();

        }
      }
    } //end of sendChatHistory method
    
    /**  addToHistory
     * 
     * @param message the message that is being sent 
     * @param userFrom the user that is sending the message
     */
    public void addToHistory(String message, String userFrom){

      //create new chat history
      String newHistory = chatHistory.get(username + userTalkingTo) + userFrom + ": " + message + "\n";

      //remove old chat history
      chatHistory.remove(username + userTalkingTo);
      chatHistory.remove(userTalkingTo + username);

      //add new chat history to the hashmap
      chatHistory.put(username + userTalkingTo, newHistory);
      chatHistory.put(userTalkingTo + username, newHistory);
      
    }//end of addToHistory method
  } //end of inner class   

  /**
   * Inner class used to send messages to clients, updating client queues and lists
   */
  class MessageSend implements Runnable {
    
    private Socket mySocket; //the socket that the message needs to be sent to 
    /** 
    private String msgTo;
    private String msg;
    private String receive;
    */
    private PrintWriter printer; //assign printwriter to network stream
    private ClientMsg qHead;
    private boolean run = true;

    /**MessageSend
     * Constructor
     */
    MessageSend(){

    }

    /** run
     * Thread for sending clients on messages and message lists 
     */
    public synchronized void run(){

      while(run){

        sendUserList(userOnline);

        /**remove for final */
        //System.out.print("");

        try{ 
          //send messages that are in thequeue to users

          while (!messageQue.isEmpty()){

            qHead = messageQue.remove();

            System.out.println("user to:" + qHead.getUserTo());

            mySocket = userOnline.get(qHead.getUserTo().getUserName()).getSocket();

            printer = new PrintWriter(mySocket.getOutputStream());
            printer.println("\\m" + qHead.getMsg());
            printer.flush();  
            printer.println("\\u"+ qHead.getUserFrom().getUserName());
            printer.flush();   //

            System.out.println("Message sent successfully");
          
          }
          
        }catch(Exception e){
          
          System.out.println("There was an error");
          e.printStackTrace();
        }
      }

    }

    /**sendUserList 
     * Method sends each client a list of users who are online
     * @param userOnline The hashmap of people who are online
     */
    public void sendUserList(ConcurrentHashMap<String, OnlineUser> userOnline){
      
      //sends the list of users who are online to other people
      if(userOnline.size() > 0){ //only loop through if there's a need to send to users

        try{
          for(String key: userOnline.keySet()){ //iterate through list of users online and and setting the sockets

            mySocket = userOnline.get(key).getSocket();
            printer = new PrintWriter(mySocket.getOutputStream());
            printer.println("\\uL" + userOnline.size());

            //iterate through and send each user usernames
            for(String key2: userOnline.keySet()){
              
              printer.println("\\n" + userOnline.get(key2).getUserName());
              printer.flush();   
            }
          }
        }catch(SocketException se){
          
          System.out.print(""); //user disconnected, socket closed

        }catch(IOException ioe){

          System.out.print(""); //user disconnected IO discontinued

        }catch(NullPointerException npe){
          System.out.print(""); //user disconnected socket null npe exception
        
        }catch(Exception e){
          System.out.println("There was an error sending the userlist to the client");
          e.printStackTrace();
        }
      }
    }    
  }
} //end of Class