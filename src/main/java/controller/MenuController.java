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
    private ChatController chatController;
    private MatchController matchController;



    public MenuController(MainWindow mainWindow, User associatedUser) {
        this.mainWindow = mainWindow;
        this.associatedUser = associatedUser;
        connectController = new ConnectController(mainWindow.getConnect(), this);  //Aixo trenca paradigmes??
        mainWindow.registraConnectController(connectController);
        editController = new EditController(mainWindow.getEdit(), this, this.associatedUser);
        chatController = new ChatController(mainWindow.getChat());
        matchController = new MatchController(mainWindow.getMatch(), this, connectController, this.associatedUser);
        mainWindow.registraChatController(chatController);
        mainWindow.registraEditController(editController);
        mainWindow.registraMatchController(matchController);

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

    /**
     * Metode que tanca la pestanya informativa de "It's a match" i torna al connect panel
     */
    public void closeMatch() { mainWindow.changePanel("CONNECT");
    }

    /**
     * Metode que permet obrir directament un chat en concret entre dues persones (el primer user de l'array es el que te
     * iniciada la sessio i se li hauria d'obrir un chat amb el user de la segona posicio de l'array)
     * @param usersMatched
     */
    public void goToChatWith(User[] usersMatched) {
        mainWindow.changePanel("CHAT");
        mainWindow.selectChat();
        //aqui s'haura d'anar al chat nou entre els dos usuaris
    }

    /**
     * Metode que canvia el panell Connect a Match en cas de que hi hagi match entre dos usuaris
     */
    public void showMatch() {
        //a la funcio showUsers es passarien els usuaris en questio
        matchController.showUsers();
        mainWindow.changePanel("MATCH");

    }
}
