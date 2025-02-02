package view;

import controller.LoginController;

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

    /**
     * Constructor de la pantalla on l'usuari pot fer Sign-In o Sign-Up.
     *
     */
    public LoginWindow(){

        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(200, 150));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Welcome to Minder");

        createHeader();
        clSignInUp = new CardLayout();
        jpCard = new JPanel(clSignInUp);
        createSignInPanel();
        createSignUpPanel();
        changePanel("SIGN IN");
        getContentPane().add(jpCard);
    }

    /**
     * Metode encarregat de crear i afegir el header de la pantalla.
     *
     */
    private void createHeader(){
        //faig un primer panell que englobi el label i el textfield dels talps
        JPanel jpLoginOption = new JPanel();
        jpLoginOption.setLayout(new FlowLayout());

        //Primer Radiobotó
        jrbSignIn = new JRadioButton("Sign in", new ImageIcon("icons/sign-in.png"));
        jrbSignIn.setForeground(Color.white);
        jrbSignIn.setBackground(HEADER_COLOR);
        jpLoginOption.add(jrbSignIn);
        //estigui sempre seleccionat per defecte
        jrbSignIn.setSelected(true);

        //Segon RadioBotó
        jrbSignUp = new JRadioButton("Sign up", new ImageIcon("icons/sign_up.png"));
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

    /**
     * Getter del JPanel que conte els camps per fer Sign-In.
     *
     * @return Retorna el JPanel que conté els camps per fer Sign-In.
     */
    public SignInPanel getSignInPanel() {
        return jpSignIn;
    }

    /**
     * Getter del JPanel que conte els camps per fer Sign-Up.
     *
     * @return Retorna el JPanel que conté els camps per fer Sign-Up.
     */
    public SignUpPanel getSignUpPanel() {
        return jpSignUp;
    }

    /**
     * Metode encarregat de crear i afegir el panell de Sign-In.
     *
     */
    private void createSignInPanel(){
        jpSignIn = new SignInPanel(clSignInUp);
        //Gestiono el panell pel CardLayout
        jpCard.add("SIGN IN", jpSignIn);
    }

    /**
     * Metode encarregat de crear i afegir el panell de Sign-Up.
     *
     */
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

    /**
     * Metode que fara resize de la finestra en funcio del CardLayout. (Podriem fer un scroll pane??)
     * @param cardLayoutName ID del panell a visualitzar. SIGN IN o SIGN UP.
     */
    private void resizeWindow(String cardLayoutName){
        if(cardLayoutName.equals("SIGN IN")){
            setSize(300, 300);
        }else{
            setSize(300, 600);
        }
    }

    /**
     * Metode que registra els ActionListeners als components de la LoginWindow
     * @param c Controlador
     */
    public void registrarControlador(LoginController c){
        jpSignIn.regsitrarControlador(c);
        jpSignUp.registerController(c);

        jrbSignIn.addActionListener(c);
        jrbSignIn.setActionCommand("SIGN-IN-JRB");

        jrbSignUp.addActionListener(c);
        jrbSignUp.setActionCommand("SIGN-UP-JRB");

        this.addWindowListener(c);
    }

    /**
     * Metode que retorna el contingut del JTextField Username de Sign In
     * @return Username introduit
     */
    public String getSignInUsername(){
        return jpSignIn.getUsername();
    }

    /**
     * Metode que retorna el contingut del JTextField Username de Sign Up
     * @return Username introduit
     */
    public String getSignUpUsername(){
        return jpSignUp.getUsername();
    }

    /**
     * Metode que retorna la contrasenya introduida per l'usuari al Sign In.
     * @return contrasenya introduida.
     */
    public String getSignInPassword(){
        return jpSignIn.getPassword();
    }

    /**
     * Metode que retorna el contingut del JTextField Password (0) i ConfirmPassword (1) de Sign Up
     * @return array de 2 Strings que conte la Password (0) i la ConfirmPassword(1).
     */
    public String[] getSignUpPasswords(){
        return jpSignUp.getPasswordFields();
    }

    /**
     * Metode que retorna el contingut del JTextField Email de Sign Up
     * @return email introduit
     */
    public String getSignUpEmail(){
        return jpSignUp.getEmailField();
    }

    /**
     * Metode que retorna el contingut del JTextField Edat de Sign Up
     * @return Edat introduida
     */
    public String getSignUpAgeField(){
        return jpSignUp.getAgeField();
    }

    /**
     * Metode que indica si l'usuari ha marcat ser premium o no al Sign Up.
     * @return boolean que indica si l'usuari ha marcat ser premium.
     */
    public boolean isPremiumSignUp(){
        return jpSignUp.isPremium();
    }

    /**
     * Metode que agafa l'edat minima dels usuaris amb els que vol connectar..
     * @return Retorna en format String l'edat escollida (entre 18 i 100).
     */
    public String getMinAge(){
        return jpSignUp.getMinAge();
    }

    /**
     * Metode que agafa l'edat maxima dels usuaris amb els que vol connectar..
     * @return Retorna en format String l'edat escollida (entre 18 i 100).
     */
    public String getMaxAge(){
        return jpSignUp.getMaxAge();
    }

    /**
     * Funció encarregada de mostrar un warning amb un missatge personalitzat.
     *
     * @param m Missatge que s'ha de mostrar al warning.
     */
    public void showWarning(String m) {
        JOptionPane.showMessageDialog(null, m,"Warning", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Mètode encarregat de fer focus al camp on s'introdueix el nom d'usuari en la pantalla de Sign-In.
     *
     */
    public void focusUserIn() {
        jpSignIn.getJtfUsername().requestFocus();
    }

    /**
     * Mètode encarregat de fer focus al camp on s'introdueix la password de l'usuari en la pantalla de Sign-In.
     *
     */
    public void focusPasswordIn() {
        jpSignIn.getJtfPassword().requestFocus();
    }

    /**
     * Mètode encarregat de fer focus al camp on s'introdueix el nom d'usuari en la pantalla de Sign-Up.
     *
     */
    public void focusUserUp() {
        jpSignUp.getJtfNewUsername().requestFocus();
    }

    /**
     * Mètode encarregat de fer focus al camp on s'introdueix la password de l'usuari en la pantalla de Sign-Up.
     *
     */
    public void focusPasswordUp() {
        jpSignUp.getJtfNewPassword().requestFocus();
    }

    /**
     * Mètode encarregat de fer focus al camp on s'introdueix la confirmació de la password de l'usuari en la pantalla de Sign-Up.
     *
     */
    public void focusPasswordConfirmUp() {
        jpSignUp.getJtfNewPasswordConfirm().requestFocus();
    }

    /**
     * Mètode encarregat de fer focus al camp on s'introdueix el mail de l'usuari en la pantalla de Sign-Up.
     *
     */
    public void focusMailUp() {
        jpSignUp.getJtfEmail().requestFocus();
    }

    /**
     * Mètode encarregat de fer focus al camp on s'introdueix l'edat de l'usuari en la pantalla de Sign-Up.
     *
     */
    public void focusAgeUp() {
        jpSignUp.getJtfAge().requestFocus();
    }


    /**
     * Mètode encarregat de netejar tots els camps de la pantalla de Sign-In.
     *
     */
    public void cleanSignInPanel() {
        jpSignIn.clean();
    }

    /**
     * Mètode encarregat de netejar tots els camps de la pantalla de Sign-Up.
     *
     */
    public void cleanSignUpPanel() {
        jpSignUp.clean();
    }

    /**
     * Getter del camp del panell de sign up corresponent a si l'usuari desitja utilitzar filtre d'edat o no
     *
     * @return true si no utilitza filtre, false altrament
     */
    public boolean getNoFilter(){
        return jpSignUp.noFilterChecked();
    }

    /**
     * Metode que endica un missatge quan s'esta tancant la finestra.
     */
    public void quitWindow() {
        JOptionPane.showMessageDialog(null, "Quiting","Warning", JOptionPane.WARNING_MESSAGE);
    }
}
