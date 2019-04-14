package controller;

import view.LogoutFrame;

import java.awt.*;

public class LogoutController {
    private LogoutFrame logoutFrame;
    private Point location;
    private MenuController menuController;
    public LogoutController(Point location, MenuController menuController){
        logoutFrame = new LogoutFrame();
        logoutFrame.registerController(menuController);
        this.location = location;
        this.menuController = menuController;
    }

    public void showLogout() {
        logoutFrame.showFrame(location);
    }

    public void hideLogout(){
        logoutFrame.hideFrame();
    }
}
