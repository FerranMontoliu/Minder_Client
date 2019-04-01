package view;

import model.User;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.text.*;
import java.awt.*;

public class ChatPanel extends JPanel {

    private JTextField jtfMissatge;
    private JButton jbSend;
    private JPanel jpImages[];
    private JScrollPane jspMatches;
    private JScrollPane jspCentre;
    private JPanel jpMatches;
    private JPanel jpMessage; //Missatge a enviar bottom
    private JButton[] jbMatches;

    /**
     * Constructor
     * @param clMainWindow
     */
    public ChatPanel(CardLayout clMainWindow) {
        super(clMainWindow);
        User u= new User(false, "Polete", "19", true, "polete@polete.polete", "Polete777", "Polete777", null, "", true, true, "Church Of Hell", null, null, null, null, null);

        createChatPanel(u);
    }

    /**
     * TEMPORAL: Cal rebre tots els usuari amb qui s'ha fet match, extreure'n la foto i el nom
     * Quan es clica sobre un d'ells, s'obre el xat
     * @param users
     */
    public void createChatPanel(User users) {

        /*this.setLayout(new BorderLayout());
        JPanel jpUserSpace = new JPanel();
        JLabel lblProfilename = new JLabel();
        jpUserSpace.add(lblProfilename);

        JLabel lblProfilePic = new JLabel();
        lblProfilePic.setSize(20, 20);
        jpUserSpace.add(lblProfilePic);
        jpUserSpace.setBackground(new Color(231, 165, 187));
        this.add(jpUserSpace, BorderLayout.PAGE_START);
        */
        //Generar els dos scroll panes
        this.setLayout(new BorderLayout());
        jspMatches = new JScrollPane();
        TitledBorder tb = new TitledBorder("Matches");
        tb.setBorder(BorderFactory.createLineBorder(Color.black)); //Color del border total
        jspMatches.setBorder(tb);
        jspMatches.createVerticalScrollBar(); //Scroll d'imatges amb els matches
        jspMatches.setPreferredSize(new Dimension(80,70));

        //Iterar per tots els matches afegint les imatges a cada panell corresponent

        //ScrollPane de la conversa
        TitledBorder tb2 = new TitledBorder("Chat");
        tb2.setBorder(BorderFactory.createLineBorder(Color.black)); //Color del border total
        jspCentre = new JScrollPane();

        jspCentre.setBorder(tb2);
        jspCentre = new JScrollPane();

        jspCentre.createVerticalScrollBar();
        JTextPane jtpane = new JTextPane();
        jtpane.setBorder(tb2);
        jtpane.setEditable(false);

        jpMatches = new JPanel();
        jbMatches = new JButton[2]; //Array de matches, per les seves fotos
        ImageIcon[] Send = new ImageIcon[2];
        Send[0] = new ImageIcon("icons/userDark.png");
        Send[1] = new ImageIcon("icons/userLight.png");

        jbMatches[0] = new JButton(Send[0]);
        jbMatches[1] = new JButton(Send[1]);

        for(int i=0;i<jbMatches.length;i++) {
            jpMatches.add(jbMatches[i]);
        }
        jspMatches.getViewport().add(jpMatches);
        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
        StyledDocument doc = jtpane.getStyledDocument();

        Style style = jtpane.addStyle("I'm a Style", null);
        StyleConstants.setForeground(style, Color.red);

        try { doc.insertString(doc.getLength(), "BLAH ",style); }
        catch (BadLocationException e){}

        StyleConstants.setForeground(style, Color.blue);

        try { doc.insertString(doc.getLength(), "BLEH",style); }
        catch (BadLocationException e){}

        // Set the attributes before adding text
        jtpane.setCharacterAttributes(attributeSet, true);
        jtpane.setText("Agusti: Text de prova");
        jtfMissatge = new JTextField(43);
        jpMessage = new JPanel(new BorderLayout());
        jpMessage.add(jtfMissatge,BorderLayout.CENTER);
        ImageIcon iSend = new ImageIcon("icons/send.png");
        jbSend = new JButton(iSend);
        jpMessage.add(jbSend,BorderLayout.EAST);
        this.add(jspMatches,BorderLayout.NORTH);
        this.add(jtpane,BorderLayout.CENTER);
        this.add(jpMessage,BorderLayout.SOUTH);

        /*for (User u : user.getMatch()) {
            JLabel jlImage = new JLabel();
            jlImage.setIcon((Icon) u.getPhoto());
            jpImages[i] = new JPanel();
            jpImages[i].add(jlImage);
            i++;
            jpTop.add(jpImages[i]); //JPanel imatges, s'afegira a JScrollPane
        }

        jspTop.add(jpTop);


        JPanel jpCentre = new JPanel();
        jpCentre.setLayout(new BoxLayout(jpCentre, BoxLayout.PAGE_AXIS)); //Finestra xat

        jpCentre.add(new JLabel("Here you will be able to chat with your matches"));

        jpBottom.add(jtfMissatge, BorderLayout.CENTER); //Text
        jpBottom.add(jbSend, BorderLayout.EAST); //Send icon


        jspTop.add(jspTop);
        jspCentre.add(jpCentre);

        this.add(jspTop, BorderLayout.NORTH);
        this.add(jspCentre, BorderLayout.CENTER);*/
    }



}

