package controller;

import view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuController implements ActionListener {
    private MainWindow mainWindow;


    public MenuController(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        switch (actionCommand) {
            case "CHAT":

                if (!mainWindow.isSelected("CHAT")){
                    mainWindow.selectChat();
                }
                break;

            case "CONNECT":
                if (!mainWindow.isSelected("CONNECT")){
                    mainWindow.selectConnect();
                }
                break;

            case "PROFILE":
                if (!mainWindow.isSelected("PROFILE")){
                    mainWindow.selectProfile();
                }
                break;

            case "LOGOUT":
                if (!mainWindow.isSelected("LOGOUT")){
                    mainWindow.selectLogout();
                }
                break;

            default:
                break;
        }
    }
}
