package view;

import controller.NotificationController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NotificationPopUp {
    private PopUpFrame frame;
    private Point location;
    private JButton jbChat;
    private JButton jbSkip;
    private Timer timer;

    /**
     * Constructor que inicialitza el frame tipus PopUpFrame que conte la notificacio de nous matches
     * @param location la localitzacio relativa en funcio del frame principal de l'aplicacio
     */
    public NotificationPopUp(Point location) {
        frame = new PopUpFrame();
        this.location = location;
        frame.setAlwaysOnTop(true);
        jbChat = new JButton("Go to messages");
        jbSkip = new JButton("  Skip  ");
    }

    /**
     * Metode que mostra el popUp i comença a comptar els 4 segons.
     */
    public void start() {
        showNotification();
        timer.start();
        timer.setRepeats(false);
    }

    /**
     * Metode que tanca la notificacio, el frame
     */
    public void closePopUp() {
        frame.dispose();
    }

    /**
     * Metode que genera el disseny del popUp.
     */
    public void showNotification() {
        //String message = "You got a new notification message. Isn't it awesome to have such a notification message.";
        frame.setSize(300,125);
        frame.setUndecorated(true);
        frame.addChat(jbChat);
        frame.addSkip(jbSkip);
        frame.setLocation(location.x, location.y - 100);
        frame.setVisible(true);
    }

    /**
     * Metode que vincula les accions dels botons del PopUp i del timer d'aquest, que genera un 4 segons despres del seu
     * start
     * @param controller controlador de les notificacions
     */
    public void registraController(NotificationController controller){
        jbChat.addActionListener(controller);
        jbChat.setActionCommand("CHAT");
        jbSkip.addActionListener(controller);
        jbSkip.setActionCommand("SKIP");
        timer = new Timer(4000, controller);
        timer.setActionCommand("TIME");
    }

    /**
     * Metode que para el comptatge del timer (per als casos en que l'usuari apreti un dels dos botons i no s'hagin d'esperar
     * els 4 segons completats
     */
    public void stopNotification() {
        timer.stop();
    }
}
