package view;

import controller.MatchController;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

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
    public void showNamesMessage(String name){
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
    public void setUsersMatched(String associatedUsername, String connectedUsername){
        ImageIcon associatedPicture = new ImageIcon(associatedUsername+"MinderDownloads/"+associatedUsername+".jpg");
        ImageIcon connectedPicture = new ImageIcon(associatedUsername+"MinderDownloads/"+connectedUsername+".jpg");
        Image associatedScaleImage = associatedPicture.getImage().getScaledInstance(64, 64,Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(associatedScaleImage);
        jlphotoAssociated.setIcon(toCircle(icon));
        Image connectedScaleImage = connectedPicture.getImage().getScaledInstance(64, 64,Image.SCALE_SMOOTH);
        ImageIcon icon2 = new ImageIcon(connectedScaleImage);
        jlphotoMatched.setIcon(toCircle(icon2));
        //A part d'actualitzar les dues imatges, tambe actualitzem el nom
        showNamesMessage(connectedUsername);
    }

    /**
     * Metode que permet canviar el format d'una imatge quadrada per una circular.
     * @param icon: imatge quadrada a transformar
     * @return Imatge en format Icon circular.
     */
    private Icon toCircle(ImageIcon icon) {
        BufferedImage image = new BufferedImage(64, 64, TYPE_INT_RGB); // Assuming logo 150x150
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.fillOval(1, 1, 148, 148); // Leaving some room for antialiasing if needed
        g.setComposite(AlphaComposite.SrcIn);
        g.drawImage(icon.getImage(), 0, 0, null);
        g.dispose();

        int width = image.getWidth();
        BufferedImage circleBuffer = new BufferedImage(width, width, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = circleBuffer.createGraphics();
        g2.setClip(new Ellipse2D.Float(0, 0, width, width));
        g2.drawImage(image, 0, 0, width, width, null);

        return new ImageIcon(circleBuffer);
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
