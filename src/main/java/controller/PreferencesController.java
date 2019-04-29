package controller;

import model.EmptyTextFieldException;
import model.InvalidFormatException;
import model.UserManager;
import model.entity.User;
import network.ServerCommunicationPreferences;
import network.ServerComunicationEdit;
import view.EditPanel;
import view.PreferencesPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class PreferencesController implements ActionListener {
    private static final char EDIT_PREFERENCES = 'l';
    private static final char CHECK_USER = 'a'; //IMPLEMENTACIO IGUAL A LOGIN_USER

    private PreferencesPanel preferencesPanel;
    private MenuController menuController;
    private User associatedUser;
    private boolean editResult;
    private boolean correctLogin;
    private ServerCommunicationPreferences sc;

    /**
     * Constructor per parametres.
     * @param preferencesPanel Panell d'edicio de preferencies del compte
     * @param menuController controlador de Menu associat.
     * @param associatedUser usuari associat al compte iniciat
     */
    public PreferencesController(PreferencesPanel preferencesPanel, MenuController menuController, User associatedUser){
        this.preferencesPanel = preferencesPanel;
        this.menuController = menuController;
        this.associatedUser = associatedUser;
        this.editResult = false;
        sc = new ServerCommunicationPreferences(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        switch (actionCommand){
            case "NO FILTER":
                    if (preferencesPanel.noFilterChecked()){
                        preferencesPanel.disableFilter();
                        updateAgeFilter();
                        System.out.println(associatedUser.getMaxAge());
                    }else{
                        preferencesPanel.enableFilter();
                    }

                break;
            case "SAVE":

                try {

                    UserManager.isEmpty(preferencesPanel.getCurrentPassword(), "password");
                    //TODO: verificar que la current es realment la current
                    //ara cutre i trencant paradigmes:
                    sc.startServerComunication(CHECK_USER); //aixo em diu si correct login o no (mira la contrassenya
                    //System.out.println(preferencesPanel.getCurrentPassword());
                    //System.out.println(associatedUser.getPassword());
                    //System.out.println(correctLogin);
                    if (correctLogin){
                        String newPassword = preferencesPanel.getNewPassword();
                        String newConfirmPassword = preferencesPanel.getNewPasswordConfirm();
                        UserManager.isEmpty(newPassword, "password");
                        UserManager.signUpPasswordIsCorrect(newPassword, newConfirmPassword);

                        //TODO: fer update de new password, age filter and premium access
                        //associatedUser.savePreferencesUpdate();

                        sc.startServerComunication(EDIT_PREFERENCES);
                        associatedUser.setCompleted(true);

                        if(editResult){
                            //nomes s'ha d'actualitzar el nou usuari associat
                            //no tindrem problema amb el connect user ja que es prepara el seguent en el moment en que demane, un altre usuari
                            //per tant, ja s'agafara el correcte
                            menuController.editionCompleted(associatedUser);
                        }else{
                            preferencesPanel.showWarning("There has been a problem with the server communication.");
                        }
                    }else{
                        preferencesPanel.showWarning("Current Password incorrect. Please, try again");
                    }
                    //TODO: enviar al servidor
                } catch (EmptyTextFieldException e1) {
                    preferencesPanel.showWarning(e1.getMessage());
                } catch (InvalidFormatException e2) {
                    preferencesPanel.showWarning("New Password and New Password Confirm do not match");
                } catch (IOException e3) {
                    e3.printStackTrace();
                }

                break;
            case "CANCEL":
                menuController.cancelPreferences();
                break;
        }
    }

    private void updateAgeFilter() {
        associatedUser.setMaxAge("0");
    }

    public User getAssociatedUser(){
        return associatedUser;
    }

    public void setEditResult(boolean editResult){
        this.editResult = editResult;
    }

    public void setCorrectLogin(boolean b) {
        this.correctLogin = b;
    }

    public void setSignInUser(User u) {
        this.associatedUser = u;
    }

    public User getChekingUser() {
        return new User(associatedUser.getUsername(), preferencesPanel.getCurrentPassword());
    }
}
