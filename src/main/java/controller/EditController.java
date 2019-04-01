package controller;

import model.FileChooser;
import model.User;
import view.EditPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class EditController implements ActionListener, MouseListener {
    private User associatedUser;
    private EditPanel editPanel;
    private MenuController menuController;

    /**
     * Constructor per parametres.
     * @param editPanel Panell d'edicio de perfil associat.
     * @param menuController controlador de Menu associat.
     * @param associatedUser usuari associat al editPanel
     */
    public EditController(EditPanel editPanel, MenuController menuController, User associatedUser){
        this.editPanel = editPanel;
        this.menuController = menuController;
        this.associatedUser = associatedUser;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        switch(actionCommand){
            case "SAVE":
                ImageIcon img = editPanel.getSelectedImage();
                String description = editPanel.getNewDescription();
                boolean Java = editPanel.likesJava();
                boolean C = editPanel.likesC();
                //UserManager?
                //associatedUser.update()
                break;
            case "CANCEL":
                menuController.cancelEdition();
                break;
        }
    }

    /**
     * Metode que escolta si han clicat a la Label per a canviar la imatge.
     * @param e MouseEvent associat.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        FileChooser fileChooser = new FileChooser();

        try {
            Image newImage = fileChooser.findImage();
            if(newImage != null){
                editPanel.setNewProfilePic(newImage);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        //Not implemented.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //Not implemented.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //Not implemented.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //Not implemented.
    }
}
