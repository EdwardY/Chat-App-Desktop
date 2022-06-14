/* [LoginPanel.java]
 * This is the template for the login panel, allows users to sign in
 * @author James
 * @ version 1.0
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class LoginPanel extends JPanel {
    private ChatClient cc;
    private String username;
    private JLabel usernameLabel;
    private JTextField usernameField;
    private JButton usernameSendButton;

    public LoginPanel(ChatClient cc) {
        this.cc = cc;
        usernameField = new JTextField(10);
        usernameLabel = new JLabel("Please enter your username: ");
        
        usernameSendButton = new JButton("Enter");
        usernameSendButton.addActionListener(new UsernameSendButtonListener());

        add(usernameLabel);
        add(usernameField);
        add(usernameSendButton);
        setBackground(new Color(255,255,255));
        setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, new Color(147, 250, 209)));
    }

    public JTextField getTextField() {
        return this.usernameField;
    }

    public String getUsername() {
         return this.username;
    }

    public class UsernameSendButtonListener implements ActionListener { 
        public void actionPerformed(ActionEvent event)  {
            //store the username in a variable
            username = getTextField().getText(); 
            usernameField.setText("");
            //creates a pop up confirm window
            cc.confirmDecision();
        }
    }   
}