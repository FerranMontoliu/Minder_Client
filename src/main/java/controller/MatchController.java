package controller;

import model.User;
import view.MatchPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MatchController implements ActionListener {
    private MatchPanel matchPanel;
    private ConnectController connectController;
    private MenuController menuController;
    private User[] usersMatched;

    /**
     * Constructor per parametres que guarda la vista, el controlador superior que provocara l'execucio d'aquest i guardar
     * els usuaris associats a aquesta connexio.
     *
     * @param matchPanel        Panell de match associat als dos usuaris.
     * @param connectController controlador de la pestanya connect.
     * @param associatedUser    usuari associat que esta donant likes a usuaris
     */
    public MatchController(MatchPanel matchPanel, MenuController menuController, ConnectController connectController, User associatedUser) {
        this.matchPanel = matchPanel;
        this.connectController = connectController;
        this.menuController = menuController;
        usersMatched = new User[2];
        usersMatched[0] = associatedUser;
        usersMatched[1] = null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        switch (actionCommand) {
            case "CHAT":
                menuController.goToChatWith(usersMatched);
                break;
            case "PLAY":
                menuController.closeMatch();
                break;
            default:
                break;

        }

    }
    public void showUsers(){
        matchPanel.setUsersMatched();
    }
    public void setMatchedUser (User matched){
        usersMatched[1] = matched;
    }
}
