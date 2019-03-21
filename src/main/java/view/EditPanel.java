package view;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class EditPanel extends JPanel {


    public EditPanel(CardLayout clMainWindow){
        super(clMainWindow);
        setProfileImage();
    }

    public void setProfileImage(){
        JLabel jlProva = new JLabel();
        File f = new File("icons/cancel.png");
        jlProva.setIcon((Icon) f);
        add(jlProva);
    }
}
