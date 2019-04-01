package model;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class FileChooser {

    public Image findImage() throws IOException {
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        //filter the files
        file.removeChoosableFileFilter(file.getFileFilter());
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","jpeg","png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);

        if(result == JFileChooser.APPROVE_OPTION){
            return ImageIO.read(file.getSelectedFile());
        }else if(result == JFileChooser.CANCEL_OPTION){
            System.out.println("No file selected.");
        }
        return null;
    }

}
