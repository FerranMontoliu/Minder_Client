package view;

import javax.swing.*;
import java.awt.*;

public class LoginWindow extends JFrame {
    public static final Color HEADER_COLOR = new Color(255, 88, 100);
    public static final Color BG_COLOR = new Color(255, 101, 91);
    private JRadioButton jrbSignIn;
    private JRadioButton jrbSignUp;
    private CardLayout clSignInUp;
    private JPanel jpCard;
    //Atributs pel Panell de Sign In
    private SignInPanel jpSignIn;
    //Atributs pel Panell de Sign Up
   private SignUpPanel jpSignUp;


    public LoginWindow(){

        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(200, 150));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Welcome to Minder");

        createHeader();
        clSignInUp = new CardLayout();
        jpCard = new JPanel(clSignInUp);
        createSignInPanel();
        createSignUpPanel();
        clSignInUp.show(jpCard, "SIGN UP");  //Per a veure la de SIGN UP escriure aqui SIGN UP
        resizeWindow("SIGN UP");    //I aqui
        getContentPane().add(jpCard);

    }

    private void createHeader(){
        //faig un primer panell que englobi el label i el textfield dels talps
        JPanel jpLoginOption = new JPanel();
        jpLoginOption.setLayout(new FlowLayout());

        //Primer Radiobotó
        jrbSignIn = new JRadioButton("Sign in");
        jrbSignIn.setForeground(Color.white);
        jrbSignIn.setBackground(HEADER_COLOR);
        jpLoginOption.add(jrbSignIn);
        //estigui sempre seleccionat per defecte
        jrbSignIn.setSelected(true);

        //Segon RadioBotó
        jrbSignUp = new JRadioButton("Sign up");
        jrbSignUp.setForeground(Color.white);
        jrbSignUp.setBackground(HEADER_COLOR);
        jpLoginOption.add(jrbSignUp);

        //Agrupo els dos botons perquè només es pugui seleccionar un d'ells
        ButtonGroup bgOption = new ButtonGroup();
        bgOption.add(jrbSignIn);
        bgOption.add(jrbSignUp);

        jpLoginOption.setBackground(HEADER_COLOR);
        //Afegeixo els dos botons al nord del contenidor principal
        getContentPane().add(jpLoginOption, BorderLayout.NORTH);
    }

    private void createSignInPanel(){
        jpSignIn = new SignInPanel(clSignInUp);
        //Gestiono el panell pel CardLayout
        jpCard.add("SIGN IN", jpSignIn);
    }

    private void createSignUpPanel() {
        jpSignUp = new SignUpPanel(clSignInUp);
        //Gestiono el panell pel CardLayout
        jpCard.add("SIGN UP", jpSignUp);
    }

    /**
     * Metode que serveix per a canviar entre finestra de sign in (SIGN IN) o sign up (SIGN UP).
     * @param cardLayoutName ID del panell a visualitzar. SIGN IN o SIGN UP.
     */
    public void changePanel(String cardLayoutName){
        clSignInUp.show(jpCard, cardLayoutName);
        resizeWindow(cardLayoutName);
    }

    public void resizeWindow(String cardLayoutName){
        if(cardLayoutName.equals("SIGN IN")){
            setSize(300, 300);
        }else{
            setSize(300, 450);
        }
    }

}
