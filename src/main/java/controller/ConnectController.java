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

    //private MatchPanel matchPanel;
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
                System.out.println("I don't like you..");
               // serverComunicationConnect.startServerComunication(CONNECT_DISLIKE);
                //serverComunicationConnect.join();
                //serverComunicationConnect.startServerComunication(CONNECT_USER); //Demanem nou User
                break;

            case "LIKE":
                System.out.println("I like you!");

                //si hi ha match
                if(true /*isMatch*/){
                    menuController.showMatch();
                }else{
                    serverComunicationConnect.startServerComunication(CONNECT_USER); //Demanem nou User a visualitzar
                }
                break;

            case "INFO":
                System.out.println("I like trains");
                loadUserInfo();  //TODO: Descomentar quan es treballi amb Server
                menuController.showUserToConnectProfile();
                break;

            default:
                break;
        }
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

    @Override
    public void mouseReleased(MouseEvent e) {
        JComponent comp = (JComponent) e.getSource();
        TransferHandler th = comp.getTransferHandler();

        if(e.getPoint().x < IMAGE_LIMIT){
            th.setDragImage(new ImageIcon("icons/cancel.png").getImage());

        }else{
            th.setDragImage(new ImageIcon("icons/checked.png").getImage());
        }
        th.exportAsDrag(comp, e, TransferHandler.COPY);
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
        this.connectUser = connectUser;
        connectPanel.loadNewUser(connectUser);
    }

    /**
     * Metode que carrega la info de perfil de l'usuari que s'esta visualitzant a l'opcio Connect
     */
    public void loadUserInfo(){
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

    public void obtainConnectUser() throws InterruptedException {
        serverComunicationConnect = new ServerComunicationConnect(this);
        serverComunicationConnect.startServerComunication(CONNECT_USER); //Demanem nou User
        serverComunicationConnect.join();
        serverComunicationConnect.stopServerComunication();
    }
}
