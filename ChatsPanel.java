/* [ChatsPanel.java]
 * This is the template for the chats panel, showcases all your chats with friends
 * @author James
 * @ version 1.0
 */

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.*;
import java.io.PrintWriter;

@SuppressWarnings("serial")
public class ChatsPanel extends JPanel {
    private ChatClient cc;
    private PrintWriter output;
    private JFrame homeWindow;
    private String userTo;
    private ArrayList<String> friendList;
    private ArrayList<FriendButton> friendListButtons;
    private ArrayList<String> userList;

    //components of the panel
    private JButton onlineList;
    private JButton edit;
    private JLabel chatsLabel;
    private JLabel chatlessLabel;
    private JLabel emptyLabel;

    public ChatsPanel(ChatClient cc, PrintWriter output, JFrame homeWindow, ArrayList<String> friendList, ArrayList<FriendButton> friendListButtons, ArrayList<String> userList) {
        this.cc = cc;
        this.output = output;
        this.homeWindow = homeWindow;
        this.friendList = friendList;
        this.friendListButtons = friendListButtons;
        this.userList = userList;

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //online list button constraints
        onlineList = new JButton("Online User List: There are currently " + userList.size() +" users online!" );
        onlineList.addActionListener(new UserListButtonListener());
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(onlineList,c);

        //chats label constraints and styling
        chatsLabel = new JLabel("Chats",0);
        chatsLabel.setBackground(new Color(88,88,88));
        chatsLabel.setOpaque(true);
        chatsLabel.setForeground(Color.WHITE);
        chatsLabel.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(85,255,243)));
        

        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        add(chatsLabel,c);
        
        //chatless label constraints
        if(friendList.size()==0) {
            chatlessLabel = new JLabel("There are currently no chats :(",0);
            chatlessLabel.setOpaque(true);
            c.gridx = 0;
            c.gridy = 2;
            c.weightx = 1;
            c.weighty = 1;
            c.gridwidth = 1;
            c.fill = GridBagConstraints.HORIZONTAL;
            add(chatlessLabel,c);    
        } else {
            friendListButtons.clear();
            for(int i = 0; i < friendList.size(); i++) {
                friendListButtons.add(new FriendButton(friendList.get(i), friendListButtons));
                friendListButtons.get(i).addActionListener(new FriendButtonsListener());    //creates a new buttons listener for each new button
               
                c.gridy = 2 + i;        //moves the location of each new button down 1 in the grid 
                add(friendListButtons.get(i), c);     //adds the new user buttons to the panel
                System.out.println(friendListButtons.get(i));
            }
        }
        
        //empty label constraints
        emptyLabel = new JLabel("");
        c.gridx = 0;
        c.gridy = 100;
        c.weightx = 1;
        c.weighty = 100;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(emptyLabel, c);
    }   

    public class UserListButtonListener implements ActionListener { 
        public void actionPerformed(ActionEvent event)  {
            //starts displaying the user list
            cc.userList();

            //closes the home window
            homeWindow.dispose();
        }
    }

    public class FriendButtonsListener implements ActionListener {
        private FriendButton friendButton;

        public void actionPerformed(ActionEvent event)  {
            try {
                friendButton = (FriendButton)event.getSource();      //gets the source button
                userTo = friendList.get(friendListButtons.indexOf(friendButton));       //finds which user button was clicked
                friendButton.setReadState(true);
                //sends the user that I'm chatting with to the server
                output.println("\\u" + userTo);
                output.flush();

                //sets the user that I'm chatting with in the main program
                cc.setUserTo(userTo);
                //starts displaying message window
                cc.chaTime();
                //gets rid of home window
                homeWindow.dispose();
            } 
            catch (Exception e){
                System.out.println("Failed to bring up the message window.");

            }
        }
    } 
}