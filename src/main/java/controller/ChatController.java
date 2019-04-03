package controller;

import view.ChatPanel;

import javax.swing.*;
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
            chatPanel.setTextFieldMessage();
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
        System.out.println("Unmatch User");
        if(SwingUtilities.isRightMouseButton(e) ) {
            boolean remove = chatPanel.throwUnmatchMessage();
        }
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
    }

    @Override
    public void focusLost(FocusEvent e) {
        chatPanel.setTextFieldMessage();
    }

    public void runDefaultAppearance() {
        chatPanel.setDefaultText();
    }
}
