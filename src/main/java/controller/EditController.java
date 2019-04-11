package controller;

import model.FileChooser;
import model.InvalidFormatException;
import model.User;
import model.UserManager;
import network.ServerComunicationEdit;
import view.EditPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class EditController implements ActionListener, MouseListener, FocusListener {
    private static final char EDIT_PROFILE = 'c';

    private User associatedUser;
    private EditPanel editPanel;
    private MenuController menuController;
    private ServerComunicationEdit sc;
    private boolean editResult;


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
        sc = new ServerComunicationEdit(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        switch(actionCommand){
            case "SAVE":
                try {
                    ImageIcon img = editPanel.getSelectedImage();
                    String description = UserManager.fixSQLInjections(editPanel.getNewDescription());
                    boolean Java = editPanel.likesJava();
                    boolean C = editPanel.likesC();
                    UserManager.checkEditProfileNewData(img, description, Java, C); //Camps obligatoris
                    String song = UserManager.fixSQLInjections(editPanel.getFavouriteSong());
                    String hobbies = UserManager.fixSQLInjections(editPanel.getUserHobbies());
                    //associatedUser.edited()
                    //associatedUser.setCompleted(true);
                    //sc.startServerComunication(EDIT_PROFILE);
                    //sc.join();
                    if(editResult){
                        //L'edicio s'ha guardat satisfactoriament i podem canviar de finestra
                    }else{ //L'edicio no s'ha guardat be en el servidor i no podem fer el canvi de finestra
                        editPanel.showWarning("Hi ha hagut problemes amb la connexió al servidor.");
                    }

                } catch (InvalidFormatException e1) {
                    editPanel.showWarning(e1.getMessage());
                }
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

    @Override
    public void focusGained(FocusEvent e) {
        editPanel.resetHobbies();
    }

    @Override
    public void focusLost(FocusEvent e) {

    }

    public User getUser() {
        return associatedUser;
    }

    public void setEditResult(boolean editOK) {
        editResult = editOK;
    }
}
