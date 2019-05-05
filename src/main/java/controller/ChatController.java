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

public class ChatController implements ActionListener,  MouseListener, FocusListener {
    private static final char USER_UNMATCHED = 'e';
    private static final char LOAD_CHAT = 'f';
    private static final char USER_MATCH_LIST = 'h';
    private ServerComunicationChat serverComunicationChat;
    private ServerComunicationMessage serverComunicationMessage;

    private ChatPanel chatPanel;
    private User associatedUser;
    private String destinationUsername;
    private String unmatchingUser;
    private Message sendingMessage;
    private Chat receivedChat;
    private LinkedList<String> matchedUsernames;
    private UnmatchFrame unmatchFrame;


    public ChatController(ChatPanel chatPanel, User associatedUser) {
        this.chatPanel = chatPanel;
        this.associatedUser = associatedUser;
    }

    /**
     * Accio quan l'usuari prem sobre la foto d'un MATCH
     * Accio quan s'envia un missatge tant (ENTER com prenent el BOTO d'enviar)
     * @param e
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
                    //TODO: Parar ela communicacio del chat
                }
                serverComunicationChat.startServerComunication(USER_UNMATCHED);
                serverComunicationChat.startServerComunication(USER_MATCH_LIST);
                chatPanel.generateDynamicMatchButtons(matchedUsernames, this);
                break;
            case "NO UNMATCH":
                unmatchFrame.hideFrame();
                break;
            default: //Ens han apretat el botó d'un match
                System.out.println("Click a un xat");
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
     * Accio de fer UNMATCH : quan l'usuari fa clic amb el boto dret sobre un match
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    private void getRightClickUnmatch(MouseEvent e) {
        JButton jbUnmatch = (JButton) e.getSource();
        unmatchingUser = jbUnmatch.getText();
    }

    /**
     * Unimplemented.
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * Accio quan es deixa d'apretar el boto d'enviar
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if(SwingUtilities.isRightMouseButton(e) ) {
            System.out.println("HOLA");
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
     * @param e
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * Unimplemented.
     * @param e
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Quan es prem el JTextField, amago el text predeterminat
     * @param e
     */
    @Override
    public void focusGained(FocusEvent e) {
        chatPanel.resetJTextField();
    }

    @Override
    public void focusLost(FocusEvent e) {

    }

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

    public void loadChat() {
        if(receivedChat != null){
            chatPanel.writeChat(receivedChat.getNewMessage());
        } else {
            chatPanel.writeChat("Start chatting now!");
        }

    }

    public void loadFullChat(){
        if(receivedChat != null){
            StringBuilder sb = new StringBuilder();
            for(Message m: receivedChat.getMessages()){
                sb.append("\n"+m.toString());
            }
            chatPanel.writeFullChat(sb);
        }
    }

    public String getSourceUsername() {
        return associatedUser.getUsername();
    }

    public Message getSendingMessage() {
        return sendingMessage;
    }

    public void setServerComunicationChat(ServerComunicationChat serverComunicationChat) {
        this.serverComunicationChat = serverComunicationChat;
    }

    public String getUnmatchingUsername() {
        return unmatchingUser;
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

    public void setReceivedChat(Chat receivedChat) {
        this.receivedChat = receivedChat;
    }

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

    public void finishComunications() {
        try{
            serverComunicationMessage.stopServerComunication();
        }catch(Exception e){

        }
    }
}












