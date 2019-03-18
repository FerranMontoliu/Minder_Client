package view;

import controller.LoginController;

import javax.swing.*;
import java.awt.*;

public class SignUpPanel extends JPanel {
    public static final Color BG_COLOR = new Color(255, 101, 91);
    private JTextField jtfNewUsername;
    private JPasswordField jtfNewPassword;
    private JPasswordField jtfNewPasswordConfirm;
    private JCheckBox jcbShowPassword;
    private JRadioButton jrbPremium;
    private JRadioButton jrbNoPremium;
    private JTextField jtfEmail;
    private JTextField jtfAge;
    private JButton jbSignUp;


    public SignUpPanel(CardLayout clSignInUp){
        super(clSignInUp);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(BG_COLOR);
        createTitle();
        createUsernameField();
        createPasswordFields();
        createEmailField();
        createAgeField();
        createPremiumOption();
        createSignUp();
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
        //Username
        JPanel jpUsername = new JPanel();
        jpUsername.setLayout(new BoxLayout(jpUsername, BoxLayout.Y_AXIS));
        jpUsername.setBackground(BG_COLOR);

        JLabel jlUsername = new JLabel("Username: ");
        jlUsername.setAlignmentX(Component.CENTER_ALIGNMENT);
        jlUsername.setForeground(Color.white);

        jpUsername.add(jlUsername);
        jtfNewUsername = new JTextField(15);
        jtfNewUsername.setMaximumSize(jtfNewUsername.getPreferredSize());
        jpUsername.add(Box.createVerticalStrut(5));
        jpUsername.add(jtfNewUsername);

        //Afegim tot allo que hem creat
        add(jpUsername);
        add(Box.createVerticalStrut(10));
    }

    private void createPasswordFields(){
        //Password and Confirm Password
        JPanel jpPasswords  = new JPanel();
        jpPasswords.setLayout(new BoxLayout(jpPasswords, BoxLayout.Y_AXIS));
        jpPasswords.setBackground(BG_COLOR);

        JLabel jlPassword = new JLabel("Password: ");
        jlPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        jlPassword.setForeground(Color.white);
        jpPasswords.add(jlPassword);
        jpPasswords.add(Box.createVerticalStrut(5));

        jtfNewPassword = new JPasswordField(15);
        jtfNewPassword.setMaximumSize(jtfNewPassword.getPreferredSize());
        jpPasswords.add(jtfNewPassword);
        jpPasswords.add(Box.createVerticalStrut(5));

        JLabel jlConfirmPassword = new JLabel("Confirm Password: ");
        jlConfirmPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        jlConfirmPassword.setForeground(Color.white);

        jpPasswords.add(jlConfirmPassword);
        jpPasswords.add(Box.createVerticalStrut(5));

        jtfNewPasswordConfirm = new JPasswordField(15);
        jtfNewPasswordConfirm.setMaximumSize(jtfNewPasswordConfirm.getPreferredSize());
        jpPasswords.add(jtfNewPasswordConfirm);

        JPanel jpShowPassword = new JPanel(new BorderLayout());
        jpShowPassword.setBackground(BG_COLOR);


        jcbShowPassword = new JCheckBox("Show Password");
        jcbShowPassword.setHorizontalAlignment(SwingConstants.CENTER);
        jcbShowPassword.setBackground(BG_COLOR);
        jcbShowPassword.setForeground(Color.white);
        jpShowPassword.add(jcbShowPassword, BorderLayout.NORTH);
        jpShowPassword.setMaximumSize(new Dimension(jpShowPassword.getPreferredSize().width, jcbShowPassword.getPreferredSize().height));


        //Afegim tot allo que hem creat
        add(jpPasswords);
        add(jpShowPassword);
        add(Box.createVerticalStrut(10));
    }

    private void createPremiumOption(){
        //Premium or Not Premium
        JPanel jpPremiumOption = new JPanel();
        jpPremiumOption.setLayout(new FlowLayout());
        jpPremiumOption.setBackground(BG_COLOR);

        //Primer Radiobotó
        jrbNoPremium= new JRadioButton("No Premium");
        jrbNoPremium.setForeground(Color.white);
        jrbNoPremium.setBackground(BG_COLOR);
        jpPremiumOption.add(jrbNoPremium);
        //estigui sempre seleccionat per defecte
        jrbNoPremium.setSelected(true);

        //Segon RadioBotó
        jrbPremium = new JRadioButton("Premium");
        jrbPremium.setForeground(Color.white);
        jrbPremium.setBackground(BG_COLOR);
        jpPremiumOption.add(jrbPremium);

        jpPremiumOption.setMaximumSize(new Dimension(jpPremiumOption.getPreferredSize().width, jrbPremium.getPreferredSize().height));

        //Agrupo els dos botons perquè només es pugui seleccionar un d'ells
        ButtonGroup bgOption = new ButtonGroup();
        bgOption.add(jrbNoPremium);
        bgOption.add(jrbPremium);

        //Afegim tot allo que hem creat
        add(jpPremiumOption);
        add(Box.createVerticalStrut(20));
    }

    private void createEmailField() {
        JPanel jpEmail = new JPanel();
        jpEmail.setLayout(new BoxLayout(jpEmail, BoxLayout.Y_AXIS));
        jpEmail.setBackground(BG_COLOR);

        JLabel jlEmail = new JLabel("Email: ");
        jlEmail.setAlignmentX(Component.CENTER_ALIGNMENT);
        jlEmail.setForeground(Color.white);
        jpEmail.add(jlEmail);

        jtfEmail = new JTextField(15);
        jtfEmail.setMaximumSize(jtfNewPassword.getPreferredSize());
        jpEmail.add(jtfEmail);

        add(jpEmail);
        add(Box.createVerticalStrut(10));
    }

    private void createAgeField(){
        JPanel jpAgeField = new JPanel();
        jpAgeField.setLayout(new BoxLayout(jpAgeField, BoxLayout.Y_AXIS));
        jpAgeField.setBackground(BG_COLOR);

        JLabel jlAge = new JLabel("Age: ");
        jlAge.setAlignmentX(CENTER_ALIGNMENT);
        jlAge.setForeground(Color.white);

        jpAgeField.add(jlAge);
        jpAgeField.add(Box.createVerticalStrut(5));
        jtfAge = new JTextField(15);
        jtfAge.setMaximumSize(jtfAge.getPreferredSize());
        jpAgeField.add(jtfAge);

        add(jpAgeField);
        add(Box.createVerticalStrut(10));
    }

    private void createSignUp(){
        jbSignUp = new JButton("Sign Up", new ImageIcon("icons/check_icon.png"));
        jbSignUp.setHorizontalAlignment(SwingConstants.CENTER);
        jbSignUp.setAlignmentX(CENTER_ALIGNMENT);
        jbSignUp.setBackground(Color.WHITE);

        add(jbSignUp);
        add(Box.createVerticalStrut(10));
    }


    public void regsitrarControlador(LoginController c){  //Flta el controlador com a parametre
        jcbShowPassword.addActionListener(c);
        jcbShowPassword.setActionCommand("SHOW-UP");
        jbSignUp.addActionListener(c);
        jbSignUp.setActionCommand("SIGN UP");
    }

    /**
     * Metode que mostra o amaga la Password en funcio de si el CheckBox exta seleccionat o no.
     */
    public void showPassword(){
        if (jcbShowPassword.isSelected()) {
            jtfNewPassword.setEchoChar((char) 0);
            jtfNewPasswordConfirm.setEchoChar((char) 0);
        } else {
            jtfNewPassword.setEchoChar('*');
            jtfNewPasswordConfirm.setEchoChar('*');
        }
    }

    /**
     * Metode que retorna el contingut del JTextField Username
     * @return Username introduit
     */
    public String getUsername(){
        return jtfNewUsername.getText();
    }

    /**
     * Metode que retorna el contingut del JTextField Email
     * @return email introduit
     */
    public String getEmailField(){
        return jtfEmail.getText();
    }

    /**
     * Metode que retorna el contingut del JTextField Edat
     * @return Edat introduida
     */
    public String getAgeField(){
        return jtfAge.getText();
    }

    /**
     * Metode que indica si l'usuari ha marcat ser premium o no.
     * @return boolean que indica si l'usuari ha marcat ser premium.
     */
    public boolean isPremium(){
        return jrbPremium.isSelected();
    }

    /**
     * Metode que retorna el contingut del JTextField Password (0) i ConfirmPassword (1)
     * @return array de 2 Strings que conte la Password (0) i la ConfirmPassword(1).
     */
    public String[] getPasswordFields() {
        String[] passwords = new String[2];
        passwords[0] = String.valueOf(jtfNewPassword.getPassword());
        passwords[1] = String.valueOf(jtfNewPasswordConfirm.getPassword());
        return  passwords;
    }

    public JTextField getJtfNewUsername() {
        return jtfNewUsername;
    }

    public JPasswordField getJtfNewPassword() {
        return jtfNewPassword;
    }

    public JPasswordField getJtfNewPasswordConfirm() {
        return jtfNewPasswordConfirm;
    }

    public JTextField getJtfEmail() {
        return jtfEmail;
    }

    public JTextField getJtfAge() {
        return jtfAge;
    }
}
