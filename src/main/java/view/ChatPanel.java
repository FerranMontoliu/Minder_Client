package view;

import model.User;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class ChatPanel extends JPanel {

    private JTextField jtfMissatge;
    private JButton jbSend;
    private JPanel jpImages[];
    private JScrollPane jspTop;
    private JScrollPane jspCentre;
    private JPanel jpTop;
    private JPanel jpMessage;

    /**
     * Constructor
     * @param clMainWindow
     */
    public ChatPanel(CardLayout clMainWindow) {
        super(clMainWindow);
        User u= new User(false, "Polete", "19", true, "polete@polete.polete", "Polete777", "Polete777", null, "", true, true, "Church Of Hell", null, null, null, null, null);

        createChatPanel(u);
    }
    public void createChatPanel(User user) {
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
        jspTop = new JScrollPane();
        jspTop.createHorizontalScrollBar(); //Scroll d'imatges amb els matches
        jspTop.setPreferredSize(new Dimension(30,60));
        this.add(jspTop,BorderLayout.NORTH);
        jspCentre = new JScrollPane();
        jspCentre.createVerticalScrollBar();
        JTextPane jtpane = new JTextPane();
        jtpane.setEditable(false);
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
        this.add(jtpane,BorderLayout.CENTER);
        jtfMissatge = new JTextField(40);
        jpMessage = new JPanel(new BorderLayout());
        jpMessage.add(jtfMissatge,BorderLayout.CENTER);
        this.add(jpMessage,BorderLayout.SOUTH);
        ImageIcon iSend = new ImageIcon("icons/send.png");
        jbSend = new JButton(iSend);
        jpMessage.add(jbSend,BorderLayout.EAST);

        //Iterar per tots els matches afegint les imatges a cada panell corresponent
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

        JPanel jpBottom = new JPanel(); //JPanel del SOUTH -> Text + button send




        jpBottom.add(jtfMissatge, BorderLayout.CENTER); //Text
        jpBottom.add(jbSend, BorderLayout.EAST); //Send icon


        jspTop.add(jspTop);
        jspCentre.add(jpCentre);

        this.add(jspTop, BorderLayout.NORTH);
        this.add(jspCentre, BorderLayout.CENTER);*/
    }



}

