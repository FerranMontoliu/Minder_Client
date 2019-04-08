package view;

import controller.NotificationController;

import javax.swing.*;
import java.awt.*;

public class NotificationPopUp extends Thread {
    private PopUpFrame frame;
    private Point location;
    private JButton jbChat;
    private JButton jbSkip;

    public NotificationPopUp(Point location) {
        frame = new PopUpFrame();
        this.location = location;
        jbChat = new JButton("Go to messages");
        jbSkip = new JButton("  Skip  ");
    }

    public void run() {
        showNotification();
        try {
            sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            closePopUp();
        }
    }

    private void closePopUp() {
        frame.dispose();
    }

    public void closeNotification() {
        //TODO: no es fa la interrupcio
        if (isInterrupted()){
            System.out.println("interrupt");
            interrupt();
        }
    }

    public void showNotification() {
        //String message = "You got a new notification message. Isn't it awesome to have such a notification message.";
        frame.setSize(300,125);
        frame.setUndecorated(true);
        frame.addChat(jbChat);
        frame.addSkip(jbSkip);
        frame.setLocation(location.x, location.y - 100);
        frame.setVisible(true);
    }

    public void registraController(NotificationController controller){
        jbChat.addActionListener(controller);
        jbChat.setActionCommand("CHAT");
        jbSkip.addActionListener(controller);
        jbSkip.setActionCommand("PLAY");
    }
}
