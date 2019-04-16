package controller;

import model.entity.Chat;
import model.entity.Message;
import model.entity.User;
import network.ServerComunicationChat;
import view.ChatPanel;

import javax.swing.*;
import java.awt.event.*;
import java.util.LinkedList;

import static java.lang.Thread.sleep;

public class ChatController implements ActionListener,  MouseListener, FocusListener {
    private static final char USER_UNMATCHED = 'e';
    private ServerComunicationChat serverComunicationChat;

    private ChatPanel chatPanel;
    private User associatedUser;
    private String destinationUsername;
    private String unmatchingUser;
    private Message sendingMessage;


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

        if(e.getActionCommand().charAt(0) == 'Z') { //Detecto que l'usuari ha premut un match
            chatPanel.setTextFieldMessage();
            chatPanel.setChosen(true);
            chatPanel.showMatchConversation((e.getActionCommand().charAt(1) - '0'));

        }

        if(e.getActionCommand().equals("SEND")) { //Ens han apretat el BOTÓ d'enviar
            System.out.println("SEND MESSAGE");
            if(chatPanel.isChosen()) {
                String message = chatPanel.retrieveTextToSend(); //Retrieve Text a enviar
                if(message.length() > 0 & !message.equals("Write a Message... ")) {
                    sendingMessage = new Message(getSourceUsername(), message,getDestinationUsername());
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
                chatPanel.throwErrorMessage();
            }
            chatPanel.resetJTextField();
        }
        if(e.getActionCommand().equals("TEXT")) { //Ens han apretat l'ENTER per enviar
            System.out.println("SEND MESSAGE");
            if(chatPanel.isChosen()) {
                String message = chatPanel.retrieveTextToSend();
                chatPanel.resetJTextField();
                if(message.length() > 0) {
                    sendingMessage = new Message(getSourceUsername(), message,getDestinationUsername());
                    chatPanel.setSentIcon();
                    try {
                        sleep(100);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    chatPanel.setSendIcon();
                }
                else {
                    chatPanel.noTextMessageError(); //Error si s'intenta enviar res de text
                }
            }
            else {
                chatPanel.throwErrorMessage();
            }
        }
    }

    /**
     * Accio de fer UNMATCH : quan l'usuari fa clic amb el boto dret sobre un match
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {

        if(SwingUtilities.isRightMouseButton(e) ) {
            System.out.println("UNMATCH");
            //TODO: Aconseguir el nom del user que has fet click
            getRightClickUnmatch(e);
            serverComunicationChat.startServerComunication(USER_UNMATCHED);
            boolean remove = chatPanel.throwUnmatchMessage();
            if(remove) {
                System.out.println("Match has been removed");
            }
        }
    }

    private void getRightClickUnmatch(MouseEvent e) {  //TODO: Aixo trenca infinits paradigmes? Es que MouseListener no deixa posar id
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
        chatPanel.setSendIcon();
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

    public void loadChat(Chat receivedChat) {
        StringBuilder stringBuilder = new StringBuilder();

        for (Message m: receivedChat.getMessages()) {
            stringBuilder.append(m.toString()).append(System.lineSeparator());
        }
        chatPanel.writeChat(stringBuilder.toString());
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
}












