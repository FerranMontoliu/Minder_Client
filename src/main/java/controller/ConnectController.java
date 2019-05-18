package controller;

import model.entity.User;
import network.ServerComunicationConnect;
import view.ConnectPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Controlador de la vista de Connect
 */
public class ConnectController implements ActionListener, MouseListener {
    //Communication commands
    private static final char USER_MATCHED = 'd';
    private static final char CONNECT_LIKE = 'i';
    private static final char CONNECT_DISLIKE = 'j';
    private static final char CONNECT_USER = 'k';

    private ConnectPanel connectPanel;
    private User associatedUser;
    private User connectUser;
    private ServerComunicationConnect serverComunicationConnect;
    private boolean isMatch;
    private boolean moreUsers;
    private MenuController menuController;
    public final static int IMAGE_LIMIT = 115;


    /**
     * Construcor per parametres.
     * @param connectPanel vista associada.
     * @param menuController controlador general.
     * @param associatedUser usuari associat a la vista.
     */
    public ConnectController(ConnectPanel connectPanel, MenuController menuController, User associatedUser) {
        this.connectPanel = connectPanel;
        this.menuController = menuController;
        this.associatedUser = associatedUser;
        this.serverComunicationConnect = new ServerComunicationConnect(this);
    }


    /**
     * Metode que implementa el ActionPerformed dels ActionListeners associats al ConnectPanel
     * @param e esdeveniment
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        switch (actionCommand) {
            case "DISLIKE":
                dislikeActions();
                break;

            case "LIKE":
                likeActions();
                break;

            case "INFO":
                infoActions();
                break;

            default:
                break;
        }
    }

    /**
     * Metode que gestiona la opcio de mostrar mes informacio (nomes accessible des del boto blau d'informacio)
     */
    private void infoActions() {
        loadUserInfo();
        menuController.showUserToConnectProfile();
    }

    /**
     * Metode que gestiona la opcio d'acceptar/donar like (accessible tant fent drag and drop com des del boto verd)
     */
    private void likeActions() {
        serverComunicationConnect.startServerComunication(CONNECT_LIKE); //Demanem nou User a visualitzar
        serverComunicationConnect.startServerComunication(CONNECT_USER);
    }

    /**
     * Metode que gestiona la opcio d'ignorar/donar dislike (accessible tant fent drag and drop com des del boto vermell)
     */
    private void dislikeActions() {
        serverComunicationConnect.startServerComunication(CONNECT_DISLIKE);
        serverComunicationConnect.startServerComunication(CONNECT_USER);
    }

    /**
     * Unimplemented.
     * @param e --
     */
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * Unimplemented.
     * @param e --
     */
    @Override
    public void mousePressed(MouseEvent e) {
    }

    /**
     * Metode que permet fer el drag and drop de la imatge de l'usuari cap a qualsevol direccio: en cas de despplaçar
     * cap a la part dreta, l'icone que es mostrara sera el corresponent al "like", i si es fa cap a l'esquerra, "dislike"
     * @param e event generat al pressionar sobre la imatge i desplaçar el ratoli
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (moreUsers){
            JComponent comp = (JComponent) e.getSource();
            TransferHandler th = comp.getTransferHandler();

            if(e.getPoint().x < IMAGE_LIMIT){
                th.setDragImage(new ImageIcon("icons/cancel.png").getImage());
                dislikeActions();
            }else{
                th.setDragImage(new ImageIcon("icons/checked.png").getImage());
                likeActions();
            }
            th.exportAsDrag(comp, e, TransferHandler.COPY);
        }
    }

    /**
     * Unimplemented.
     * @param e --
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * Unimplemented.
     * @param e --
     */
    @Override
    public void mouseExited(MouseEvent e) {
    }

    /**
     * Metode utilitzat per a notficar errors a traves de la vista.
     * @param errorMessage missatge que indica l'error.
     */
    public void showWarning(String errorMessage) {
        connectPanel.showWarning(errorMessage);
    }

    /**
     * Metode que carrega el usuari solicitat al servidor a la vista Connect.
     * @param connectUser usuari solicitat.
     */
    public void loadNewUser(User connectUser) {
        if (connectUser.getUsername() == null){
            moreUsers = false;
            connectPanel.showEndOfUsers();
            connectPanel.enableButtons(moreUsers);

        }else{
            this.connectUser = connectUser;
            moreUsers = true;
            connectUser.base64ToImage(associatedUser.getUsername(), connectUser.getUsername());
            connectPanel.loadNewUser(associatedUser.getUsername(), connectUser.getUsername(), connectUser.getAge());
            connectPanel.enableButtons(moreUsers);

        }

    }

    /**
     * Metode que carrega la info de perfil de l'usuari que s'esta visualitzant a l'opcio Connect
     */
    public void loadUserInfo(){
        menuController.setPanelReturn(1);
        menuController.loadConnectUserInfo(connectUser);
    }

    /**
     * Getter del username del usuari associat.
     * @return Valor del username del usuari associat.
     */
    public String getSourceUsername() {
        return associatedUser.getUsername();
    }

    /**
     * Getter del username del usuari a connectar
     * @return Valor del username del usuari a connectar
     */
    public String getConnectUsername() {
        return connectUser.getUsername();
    }

    /**
     * Setter del atribut isMatch
     * @param match nou valor de isMatch
     */
    public void setMatch(boolean match) {
        isMatch = match;
    }

    /**
     * Metode que es comunica amb el server per a obtenir un nou user.
     */
    public void obtainConnectUser() {
        serverComunicationConnect = new ServerComunicationConnect(this);
        serverComunicationConnect.startServerComunication(CONNECT_USER); //Demanem nou User
    }

    /**
     * Getter del atribut isMatch
     * @return valor del atribut is Match
     */
    public boolean getMatch(){
        return isMatch;
    }

    /**
     * Getter de l'atribut associatedUser
     * @return associatedUser
     */
    public User getAssociatedUser() {
        return associatedUser;
    }

    /**
     * getter de l'atribut connectUser
     * @return usuari que s'esta visualitzant pel panell connect
     */
    public Object getConnectUser() {
        return connectUser;
    }

    /**
     * Metode que actualitza la base de dades per a afegir un nou match i mostra el "It's a Match" per la pantalla del client
     * @param isMatch boolean que indica si s'ha produit un match o no (que ens ho diu el servidor)
     */
    public void matchActions(boolean isMatch){
        if (isMatch){
            menuController.showMatch(associatedUser.getUsername(), connectUser.getUsername());
            serverComunicationConnect.startServerComunication(USER_MATCHED);
        }
    }
}
