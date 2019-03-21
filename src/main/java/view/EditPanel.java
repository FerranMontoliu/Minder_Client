package view;
import controller.EditController;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.File;

public class EditPanel extends JPanel {
    private JLabel jlNewImage;
    private JTextArea jtaNewDescription;
    private JCheckBox jcbJava;
    private JCheckBox jcbC;
    private JButton jbCancel;
    private JButton jbSave;
    private JLabel jlProfilePic;


    public EditPanel(CardLayout clMainWindow){
        super(clMainWindow);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        createBorder();
        createImagePanel();
        createDescriptionPanel();
        createProgrammingOptions();
        createButtons();
        //setProfileImage();
    }

    public void createBorder(){
        TitledBorder border = new TitledBorder("\u2328 Edit Profile");
        border.setTitleJustification(TitledBorder.LEFT);
        border.setTitlePosition(TitledBorder.TOP);
        this.setBorder(border);
    }

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
        jlProfilePic.setIcon(new ImageIcon(new ImageIcon("Pictures/images.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
        jpImage.add(jlProfilePic);

        add(jpImage);
    }

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

    public void createProgrammingOptions(){
        TitledBorder border = new TitledBorder("Favourite Languages");
        border.setTitleJustification(TitledBorder.LEFT);
        border.setTitlePosition(TitledBorder.TOP);
        JPanel jpCheckBoxes = new JPanel(new FlowLayout());
        jpCheckBoxes.setBorder(border);
        jcbJava = new JCheckBox();
        jcbC = new JCheckBox();
        jpCheckBoxes.add(jcbC);
        jpCheckBoxes.add(new JLabel(new ImageIcon("icons/cancel_16px.png")));  //Posar icones corresponents.
        jpCheckBoxes.add(jcbJava);
        jpCheckBoxes.add(new JLabel(new ImageIcon("icons/cancel_16px.png")));

        add(jpCheckBoxes);
    }

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

    public void setNewProfilePic(String filename){
        jlProfilePic.setIcon(new ImageIcon(new ImageIcon(filename).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
    }


    /**
     * Getter del TextArea NewDescription
     * @return Text que conte el TextArea
     */
    public String getNewDescription() {
        return jtaNewDescription.getText();
    }

    public JCheckBox getJcbJava() {
        return jcbJava;
    }

    public JCheckBox getJcbC() {
        return jcbC;
    }

    public JButton getJbCancel() {
        return jbCancel;
    }

    public JButton getJbSave() {
        return jbSave;
    }
}
