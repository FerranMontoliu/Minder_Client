package view;

import javax.swing.*;
import java.awt.*;

public class SignInPanel extends JPanel {
    public static final Color BG_COLOR = new Color(255, 101, 91);
    private JTextField jtfUsername;
    private JPasswordField jtfPassword;
    private JCheckBox jcbShowPassword;
    private JButton jbSignIn;

    public SignInPanel(CardLayout clSignInUp){
        super(clSignInUp);
        setBackground(BG_COLOR);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        createTitle();
        createUsernameField();
        createPasswordField();
        createButtons();
    }

    private void createTitle(){
        JPanel jpTitle = new JPanel();
        jpTitle.setLayout(new BoxLayout(jpTitle, BoxLayout.Y_AXIS));
        JLabel jlTitle = new JLabel("MINDER");
        jlTitle.setForeground(Color.WHITE);
        jlTitle.setFont(new Font("Hobo Std", Font.PLAIN, 28));
        jlTitle.setAlignmentX(CENTER_ALIGNMENT);
        jpTitle.setBackground(BG_COLOR);
        jpTitle.add(Box.createVerticalStrut(10));
        jpTitle.add(jlTitle, BorderLayout.CENTER);

        add(jpTitle);
        add(Box.createVerticalStrut(20));

    }

    private void createUsernameField(){
        //JPanel Username: Label i TextField.
        JPanel jpUsername = new JPanel();
        jpUsername.setLayout(new BoxLayout(jpUsername, BoxLayout.Y_AXIS));
        jpUsername.setBackground(BG_COLOR);
        JLabel jlUsername = new JLabel("Username: ");
        jlUsername.setAlignmentX(Component.CENTER_ALIGNMENT);
        jlUsername.setForeground(Color.white);
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
        jpPassword.setBackground(BG_COLOR);

        JLabel jlPassword = new JLabel("Password: ");
        jlPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        jlPassword.setForeground(Color.WHITE);
        jpPassword.add(jlPassword);

        jtfPassword = new JPasswordField(15);
        jtfPassword.setMaximumSize(jtfPassword.getPreferredSize());
        jpPassword.add(Box.createVerticalStrut(5));
        jpPassword.add(jtfPassword);

        jcbShowPassword = new JCheckBox("Show Password");
        jcbShowPassword.setHorizontalAlignment(SwingConstants.CENTER);
        jcbShowPassword.setForeground(Color.WHITE);
        jcbShowPassword.setBackground(BG_COLOR);

        add(jpPassword);
        add(jcbShowPassword);
        add(Box.createVerticalStrut(10));
    }

    private void createButtons(){
        //JPanel Buttons: Buttons Show Password i Sign In
        JPanel jpButtons = new JPanel(new FlowLayout());
        jpButtons.setBackground(BG_COLOR);
        jbSignIn = new JButton("Sign In", new ImageIcon("icons/check_icon.png"));
        jbSignIn.setHorizontalAlignment(SwingConstants.CENTER);
        jbSignIn.setBackground(Color.WHITE);
        jpButtons.add(jbSignIn);

        add(jpButtons);
        add(Box.createVerticalStrut(10));
    }

    public void regsitrarControlador(/**Controlador c***/){
        jcbShowPassword.addActionListener(c);
        jcbShowPassword.setActionCommand("SHOW-IN");
        jbSignIn.addActionListener(c);
        jbSignIn.setActionCommand("SIGN IN");
    }

    /**
     * Metode que mostra o amaga la Password en funcio de si el CheckBox exta seleccionat o no.
     */
    public void showPassword(){
        if (jcbShowPassword.isSelected()) {
            jtfPassword.setEchoChar((char) 0);
        } else {
            jtfPassword.setEchoChar('*');
        }
    }
}
