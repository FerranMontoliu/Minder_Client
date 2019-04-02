package view;

import controller.MenuController;

import javax.swing.*;
import java.awt.*;

public class LogoutPanel extends JPanel {
    private JButton jbLogOut;
    private JButton jbCancel;

    public LogoutPanel(CardLayout clMainWindow){
        super(clMainWindow);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel jlTitle = new JLabel("Are you sure?");
        add(jlTitle);

        JPanel jpButtons = new JPanel(new FlowLayout());
        jbLogOut = new JButton("LogOut", new ImageIcon("icons/logout_button.png"));
        jbCancel = new JButton("Cancel", new ImageIcon("icons/cancel_16px.png"));
        jbCancel.setBackground(Color.WHITE);
        jbLogOut.setBackground(Color.WHITE);

        jpButtons.add(jbCancel);
        jpButtons.add(jbLogOut);

        add(jpButtons);
    }

    public void registerController(MenuController menuController){
        jbLogOut.addActionListener(menuController);
        jbLogOut.setActionCommand("YES LOGOUT");
        jbCancel.addActionListener(menuController);
        jbCancel.setActionCommand("NO LOGOUT");
    }
}