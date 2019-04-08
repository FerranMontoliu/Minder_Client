package controller;

import view.ConnectPanel;
import view.MainWindow;
import view.MatchPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ConnectController implements ActionListener, MouseListener {
    private ConnectPanel connectPanel;
    //private MatchPanel matchPanel;
    private MenuController menuController;


    public ConnectController(ConnectPanel connectPanel, MenuController menuController) {
        this.connectPanel = connectPanel;
        this.menuController = menuController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        //TODO: marcar com a usuari vist nomes despres de donar like o dislike, info no, ja que ha de tornar a apareixer l'usuari

        switch (actionCommand) {
            case "DISLIKE":
                System.out.println("I don't like you..");
                break;

            case "LIKE":
                System.out.println("I like you!");
                //si hi ha match
                menuController.showMatch();
                break;

            case "INFO":
                System.out.println("I like trains");
                //a aquesta funcio es passaria l'usuari
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
        System.out.println("pressed");
        JComponent comp = (JComponent) e.getSource();
        TransferHandler th = comp.getTransferHandler();
        th.exportAsDrag(comp, e, TransferHandler.COPY);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
