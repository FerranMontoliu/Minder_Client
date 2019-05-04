package view;

import controller.LoginController;

import javax.swing.*;
import java.awt.*;

public class SignInPanel extends JPanel {
    private static final Color BG_COLOR = new Color(255, 101, 91);
    private JTextField jtfUsername;
    private JPasswordField jtfPassword;
    private JCheckBox jcbShowPassword;
    private JButton jbSignIn;

    /**
     * Constructor del panell de Sign-In.
     *
     */
    public SignInPanel(CardLayout clSignInUp){
        super(clSignInUp);
        setBackground(BG_COLOR);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        createTitle();
        createUsernameField();
        createPasswordField();
        createButtons();
    }

    /**
     * Mètode que afegeix la capçalera de la pantalla.
     */
    private void createTitle(){
        JPanel jpTitle = new JPanel();
        jpTitle.setLayout(new BoxLayout(jpTitle, BoxLayout.Y_AXIS));
        JLabel jlTitle = new JLabel(new ImageIcon(new ImageIcon("icons/Minder_Logo.png").getImage().getScaledInstance(180, 35, Image.SCALE_DEFAULT)));
        jlTitle.setAlignmentX(CENTER_ALIGNMENT);
        jpTitle.setBackground(BG_COLOR);
        jpTitle.add(Box.createVerticalStrut(10));
        jpTitle.add(jlTitle, BorderLayout.CENTER);

        add(jpTitle);
        add(Box.createVerticalStrut(20));

    }

    /**
     * Getter del nom d'usuari.
     * @return Retorna un String que conté el contingut del camp del nom d'usuari.
     */
    public JTextField getJtfUsername() {
        return jtfUsername;
    }

    /**
     * Getter de la password.
     *
     * @return Retorna un String que conté el contingut del camp de la password de l'usuari.
     */
    public JPasswordField getJtfPassword() {
        return jtfPassword;
    }

    /**
     * Mètode encarregat de crear i afegir el lloc on l'usuari fica el seu nom.
     *
     */
    private void createUsernameField(){
        //JPanel Username: Label i TextField.
        JPanel jpUsername = new JPanel();
        jpUsername.setLayout(new FlowLayout());
        jpUsername.setBackground(BG_COLOR);
        JLabel jlUsername = new JLabel("Username: ");
        jlUsername.setHorizontalAlignment(SwingConstants.CENTER);
        jlUsername.setForeground(Color.white);
        jpUsername.add(jlUsername);

        jtfUsername = new JTextField(15);
        jtfUsername.setMaximumSize(jtfUsername.getPreferredSize());
        jpUsername.add(jtfUsername);

        //add(jlUsername);
        add(jpUsername);
        add(Box.createVerticalStrut(10));
    }

    /**
     * Mètode encarregat de crear i afegir el lloc on l'usuari fica la seva password.
     *
     */
    private void createPasswordField(){
        //JPanel Password: Label i Password Text Field
        JPanel jpPassword = new JPanel();
        jpPassword.setLayout(new FlowLayout());
        jpPassword.setBackground(BG_COLOR);

        JLabel jlPassword = new JLabel("Password: ");
        jlPassword.setHorizontalAlignment(SwingConstants.CENTER);
        jlPassword.setForeground(Color.WHITE);
        jpPassword.add(jlPassword);

        jtfPassword = new JPasswordField(15);
        jtfPassword.setMaximumSize(jtfPassword.getPreferredSize());
        jpPassword.add(Box.createVerticalStrut(5));
        jtfPassword.setEchoChar('*');
        jpPassword.add(jtfPassword);

        jcbShowPassword = new JCheckBox("Show Password");
        jcbShowPassword.setHorizontalAlignment(SwingConstants.CENTER);
        jcbShowPassword.setForeground(Color.WHITE);
        jcbShowPassword.setBackground(BG_COLOR);

        //add(jlPassword);
        add(jpPassword);
        add(jcbShowPassword);
        add(Box.createVerticalStrut(10));
    }

    /**
     * Mètode encarregat de crear i afegir el botó de Sign In.
     *
     */
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

    /**
     * Mètode encarregat de registrar el controlador associat a aquesta vista.
     *
     * @param c  Controlador de la pantalla de Log-In.
     */
    public void regsitrarControlador(LoginController c){  //Flta el controlador com a parametre
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

    /**
     * Metode que retorna la contrasenya introduida per l'usuari al Sign In.
     * @return contrasenya introduida.
     */
    public String getPassword(){
        return String.valueOf(jtfPassword.getPassword());
    }

    /**
     * Metode que retorna el nom introduït per l'usuari al Sign In.
     * @return nom introduït per l'usuari.
     */
    public String getUsername(){
        return jtfUsername.getText();
    }

    /**
     * Mètode encarregat de netejar tots els camps del panell de Sign-In.
     */
    public void clean() {
        jtfUsername.setText("");
        jtfPassword.setText("");
        jcbShowPassword.setSelected(false);
        showPassword();
    }
}
