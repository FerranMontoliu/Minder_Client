package view;

import controller.MenuController;

import javax.swing.*;
import java.awt.*;

public class LogoutPanel extends JPanel {
    private JButton jbLogOut;
    private JButton jbCancel;
    private final Color BG_COLOR = new Color(173, 105, 127);

    public LogoutPanel(CardLayout clMainWindow){
        super(clMainWindow);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(BG_COLOR);

        JLabel jlTitle = new JLabel("Are you sure?");
        jlTitle.setAlignmentX(CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(30));
        add(jlTitle);
        add(Box.createVerticalStrut(10));

        JPanel jpButtons = new JPanel(new FlowLayout());
        jpButtons.setBackground(BG_COLOR);
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