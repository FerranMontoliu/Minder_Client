package view;

import javax.swing.*;
import java.awt.*;

public class SignInPanel extends JPanel {
    private JTextField jtfUsername;
    private JPasswordField jtfPassword;
    private JButton jbDisplayPassword;
    private JButton jbSingIn;

    public SignInPanel(CardLayout clSignInUp){
        super(clSignInUp);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        createUsernameField();
        createPasswordField();
        createButtons();
    }

    private void createUsernameField(){
        //JPanel Username: Label i TextField.
        JPanel jpUsername = new JPanel();
        jpUsername.setLayout(new BoxLayout(jpUsername, BoxLayout.Y_AXIS));
        JLabel jlUsername = new JLabel("Username: ");
        jlUsername.setAlignmentX(Component.CENTER_ALIGNMENT);
        jpUsername.add(jlUsername);

        jtfUsername = new JTextField(15);
        jtfUsername.setMaximumSize(jtfUsername.getPreferredSize());
        jpUsername.add(Box.createVerticalStrut(5));
        jpUsername.add(jtfUsername);

        add(jpUsername);
        add(Box.createVerticalStrut(10));
    }

    private void createPasswordField(){
        //JPanel Password: Label i Password Text Field
        JPanel jpPassword = new JPanel();
        jpPassword.setLayout(new BoxLayout(jpPassword, BoxLayout.Y_AXIS));

        JLabel jlPassword = new JLabel("Password: ");
        jlPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        jpPassword.add(jlPassword);

        jtfPassword = new JPasswordField(15);
        jtfPassword.setMaximumSize(jtfPassword.getPreferredSize());
        jpPassword.add(Box.createVerticalStrut(5));
        jpPassword.add(jtfPassword);

        add(jpPassword);
        add(Box.createVerticalStrut(10));
    }

    private void createButtons(){
        //JPanel Buttons: Buttons Show Password i Sign In
        JPanel jpButtons = new JPanel(new FlowLayout());
        jbDisplayPassword = new JButton("Display Password");
        jbDisplayPassword.setHorizontalAlignment(SwingConstants.CENTER);
        jbSingIn = new JButton("Sign In");
        jbSingIn.setHorizontalAlignment(SwingConstants.CENTER);
        jpButtons.add(jbDisplayPassword);
        jpButtons.add(jbSingIn);

        add(jpButtons);
        add(Box.createVerticalStrut(10));
    }
}
