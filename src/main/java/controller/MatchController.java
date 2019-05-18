package controller;


import network.ServerComunicationConnect;
import view.MatchPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controlador de la pantalla emergent de Match.
 */
public class MatchController implements ActionListener {
    //Communication commands
    private static final char CONNECT_USER = 'k';

    private ServerComunicationConnect serverComunicationConnect;
    private MatchPanel matchPanel;
    private MenuController menuController;

    /**
     * Constructor per parametres que guarda la vista i el controlador superior que provocara l'execucio d'aquest.
     * @param matchPanel  Panell de match associat als dos usuaris.
     * @param menuController Controlador del menu principal.
     * @param connectController controlador de la pestanya connect.
     */
    public MatchController(MatchPanel matchPanel, MenuController menuController, ConnectController connectController) {
        this.matchPanel = matchPanel;
        this.menuController = menuController;
        serverComunicationConnect = new ServerComunicationConnect(connectController);
    }


    /**
     * Metode que permet reaccionar als esdeveniments provocats per els botons del panell de match
     * @param e Accio que ve dels botons del panell
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        switch (actionCommand) {
            case "CHAT":
                menuController.goToChatPanel();
                break;
            case "PLAY":
                //demano un nou usuari a mosrar al connect
                serverComunicationConnect.startServerComunication(CONNECT_USER);
                menuController.closeMatch();
                break;
            default:
                break;

        }

    }

    /**
     * Metode que mostrara al "It's a Match" les dades (nom i fotografia de perfil) dels dos usuaris
     * @param associatedUsername username del usuari que esta usant el client
     * @param connectedUsername username del usuari amb qui acaba de fer match
     */
    public void showUsers(String associatedUsername, String connectedUsername){
        matchPanel.setUsersMatched(associatedUsername, connectedUsername);
    }

}
