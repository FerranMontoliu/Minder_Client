package controller;

import model.entity.Chat;
import model.entity.Message;
import model.entity.User;
import network.ServerComunicationChat;
import network.ServerComunicationMessage;
import view.ChatPanel;
import view.LogoutFrame;
import view.UnmatchFrame;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.LinkedList;

import static java.lang.Thread.sleep;

/**
 * Controlador de la vista de Chat
 */
public class ChatController implements ActionListener,  MouseListener, FocusListener {

    //Communication commands
    private static final char USER_UNMATCHED = 'e';
    private static final char LOAD_CHAT = 'f';
    private static final char USER_MATCH_LIST = 'h';
    private static final char USER_INFO = 'm';

    private ServerComunicationChat serverComunicationChat;
    private ServerComunicationMessage serverComunicationMessage;

    private MenuController menuController;
    private ChatPanel chatPanel;
    private User associatedUser;
    private User infoUser;
    private String destinationUsername;
    private String unmatchingUser;
    private Message sendingMessage;
    private Chat receivedChat;
    private LinkedList<String> matchedUsernames;
    private UnmatchFrame unmatchFrame;


    /**
     * Constructor per parametres del ChatController
     * @param chatPanel vista associada
     * @param associatedUser usuari del qual s'esta controlant la vista.
     * @param menuController controlador de menu
     */
    public ChatController(ChatPanel chatPanel, User associatedUser, MenuController menuController) {
        this.chatPanel = chatPanel;
        this.associatedUser = associatedUser;
        this.menuController = menuController;
    }

    /**
     * Metode que implementa el ActionPerformed dels ActionListeners associats al ChatPanel
     * @param e esdeveniment
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "SEND":
                System.out.println("SEND MESSAGE");
                if(chatPanel.isChosen()) {
                    String message = chatPanel.retrieveTextToSend(); //Retrieve Text a enviar
                    if(message.length() > 0 & !message.equals("Write a Message... ")) {
                        sendingMessage = new Message(getSourceUsername(), message,getDestinationUsername());
                        try {
                            serverComunicationMessage.sendMessage(sendingMessage);
                        } catch (IOException e1) {
                            chatPanel.throwErrorMessage("Failed to send the message!");
                        }
                        chatPanel.setSentIcon();
                        try {
                            sleep(100);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        chatPanel.setSendIcon();
                    }
                    else {
                        chatPanel.noTextMessageError();
                    }
                }
                else {
                    chatPanel.throwErrorMessage("You have not chosen a chat!");
                }
                chatPanel.resetJTextField();
                break;
            case "TEXT":
                if (chatPanel.isChosen()) {
                    String message = chatPanel.retrieveTextToSend();
                    chatPanel.resetJTextField();
                    if (message.length() > 0) {
                        sendingMessage = new Message(getSourceUsername(), message, getDestinationUsername());
                        try {
                            serverComunicationMessage.sendMessage(sendingMessage);
                        } catch (IOException e1) {
                            chatPanel.throwErrorMessage("Failed to send the message!");
                        }
                        chatPanel.setSentIcon();
                        try {
                            sleep(100);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        chatPanel.setSendIcon();
                    } else {
                        chatPanel.noTextMessageError(); //Error si s'intenta enviar res de text
                    }
                } else {
                    chatPanel.throwErrorMessage("You have not chosen a chat!");
                }
                break;
            case "YES UNMATCH":
                unmatchFrame.hideFrame();
                if(destinationUsername.equals(unmatchingUser)){
                    chatPanel.setDefaultText();
                    chatPanel.disableSend();
                    if(serverComunicationMessage != null){
                        serverComunicationMessage.stopServerComunication();
                    }
                }
                serverComunicationChat.startServerComunication(USER_UNMATCHED);
                serverComunicationChat.startServerComunication(USER_MATCH_LIST);
                chatPanel.generateDynamicMatchButtons(matchedUsernames, this);
                break;
            case "NO UNMATCH":
                unmatchFrame.hideFrame();
                break;
            case "INFO":
                serverComunicationChat.startServerComunication(USER_INFO);
                menuController.setPanelReturn(0);
                menuController.loadConnectUserInfo(infoUser);
                menuController.showUserToConnectProfile();
                serverComunicationMessage.stopServerComunication();
                break;
            default: //Ens han apretat el botó associat a un match
                System.out.println("CLICK ESQUERRE");
                chatPanel.setTextFieldMessage();
                chatPanel.setChosen(true);
                String chatUsername = e.getActionCommand();
                loadMatchingChat(chatUsername);
                chatPanel.enableSend();
                chatPanel.changeBorderName(chatUsername);
                serverComunicationMessage = new ServerComunicationMessage(this, associatedUser.getUsername());
                serverComunicationMessage.startServerComunication();
                break;

        }

    }

    /**
     * Unimplemented.
     * @param e --
     */
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    /**
     * Metode que obte el nom d'usuari a partir del JButton al que se li ha fet click dret.
     * @param e esdeveniment.
     */
    private void getRightClickUnmatch(MouseEvent e) {
        JButton jbUnmatch = (JButton) e.getSource();
        unmatchingUser = jbUnmatch.getText();
    }

    /**
     * Unimplemented.
     * @param e --
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * Metode que implementa dues funcions: Habilitar el boto d'enviar o fer unmatch a un User
     * @param e esdeveniment.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if(SwingUtilities.isRightMouseButton(e) ) {
            getRightClickUnmatch(e);
            unmatchFrame = new UnmatchFrame();
            unmatchFrame.registerController(this);
            unmatchFrame.showFrame(chatPanel.getLocations());
        }else{
            chatPanel.setSendIcon();
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
     * Quan es prem el JTextField, s'amaga el text predeterminat
     * @param e Esdeveniment
     */
    @Override
    public void focusGained(FocusEvent e) {
        chatPanel.resetJTextField();
    }

    /**
     * Unimplemented.
     * @param e --
     */
    @Override
    public void focusLost(FocusEvent e) {

    }

    /**
     * Metode que crida a la vista a mostrar la seva aparensa per defecte.
     */
    public void runDefaultAppearance() {
        chatPanel.setDefaultText();
    }

    /**
     * Metode que retorna el User associat a la finestra, es a dir, el que ha iniciat sessio.
     * @return User associat.
     */
    public User getAssociatedUser() {
        return associatedUser;
    }

    /**
     * Metode que retorna el nom del usuari amb qui s'esta xatejant
     * @return nom del usuari amb qui s'esta xatejant.
     */
    public String getDestinationUsername() {
        return destinationUsername;
    }

    /**
     * Metode que carrega el nou missatge d'un User en concret. Carrega un text per defecte si el chat esta buit.
     */
    public void loadChat() {
        if(receivedChat != null){
            chatPanel.writeChat(receivedChat.getNewMessage());
        } else {
            chatPanel.writeChat("Start chatting now!");
        }

    }

    /**
     * Demana i carrega el chat directament quan s'ha fet un match
     * @param destinationUsername usuari amb el que s'acaba de fer match
     */
    public void loadMatchingChat(String destinationUsername) {
        this.destinationUsername = destinationUsername;
        serverComunicationChat.startServerComunication(LOAD_CHAT);
        loadFullChat();
    }


    /**
     * Metode que carrega TOT el chat entre dos users.
     */
    public void loadFullChat(){
        if(receivedChat != null){
            StringBuilder sb = new StringBuilder();
            for(Message m: receivedChat.getMessages()){
                sb.append("\n"+m.toString());
            }
            chatPanel.writeFullChat(sb);
        }
    }

    /**
     * Getter del nom d'usuari del associatedUser.
     * @return nom d'usuari del associatedUser.
     */
    public String getSourceUsername() {
        return associatedUser.getUsername();
    }

    /**
     * Setter del atribut serverCommunicationChat.
     * @param serverComunicationChat nova instancia.
     */
    public void setServerComunicationChat(ServerComunicationChat serverComunicationChat) {
        this.serverComunicationChat = serverComunicationChat;
    }

    /**
     * Getter de l'atribut unmatchingUser.
     * @return nom del usuari a qui es vol fer unmatch.
     */
    public String getUnmatchingUsername() {
        return unmatchingUser;
    }

    /**
     * Setter del atribut receivedChat
     * @param receivedChat nova instancia del chat rebut.
     */
    public void setReceivedChat(Chat receivedChat) {
        this.receivedChat = receivedChat;
    }

    /**
     * Metode que carrega la llista de matchs del associatedUser a la vista.
     * @param matchedUsernames Llista de usernames dels matchs del associatedUser.
     */
    public void loadMatchesList(LinkedList<String> matchedUsernames) {
        if(this.matchedUsernames == null){
            this.matchedUsernames = new LinkedList<>();
        }else{
            if(this.matchedUsernames.size() > 0){
                this.matchedUsernames.clear();
            }
        }

        this.matchedUsernames = matchedUsernames;
    }

    /**
     * Metode que finalitza la comunicacio de missatgeria en temps real.
     */
    public void finishComunications() {
        try{
            serverComunicationMessage.stopServerComunication();
        }catch(Exception e){

        }
    }

    /**
     * Metode que informa a l'usuari en cas de que li hagin fet unmatch.
     */
    public void informUnmatch() {
        chatPanel.throwErrorMessage("This user has unmatched you!");
        chatPanel.setDefaultText();
        serverComunicationChat.startServerComunication(USER_MATCH_LIST);
        chatPanel.generateDynamicMatchButtons(matchedUsernames, this);
    }

    /**
     * Setter del atribut userInfo.
     * @param userInfo nova instancia del atribut userInfo.
     */
    public void setUserInfo(User userInfo){
        this.infoUser = userInfo;
    }
}












