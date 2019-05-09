package view;


import controller.ConnectController;
import model.entity.User;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Panell que es mostrarà en cas que el mode Connect estigui seleccionat, on l'usuari podra acceptar o refusar usuaris
 * en funcio de la seva imatge de perfil i informacio addicional a la que podra accedir prement el boto de mes informacio
 */
public class ConnectPanel extends JPanel {

    private JLabel lblProfilename;
    private JLabel lblProfilePic;
    private JButton jbDislike;
    private JButton jbMoreInfo;
    private JButton jbLike;

    /**
     * Constructor del panell principal de connexions entre usuaris.
     * @param clMainWindow CardLayout que el contindra i mostrara en cas desitjat
     */
    public ConnectPanel(CardLayout clMainWindow){
        super(clMainWindow);
        setLayout(new BorderLayout()); //mostrarem els components vesrticalment
        createUserSpace();
        createButtonsOptions();

        this.setBackground(new Color (173, 105, 127));
    }

    /**
     * Metode que inicialitza els camps de l'usuari per a poder mostrar-los a partir de la base de dades
     */
    private void createUserSpace() {
        JPanel jpUserSpace = new JPanel();
        lblProfilename = new JLabel();
        jpUserSpace.add(lblProfilename);

        lblProfilePic = new JLabel();
        lblProfilePic.setSize(20, 20);
        jpUserSpace.add(lblProfilePic);
        jpUserSpace.setBackground(new Color(231, 165, 187));

        add(jpUserSpace, BorderLayout.PAGE_START);
        showUser();
    }

    /**
     * Metode que crea el panell de botons inferiors per a donar Like, Dislike, o mostrar mes informacio
     */
    private void createButtonsOptions(){
        JPanel jpButtons = new JPanel();
        jpButtons.setAlignmentX(Component.CENTER_ALIGNMENT);

        ImageIcon iDislike = new ImageIcon("icons/cancel.png");
        jbDislike = new JButton(iDislike);

        jpButtons.add(jbDislike);

        ImageIcon iMoreInfo = new ImageIcon("icons/infoBlue.png");
        jbMoreInfo = new JButton(iMoreInfo);

        jpButtons.add(jbMoreInfo);

        ImageIcon ilike = new ImageIcon("icons/checked.png");
        jbLike = new JButton(ilike);

        jpButtons.add(jbLike);

        add(jpButtons, BorderLayout.SOUTH);

        jpButtons.setBackground(new Color(202, 123, 148));

    }

    /**
     * TEMPORAL: metode que mostra l'usuari desitjat des del controlador per tal de mostrar les seves dades: Nom, edat,
     * i fotografia de perfil
     */
    //public void showUser(User user){
        //lblProfilename.setText(user.getUsername()+ " ," + user.getAge());
        //lblProfilePic.setIcon((Icon)user.getPhoto());
    public void showUser() {
        //TODO: lblProfileName i ProfilePic per parametre
        JPanel jpUserImage = new JPanel();
        jpUserImage.setLayout(new BoxLayout(jpUserImage, BoxLayout.PAGE_AXIS));

        lblProfilename.setFont(new Font(Font.DIALOG,  Font.ROMAN_BASELINE, 15));
        lblProfilename.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblProfilename.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        jpUserImage.add(lblProfilename);

        lblProfilePic.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblProfilePic.setBorder(BorderFactory.createLineBorder(new Color(156, 120, 130), 3));
        jpUserImage.add(lblProfilePic);
        jpUserImage.setBackground(new Color(231, 165, 187));
        add(jpUserImage, BorderLayout.CENTER);
    }

    public void registraController(ConnectController controller) {
        jbDislike.addActionListener(controller);
        jbDislike.setActionCommand("DISLIKE");

        jbLike.addActionListener(controller);
        jbLike.setActionCommand("LIKE");

        jbMoreInfo.addActionListener(controller);
        jbMoreInfo.setActionCommand("INFO");

        lblProfilePic.setTransferHandler(new TransferHandler("text"));
        lblProfilePic.addMouseListener(controller);
    }

    /**
     * metode que mostra un nou disseny avisamt a l'usuari que hi ha hagut un match amb un altre usuari. Aqui se li dona
     * la possibilitat de començar a xatejar amb aquesta persona o seguir "jugant". Es passaria la informacio dels dos
     * usuaris per tal de mostrar les seves fotografies de perfil i noms
     */
    public void matchDesign(){
        JFrame jfMatch = new JFrame();
        JPanel jpMatch = new JPanel();
        jpMatch.setLayout(new BorderLayout());
        JLabel jlMatch = new JLabel();
        jlMatch.setIcon(new ImageIcon("icons/itsAMatch.png"));

        jpMatch.setBackground(Color.LIGHT_GRAY);
        jfMatch.add(jpMatch);
        jfMatch.setVisible(true);
    }

    /**
     * Metode utilitzat per a notficar errors. Genera un JDialog informatiu.
     * @param errorMessage missatge que indica l'error.
     */
    public void showWarning(String errorMessage) {
        JOptionPane.showMessageDialog(null, errorMessage,"Warning", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Metode que carrega les dades principals de l'usuari solicitat al servidor que es visualitza en el connectPanel .
     *
     */
    public void loadNewUser(String username, int age) {
        lblProfilename.setText(username + ", "+age);
        ImageIcon picture = new ImageIcon("MinderDownloads/"+username+".jpg");
        Image scaledImage = picture.getImage().getScaledInstance(256, 256,Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(scaledImage);
        lblProfilePic.setIcon(icon);  //TODO: Concretar el tema del tipus de l'atribut imatge
    }

    /**
     * Metode que mostra un missatge amb el format d'usuari indicant el fi d'usuaris disponibles per a conectar
     */
    public void showEndOfUsers() {
        lblProfilename.setText("You don't have any user to connect with.");
        lblProfilePic.setIcon(new ImageIcon("icons/finish.png"));
    }

    /**
     * Metode que activa o desactiva els botons d'accio en funcio del parametre
     */
    public void enableButtons(boolean enable){
        jbMoreInfo.setEnabled(enable);
        jbLike.setEnabled(enable);
        jbDislike.setEnabled(enable);
    }
}
