package controller;

import model.FileChooser;
import model.InvalidFormatException;
import model.ProfileImage;
import model.entity.User;
import model.UserManager;
import network.ServerComunicationEdit;
import view.EditPanel;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

/**
 * Controlador de la vista de Edit Profile
 */
public class EditController implements ActionListener, MouseListener, FocusListener {
    //Communication command
    private static final char EDIT_PROFILE = 'c';

    private User associatedUser;
    private User provisionalUser;
    private ProfileImage newImage;
    private EditPanel editPanel;
    private MenuController menuController;
    private ServerComunicationEdit sc;
    private boolean editResult;
    private boolean photoChanged;


    /**
     * Constructor per parametres.
     * @param editPanel Panell d'edicio de perfil associat.
     * @param menuController controlador general del menu.
     * @param associatedUser usuari associat al editPanel.
     */
    public EditController(EditPanel editPanel, MenuController menuController, User associatedUser){
        this.editPanel = editPanel;
        this.menuController = menuController;
        this.associatedUser = associatedUser;
        sc = new ServerComunicationEdit(this);
    }

    /**
     * Metode que implementa el ActionPerformed dels ActionListeners associats al EditPanel
     * @param e esdeveniment
     */
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
                    provisionalUser = new User(associatedUser.getUsername(), "");
                    if(photoChanged){
                        provisionalUser.imageToBase64(newImage.getFullPath());
                    }else{
                        provisionalUser.setPhoto(associatedUser.getPhoto());//else, no han canviat la foto, per tant, seguim amb la string que tenia abans.
                    }
                    provisionalUser.saveEdition(description, Java, C, song, hobbies);
                    sc.startServerComunication(EDIT_PROFILE);

                    associatedUser.saveEdition(description, Java, C, song, hobbies);
                    if(photoChanged){
                        associatedUser.imageToBase64(newImage.getFullPath());
                    }

                    associatedUser.setCompleted(true);
                    if(editResult){
                        associatedUser.base64ToImage(associatedUser.getUsername(), associatedUser.getUsername());
                        menuController.editionCompleted(associatedUser);

                    }else{
                        editPanel.showWarning("There has been a problem with the server communication.");
                    }
                    photoChanged = false;
                } catch (InvalidFormatException e1) {
                    editPanel.showWarning(e1.getMessage());
                } catch (IOException e1) {
                    editPanel.showWarning("There has been a problem with the server communication.");
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
            newImage = fileChooser.findImage();
            if(newImage != null){
                editPanel.setNewProfilePic(newImage.getProfilePic());
                photoChanged = true;
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

    /**
     * Unimplemented.
     * @param e --
     */
    @Override
    public void mousePressed(MouseEvent e) {
        //Not implemented.
    }

    /**
     * Unimplemented.
     * @param e --
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        //Not implemented.
    }

    /**
     * Unimplemented.
     * @param e --
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        //Not implemented.
    }

    /**
     * Unimplemented.
     * @param e --
     */
    @Override
    public void mouseExited(MouseEvent e) {
        //Not implemented.
    }

    /**
     * Unimplemented.
     * @param e --
     */
    @Override
    public void focusGained(FocusEvent e) {
        editPanel.resetHobbies();
    }

    /**
     * Unimplemented.
     * @param e --
     */
    @Override
    public void focusLost(FocusEvent e) {

    }

    /**
     * Getter del atribut provisionalUser
     * @return provisionalUser
     */
    public User getUser() {
        return provisionalUser;
    }

    /**
     * Setter del atribut editOK
     * @param editOK valor de editOK
     */
    public void setEditResult(boolean editOK) {
        editResult = editOK;
    }
}
