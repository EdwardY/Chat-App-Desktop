/* [MessagesPanel.java]
 * This is the template for the message panel
 * @author James
 * @ version 1.0
 */

import java.awt.*;
import javax.swing.*;

import java.io.*;
import java.util.ArrayList;
import java.awt.event.*;

@SuppressWarnings("serial")
public class MessagesPanel extends JPanel {
    private ChatClient cc;
    private PrintWriter output;
    private String userTo;
    private ArrayList<String> friendList;
    private ArrayList<FriendButton> friendListButtons;
    private String chatHistory;

    //components of the panel
    private JButton sendButton;
    private JTextField typeField;
    private JTextArea msgArea;
    private JScrollPane scrollPane;

    public MessagesPanel(ChatClient cc, PrintWriter output, String userTo, ArrayList<String> friendList, ArrayList<FriendButton> friendListButtons, String chatHistory) {
        this.cc = cc;
        this.output = output;
        this.userTo = userTo;
        this.friendList = friendList;
        this.friendListButtons = friendListButtons;
        this.chatHistory = chatHistory;

        //creates the window and the southpanel
        setLayout(new GridLayout(2,2));
        
        //this creates the send button and the quit button, also implements a button listener
        sendButton = new JButton("SEND");
        sendButton.addActionListener(new SendButtonListener());
        
        //this is where the users can type
        typeField = new JTextField(10);
        
        //this is where the users can see messages that have already been typed
        msgArea = new JTextArea();
        msgArea.setEditable(false);
        scrollPane = new JScrollPane(msgArea);
        msgArea.setLineWrap(true);
        msgArea.setWrapStyleWord(true);
        
        msgArea.append(chatHistory);
        
        //adds all the previous components to the south panel
        add(typeField);
        add(sendButton);
    }

    // send - send msg to server (also flush), then clear the JTextField
    class SendButtonListener implements ActionListener { 
        public void actionPerformed(ActionEvent event)  {
            //Send a message to the client
            System.out.println("Before, your friend list size is: " + friendList.size());
            output.println(typeField.getText());
            output.flush();
            typeField.setText("");
            if(!friendList.contains(userTo)) {
                cc.addToFriendList(userTo);
            }
            System.out.println(friendList);
            System.out.println("Your friend list size is: " + friendList.size());
        }
    }

    public JTextArea getMsgArea() {
        return this.msgArea;
    }

    public JScrollPane getScrollPane() {
        return this.scrollPane;
    }
}