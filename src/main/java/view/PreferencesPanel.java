package view;

import controller.EditController;
import controller.PreferencesController;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;


public class PreferencesPanel extends JPanel {
    private JTextField jtfUsername;
    private JPasswordField jtfCurrentPassword;
    private JPasswordField jtfNewPassword;
    private JPasswordField jtfNewPasswordConfirm;
    private JTextField jtfMail;
    private JComboBox jcbAge;
    private JRadioButton jrbPremium;
    private JRadioButton jrbNoPremium;
    private JComboBox jcbMinAgeFilter;
    private JComboBox jcbMaxAgeFilter;
    private JButton jbSave;
    private JButton jbCancel;
    private JCheckBox jcNoFilter;

    public PreferencesPanel(){
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        createBorder();
        createUsernameField();
        createPasswordPanel();
        createMailField();
        //createAgeField();
        createPremiumOptions();
        createAgeFilter();
        updateUneditableFields();
        createSaveCancelButtons();
    }

    /**
     * Metode que genera el Border principal del EditPanel
     */
    public void createBorder(){
        TitledBorder border = new TitledBorder("\u2328 Edit Account Preferences");
        border.setTitleJustification(TitledBorder.LEFT);
        border.setTitlePosition(TitledBorder.TOP);
        this.setBorder(border);
    }

    /**
     * Metode que genera el camp de nom d'usuari
     */
    private void createUsernameField() {
        TitledBorder border = new TitledBorder("Username");
        border.setTitleJustification(TitledBorder.LEFT);
        border.setTitlePosition(TitledBorder.TOP);
        JPanel jpUsername = new JPanel();
        jpUsername.setLayout(new GridLayout(1, 2));
        jpUsername.setBorder(border);

        JLabel jlUsername = new JLabel("New Username:");
        jlUsername.setHorizontalAlignment(SwingConstants.LEFT);
        jpUsername.add(jlUsername);

        jtfUsername = new JTextField();
        jpUsername.add(jtfUsername);

        add(jpUsername);
    }

    /**
     * Metode que genera els tres camps corresponents a la contrassenya
     */
    private void createPasswordPanel() { //TODO: nomes quan els tres camps siguin isEmpty, no demanar al server el login i actualitzar els altres camps
        TitledBorder border = new TitledBorder("Password");
        border.setTitleJustification(TitledBorder.LEFT);
        border.setTitlePosition(TitledBorder.TOP);
        JPanel jpPassword = new JPanel();
        jpPassword.setLayout(new GridLayout(3, 2));
        jpPassword.setBorder(border);

        JLabel jlCurrPassword = new JLabel("Current Password:");
        jlCurrPassword.setHorizontalAlignment(SwingConstants.LEFT);
        jpPassword.add(jlCurrPassword);

        jtfCurrentPassword = new JPasswordField();
        jpPassword.add(jtfCurrentPassword);

        JLabel jlNewPassword = new JLabel("New Password:");
        jlNewPassword.setHorizontalAlignment(SwingConstants.LEFT);
        jpPassword.add(jlNewPassword);

        jtfNewPassword = new JPasswordField();
        jpPassword.add(jtfNewPassword);

        JLabel jlNewConfirmPassword = new JLabel("Confirm New Password:");
        jlNewConfirmPassword.setHorizontalAlignment(SwingConstants.LEFT);
        jpPassword.add(jlNewConfirmPassword);

        jtfNewPasswordConfirm = new JPasswordField();
        jpPassword.add(jtfNewPasswordConfirm);

        add(jpPassword);
    }

    /**
     * Metode que genera el camp de mail de l'usuari
     */
    private void createMailField() {
        TitledBorder border = new TitledBorder("Email");
        border.setTitleJustification(TitledBorder.LEFT);
        border.setTitlePosition(TitledBorder.TOP);
        JPanel jpMail = new JPanel();
        jpMail.setLayout(new GridLayout(1, 2));
        jpMail.setBorder(border);

        JLabel jlMail = new JLabel("Your Email:");
        jlMail.setHorizontalAlignment(SwingConstants.LEFT);
        jpMail.add(jlMail);

        jtfMail = new JTextField();
        jpMail.add(jtfMail);

        add(jpMail);
    }

    private void createAgeField() {
        TitledBorder border = new TitledBorder("Age");
        border.setTitleJustification(TitledBorder.LEFT);
        border.setTitlePosition(TitledBorder.TOP);
        JPanel jpAge = new JPanel();
        jpAge.setLayout(new GridLayout(1, 2));
        jpAge.setBorder(border);

        JLabel jlAge = new JLabel("Your age:");
        jlAge.setHorizontalAlignment(SwingConstants.LEFT);
        jpAge.add(jlAge);

        jcbAge = new JComboBox();
        jcbAge.setEditable(false);
        jcbAge.setPreferredSize(new Dimension(100,30));
        //jcbAge.setMaximumSize(jcbMaxAgeFilter.getPreferredSize());

        for (int i = 18; i <= 100; ++i) {
            jcbAge.addItem(i);
        }

        jpAge.add(jcbAge);

        add(jpAge);
    }

    /**
     * Metode que genera les dues opcions de "premium" i "no premium"
     */
    private void createPremiumOptions() {
        TitledBorder border = new TitledBorder("Premium Access");
        border.setTitleJustification(TitledBorder.LEFT);
        border.setTitlePosition(TitledBorder.TOP);

        //Premium or Not Premium
        JPanel jpPremiumOption = new JPanel();
        jpPremiumOption.setLayout(new FlowLayout());
        jpPremiumOption.setBorder(border);

        //Primer Radiobotó
        jrbNoPremium= new JRadioButton("No Premium");

        jpPremiumOption.add(jrbNoPremium);
        //estigui sempre seleccionat per defecte
        jrbNoPremium.setSelected(true);

        //Segon RadioBotó
        jrbPremium = new JRadioButton("Premium");

        jpPremiumOption.add(jrbPremium);


        ButtonGroup bgOption = new ButtonGroup();
        bgOption.add(jrbNoPremium);
        bgOption.add(jrbPremium);

        
        add(jpPremiumOption);
    }

    /**
     * Metode que genera els dos despplegables per al filtre d'edat i el checkbox per a no aplicar aquest
     */
    private void createAgeFilter(){

        TitledBorder border = new TitledBorder("Age Filter");
        border.setTitleJustification(TitledBorder.LEFT);
        border.setTitlePosition(TitledBorder.TOP);

        JPanel jpBothFilters = new JPanel();
        jpBothFilters.setBorder(border);
        jpBothFilters.setLayout(new GridLayout(2,2));


        JLabel jlMin = new JLabel("Minimum Age:");
        jlMin.setHorizontalAlignment(SwingConstants.LEFT);
        jpBothFilters.add(jlMin);

        jcbMinAgeFilter = new JComboBox();
        jcbMinAgeFilter.setEditable(false);
        jcbMinAgeFilter.setPreferredSize(new Dimension(100,30));
        //jcbAge.setMaximumSize(jcbMaxAgeFilter.getPreferredSize());
        jcbMaxAgeFilter = new JComboBox();
        jcbMaxAgeFilter.setEditable(false);
        jcbMaxAgeFilter.setPreferredSize(new Dimension(100,30));

        for (int i = 18; i <= 100; ++i) {
            jcbMinAgeFilter.addItem(i);
            jcbMaxAgeFilter.addItem(i);
        }

        jpBothFilters.add(jcbMinAgeFilter);

        JLabel jlMax = new JLabel("Maximum Age:");
        jlMax.setHorizontalAlignment(SwingConstants.LEFT);
        jpBothFilters.add(jlMax);

        jpBothFilters.add(jcbMaxAgeFilter);

        add(jpBothFilters);

        jcNoFilter = new JCheckBox("I don't want any age filter");
        jcNoFilter.setAlignmentX(CENTER_ALIGNMENT);
        add(jcNoFilter);
    }

    /**
     * Metode que bloqueja aquells camps que no es poden editar: username i mail
     */
    private void updateUneditableFields() {
        jtfUsername.setEditable(false);
        jtfUsername.setEnabled(false);
        jtfMail.setEditable(false);
        jtfMail.setEnabled(false);
    }

    /**
     * Metode que crea els dos botons de guardar i cancelar canvis
     */
    private void createSaveCancelButtons() {
        JPanel jpButtons = new JPanel(new FlowLayout());

        jbSave = new JButton(" Save ", new ImageIcon("icons/save.png"));
        jbSave.setBackground(Color.WHITE);

        jbCancel = new JButton("Cancel", new ImageIcon("icons/cancel_16px.png"));
        jbCancel.setBackground(Color.WHITE);
        jbCancel.setMaximumSize(jbSave.getSize());

        jpButtons.add(jbCancel);
        jpButtons.add(jbSave);

        add(jpButtons);
    }

    /**
     * Metode encarregat de mostrar per defecte les dades de l'usuari per a poder-les modificar
     * @param username
     * @param email
     * @param age
     * @param isPremium
     * @param minAge
     * @param maxAge
     */
    public void initiatePreferences(String username, String email, int age, boolean isPremium, int minAge, int maxAge) {
        //es pot fer mes optim pero ara me la sua
        int minimumAge = minAge - 18;
        int maximumAge = maxAge - 18;
        int actualAge = age - 18;
        jtfUsername.setText(username);
        jtfMail.setText(email);
        //jcbAge.setSelectedItem(actualAge);
        jrbPremium.setSelected(isPremium);
        jrbNoPremium.setSelected(!isPremium);
        jcbMinAgeFilter.setSelectedIndex(minimumAge);
        jcbMaxAgeFilter.setSelectedIndex(maximumAge);
        jtfCurrentPassword.setText("");
        jtfNewPassword.setText("");
        jtfNewPasswordConfirm.setText("");
    }

    /**
     * Metode que genera un optionPane amb l'error generat
     * @param message
     */
    public void showWarning(String message) {
        JOptionPane.showMessageDialog(null, message,"Warning", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Metode que vinvula les accions dels botons i checkbox amb el controlador
     * @param ec
     */
    public void registerController(PreferencesController ec){
        jbSave.addActionListener(ec);
        jbSave.setActionCommand("SAVE");
        jbCancel.addActionListener(ec);
        jbCancel.setActionCommand("CANCEL");
        jcNoFilter.addActionListener(ec);
        jcNoFilter.setActionCommand("NO FILTER");

    }

    public String getCurrentPassword(){
        return String.valueOf(jtfCurrentPassword.getPassword());
    }

    public String getNewPassword(){
        return String.valueOf(jtfNewPassword.getPassword());
    }

    public String getNewPasswordConfirm(){
        return String.valueOf(jtfNewPasswordConfirm.getPassword());
    }

    public String getUsername() {
        return jtfUsername.getText();
    }

    public boolean getIsPremium(){
        return jrbPremium.isSelected();
    }

    public String getMinAge() {
        int age = jcbMinAgeFilter.getSelectedIndex() + 18;
        return String.valueOf(age);
    }

    public String getMaxAge() {
        int age = jcbMaxAgeFilter.getSelectedIndex() + 18;
        return String.valueOf(age);
    }

    public void disableFilter() {
        jcbMinAgeFilter.setEnabled(false);
        jcbMaxAgeFilter.setEnabled(false);
    }

    public void enableFilter(){
        jcbMinAgeFilter.setEnabled(true);
        jcbMaxAgeFilter.setEnabled(true);
    }

    public boolean noFilterChecked() {
        return jcNoFilter.isSelected();
    }


}
