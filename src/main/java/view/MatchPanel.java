package view;

import controller.MatchController;
import model.User;

import javax.swing.*;
import java.awt.*;

public class MatchPanel extends JPanel {
    private JButton jbChat;
    private JButton jbPlay;
    private JLabel jlphotoAssociated;
    private JLabel jlphotoMatched;

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

    private void createOptions() {
        JLabel jldescription = new JLabel("You and x have liked each other");
        jldescription.setAlignmentX(Component.CENTER_ALIGNMENT);
        jldescription.setBorder(BorderFactory.createEmptyBorder(3, 0, 10, 0));
        add(jldescription);

        jbChat = new JButton("Send a message");
        jbChat.setIcon(new ImageIcon("icons/chat.png"));
        jbChat.setAlignmentX(Component.CENTER_ALIGNMENT);

        jbPlay = new JButton("Continue playing");
        jbPlay.setIcon(new ImageIcon("icons/playing-cards.png"));
        jbPlay.setAlignmentX(Component.CENTER_ALIGNMENT);


        add(jbChat);
        add(jbPlay);
    }

    private void createPhotoMatch() {
        JPanel jpPhotoMatch = new JPanel();
        //jpPhotoMatch.setLayout(new FlowLayout());

        jlphotoAssociated = new JLabel();
        jlphotoMatched = new JLabel();

        jpPhotoMatch.add(jlphotoAssociated);
        jpPhotoMatch.add(jlphotoMatched);

        jpPhotoMatch.setBackground(new Color(129, 110, 115));
        add(jpPhotoMatch);
    }

    private void createTitle() {
        JLabel jlTitle = new JLabel();
        jlTitle.setIcon(new ImageIcon("icons/itsAMatchLight.png"));
        jlTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(jlTitle);
    }

    //public void setUsersMatched(User userAssociated, User userMatched){
    public void setUsersMatched(){
        jlphotoAssociated.setIcon(new ImageIcon("Pictures/user-64px.png"));
        jlphotoMatched.setIcon(new ImageIcon("Pictures/user-64px.png"));
    }
    public void registraController(MatchController matchController) {
        jbChat.addActionListener(matchController);
        jbChat.setActionCommand("CHAT");
        jbPlay.addActionListener(matchController);
        jbPlay.setActionCommand("PLAY");
    }
}
