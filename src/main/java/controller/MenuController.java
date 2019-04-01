package controller;

import model.User;
import view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuController implements ActionListener {
    private User associatedUser;
    private MainWindow mainWindow;
    private ConnectController connectController;
    private EditController editController;



    public MenuController(MainWindow mainWindow, User associatedUser) {
        this.mainWindow = mainWindow;
        this.associatedUser = associatedUser;
        connectController = new ConnectController(mainWindow.getConnect());  //Aixo trenca paradigmes??
        mainWindow.registraConnectController(connectController);
        editController = new EditController(mainWindow.getEdit(), this, this.associatedUser);
        mainWindow.registraEditController(editController);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        switch (actionCommand) {
            case "CHAT":
                if(!mainWindow.isSelected("CHAT")) {
                    mainWindow.selectChat();
                    mainWindow.changePanel("CHAT");
                }
                break;

            case "CONNECT":
                if(!mainWindow.isSelected("CONNECT")) {
                    mainWindow.selectConnect();
                    mainWindow.changePanel("CONNECT");
                }

                break;

            case "PROFILE":
                if(!mainWindow.isSelected("PROFILE")) {
                    mainWindow.selectProfile();
                    mainWindow.changePanel("PROFILE");
                }
                break;

            case "LOGOUT":
                if(!mainWindow.isSelected("LOGOUT")) {
                    mainWindow.selectLogout();
                }
                break;

            case "EDIT":
                mainWindow.changePanel("EDIT");
                //mainWindow.setSelectedImage(associatedUser.getImage());
                break;
        }
    }

    /**
     * Metode que cancela la edicio de perfil i es retorna al panell Profile.
     */
    public void cancelEdition() {
        mainWindow.changePanel("PROFILE");
    }
}
