package view;

import javax.swing.*;
import java.awt.*;

public class SignUpPanel extends JPanel {
    private JTextField jtfNewUsername;
    private JPasswordField jtfNewPassword;
    private JPasswordField jtfNewPasswordConfirm;
    private JRadioButton jrbPremium;
    private JRadioButton jrbNoPremium;
    private JTextField jtfEmail;
    private JTextField jtfAge;
    private JButton jbSignUp;

    public SignUpPanel(CardLayout clSignInUp){
        super(clSignInUp);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        createUsernameField();
        createPasswordFields();
        createEmailField();
        createAgeField();
        createPremiumOption();
        createSignUp();

    }

    private void createUsernameField(){
        //Username
        JPanel jpUsername = new JPanel();
        jpUsername.setLayout(new BoxLayout(jpUsername, BoxLayout.Y_AXIS));
        JLabel jlUsername = new JLabel("Username: ");
        jlUsername.setAlignmentX(Component.CENTER_ALIGNMENT);
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
        JLabel jlPassword = new JLabel("Password: ");
        jlPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        jpPasswords.add(jlPassword);
        jpPasswords.add(Box.createVerticalStrut(5));

        jtfNewPassword = new JPasswordField(15);
        jtfNewPassword.setMaximumSize(jtfNewPassword.getPreferredSize());
        jpPasswords.add(jtfNewPassword);
        jpPasswords.add(Box.createVerticalStrut(5));

        JLabel jlConfirmPassword = new JLabel("Confirm Password: ");
        jlConfirmPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        jpPasswords.add(jlConfirmPassword);
        jpPasswords.add(Box.createVerticalStrut(5));

        jtfNewPasswordConfirm = new JPasswordField(15);
        jtfNewPasswordConfirm.setMaximumSize(jtfNewPasswordConfirm.getPreferredSize());
        jpPasswords.add(jtfNewPasswordConfirm);

        //Afegim tot allo que hem creat
        add(jpPasswords);
        add(Box.createVerticalStrut(10));
    }

    private void createPremiumOption(){
        //Premium or Not Premium
        JPanel jpPremiumOption = new JPanel();
        jpPremiumOption.setLayout(new FlowLayout());

        //Primer Radiobotó
        jrbNoPremium= new JRadioButton("No Premium");
        jpPremiumOption.add(jrbNoPremium);
        //estigui sempre seleccionat per defecte
        jrbNoPremium.setSelected(true);

        //Segon RadioBotó
        jrbPremium = new JRadioButton("Premium");
        jpPremiumOption.add(jrbPremium);

        //Agrupo els dos botons perquè només es pugui seleccionar un d'ells
        ButtonGroup bgOption = new ButtonGroup();
        bgOption.add(jrbNoPremium);
        bgOption.add(jrbPremium);

        //Afegim tot allo que hem creat
        add(jpPremiumOption);
        add(Box.createVerticalStrut(10));
    }

    private void createEmailField() {
        JPanel jpEmail = new JPanel();
        jpEmail.setLayout(new BoxLayout(jpEmail, BoxLayout.Y_AXIS));

        JLabel jlEmail = new JLabel("Email: ");
        jlEmail.setAlignmentX(Component.CENTER_ALIGNMENT);
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

        JLabel jlAge = new JLabel("Age: ");
        jlAge.setAlignmentX(CENTER_ALIGNMENT);
        jpAgeField.add(jlAge);
        jpAgeField.add(Box.createVerticalStrut(5));
        jtfAge = new JTextField(15);
        jtfAge.setMaximumSize(jtfAge.getPreferredSize());
        jpAgeField.add(jtfAge);

        add(jpAgeField);
        add(Box.createVerticalStrut(10));
    }

    private void createSignUp(){
        jbSignUp = new JButton("Sign Up");
        jbSignUp.setHorizontalAlignment(SwingConstants.CENTER);
        jbSignUp.setAlignmentX(CENTER_ALIGNMENT);

        add(jbSignUp);
        add(Box.createVerticalStrut(10));
    }

}
