package controller;

import model.entity.User;
import network.ServerComunicationConnect;
import view.ConnectPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ConnectController implements ActionListener, MouseListener {
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
    private boolean like;
    public final static int IMAGE_LIMIT = 115;


    public ConnectController(ConnectPanel connectPanel, MenuController menuController, User associatedUser) {
        this.connectPanel = connectPanel;
        this.menuController = menuController;
        this.associatedUser = associatedUser;
        this.serverComunicationConnect = new ServerComunicationConnect(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        //TODO: marcar com a usuari vist nomes despres de donar like o dislike, info no, ja que ha de tornar a apareixer l'usuari

        switch (actionCommand) {
            case "DISLIKE": //TODO: Descomentar les comandes de server-client quan tot funcioni
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
        System.out.println("I like you!");
        //si hi ha match
        if(isMatch){
            menuController.showMatch();
        }else{
            serverComunicationConnect.startServerComunication(CONNECT_LIKE); //Demanem nou User a visualitzar
            //TODO: aqui s'ha de rebre el nou usuari a mostrar
            serverComunicationConnect.startServerComunication(CONNECT_USER);
        }
    }

    /**
     * Metode que gestiona la opcio d'ignorar/donar dislike (accessible tant fent drag and drop com des del boto vermell)
     */
    private void dislikeActions() {
        System.out.println("I don't like you..");
        serverComunicationConnect.startServerComunication(CONNECT_DISLIKE);
        //serverComunicationConnect.join();
        serverComunicationConnect.startServerComunication(CONNECT_USER); //Demanem nou User
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        /*JComponent comp = (JComponent) e.getSource();
        TransferHandler th = comp.getTransferHandler();
        th.exportAsDrag(comp, e, TransferHandler.COPY);
        if (like){
            System.out.println("Like");
        }else{
            System.out.println("dislike");
        }*/
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

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
        /*if(e.getPoint().x < 115){
            like = false;
        }else{
            like = true;
        }*/
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
            System.out.println("no hi ha mes usuaris a mostrar");
            moreUsers = false;
            connectPanel.showEndOfUsers();
            connectPanel.enableButtons(moreUsers);

        }else{
            this.connectUser = connectUser;
            moreUsers = true;
            connectPanel.loadNewUser(connectUser);
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

    public String getSourceUsername() {
        return associatedUser.getUsername();
    }

    public String getConnectUsername() {
        return connectUser.getUsername();
    }

    public void setMatch(boolean match) {
        isMatch = match;
    }

    public void obtainConnectUser() {
        serverComunicationConnect = new ServerComunicationConnect(this);
        serverComunicationConnect.startServerComunication(CONNECT_USER); //Demanem nou User
    }

    public boolean getMatch(){
        return isMatch;
    }

    public User getAssociatedUser() {
        return associatedUser;
    }
}
