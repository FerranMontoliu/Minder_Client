package controller;

import model.User;
import view.EditPanel;
import view.MatchPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MatchController implements ActionListener {
    private MatchPanel matchPanel;
    private ConnectController connectController;
    private User[] usersMatched;

    /**
     * Constructor per parametres que guarda la vista, el controlador superior que provocara l'execucio d'aquest i guardar
     * els usuaris associats a aquesta connexio.
     * @param matchPanel Panell de match associat als dos usuaris.
     * @param connectController controlador de la pestanya connect.
     * @param associatedUser usuari associat que ha donat like a l'usuari
     * @param matchedUser usuari que ja havia donat like a l'usuari associat
     */
    public MatchController(MatchPanel matchPanel, ConnectController connectController, User associatedUser, User matchedUser){
        this.matchPanel = matchPanel;
        this.connectController = connectController;
        usersMatched = new User[2];
        usersMatched[0] = associatedUser;
        usersMatched[1] = matchedUser;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
