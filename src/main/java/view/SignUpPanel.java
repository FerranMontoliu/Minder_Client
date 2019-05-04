package view;

import controller.LoginController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SignUpPanel extends JPanel {
    private static final Color BG_COLOR = new Color(255, 101, 91);
    private JTextField jtfNewUsername;
    private JPasswordField jtfNewPassword;
    private JPasswordField jtfNewPasswordConfirm;
    private JCheckBox jcbShowPassword;
    private JRadioButton jrbPremium;
    private JRadioButton jrbNoPremium;
    private JTextField jtfEmail;
    private JTextField jtfAge;
    private JButton jbSignUp;
    private JComboBox jcbMaxAgeFilter;
    private JComboBox jcbMinAgeFilter;

    /**
     * Constructor del panell de Sign-Up.
     *
     */
    public SignUpPanel(CardLayout clSignInUp) {
        super(clSignInUp);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(BG_COLOR);
        createTitle();
        createUsernameField();
        createPasswordFields();
        createEmailField();
        createAgeField();
        createPremiumOption();
        createAgeFilters();
        createSignUp();

    }

    private void createAgeFilters() {
        JPanel jpMaxAge = new JPanel();
        jpMaxAge.setLayout(new FlowLayout());

        JLabel jlFilter = new JLabel("Choose the age range to connect with");
        jlFilter.setHorizontalAlignment(SwingConstants.CENTER);
        jlFilter.setForeground(Color.white);
        jpMaxAge.add(jlFilter);

        JPanel jpBothFilters = new JPanel();
        jpBothFilters.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        //Minimum Age
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0f;
        constraints.weighty = 1.0f;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.WEST;

        JLabel jlMin = new JLabel("Minimum age:");
        jlMin.setHorizontalAlignment(SwingConstants.CENTER);
        jlMin.setForeground(Color.white);
        jpBothFilters.add(jlMin, constraints);

        //Min age comboBox
        jcbMinAgeFilter = new JComboBox<>();
        jcbMinAgeFilter.setEditable(false);
        jcbMinAgeFilter.setPreferredSize(new Dimension(100,30));
        jcbMinAgeFilter.setMaximumSize(jcbMinAgeFilter.getPreferredSize());

        for (int i = 18; i <= 100; ++i) {
            jcbMinAgeFilter.addItem(i);
        }

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.weightx = 1.0f;
        constraints.weighty = 1.0f;
        constraints.insets = new Insets(5, 2, 5, 5);
        constraints.fill = GridBagConstraints.CENTER;

        jpBothFilters.add(jcbMinAgeFilter, constraints);

        //Maximum Age
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weightx = 1.0f;
        constraints.weighty = 1.0f;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.WEST;

        JLabel jlMax = new JLabel("Maximum age:");
        jlMax.setHorizontalAlignment(SwingConstants.CENTER);
        jlMax.setForeground(Color.white);
        jpBothFilters.add(jlMax, constraints);

        //Max age comboBox

        jcbMaxAgeFilter = new JComboBox<>();
        jcbMaxAgeFilter.setEditable(false);
        jcbMaxAgeFilter.setPreferredSize(new Dimension(100,30));
        jcbMaxAgeFilter.setMaximumSize(jcbMaxAgeFilter.getPreferredSize());

        for (int i = 18; i <= 100; ++i) {
            jcbMaxAgeFilter.addItem(i);
        }

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.weightx = 1.0f;
        constraints.weighty = 1.0f;
        constraints.insets = new Insets(5, 5, 5, 2);
        constraints.fill = GridBagConstraints.CENTER;

        jpBothFilters.add(jcbMaxAgeFilter, constraints);

        jpBothFilters.setBackground(BG_COLOR);
        jpMaxAge.add(jpBothFilters);
        jpMaxAge.setBackground(BG_COLOR);
        add(jpMaxAge);
        add(Box.createVerticalStrut(10));
    }


    /**
     * Metode que afegeix la capçalera de la pantalla.
     *
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
     * Metode encarregat de crear i afegir el camp del nom d'usuari.
     *
     */
    private void createUsernameField(){
        //Username
        JPanel jpUsername = new JPanel();
        jpUsername.setLayout(new FlowLayout());
        jpUsername.setBackground(BG_COLOR);

        JLabel jlUsername = new JLabel("Username:");
        jlUsername.setHorizontalAlignment(SwingConstants.CENTER);
        jlUsername.setForeground(Color.white);

        jpUsername.add(jlUsername);
        jtfNewUsername = new JTextField(15);
        jtfNewUsername.setMaximumSize(jtfNewUsername.getPreferredSize());
        jtfNewUsername.setAlignmentX(CENTER_ALIGNMENT);
        jpUsername.add(jtfNewUsername);

        //Afegim tot allo que hem creat
        add(jpUsername);
        add(Box.createVerticalStrut(10));
    }

    /**
     * Metode encarregat de crear i afegir els camps de les passwords.
     *
     */
    private void createPasswordFields(){
        //Password and Confirm Password
        JPanel jpPasswords  = new JPanel();
        jpPasswords.setLayout(new FlowLayout());
        jpPasswords.setBackground(BG_COLOR);

        JLabel jlPassword = new JLabel("Password:");
        jlPassword.setHorizontalAlignment(SwingConstants.CENTER);
        jlPassword.setForeground(Color.white);
        jpPasswords.add(jlPassword);


        jtfNewPassword = new JPasswordField(15);
        jtfNewPassword.setMaximumSize(jtfNewPassword.getPreferredSize());
        jpPasswords.add(jtfNewPassword);
        jtfNewPassword.setEchoChar('*');

        JPanel jpConfirm = new JPanel(new FlowLayout());
        jpConfirm.setBackground(BG_COLOR);
        JLabel jlConfirmPassword = new JLabel("Confirm:    ");
        jlConfirmPassword.setHorizontalAlignment(SwingConstants.CENTER);
        jlConfirmPassword.setForeground(Color.white);

        jpConfirm.add(jlConfirmPassword);

        jtfNewPasswordConfirm = new JPasswordField(15);
        jtfNewPasswordConfirm.setMaximumSize(jtfNewPasswordConfirm.getPreferredSize());
        jtfNewPasswordConfirm.setEchoChar('*');
        jpConfirm.add(jtfNewPasswordConfirm);

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
        add(jpConfirm);
        add(jpShowPassword);
        add(Box.createVerticalStrut(10));
    }

    /**
     * Metode encarregat de crear i afegir el camp de si l'usuari es Premium o Normal.
     *
     */
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

    /**
     * Mètode encarregat de crear i afegir el camp del mail.
     *
     */
    private void createEmailField() {
        JPanel jpEmail = new JPanel();
        jpEmail.setLayout(new FlowLayout());
        jpEmail.setBackground(BG_COLOR);

        JLabel jlEmail = new JLabel("Email:         ");
        jlEmail.setHorizontalAlignment(SwingConstants.CENTER);
        jlEmail.setForeground(Color.white);
        jpEmail.add(jlEmail);

        jtfEmail = new JTextField(15);
        jtfEmail.setMaximumSize(jtfNewPassword.getPreferredSize());
        jpEmail.add(jtfEmail);

        add(jpEmail);
        add(Box.createVerticalStrut(10));
    }

    /**
     * Mètode encarregat de crear i afegir el camp de l'edat.
     *
     */
    private void createAgeField(){
        JPanel jpAgeField = new JPanel();
        jpAgeField.setLayout(new FlowLayout());
        jpAgeField.setBackground(BG_COLOR);

        JLabel jlAge = new JLabel("Age:           ");
        jlAge.setAlignmentX(CENTER_ALIGNMENT);
        jlAge.setForeground(Color.white);

        jpAgeField.add(jlAge);
        jtfAge = new JTextField(15);
        jtfAge.setMaximumSize(jtfAge.getPreferredSize());
        jpAgeField.add(jtfAge);

        add(jpAgeField);
        add(Box.createVerticalStrut(10));
    }

    /**
     * Mètode encarregat de crear i afegir el botó de Sign-Up.
     *
     */
    private void createSignUp(){
        jbSignUp = new JButton("Sign Up", new ImageIcon("icons/check_icon.png"));
        jbSignUp.setHorizontalAlignment(SwingConstants.CENTER);
        jbSignUp.setAlignmentX(CENTER_ALIGNMENT);
        jbSignUp.setBackground(Color.WHITE);

        add(jbSignUp);
        add(Box.createVerticalStrut(10));
    }

    /**
     * Mètode encarregat de registrar el controlador associat a aquesta vista.
     *
     * @param c  Controlador de la pantalla de Log-In.
     */
    public void registerController(LoginController c){  //Flta el controlador com a parametre
        jcbShowPassword.addActionListener(c);
        jcbShowPassword.setActionCommand("SHOW-UP");
        jbSignUp.addActionListener(c);
        jbSignUp.setActionCommand("SIGN UP");
    }

    /**
     * Metode que mostra o amaga la Password en funcio de si el CheckBox exta seleccionat o no.
     */
    public void showPassword() {
        if (jcbShowPassword.isSelected()) {
            jtfNewPassword.setEchoChar((char) 0);
            jtfNewPasswordConfirm.setEchoChar((char) 0);
        } else {
            jtfNewPassword.setEchoChar('*');
            jtfNewPasswordConfirm.setEchoChar('*');
        }
    }

    /**
     * Mètode que retorna el contingut del JTextField Username
     * @return Username introduit
     */
    public String getUsername() {
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
        return passwords;
    }

    /**
     * Getter del JTextField on l'usuari introdueix el seu nom.
     *
     * @return Retorna el JTextField on l'usuari introdueix el seu nom.
     */
    public JTextField getJtfNewUsername() {
        return jtfNewUsername;
    }

    /**
     * Getter del JPasswordField on l'usuari introdueix la seva password.
     *
     * @return Retorna el JpasswordField on l'usuari introdueix la seva password.
     */
    public JPasswordField getJtfNewPassword() {
        return jtfNewPassword;
    }

    /**
     * Getter del JPasswordField on l'usuari introdueix la seva confirmació de password.
     *
     * @return Retorna el JPasswordField on l'usuari introdueix la seva confirmació de password.
     */
    public JPasswordField getJtfNewPasswordConfirm() {
        return jtfNewPasswordConfirm;
    }

    /**
     * Getter del JTextField on l'usuari introdueix el seu mail.
     *
     * @return Retorna el JTextField on l'usuari introdueix el seu mail.
     */
    public JTextField getJtfEmail() {
        return jtfEmail;
    }

    /**
     * Getter del JTextField on l'usuari introdueix la seva edat.
     *
     * @return Retorna el JTextField on l'usuari introdueix la seva edat.
     */
    public JTextField getJtfAge() {
        return jtfAge;
    }

    /**
     * Getter del JComboBox on l'usuari selecciona l'edat minima dels usuaris amb els que vol connectar.
     *
     * @return Retorna en format String l'edat escollida (entre 18 i 100)
     */
    public String getMinAge() { return jcbMinAgeFilter.getSelectedItem().toString(); }

    /**
     * Getter del JComboBox on l'usuari selecciona l'edat maxima dels usuaris amb els que vol connectar.
     *
     * @return Retorna en format String l'edat escollida (entre 18 i 100)
     */
    public String getMaxAge() { return jcbMaxAgeFilter.getSelectedItem().toString(); }


    /**
     * Mètode encarregat de netejar tots els camps del panell de Sign-Up.
     *
     */
    public void clean() {
        jtfNewUsername.setText("");
        jtfNewPassword.setText("");
        jtfNewPasswordConfirm.setText("");
        jtfEmail.setText("");
        jtfAge.setText("");
        jcbShowPassword.setSelected(false);
        showPassword();
        jrbNoPremium.setSelected(true);
        jcbMinAgeFilter.setSelectedItem(jcbMinAgeFilter.getItemAt(0));//18 anys
        jcbMaxAgeFilter.setSelectedItem(jcbMaxAgeFilter.getItemAt(82));//100 anys
    }
}
