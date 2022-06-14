/* [ChatClient.java]
 * A not-so-pretty implementation of a basic chat client
 * @author Mangat
 * @ version 1.0a
 */

import java.awt.*;
import javax.swing.*;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.awt.event.*;

class ChatClient {
  private static JFrame loginWindow;
  private static LoginPanel loginPanel;
  private static JFrame homeWindow;
  private static ChatsPanel chatsPanel;
  private static JFrame chaTimeWindow;
  private static MessagesPanel messagesPanel;;

  private static ChatClient cc = new ChatClient();
  private static String userTo;
  private static String chatHistory ;

  //variables for information received from server
  private static int userListSize;
  private static ArrayList<String> userList = new ArrayList<String>();
  private static ArrayList<JButton> userListButtons = new ArrayList<JButton>();
  private static ArrayList<String> friendList = new ArrayList<String>();
  private static ArrayList<FriendButton> friendListButtons = new ArrayList<FriendButton>();

  private Socket mySocket; //socket for connection
  private BufferedReader input; //reader for network stream
  private PrintWriter output;  //printwriter for network output
  private boolean running = true; //thread status via boolean
  
  public static void main(String[] args) {
    //userList.add("Monica");
    // userListButtons.add(new JButton("Monica"));
    //userList.add("Dorian");
    // userListButtons.add(new JButton("Dorian"));

    cc.login();

    // cc.login();
    // cc.home();
    // cc.chaTime();
    // call a method that connects to the server 
    cc.connect("localhost",5000);
    // after connecting loop and keep appending[.append()] to the JTextArea
    cc.readMessagesFromServer();
  }

  public void chaTime() {
    //creates the window and the southpanel
    chaTimeWindow = new JFrame("Chat Client");
    TitlePanel messagesTitlePanel = new TitlePanel(cc, chaTimeWindow, userTo);
    messagesPanel = new MessagesPanel(cc, output, userTo, friendList, friendListButtons, chatHistory);
    chaTimeWindow.addWindowListener(messagesListener);

    System.out.println("Currently talking to: " + userTo);

    //formats and adds the separate panels onto the window
    chaTimeWindow.add(BorderLayout.NORTH, messagesTitlePanel);
    chaTimeWindow.add(BorderLayout.CENTER, messagesPanel.getMsgArea());
    chaTimeWindow.add(BorderLayout.SOUTH, messagesPanel);
    
    //default styling and methods
    chaTimeWindow.setSize(500,500);
    chaTimeWindow.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    chaTimeWindow.setVisible(true);
  }

  public void home() {
    //initialize all the panels and labels
    homeWindow = new JFrame("Home Client");
    TitlePanel homeTitlePanel = new TitlePanel();
    chatsPanel = new ChatsPanel(cc, output, homeWindow, friendList, friendListButtons, userList);
    homeWindow.addWindowListener(homeListener);

    //adds title panel
    homeWindow.add(BorderLayout.NORTH, homeTitlePanel);
    //adds in the chat panel 
    homeWindow.add(chatsPanel);

    homeWindow.setSize(500,500);
    homeWindow.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    homeWindow.setVisible(true);
  }

  public void login() {
    loginWindow = new JFrame("Login Client");
    TitlePanel loginTitlePanel = new TitlePanel();
    loginPanel = new LoginPanel(cc);

    //adds title panel
    loginWindow.add(BorderLayout.NORTH, loginTitlePanel);
    //adds in the login panel
    loginWindow.add(loginPanel);

    loginWindow.setSize(350,200);
    loginWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    loginWindow.setVisible(true);
  }

  public void userList() {
    JFrame userListWindow = new JFrame("User List");
    TitlePanel userListTitlePanel = new TitlePanel(cc, userListWindow);
    UserListPanel userListPanel = new UserListPanel(cc, output, userListSize, userList, userListButtons, userListWindow);
    userListWindow.addWindowListener(userListListener);

    userListWindow.add(BorderLayout.NORTH, userListTitlePanel);
    userListWindow.add(userListPanel); 
    userListWindow.setSize(500, 500);
    userListWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    userListWindow.setVisible(true);
  }

  WindowListener messagesListener = new WindowAdapter(){
    public void windowClosing(WindowEvent evt) {
       JFrame frame = (JFrame) evt.getSource();
       frame.dispose();
       cc.home();
    }
  };
  
  WindowListener homeListener = new WindowAdapter(){
    public void windowClosing(WindowEvent evt) {
       JFrame frame = (JFrame) evt.getSource();
       frame.dispose();
       output.println("\\q");
       output.flush();
    }
  };

  WindowListener userListListener = new WindowAdapter(){
    public void windowClosing(WindowEvent evt) {
       JFrame frame = (JFrame) evt.getSource();
       frame.dispose();
       cc.home();
    }
  };

  //Attempts to connect to the server and creates the socket and streams
  public Socket connect(String ip, int port) { 
    System.out.println("Attempting to make a connection..");
    
    try {
      mySocket = new Socket("localhost",5000); //attempt socket connection (local address). This will wait until a connection is made
      
      InputStreamReader stream1 = new InputStreamReader(mySocket.getInputStream()); //Stream for network input
      input = new BufferedReader(stream1);
      output = new PrintWriter(mySocket.getOutputStream()); //assign printwriter to network stream
      
    } catch (IOException e) {  //connection error occured
      System.out.println("Connection to Server Failed");
      e.printStackTrace();
    }
    
    System.out.println("Connection made.");
    return mySocket;
  }
  
  //Starts a loop waiting for server input and then displays it on the textArea
  public synchronized void readMessagesFromServer() { 
  
  String msgChat ="";

  while(running) {  // loop unit a message is received
      try {
        if (input.ready()) { //check for an incoming messge
          String msg;  
          
          String userFrom;
          String msgOwn;
          String userFromNotification;

          msg = input.readLine(); //read the message

          if(msg.startsWith("\\uL")) {    // the information that follows will be the user list size
            userListSize = Integer.parseInt(msg.substring(3)) - 1;
            // System.out.println("The user list size is: " + userListSize);

          } else if(msg.startsWith("\\n")) {    //this encodes for the usernames on the user list
            if (userList.size() < userListSize) { 
              //if the new name isn't in the user list or the users own name
              if(!userList.contains(msg.substring(2)) && !loginPanel.getUsername().equals(msg.substring(2))) {
                userList.add(msg.substring(2));
                homeWindow.dispose();
                cc.home();
              // System.out.println("There is a user named " + msg.substring(2) + " online!");
              } else {
                ;
              }
            }
          } else if(msg.startsWith("\\m")) {    //this encodes for user that sent me the message
              msgChat = msg.substring(2);
            
          } else if(msg.startsWith("\\u") && !msgChat.equals("")) {    //this encodes for messages that are sent to me
              userFrom = msg.substring(2);

              if(userFrom.equals(userTo)) {
                messagesPanel.getMsgArea().append(userFrom + ": " + msgChat+"\n");
              } else {
                friendList.add(userFrom);
                friendListButtons.add(new FriendButton(userFrom, friendListButtons));

                FriendButton currentButton = friendListButtons.get(friendList.indexOf(userFrom) - 1);
                currentButton.setNumNewMessages(currentButton.getNumNewMessages()+1);
                currentButton.setReadState(false);   
              }

          } else if(msg.startsWith("\\s")) {    //this encodes for echoed messages from server
              msgOwn = msg.substring(2);
              messagesPanel.getMsgArea().append(loginPanel.getUsername() + ": " + msgOwn+"\n");

          } else if(msg.startsWith("\\c")) {
              chatHistory = msg.substring(2);
              System.out.println(chatHistory);
          }
          
          
          // } else if(msg.startsWith("\\")) {    //this encodes new messages from other users
          //     userFromNotification = msg.substring(2);
          // }
          // System.out.println("received: "+msg);
          
        }
        
      }catch (IOException e) { 
        System.out.println("Failed to receive msg from the server");
        e.printStackTrace();
      }
  }
     try {  //after leaving the main loop we need to close all the sockets
      input.close();
      output.close();
      mySocket.close();
    }catch (Exception e) { 
      System.out.println("Failed to close socket");
    }
  
  }
  //****** Inner Classes for Action Listeners ****

    //listens for the yes button on the login confirmation screen
    class YesButtonListenerLogin implements ActionListener {
      private JFrame confirmWindow;

      public YesButtonListenerLogin(JFrame confirmWindow) {
        this.confirmWindow = confirmWindow;
      }

      public void actionPerformed(ActionEvent event)  {
        loginWindow.dispose();      //closes the login window
        confirmWindow.dispose();      //closes the confirm pop up
        output.println(loginPanel.getUsername());   //sends the username to the server
        output.flush();   //flushes the stream
        cc.home();    //begins displaying the home window
      }
    } 

    //listens for the no button on the login confirmation screen
    class NoButtonListenerLogin implements ActionListener { 
      private JFrame confirmWindow;

      public NoButtonListenerLogin( JFrame confirmWindow) {
        this.confirmWindow = confirmWindow;
      }

      public void actionPerformed(ActionEvent event)  {
        loginWindow.setVisible(true);
        confirmWindow.dispose();      //closes the confirm pop up window
        System.out.println("HelloNo");
      }
    } 

  //displays the user list

  // QuitButtonListener - Quit the program
     class QuitButtonListener implements ActionListener { 
     public void actionPerformed(ActionEvent event)  {
       running=false;
     }     
   }

  //this method is used for sending a confirmation popup
  public void confirmDecision() {
    JFrame confirmWindow = new JFrame("Confirm Decision");
    JPanel confirmationPanel = new JPanel();
    JPanel confirmationLabelPanel = new JPanel();

    //initializing labelss
    JLabel confirmationLabel = new JLabel("Are you sure?");

    //initializing buttons
    JButton yesButton = new JButton("YES");
    yesButton.addActionListener(cc.new YesButtonListenerLogin(confirmWindow));
    JButton noButton = new JButton("NO");
    noButton.addActionListener(cc.new NoButtonListenerLogin(confirmWindow));

    //styling the labels
    confirmationLabelPanel.setBackground(new Color(102, 100, 93));
    confirmationLabel.setBorder(BorderFactory.createMatteBorder(5, 20, 5, 20, new Color(102, 100, 93)));
    confirmationLabel.setForeground(Color.white);
    // confirmationLabel.setBackground(new Color(102,100,93));

    //styling the buttons
    yesButton.setBackground(new Color(45, 214, 113));
    yesButton.setBorder(BorderFactory.createMatteBorder(5, 20, 5, 20, new Color(45, 214, 113)));
    yesButton.setForeground(Color.white);

    noButton.setBackground(new Color(235, 52, 52));
    noButton.setBorder(BorderFactory.createMatteBorder(5, 20, 5, 20, new Color(235, 52, 52)));
    noButton.setForeground(Color.white);

    //adding the labels to the panel
    confirmationLabelPanel.add(confirmationLabel);

    //adding the buttons to the panel
    confirmationPanel.add(yesButton);
    confirmationPanel.add(noButton);

    //setting the size and visibility of the window
    confirmWindow.add(BorderLayout.NORTH, confirmationLabelPanel);
    confirmWindow.add(BorderLayout.CENTER, confirmationPanel);
    confirmWindow.setSize(200,130);
    confirmWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    confirmWindow.setVisible(true);

    //hides the login window so that no other inputs can be entered
    loginWindow.setVisible(false);
  } 

  public void setUserTo(String currentUserTo) {
    userTo = currentUserTo;
  }

  public void addToFriendList(String currentUserTo) {
    friendList.add(currentUserTo);
  }
}
