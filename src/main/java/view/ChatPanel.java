package view;

import model.User;

import javax.swing.*;
import java.awt.*;

public class ChatPanel extends JPanel{

    private JTextField jtfMissatge;
    private JButton jbSend;
    private JPanel jpImages[];

    /**
     * Constructor del panell de chats
     * @param user
     */
    public ChatPanel(User user) {

        this.setLayout(new BorderLayout());

        //Generar els dos scroll panes
        JScrollPane jspTop = new JScrollPane();
        jspTop.createHorizontalScrollBar(); //Scroll d'imatges amb els matches
        JScrollPane jspCentre = new JScrollPane();
        jspCentre.createVerticalScrollBar();

        JPanel jpTop = new JPanel();
        jpTop.setLayout(new BoxLayout(jpTop, BoxLayout.LINE_AXIS));

        jpImages = new JPanel[user.getMatch().size()]; //?
        int i = 0;

        //Iterar per tots els matches afegint les imatges a cada panell corresponent
        for(User u:user.getMatch()){
            JLabel jlImage = new JLabel();
            jlImage.setIcon((Icon)u.getPhoto());
            jpImages[i] = new JPanel();
            jpImages[i].add(jlImage);
            i++;
            jpTop.add(jpImages[i]); //JPanel imatges, s'afegira a JScrollPane
        }

        jspTop.add(jpTop);


        JPanel jpCentre = new JPanel();
        jpCentre.setLayout(new BoxLayout(jpCentre, BoxLayout.PAGE_AXIS)); //Finestra xat

        jpCentre.add(new JLabel("Here you will be able to chat with your matches");

        JPanel jpBottom = new JPanel(); //JPanel del SOUTH -> Text + button send

        ImageIcon iSend = new ImageIcon("icons/send.png");
        jbSend = new JButton(iSend);


        jpBottom.add (jtfMissatge, BorderLayout.CENTER); //Text
        jpBottom.add(jbSend,BorderLayout.EAST); //Send icon


        jspTop.add(jspTop);
        jspCentre.add(jpCentre);

        this.add(jspTop, BorderLayout.NORTH);
        this.add(jspCentre,BorderLayout.CENTER);


    }

}
