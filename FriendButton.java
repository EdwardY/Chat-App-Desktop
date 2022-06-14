/* [ChatClient.java]
 * A not-so-pretty implementation of a basic chat client
 * @author Mangat
 * @ version 1.0a
 */

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.*;
import java.io.PrintWriter;

@SuppressWarnings("serial")
public class FriendButton extends JButton {
    private boolean unread;

    private int numNewMessages;
    private String friend;

    private ArrayList<FriendButton> friendListButtons;

    public FriendButton(String friend, ArrayList<FriendButton> friendListButtons) {
        this.friend = friend;
        this.friendListButtons = friendListButtons;
        this.unread = false;

        setText(friend);

        numNewMessages = 0;
    }

    public void setReadState(Boolean unread) {
        if(unread == false) {
            setBackground(new Color(245, 84, 66));
            setText("You have " + numNewMessages + " new messages from " + friend);
        } else {
            setBackground(new JButton().getBackground());
            setText(friend);
            numNewMessages = 0;
        }
    }

    public boolean getReadState() {
        return this.unread;
    }

    public void setNumNewMessages(int numNewMessages) {
        this.numNewMessages = numNewMessages;
    }

    public int getNumNewMessages() {
        return this.numNewMessages;
    }
}