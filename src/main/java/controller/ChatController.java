package controller;

import view.ChatPanel;

import javax.swing.*;
import java.awt.event.*;

import static java.lang.Thread.sleep;

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
            System.out.println("SEND MESSAGE");
            if(chatPanel.isChosen()) {
                String message = chatPanel.retrieveTextToSend(); //Retrieve Text a enviar
                chatPanel.setSentIcon();
                try {
                    sleep(100);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                chatPanel.setSendIcon();
            }
            else {
                chatPanel.throwErrorMessage();
            }
            chatPanel.resetJTextField();
        }
        if(e.getActionCommand().equals("TEXT")) { //Faig que amb l'enter també es pugui enviar
            System.out.println("SEND MESSAGE");
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

        if(SwingUtilities.isRightMouseButton(e) ) {
            System.out.println("UNMATCH");
            boolean remove = chatPanel.throwUnmatchMessage();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

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

    }

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
}
