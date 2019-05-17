package view;

import controller.EditController;
import controller.PreferencesController;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Classe que s'encarrega de la vista del panell generat quan l'usuari vol editar les seves preferencies de compte
 */
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

    /**
     * Constructor principal de la classe
     */
    public PreferencesPanel(){
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        createBorder();
        createUsernameField();
        createPasswordPanel();
        createMailField();
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
    private void createPasswordPanel() {
        //Titol de l'espai de contrassenyes
        TitledBorder border = new TitledBorder("Password");
        border.setTitleJustification(TitledBorder.LEFT);
        border.setTitlePosition(TitledBorder.TOP);

        //Panell que engloba tots els camps de les contrassenyes
        JPanel jpPassword = new JPanel();
        jpPassword.setLayout(new GridLayout(3, 2));
        jpPassword.setBorder(border);

        //Etiqueta i PasswordField de la contrassenya actual
        JLabel jlCurrPassword = new JLabel("Current Password:");
        jlCurrPassword.setHorizontalAlignment(SwingConstants.LEFT);
        jpPassword.add(jlCurrPassword);
        jtfCurrentPassword = new JPasswordField();
        jpPassword.add(jtfCurrentPassword);

        //Etiqueta i PasswordField de la contrassenya nova
        JLabel jlNewPassword = new JLabel("New Password:");
        jlNewPassword.setHorizontalAlignment(SwingConstants.LEFT);
        jpPassword.add(jlNewPassword);
        jtfNewPassword = new JPasswordField();
        jpPassword.add(jtfNewPassword);

        //Etiqueta i PasswordField de la confirmacio de la nova contrassenya
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
        //Titol de l'espai de email
        TitledBorder border = new TitledBorder("Email");
        border.setTitleJustification(TitledBorder.LEFT);
        border.setTitlePosition(TitledBorder.TOP);

        //Panell que engloba els camps del mail
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


    /**
     * Metode que genera les dues opcions de "premium" i "no premium", deixant per defecte el radioButton de no premium
     */
    private void createPremiumOptions() {
        //Titol de l'espai de premium
        TitledBorder border = new TitledBorder("Premium Access");
        border.setTitleJustification(TitledBorder.LEFT);
        border.setTitlePosition(TitledBorder.TOP);

        //Premium or Not Premium panell
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

        //Agrupem els dos buttons
        ButtonGroup bgOption = new ButtonGroup();
        bgOption.add(jrbNoPremium);
        bgOption.add(jrbPremium);

        
        add(jpPremiumOption);
    }

    /**
     * Metode que genera els dos despplegables per al filtre d'edat minima i maxima a triar per l'usuari
     * i el checkbox per a no aplicar aquest
     */
    private void createAgeFilter(){
        //Titol de l'espai de filtre d'edat
        TitledBorder border = new TitledBorder("Age Filter");
        border.setTitleJustification(TitledBorder.LEFT);
        border.setTitlePosition(TitledBorder.TOP);

        //Panell que engloba els camps del filtre d'edat
        JPanel jpBothFilters = new JPanel();
        jpBothFilters.setBorder(border);
        jpBothFilters.setLayout(new GridLayout(2,2));

        //Etiqueta i comboBox de l'edat minima i maxima
        JLabel jlMin = new JLabel("Minimum Age:");
        jlMin.setHorizontalAlignment(SwingConstants.LEFT);
        jpBothFilters.add(jlMin);
        JLabel jlMax = new JLabel("Maximum Age:");
        jlMax.setHorizontalAlignment(SwingConstants.LEFT);
        jpBothFilters.add(jlMax);

        jcbMinAgeFilter = new JComboBox();
        jcbMinAgeFilter.setEditable(false);
        jcbMinAgeFilter.setPreferredSize(new Dimension(100,30));

        jcbMaxAgeFilter = new JComboBox();
        jcbMaxAgeFilter.setEditable(false);
        jcbMaxAgeFilter.setPreferredSize(new Dimension(100,30));

        for (int i = 18; i <= 100; ++i) {
            jcbMinAgeFilter.addItem(i);
            jcbMaxAgeFilter.addItem(i);
        }

        jpBothFilters.add(jcbMinAgeFilter);
        jpBothFilters.add(jcbMaxAgeFilter);
        add(jpBothFilters);

        //CheckBox per a triar si es vol aplicar filtre d'edat mitjancant els dos filtres anteriors o si no es vol
        //aplicar el filtre anterior
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
     *
     * @param username Nom de l'usuari.
     * @param age Edat de l'usuari.
     * @param isPremium Indica si es usuari premium o normal.
     * @param email Mail de l'usuari.
     * @param noFilter l'usuari vol filtre d'edat o no.
     * @param minAge Edat minima del filtre per edat.
     * @param maxAge Edat maxima del filtre per edat.
     */
    public void initiatePreferences(String username, String email, int age, boolean isPremium, int minAge, int maxAge, boolean noFilter) {
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
        jcNoFilter.setSelected(noFilter);
    }

    /**
     * Metode que genera un optionPane amb l'error generat
     *
     * @param message text a mostrar al nou frame informatoi
     */
    public void showWarning(String message) {
        JOptionPane.showMessageDialog(null, message,"Warning", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Metode que vinvula les accions dels botons i checkbox amb el controlador
     *
     * @param ec controlador que s'ha de vincular amb aquests botons
     */
    public void registerController(PreferencesController ec){
        jbSave.addActionListener(ec);
        jbSave.setActionCommand("SAVE");
        jbCancel.addActionListener(ec);
        jbCancel.setActionCommand("CANCEL");
        jcNoFilter.addActionListener(ec);
        jcNoFilter.setActionCommand("NO FILTER");

    }

    /**
     * Getter del text introduit al camp Current Password
     *
     * @return String amb el contingut del JPasswordField de la contrassenya actual
     */
    public String getCurrentPassword(){
        return String.valueOf(jtfCurrentPassword.getPassword());
    }

    /**
     * Getter del text introduit al camp New Password
     *
     * @return String amb el contingut del JPasswordField de la nova contrassenya
     */
    public String getNewPassword(){
        return String.valueOf(jtfNewPassword.getPassword());
    }

    /**
     * Getter del text introduit al camp New Password Confirmation
     *
     * @return String amb el contingut del JPasswordField de la nova contrassenya
     */
    public String getNewPasswordConfirm(){
        return String.valueOf(jtfNewPasswordConfirm.getPassword());
    }

    /**
     * Getter del text introduit al camp Username
     *
     * @return String amb el nom introduit per l'usuari
     */
    public String getUsername() {
        return jtfUsername.getText();
    }

    /**
     * Getter del radiobutton Premium
     *
     * @return 1 si es Premium i 0 si no ho es
     */
    public boolean getIsPremium(){
        return jrbPremium.isSelected();
    }

    /**
     * Getter del item seleccionat al desplegable del camp Minimum age filter
     *
     * @return enter amb l'edat seleccionada al desplegable del filtre de minima edat
     */
    public int getMinAge() {
        int age = jcbMinAgeFilter.getSelectedIndex() + 18;
        return age;
    }
    /**
     * Getter del item seleccionat al desplegable del camp Maximum age filter
     *
     * @return enter amb l'edat seleccionada al desplegable del filtre de maxima edat
     */
    public int getMaxAge() {
        int age = jcbMaxAgeFilter.getSelectedIndex() + 18;
        return age;
    }

    /**
     * Metode que bloqueja el filtre d'edat (els dos desplegables) per tal que l'usuari no el pugui modificar
     */
    public void disableFilter() {
        jcbMinAgeFilter.setEnabled(false);
        jcbMaxAgeFilter.setEnabled(false);
        jcNoFilter.setSelected(true);
    }

    /**
     * Metode que habilita el filtre d'edat (els dos desplegables) per tal que l'usuari pugui modificar-los
     */
    public void enableFilter(){
        jcbMinAgeFilter.setEnabled(true);
        jcbMaxAgeFilter.setEnabled(true);
    }

    /**
     * Getter del CheckBox que indica si l'usuari vol utilitzar el filtre d'edat o no
     *
     * @return true en cas que l'usuari no vulgui cap filtre d'edat, false en cas de que haguem de fer cas dels valors
     * continguts als  ComboBox
     */
    public boolean noFilterChecked() {
        return jcNoFilter.isSelected();
    }

    /**
     * Metode que genera un OptionPane mostrant un missatge d'exit després de completar correctament l'edicio
     *
     * @param message text a mostrar al nou frame informatoi
     */
    public void showSuccess(String message) {
        JOptionPane.showMessageDialog(null, message,"Success", JOptionPane.INFORMATION_MESSAGE);

    }
}
