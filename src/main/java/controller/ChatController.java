package controller;

import view.ChatPanel;

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
        buttonPressed = true;
        if(e.getActionCommand().equals("SEND")) {
            String message = chatPanel.retrieveTextToSend(); //Retrieve Text a enviar
            chatPanel.resetJTextField();
        }
        if(e.getActionCommand().equals("TEXT")) {
            System.out.println("Buenas");
        }
    }

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

    @Override
    public void mouseEntered(MouseEvent e) {

    }

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
}
