package view;

import javax.swing.*;
import java.awt.*;

public class LoginWindow extends JFrame {
    private JRadioButton jrbSignIn;
    private JRadioButton jrbSignUp;
    private JTextField jtfUsername;
    private JPasswordField jtfPassword;
    private JButton jbDisplayPassword;
    private JButton jbSingIn;


    public LoginWindow(){

        createHeader();
        getContentPane().setBackground(new Color(173, 105, 127));
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(200, 150));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Welcome to Minder");

        createSignInPanel();

        pack();

    }


    private void createHeader(){
        //faig un primer panell que englobi el label i el textfield dels talps
        JPanel jpLoginOption = new JPanel();
        jpLoginOption.setLayout(new FlowLayout());

        //Primer Radiobotó
        jrbSignIn = new JRadioButton("Sign in");
        jpLoginOption.add(jrbSignIn);
        //estigui sempre seleccionat per defecte
        jrbSignIn.setSelected(true);

        //Segon RadioBotó
        jrbSignUp = new JRadioButton("Sign up");
        jpLoginOption.add(jrbSignUp);

        //Agrupo els dos botons perquè només es pugui seleccionar un d'ells
        ButtonGroup bgOption = new ButtonGroup();
        bgOption.add(jrbSignIn);
        bgOption.add(jrbSignUp);

        //Afegeixo els dos botons al nord del contenidor principal
        getContentPane().add(jpLoginOption, BorderLayout.NORTH);
    }

    private void createSignInPanel(){
        JPanel jpSignIn = new JPanel();
        jpSignIn.setLayout(new BoxLayout(jpSignIn, BoxLayout.PAGE_AXIS));
        JPanel jpUsername = new JPanel();
        jpUsername.setLayout(new BoxLayout(jpUsername, BoxLayout.Y_AXIS));

        JLabel jlUsername = new JLabel("Username: ");
        jlUsername.setAlignmentX(Component.CENTER_ALIGNMENT);
        jpUsername.add(jlUsername);

        jtfUsername = new JTextField(15);
        jtfUsername.setMaximumSize(jtfUsername.getPreferredSize());
        jpUsername.add(jtfUsername);

        jpSignIn.add(jpUsername);

        //faig un altre panell que englobi el label i el textfield del tamany del taulell
        JPanel jpPassword = new JPanel();
        jpPassword.setLayout(new BoxLayout(jpPassword, BoxLayout.Y_AXIS));

        JLabel jlPassword = new JLabel("Password: ");
        jlPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        jpPassword.add(jlPassword);

        jtfPassword = new JPasswordField(15);
        jtfPassword.setMaximumSize(jtfPassword.getPreferredSize());
        jpPassword.add(jtfPassword);
        jpSignIn.add(jpPassword);

        jbDisplayPassword = new JButton("Display Password");
        jbDisplayPassword.setHorizontalAlignment(SwingConstants.CENTER);
        jbSingIn = new JButton("Sign In");
        jbSingIn.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel jpButtons = new JPanel(new FlowLayout());
        jpButtons.add(jbDisplayPassword);
        jpButtons.add(jbSingIn);
        jpSignIn.add(jpButtons);

        getContentPane().add(jpSignIn);
    }



}
