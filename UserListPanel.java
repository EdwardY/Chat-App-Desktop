/* [UserListPanel.java]
 * This is the template for the user list panel
 * @author James
 * @ version 1.0
 */

import java.awt.*;
import javax.swing.*;


import java.io.*;
import java.util.ArrayList;
import java.awt.event.*;

@SuppressWarnings("serial")
public class UserListPanel extends JPanel {
    private ChatClient cc;
    private PrintWriter output;
    private JFrame userListWindow;
    private String userTo;

    //components of the panel
    private ArrayList<String> userList;
    private ArrayList<JButton> userListButtons;

    public UserListPanel(ChatClient cc, PrintWriter output, int userListSize, ArrayList<String> userList, ArrayList<JButton> userListButtons, JFrame userListWindow) {   
        //assigning values to variables
        this.cc = cc;
        this.output = output;
        this.userListWindow = userListWindow;
        this.userList = userList;
        this.userListButtons = userListButtons;
    
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();  
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.HORIZONTAL;

        System.out.println("display");
        
        //if there are 1 or more users online, display the buttons on screen
        if(userListSize > 0) {
            System.out.println("userlist");
            userListButtons.clear();
            //will create new buttons and add on any new users
            for(int i = 0; i < userListSize; i++) {
                System.out.println(userListButtons.size());
                System.out.println(userListSize);

                userListButtons.add(new JButton(userList.get(i)));      //creates buttons for new users
                userListButtons.get(i).addActionListener(new UserButtonsListener());    //creates a new buttons listener for each new button
               
                c.gridy = i;        //moves the location of each new button down 1 in the grid 
                add(userListButtons.get(i), c);     //adds the new user buttons to the panel
                System.out.println(userListButtons.get(i));
            }
        }

        c.gridy = userListSize;
        c.weighty = 100;
        add(new JLabel(""), c);
    }

    //displays the chat screen of these selected user
    public class UserButtonsListener implements ActionListener {
        private JButton userButton;

        public void actionPerformed(ActionEvent event)  {
            try {
                userButton = (JButton)event.getSource();
                // System.out.println(userButton);
                // System.out.println(userList);
                // System.out.println(userListButtons);
                userTo = userList.get(userListButtons.indexOf(userButton));
                output.println("\\u" + userTo);
                output.flush();
                cc.setUserTo(userTo);
                cc.chaTime();
                userListWindow.dispose();
            } 
            catch (Exception e){
                System.out.println("Failed to bring up the message window.");

            }
        }
    } 

    public String getUserTo() {
        return this.userTo;
    }
}