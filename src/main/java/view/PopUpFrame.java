package view;

import javax.swing.*;
import java.awt.*;

public class PopUpFrame extends JFrame {
    private GridBagConstraints constraints;

    /**
     * Constructor que inicialitza el Frame que contindra la notificacio de nous matches
     */
    public PopUpFrame(){
        String header = "You have new matches!";
        setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();

        //Header: icono amb titol
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 2.0f;
        constraints.weighty = 1.0f;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.BOTH;
        JLabel headingLabel = new JLabel(header);
        headingLabel.setIcon(new ImageIcon("icons/notification-2.png")); // --- use image icon you want to be as heading image.
        headingLabel.setOpaque(false);
        add(headingLabel, constraints);

    }

    /**
     * Metode que afegeix el boto d'anar als missatges
     * @param jbChat
     */
    public void addChat(JButton jbChat){
        //Boto de missatges
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weightx = 1.0f;
        constraints.weighty = 1.0f;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.WEST;
        add(jbChat, constraints);
    }

    /**
     * Metode que afegeix el boto de tancar el popup
     * @param jbSkip
     */
    public void addSkip (JButton jbSkip){
        //Boto de Skip
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.weightx = 1.0f;
        constraints.weighty = 1.0f;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.EAST;
        add(jbSkip, constraints);
    }
}

