package view;

import controller.ChatController;
import controller.MenuController;

import javax.swing.*;
import java.awt.*;

public class UnmatchFrame extends JFrame{
    private JButton jbCancel;
    private final Color BG_COLOR = new Color(173, 105, 127);
    private GridBagConstraints constraints;
    private JButton jbUnmatch;

    public UnmatchFrame(){
        setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();
        addHeader();
        addUnmatchButton();
        addCancelButton();
        setBackground(new Color(94, 94, 94));
    }

    private void addHeader() {
        //Header: icono amb titol
        String header = "   Are you sure you want to unmatch this user?";
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0f;
        constraints.weighty = 1.0f;
        constraints.insets = new Insets(5, 5, 2, 5);
        constraints.fill = GridBagConstraints.CENTER;
        JLabel headingLabel = new JLabel(header);
        headingLabel.setIcon(new ImageIcon("icons/delete-friend.png"));
        headingLabel.setOpaque(false);
        add(headingLabel, constraints);
    }

    private void addUnmatchButton() {
        jbUnmatch = new JButton("Unmatch", new ImageIcon("icons/check_icon.png"));

        //constraints.gridx = 1;
        //constraints.gridy = 1;
        //constraints.weightx = 3.0f;
        //constraints.weighty = 2.0f;
        constraints.insets = new Insets(1, 4, 1, 4);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.fill = GridBagConstraints.SOUTHEAST;
        constraints.gridwidth = 2;
        constraints.ipady = 12;
        constraints.ipadx = 12;
        //constraints.insets = new Insets(5, 1, 5, 1);
        //constraints.fill = GridBagConstraints.EAST;
        add(jbUnmatch, constraints);


    }

    private void addCancelButton() {
        jbCancel = new JButton("  Cancel  ", new ImageIcon("icons/cancel_16px.png"));
        jbCancel.setSize(jbUnmatch.getSize());
        constraints.insets = new Insets(1, 4, 1, 4);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.fill = GridBagConstraints.SOUTHWEST;
        constraints.gridwidth = 1;
        constraints.ipady = 12;
        constraints.ipadx = 12;
        //constraints.fill = GridBagConstraints.WEST;
        add(jbCancel, constraints);
    }

    public void registerController(ChatController chatController){
        jbUnmatch.addActionListener(chatController);
        jbUnmatch.setActionCommand("YES UNMATCH");
        jbCancel.addActionListener(chatController);
        jbCancel.setActionCommand("NO UNMATCH");
    }

    public void showFrame(){
        setSize(370,170);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setBackground(BG_COLOR);
        setVisible(true);
    }

    public void hideFrame() {
        dispose();
    }
}

