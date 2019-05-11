package controller;

import view.LogoutFrame;

import java.awt.*;

/**
 * Controlador de la vista de Logout
 */
public class LogoutController {
    private LogoutFrame logoutFrame;
    private Point location;

    /**
     * Constructor per parametres
     * @param location location de la vista
     * @param menuController controlador de menu
     */
    public LogoutController(Point location, MenuController menuController){
        logoutFrame = new LogoutFrame();
        logoutFrame.registerController(menuController);
        this.location = location;
    }

    /**
     * Metode que fa el frame de logout visible.
     */
    public void showLogout() {
        logoutFrame.showFrame(location);
    }

    /**
     * Metode que fa el frame de logout invisible.
     */
    public void hideLogout(){
        logoutFrame.hideFrame();
    }
}
