package controller;

import model.entity.User;
import network.ServerComunicationEdit;
import view.EditPanel;
import view.PreferencesPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PreferencesController implements ActionListener {
    private PreferencesPanel preferencesPanel;
    private MenuController menuController;
    private User associatedUser;
    //TODO: Server communication preferences
    //private ServerCommunicationPreferences sc;

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
        //sc = new ServerComunicationEdit(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        switch (actionCommand){
            case "SAVE":

                break;
            case "CANCEL":
                menuController.cancelPreferences();
                break;
        }
    }
}
