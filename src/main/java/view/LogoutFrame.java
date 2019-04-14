package view;

import controller.MenuController;

import javax.swing.*;
import java.awt.*;

public class LogoutFrame extends JFrame {
    private JButton jbLogOut;
    private JButton jbCancel;
    private final Color BG_COLOR = new Color(173, 105, 127);
    private GridBagConstraints constraints;

    public LogoutFrame(){
        setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();
        addHeader();
        addLogOutButton();
        addCancelButton();
    }

    private void addHeader() {
        //Header: icono amb titol
        String header = "   Are you sure you want to log out of Minder?";
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0f;
        constraints.weighty = 1.0f;
        constraints.insets = new Insets(5, 5, 2, 5);
        constraints.fill = GridBagConstraints.CENTER;
        JLabel headingLabel = new JLabel(header);
        headingLabel.setIcon(new ImageIcon("icons/exit.png"));
        headingLabel.setOpaque(false);
        add(headingLabel, constraints);
    }

    private void addLogOutButton() {
        jbLogOut = new JButton("LogOut", new ImageIcon("icons/logout_button.png"));

        //constraints.gridx = 1;
        //constraints.gridy = 1;
        //constraints.weightx = 3.0f;
        //constraints.weighty = 2.0f;
        //constraints.insets = new Insets(1, 4, 1, 4);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.fill = GridBagConstraints.SOUTHEAST;
        constraints.gridwidth = 2;
        constraints.ipady = 12;
        constraints.ipadx = 12;
        //constraints.insets = new Insets(5, 1, 5, 1);
        //constraints.fill = GridBagConstraints.EAST;
        add(jbLogOut, constraints);


    }

    private void addCancelButton() {
        jbCancel = new JButton("Cancel", new ImageIcon("icons/cancel_16px.png"));
        //Boto de missatges
        //constraints.gridx = 0;
        //constraints.gridy = 1;
        //constraints.weightx = 3.0f;
        //constraints.weighty = 2.0f;
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

    public void registerController(MenuController menuController){
        jbLogOut.addActionListener(menuController);
        jbLogOut.setActionCommand("YES LOGOUT");
        jbCancel.addActionListener(menuController);
        jbCancel.setActionCommand("NO LOGOUT");
    }

    public void showFrame(Point location){
            setSize(370,170);
            setAlwaysOnTop(true);
            setUndecorated(true);
            setLocation(location.x+25, location.y);
            setVisible(true);

    }

    public void hideFrame() {
        dispose();
    }
}