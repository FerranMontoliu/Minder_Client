package view;

import controller.ChatController;
import controller.MenuController;

import javax.swing.*;
import java.awt.*;

/**
 * Classe que genera un JFrame per a mostrar en cas que l'usuari desitgi fer un unMatch amb un altre usuari des del chat
 */
public class UnmatchFrame extends JFrame{
    private JButton jbCancel;
    private final Color BG_COLOR = new Color(173, 105, 127);
    private GridBagConstraints constraints;
    private JButton jbUnmatch;

    /**
     * Constructor sense parametres que inicialitza els components, layout i panells intermitjos del jframe
     */
    public UnmatchFrame(){
        setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();
        addHeader();
        addUnmatchButton();
        addCancelButton();
        setBackground(new Color(94, 94, 94));
    }

    /**
     * Metode que crea la capcalera del JFrame amb una icone i un text en forma de titol interrogatiu
     */
    private void addHeader() {
        //Header: icono amb titol
        String header = "   Are you sure you want to unmatch this user?";
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0f;
        constraints.weighty = 1.0f;
        constraints.insets = new Insets(5, 5, 2, 5);
        constraints.fill = GridBagConstraints.CENTER;
        JLabel headingLabel = new JLabel(header);
        headingLabel.setIcon(new ImageIcon("icons/delete-friend.png"));
        headingLabel.setOpaque(false);
        add(headingLabel, constraints);
    }

    /**
     * Metode que afegeix el boto de confirmacio de l'UnMacth amb la seva icona
     */
    private void addUnmatchButton() {
        jbUnmatch = new JButton("Unmatch", new ImageIcon("icons/check_icon.png"));
        constraints.insets = new Insets(1, 4, 1, 4);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.fill = GridBagConstraints.SOUTHEAST;
        constraints.gridwidth = 2;
        constraints.ipady = 12;
        constraints.ipadx = 12;

        add(jbUnmatch, constraints);
    }

    /**
     * Metode que afegeix el boto de cancel.lacio de l'UnMacth amb la seva icona corresponent
     */
    private void addCancelButton() {
        jbCancel = new JButton("  Cancel  ", new ImageIcon("icons/cancel_16px.png"));
        jbCancel.setSize(jbUnmatch.getSize());
        constraints.insets = new Insets(1, 4, 1, 4);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.fill = GridBagConstraints.SOUTHWEST;
        constraints.gridwidth = 1;
        constraints.ipady = 12;
        constraints.ipadx = 12;

        add(jbCancel, constraints);
    }

    /**
     * Metode que permet vincular els elements que generen un ActionEvent amb el controlador d'aques Jframe per a dur a
     * terme les corresponents accions en funcio d'aquests events i valors de components
     *
     * @param chatController Controlador del Panell de chat entre usuaris que han fet Match
     */
    public void registerController(ChatController chatController){
        jbUnmatch.addActionListener(chatController);
        jbUnmatch.setActionCommand("YES UNMATCH");
        jbCancel.addActionListener(chatController);
        jbCancel.setActionCommand("NO UNMATCH");
    }

    /**
     * Metode que permet mostrar el Frame per sobre de les altres finestres
     */
    public void showFrame(){
        setSize(370,170);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setBackground(BG_COLOR);
        setVisible(true);
    }

    /**
     * Metode que tanca aquesta finestra
     */
    public void hideFrame() {
        dispose();
    }
}

