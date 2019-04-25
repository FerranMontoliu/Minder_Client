package model;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Classe encarregada d'obrir un explorador d'arxius per a escollir una Imatge.
 */
public class FileChooser {
    private JFileChooser fileChooser;
    private String fullPath;
    /**
     * Metode que obra l'explorador d'arxius i permet seleccionar una imatge
     * @return Imatge seleccionada
     * @throws IOException
     */
    public Image findImage() throws IOException {
        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        setFilter();
        int result = fileChooser.showSaveDialog(null);
        if(result == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getCurrentDirectory();
            fullPath = file.getCanonicalPath() + "\\" + fileChooser.getDescription(fileChooser.getSelectedFile());
            System.out.println("PATH: " + fullPath);
            return ImageIO.read(fileChooser.getSelectedFile());
        }else if(result == JFileChooser.CANCEL_OPTION){
            System.out.println("No file selected.");
        }
        return null;
    }

    /**
     * Metode que filtra els arxius seleccionables a nomes imatges.
     */
    private void setFilter(){
        fileChooser.removeChoosableFileFilter(fileChooser.getFileFilter());
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","jpeg","png");
        fileChooser.addChoosableFileFilter(filter);
    }

}
