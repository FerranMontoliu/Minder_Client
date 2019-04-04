package view;

import controller.MatchController;

import javax.swing.*;
import java.awt.*;

public class MatchPanel extends JPanel {
    private JButton jbChat;
    private JButton jbPlay;
    private JLabel jlphotoAssociated;
    private JLabel jlphotoMatched;
    private JLabel jldescription;

    /**
     * Constructor del panell que es mostrara en cas d'haver-hi un match entre dos usuaris.
     * @param clMainWindow CardLayout que el contindra i mostrara en cas desitjat
     */
    public MatchPanel(CardLayout clMainWindow){
        super(clMainWindow);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        createTitle();
        createPhotoMatch();
        createOptions();
        setBackground(new Color(129, 110, 115));
    }

    /**
     * Metode que inclou els botons i el missatge que conte els dos noms dels usuaris
     */
    private void createOptions() {
        jldescription = new JLabel();
        //TODO: substituir aquesta funcio i cridar-la des del controlador passant usuari matched
        showNamesMessage();
        jldescription.setAlignmentX(Component.CENTER_ALIGNMENT);
        jldescription.setBorder(BorderFactory.createEmptyBorder(3, 0, 10, 0));
        add(jldescription);

        JPanel jpButtons = new JPanel();
        jpButtons.setLayout(new BoxLayout(jpButtons, BoxLayout.PAGE_AXIS));

        jbChat = new JButton("Send a message");
        jbChat.setIcon(new ImageIcon("icons/chat.png"));
        jbChat.setAlignmentX(Component.CENTER_ALIGNMENT);
        //jbChat.setMaximumSize(new Dimension(30, 30));

        jbPlay = new JButton("Continue playing");
        jbPlay.setIcon(new ImageIcon("icons/playing-cards.png"));
        jbPlay.setAlignmentX(Component.CENTER_ALIGNMENT);

        jpButtons.add(jbChat);
        jpButtons.add(jbPlay);
        jpButtons.setBackground(new Color(129, 110, 115));
        jpButtons.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));
        add(jpButtons);
    }

    /**
     * Metode que mostra el missatge de connexio entre l'usuari que ha donat el segon like amb el que ja li havia donat
     * anteriorment
     */
    public void showNamesMessage(){
        String name = "Pol";
        //TODO: Enviar per parametre el MatchedUser
        jldescription.setText("You and "+ name + " have liked each other!");
    }

    /**
     * Metode que crea els labels que contindran les imatges dels dos usuaris
     */
    private void createPhotoMatch() {
        JPanel jpPhotoMatch = new JPanel();

        jlphotoAssociated = new JLabel();
        jlphotoMatched = new JLabel();

        jpPhotoMatch.add(jlphotoAssociated);
        jpPhotoMatch.add(jlphotoMatched);

        jpPhotoMatch.setBackground(new Color(129, 110, 115));
        jpPhotoMatch.setMaximumSize(new Dimension(150,80));
        add(jpPhotoMatch);

    }

    /**
     * Metode que crea el titol amb la imatge "It's a Match!"
     */
    private void createTitle() {
        JLabel jlTitle = new JLabel();
        jlTitle.setIcon(new ImageIcon("icons/itsAMatchLight.png"));
        jlTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(jlTitle);
    }

    /**
     * Metode TEMPORAL: s'haura de substituir la capçalera per la de parametres per tal de posar les imatges i noms dels
     * usuaris en funcio d'aquests
     */
    //public void setUsersMatched(User userAssociated, User userMatched){
    public void setUsersMatched(){
        //TODO: Canviar les imatges dels dos usuaris corresponents al match
        jlphotoAssociated.setIcon(new ImageIcon("Pictures/user-64px.png"));
        jlphotoMatched.setIcon(new ImageIcon("Pictures/user-64px.png"));
    }

    /**
     * Metode que vincula les accions dels buttons que permeten anar al chat o continuar jugant
     * @param matchController controlador de la pestanya Match
     */
    public void registraController(MatchController matchController) {
        jbChat.addActionListener(matchController);
        jbChat.setActionCommand("CHAT");
        jbPlay.addActionListener(matchController);
        jbPlay.setActionCommand("PLAY");
    }
}
