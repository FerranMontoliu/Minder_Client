package view;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class EditPanel extends JPanel {
    private JFileChooser jfcImage;

    public EditPanel(){
        jfcImage = new JFileChooser();
        jfcImage.setCurrentDirectory(new File(System.getProperty("user.home")));
        //filter the files
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png");
        jfcImage.addChoosableFileFilter(filter);
    }
}
