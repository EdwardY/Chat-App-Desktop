/* [TitlePanel.java]
 * This is the title panel, contains name of product and additional information
 * @author James
 * @ version 1.0
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class TitlePanel extends JPanel {
    private ChatClient cc;
    private JFrame generalWindow;

    private JLabel titleLabel;
    private JButton backButton;
    private JLabel userToLabel;


    public TitlePanel() {
        titleLabel = new JLabel("Aging Mess");

        //title panel colours
        setBackground(new Color(88, 88, 88));

        //title label styling
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(85,255,243)));

        add(titleLabel);
    }

    public TitlePanel(ChatClient cc, JFrame generalWindow) {
        this.cc = cc;
        this.generalWindow = generalWindow;
        
        //initializing variables
        titleLabel = new JLabel("Aging Mess", 0);
        backButton = new JButton("Back");
        backButton.addActionListener(new BackButtonListener());

        //title panel styling
        setBackground(new Color(88, 88, 88));
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //title label styling and constraints
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createMatteBorder(3, 3, 1, 3, new Color(85,255,243)));
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        //adds the title label to the panel
        add(titleLabel,c);

        //back button styling and constraints
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        //adds the back button to the panel
        add(backButton,c);
    }

    public TitlePanel(ChatClient cc, JFrame generalWindow, String userTo) {
        this.cc = cc;
        this.generalWindow = generalWindow;
        
        //initializing variables
        titleLabel = new JLabel("Aging Mess", 0);
        backButton = new JButton("Back");
        backButton.addActionListener(new BackButtonListener());
        userToLabel = new JLabel("Chatting with: " + userTo, 0);

        //title panel styling
        setBackground(new Color(88, 88, 88));
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        //title label styling and constraints
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createMatteBorder(3, 3, 1, 3, new Color(85,255,243)));
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        //adds the title label to the panel
        add(titleLabel,c);

        //back button styling and constraints
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        //adds the back button to the panel
        add(backButton,c);

        //userTo label styling and constraints
        userToLabel.setForeground(Color.WHITE);
        userToLabel.setBorder(BorderFactory.createMatteBorder(1, 3, 3, 3, new Color(85,255,243)));
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        //adds user label to panel
        add(userToLabel,c);
    }

    public class BackButtonListener implements ActionListener { 
        public void actionPerformed(ActionEvent event)  {
            generalWindow.dispose();
            cc.home();
        }
    }
}