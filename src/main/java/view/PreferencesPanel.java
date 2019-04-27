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

    public PreferencesPanel(){
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        createBorder();
        createUsernameField();
        createPasswordPanel();
        createMailField();
        createAgeField();
        createPremiumOptions();
        createAgeFilter();
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

    private void createPasswordPanel() {
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
    }

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


    public void initiatePreferences(String username, String email, int age, boolean isPremium, int minAge, int maxAge) {
        //es pot fer mes optim pero ara me la sua
        int minimumAge = minAge - 18;
        int maximumAge = maxAge - 18;
        int actualAge = age - 18;
        jtfUsername.setText(username);
        jtfMail.setText(email);
        jcbAge.setSelectedItem(actualAge);
        jrbPremium.setSelected(isPremium);
        jrbNoPremium.setSelected(!isPremium);
        jcbMinAgeFilter.setSelectedIndex(minimumAge);
        jcbMaxAgeFilter.setSelectedIndex(maximumAge);
        jtfCurrentPassword.setText("");
        jtfNewPassword.setText("");
        jtfNewPasswordConfirm.setText("");
    }

    public void showWarning(String message) {
        JOptionPane.showMessageDialog(null, message,"Warning", JOptionPane.WARNING_MESSAGE);
    }

    //TODO: fer controller i panell funcional (tasca: Alba)
    public void registerController(PreferencesController ec){
        jbSave.addActionListener(ec);
        jbSave.setActionCommand("SAVE");
        jbCancel.addActionListener(ec);
        jbCancel.setActionCommand("CANCEL");

    }

    public String getCurrentPassword(){
        return String.valueOf(jtfCurrentPassword.getPassword());
    }

    public String getNewPassword(){
        return String.valueOf(jtfNewPassword.getPassword());
    }

    public String getNewPasswordConfirm(){
        return String.valueOf(jtfCurrentPassword.getPassword());
    }

    public String getUsername() {
        return jtfUsername.getText();
    }
}
