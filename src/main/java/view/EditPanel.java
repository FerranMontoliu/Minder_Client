package view;
import controller.EditController;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class EditPanel extends JPanel {
    private JLabel jlNewImage;
    private JTextArea jtaNewDescription;
    private JCheckBox jcbJava;
    private JCheckBox jcbC;
    private JButton jbCancel;
    private JButton jbSubmit;
    private JLabel jlProfilePic;


    public EditPanel(CardLayout clMainWindow){
        super(clMainWindow);
        createImagePanel();

        //setProfileImage();
    }

    public void createImagePanel(){
        JPanel jpImage = new JPanel(new FlowLayout());
        ImageIcon changePicture = new ImageIcon("icons/cancel.png");
        jlNewImage = new JLabel(changePicture);
        jpImage.add(jlNewImage);
        jlProfilePic = new JLabel();
        jlProfilePic.setIcon(new ImageIcon(new ImageIcon("Pictures/images.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        jpImage.add(jlProfilePic);

        add(jpImage);
    }

    public void registerController(EditController ec){
        jlNewImage.addMouseListener(ec);
        jbSubmit.addActionListener(ec);
        jbSubmit.setActionCommand("SUBMIT");
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

    public JButton getJbSubmit() {
        return jbSubmit;
    }
}
