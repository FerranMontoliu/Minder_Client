package View;

import javax.swing.*;
import java.awt.*;

public class LoginWindow extends JFrame {
    private JRadioButton jrbSignIn;
    private JRadioButton jrbSignUp;

    public LoginWindow(){
        createHeader();

        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(200, 150));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Welcome to Minder");



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


}
