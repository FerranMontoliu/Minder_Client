package view;
import controller.EditController;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class EditPanel extends JPanel {
    private JLabel jlNewImage;
    private JTextArea jtaNewDescription;
    private JCheckBox jcbJava;
    private JCheckBox jcbC;
    private JButton jbCancel;
    private JButton jbSave;
    private JLabel jlProfilePic;
    private ImageIcon selectedImage; //Tambe es podria guardar com a Image.


    /**
     * Constructor i generador del EditPanel
     * @param clMainWindow cardLayout al qual pertany.
     */
    public EditPanel(CardLayout clMainWindow){
        super(clMainWindow);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        createBorder();
        createImagePanel();
        createDescriptionPanel();
        createProgrammingOptions();
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
            jlProfilePic.setIcon(new ImageIcon(new ImageIcon("Pictures/images.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
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
        jpDescriptionLabel.setMaximumSize(new Dimension(250, 10)); //Per confirmar
        jpDescription.add(jpDescriptionLabel);

        jtaNewDescription = new JTextArea(5, 25);
        //jtaNewDescription.setMaximumSize();
        jtaNewDescription.setEditable(true);
        jtaNewDescription.setLineWrap(true);
        jtaNewDescription.setWrapStyleWord(true);
        //jtaNewDescription.setText(user.getDesciption()); Caldria passar el usuari per parametre i ensenyarli la descripcio actual.
        jpDescription.add(jtaNewDescription);

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
        jpCheckBoxes.add(new JLabel(new ImageIcon("icons/c-logo.png")));  //Posar icones corresponents.
        jpCheckBoxes.add(jcbJava);
        jpCheckBoxes.add(new JLabel(new ImageIcon("icons/java-2.png")));

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

    public void registerController(EditController ec){
        jlNewImage.addMouseListener(ec);
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
        selectedImage = new ImageIcon(newImage);
        jlProfilePic.setIcon(new ImageIcon(newImage.getScaledInstance(jlProfilePic.getWidth(), jlProfilePic.getHeight(), Image.SCALE_SMOOTH)));
    }


    /**
     * Getter del TextArea NewDescription
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
        return selectedImage;
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
}
