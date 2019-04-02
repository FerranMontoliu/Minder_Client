package controller;

import view.ChatPanel;

import java.awt.event.*;

public class ChatController implements ActionListener,  MouseListener, FocusListener {
    private ChatPanel chatPanel;
    private boolean buttonPressed;

    public ChatController(ChatPanel chatPanel) {
        this.chatPanel = chatPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().charAt(0) == 'Z') {
            chatPanel.setChosen(true); //A chat has been chosen
            chatPanel.showMatchConversation((e.getActionCommand().charAt(1) - '0'));
        }

        if(e.getActionCommand().equals("SEND")) { //Ens han apretat el boto d'enviar
            buttonPressed = true;
            if(chatPanel.isChosen()) {
                String message = chatPanel.retrieveTextToSend(); //Retrieve Text a enviar
            }
            else {
                chatPanel.throwErrorMessage();
            }
            chatPanel.resetJTextField();
        }
        if(e.getActionCommand().equals("TEXT")) { //Faig que amb l'enter també es pugui enviar

            if(chatPanel.isChosen()) {
                chatPanel.setSentIcon();
                chatPanel.retrieveTextToSend();
                chatPanel.resetJTextField();
            }
            else {
                chatPanel.throwErrorMessage();
            }
        }
    }

    /**
     * Unimplemented.
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(!buttonPressed) chatPanel.setSentIcon();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        chatPanel.setSendIcon();
        buttonPressed = false;
    }

    /**
     * Unimplemented.
     * @param e
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        //chatPanel.setSendIcon();
    }

    /**
     * Unimplemented.
     * @param e
     */
    @Override
    public void mouseExited(MouseEvent e) {
        //chatPanel.setSendIcon();

    }

    @Override
    public void focusGained(FocusEvent e) {
        chatPanel.resetJTextField();
    }

    @Override
    public void focusLost(FocusEvent e) {
        //chatPanel.setTextFieldMessage();
        //chatPanel.setSendIcon();
    }

    public void runDefaultAppearance() {
        chatPanel.setDefaultText();
    }
}
