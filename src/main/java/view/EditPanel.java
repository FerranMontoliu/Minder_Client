package view;
import controller.EditController;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class EditPanel extends JPanelSlider {
    private final static String HOBBIES_DEFAULT_TEXT = "Separate them with commas...";
    private JLabel jlNewImage;
    private JTextArea jtaNewDescription;
    private JCheckBox jcbJava;
    private JCheckBox jcbC;
    private JButton jbCancel;
    private JButton jbSave;
    private JLabel jlProfilePic;
    private ImageIcon provisionalImage;
    private ImageIcon selectedImage; //Tambe es podria guardar com a Image.
    private JTextField jtfSong;
    private JTextArea jtaHobbies;



    /**
     * Constructor i generador del EditPanel
     * @param clMainWindow cardLayout al qual pertany.
     */
    public EditPanel(CardLayout clMainWindow){
        //super(clMainWindow);

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        createBorder();
        createImagePanel();
        createDescriptionPanel();
        createProgrammingOptions();
        createOptionalFields();
        createButtons();
    }

    /**
     * Metode que genera el Border principal del EditPanel
     */
    public void createBorder(){
        TitledBorder border = new TitledBorder("\u2328 Edit Profile");
        border.setTitleJustification(TitledBorder.LEFT);
        border.setTitlePosition(TitledBorder.TOP);
        this.setBorder(border);
    }

    /**
     * Metode que crea la seccio de canviar imatge de perfil.
     */
    public void createImagePanel(){
        TitledBorder border = new TitledBorder("Change Picture");
        border.setTitleJustification(TitledBorder.LEFT);
        border.setTitlePosition(TitledBorder.TOP);
        JPanel jpImage = new JPanel(new FlowLayout());
        jpImage.setBorder(border);
        ImageIcon changePicture = new ImageIcon("icons/camera.png");
        jlNewImage = new JLabel(changePicture);
        jpImage.add(jlNewImage);
        jpImage.add(Box.createHorizontalStrut(60));

        jlProfilePic = new JLabel();
        if(selectedImage == null){
            jlProfilePic.setText("No image selected.");
            //jlProfilePic.setIcon(new ImageIcon(new ImageIcon("Pictures/images.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
        }else{
            jlProfilePic.setIcon(selectedImage);
        }

        jpImage.add(jlProfilePic);

        add(jpImage);
    }

    /**
     * Metode que crea la seccio de canviar descripcio d'usuari.
     */
    public void createDescriptionPanel(){
        TitledBorder border = new TitledBorder("Change Description");
        border.setTitleJustification(TitledBorder.LEFT);
        border.setTitlePosition(TitledBorder.TOP);
        JPanel jpDescription = new JPanel();
        jpDescription.setLayout(new BoxLayout(jpDescription, BoxLayout.PAGE_AXIS));
        jpDescription.setBorder(border);

        JLabel jlDescription = new JLabel("Description");
        jlDescription.setHorizontalAlignment(SwingConstants.LEFT);
        JPanel jpDescriptionLabel = new JPanel(new BorderLayout());
        jpDescriptionLabel.add(jlDescription, BorderLayout.WEST);
        jpDescriptionLabel.setMaximumSize(new Dimension(jpDescriptionLabel.getPreferredSize().width, 10)); //Per confirmar
        jpDescription.add(jpDescriptionLabel);

        jtaNewDescription = new JTextArea(5, 25);
        jtaNewDescription.setEditable(true);
        jtaNewDescription.setLineWrap(true);
        jtaNewDescription.setWrapStyleWord(true);
        JScrollPane jspDescription = new JScrollPane(jtaNewDescription);

        //jtaNewDescription.setText(user.getDesciption()); Caldria passar el usuari per parametre i ensenyarli la descripcio actual.
        jpDescription.add(jspDescription);

        add(jpDescription);
    }

    /**
     * Metode que crea la seccio de triar llenguatge de Programacio preferit.
     */
    public void createProgrammingOptions(){
        TitledBorder border = new TitledBorder("Favourite Languages");
        border.setTitleJustification(TitledBorder.LEFT);
        border.setTitlePosition(TitledBorder.TOP);
        JPanel jpCheckBoxes = new JPanel(new FlowLayout());
        jpCheckBoxes.setBorder(border);
        jcbJava = new JCheckBox();
        jcbC = new JCheckBox();
        jpCheckBoxes.add(jcbC);
        jpCheckBoxes.add(new JLabel(new ImageIcon("icons/c-24px.png")));  //Posar icones corresponents...
        jpCheckBoxes.add(jcbJava);
        jpCheckBoxes.add(new JLabel(new ImageIcon("icons/java-24px.png")));

        add(jpCheckBoxes);
    }

    /**
     * Metode que crea la seccio dels JButtons Cancel i Save
     */
    public void createButtons(){
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

    public void createOptionalFields(){
        TitledBorder border = new TitledBorder("Favourite Song");
        border.setTitleJustification(TitledBorder.LEFT);
        border.setTitlePosition(TitledBorder.TOP);
        JPanel jpSong = new JPanel(new FlowLayout());
        jpSong.setBorder(border);

        jtfSong = new JTextField(15);
        jpSong.add(jtfSong);
        add(jpSong);

        JPanel jpHobbies = new JPanel(new BorderLayout());
        TitledBorder border2 = new TitledBorder("Hobbies");
        border2.setTitleJustification(TitledBorder.LEFT);
        border2.setTitlePosition(TitledBorder.TOP);
        jpHobbies.setBorder(border2);

        jtaHobbies = new JTextArea(5, 25);
        jtaHobbies.setLineWrap(true);
        jtaHobbies.setWrapStyleWord(true);
        JScrollPane jscHobbies = new JScrollPane(jtaHobbies);
        jpHobbies.add(jscHobbies, BorderLayout.CENTER);

        add(jpHobbies);
    }

    public void registerController(EditController ec){
        jlNewImage.addMouseListener(ec);
        jtaHobbies.addFocusListener(ec);
        jbSave.addActionListener(ec);
        jbSave.setActionCommand("SAVE");
        jbCancel.addActionListener(ec);
        jbCancel.setActionCommand("CANCEL");

    }

    /**
     * Metode que canvia la imatge de perfil seleccionada.
     * @param newImage nova imatge seleccionada.
     */
    public void setNewProfilePic(Image newImage){
        if(selectedImage == null){
            jlProfilePic.setText("");
        }
        provisionalImage = new ImageIcon(newImage);
        jlProfilePic.setIcon(new ImageIcon(newImage.getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
    }


    /**
     * Getter del text del TextArea NewDescription
     * @return Text que conte el TextArea
     */
    public String getNewDescription() {
        return jtaNewDescription.getText();
    }

    /**
     * Metode que retorna la imatge de perfil seleccionada.
     * @return imatge seleccionada.
     */
    public ImageIcon getSelectedImage() {
        return (ImageIcon) jlProfilePic.getIcon();
    }

    /**
     * Metode que informa si l'usuari ha seleccionat que li agrada Java
     * @return indica si ha seleccionat el CheckBox
     */
    public boolean likesJava() {
        return jcbJava.isSelected();
    }

    /**
     * Metode que informa si l'usuari ha seleccionat que li agrada C
     * @return indica si ha seleccionat el CheckBox
     */
    public boolean likesC() {
        return jcbC.isSelected();
    }

    public void showWarning(String message) {
        JOptionPane.showMessageDialog(null, message,"Warning", JOptionPane.WARNING_MESSAGE);
    }

    public void disableCancel() {
        jbCancel.setEnabled(false);
    }

    public void enableCancel() {
        jbCancel.setEnabled(true);
    }

    /**
     * Metode que omple el EditPanel amb els continguts del User.
     * @param profilePicture imatge seleccionada
     * @param userDescription descripcio del User
     * @param java boolean que es true si li agrada Java
     * @param c boolean que es true si li agrada C
     */
    public void initiateEdit(Image profilePicture, String userDescription, boolean java, boolean c, String song, String hobbies) {
        if(profilePicture == null){
            provisionalImage = null;
            jlProfilePic.setText("No image selected.");
        }else{
            jlProfilePic.setIcon(new ImageIcon(profilePicture.getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
        }
        if((userDescription == null)||(userDescription.length() == 0)){
            jtaNewDescription.setText("");
        }else{
            jtaNewDescription.setText(userDescription);
        }
        if(java){
            jcbJava.setSelected(true);
        }
        if(c){
            jcbC.setSelected(true);
        }
        if(song == null){
            jtfSong.setText("");
        }else{
            jtfSong.setText(song);
        }
        if(hobbies == null){
            jtaHobbies.setText(HOBBIES_DEFAULT_TEXT);
        }else{
            jtaHobbies.setText(hobbies);
        }
    }


    /**
     * Metode que esborra el text per defecte del TextArea Hobbies
     */
    public void resetHobbies() {
        if(jtaHobbies.getText().equals(HOBBIES_DEFAULT_TEXT)){
           jtaHobbies.setText("");
        }
    }

    /**
     * Getter del text del TextField Song
     * @return Text que conte el TextField
     */
    public String getFavouriteSong() {
        return jtfSong.getText();
    }

    /**
     * Getter del text del TextArea Hobbies
     * @return Text que conte el TextArea
     */
    public String getUserHobbies() {
        if(jtaHobbies.getText().equals(HOBBIES_DEFAULT_TEXT)){
            return "";
        }
        return jtaHobbies.getText();
    }
}
