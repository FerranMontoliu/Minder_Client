package view;

import controller.MenuController;
import controller.OtherUserProfileController;


import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

/**
 * Classe encarregada de generar i gestionar el panell de Profile.
 */
public class ProfilePanel extends JPanel {
    private static final String DESCRIPTION_TAG = " Description: ";
    private static final String HOBBIES_TAG = " Favourite Hobbies: ";
    private static final String PROGRAMMING_TAG = " Favourite programming language: ";
    private static final String SONG_TAG = " Favourite song: ";
    private static final Color MINDER_PINK = new Color(202, 123, 148);
    private static final Font INFO_FONT = new Font(Font.DIALOG,  Font.TYPE1_FONT, 12);
    private static final Font TAG_FONT = new Font(Font.DIALOG_INPUT, Font.PLAIN, 11);

    private JLabel jlName;
    private JLabel jlPhoto;
    private JLabel jlAge;
    private JLabel jlDescription;
    private JLabel jlFavHobbies;
    private JLabel jlFavProgramming;
    private JLabel jlFavSong;
    private JButton jbEditProfile;
    private JButton jbBack;
    private JPanel jpEdit;
    private JButton jbAccountPreferences;

    /**
     * Constructor del panell principal de connexions entre usuaris. aquest panell es genera en dos casos: per a mostrar
     * la informacio de l'usuari associat a la compta, o be per a mostrar els usuaris dels que demana informació addicional.
     * Ho distingim a partir de l'String que passem per paràmetre
     *
     * @param clMainWindow CardLayout que el contindra i mostrara en cas desitjat
     * @param user string que indica si es tracta del panell de l'usuari associat o dels altres usuaris
     */
    public ProfilePanel(CardLayout clMainWindow, String user){
        super(clMainWindow);
        setLayout(new BorderLayout());
        createPhoto();
        createUserInfo();
        if (user.equals("ASSOCIATED")){
            createEditButton();
            createAccountPreferencesButton();
        }else{ //user.equals("OTHER")
            createReturnButton();
        }

    }

    /**
     * Metode que crea el button que permet tornar al connect panel
     */
    private void createReturnButton() {
        JPanel jpButton = new JPanel(new FlowLayout());
        jbBack = new JButton("Return", new ImageIcon("icons/back-arrow.png"));
        jpButton.add(jbBack);

        add(jpButton, BorderLayout.SOUTH);
    }

    /**
     * Metode que crea i col·loca el JButton d'edicio de perfil.
     */
    private void createEditButton() {
        jpEdit = new JPanel(new FlowLayout());
        jbEditProfile = new JButton("Edit Profile", new ImageIcon("icons/edit_profile_icon.png"));
        jbEditProfile.setBackground(Color.WHITE);
        jpEdit.add(jbEditProfile);
    }

    /**
     * Metode que crea i col·loca el boto d'edicio de preferencies del compte (on es podra editar el nom d'usuari,
     * contrassenya, filtre d'edat
     */
    private void createAccountPreferencesButton() {
        jbAccountPreferences = new JButton("Edit Account Preferences", new ImageIcon("icons/preferences.png"));
        jbAccountPreferences.setBackground(Color.WHITE);
        jpEdit.add(jbAccountPreferences);
        add(jpEdit, BorderLayout.SOUTH);
    }
    /**
     * Metode que es crida des del constructor per a crear els jlabels fixats ("Name: ", "Age: ", "Hobbies: ")
     * i crear (Sense fixar cap valor) per la informacio de l'usuari que acompanyara els jLabels anteriors. Aquesta info
     * s'incloura al metode includeUserInfo on se li passara la informacio de l'usuari en concret
     */
    private void createUserInfo() {
        TitledBorder border = new TitledBorder("Basic Information");
        border.setTitleColor(MINDER_PINK);
        border.setTitleJustification(TitledBorder.LEFT);
        border.setTitlePosition(TitledBorder.TOP);

        //Panell que conte els camps d'informacio de l'usuari
        JPanel jpUserInfo = new JPanel();
        jpUserInfo.setLayout(new BoxLayout(jpUserInfo, BoxLayout.PAGE_AXIS));
        jpUserInfo.setBorder(border);

        jpUserInfo.add(createDescription());
        jpUserInfo.add(createFavHobbies());
        jpUserInfo.add(createFavProg());
        jpUserInfo.add(createFavSong());
        add(jpUserInfo, BorderLayout.CENTER);


    }

    /**
     * Metode que genera el panell de mes baix nivell amb l'etiqueta i icone de canco preferida amb el jlbael
     * que contindra la info de la bbdd
     * @return JPanel a incloure al jpUserInfo, que conte els 4 panells d'informacio
     */
    private JPanel createFavSong() {
        JPanel jpSong = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        JLabel jlTag = new JLabel(SONG_TAG);
        jlTag.setIcon(new ImageIcon("icons/music-player.png"));
        jlTag.setFont(TAG_FONT);
        jlTag.setForeground(Color.GRAY);
        jlTag.setAlignmentX(Component.LEFT_ALIGNMENT);
        jpSong.add(jlTag);

        jlFavSong = new JLabel("");
        jlFavSong.setFont(INFO_FONT);
        jlFavSong.setAlignmentX(Component.LEFT_ALIGNMENT);
        jpSong.add(jlFavSong);
        jpSong.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));

        return jpSong;
    }

    /**
     * Metode que genera el panell de mes baix nivell amb l'etiqueta i icone de llenguatge de programacio preferit
     * amb el jlabel que contindra la info de la bbdd
     * @return JPanel a incloure al jpUserInfo, que conte els 4 panells d'informacio
     */
    private JPanel createFavProg() {

        JPanel jpProg = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));

        JLabel jlTag = new JLabel(PROGRAMMING_TAG);
        jlTag.setIcon(new ImageIcon("icons/programming-code.png"));
        jlTag.setFont(TAG_FONT);
        jlTag.setForeground(Color.GRAY);
        jpProg.add(jlTag);

        jlFavProgramming = new JLabel("");
        jlFavProgramming.setFont(INFO_FONT);

        jpProg.add(jlFavProgramming);

        jpProg.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));

        return jpProg;
    }

    /**
     * Metode que genera el panell de mes baix nivell amb l'etiqueta i icone de hobbies preferits amb el jlabel
     * que contindra la info de la bbdd
     * @return JPanel a incloure al jpUserInfo, que conte els 4 panells d'informacio
     */
    private JPanel createFavHobbies() {

        JPanel jpHobbies = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));

        JLabel jlTag = new JLabel(HOBBIES_TAG);
        jlTag.setIcon(new ImageIcon("icons/ticket.png"));
        jlTag.setFont(TAG_FONT);
        jlTag.setForeground(Color.GRAY);
        jlTag.setAlignmentX(Component.LEFT_ALIGNMENT);
        jpHobbies.add(jlTag);

        jlFavHobbies = new JLabel("");
        jlFavHobbies.setFont(INFO_FONT);
        jlFavHobbies.setAlignmentX(Component.LEFT_ALIGNMENT);
        jpHobbies.add(jlFavHobbies);

        jpHobbies.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));

        return jpHobbies;
    }

    /**
     * Metode que genera el panell de mes baix nivell amb l'etiqueta i icone de descripcio d'usuaru amb el jlabel
     * que contindra la info de la bbdd
     * @return JPanel a incloure al jpUserInfo, que conte els 4 panells d'informacio
     */
    private JPanel createDescription() {
        JPanel jpDescription = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        JLabel jlTag = new JLabel(DESCRIPTION_TAG);
        jlTag.setIcon(new ImageIcon("icons/hastag.png"));
        jlTag.setFont(TAG_FONT);
        jlTag.setForeground(Color.GRAY);
        jlTag.setAlignmentX(Component.LEFT_ALIGNMENT);
        jpDescription.add(jlTag);

        jlDescription = new JLabel("");
        jlDescription.setFont(INFO_FONT);
        jlDescription.setAlignmentX(Component.LEFT_ALIGNMENT);
        jpDescription.add(jlDescription);

        jpDescription.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));

        return jpDescription;
    }

    /**
     * Metode que es crida des del constructor per a crear el jlabel que contindra la foto principal, sense incloure cap
     * imatge, ja que aquesta nomes es posara quan es cridi a la funcio IncludePhoto amb la info de l'usuari
     */
    private void createPhoto() {

        JPanel jpUserPhoto = new JPanel();
        jpUserPhoto.setLayout(new BoxLayout(jpUserPhoto, BoxLayout.PAGE_AXIS));
        jlPhoto = new JLabel(new ImageIcon("Pictures/user-64px.png"));
        jlPhoto.setAlignmentX(Component.CENTER_ALIGNMENT);
        jlPhoto.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        jpUserPhoto.add(jlPhoto);

        jlName = new JLabel(); //omplire amb la informacio de l'usuari
        jlName.setFont(new Font(Font.DIALOG,  Font.BOLD, 15));
        jlName.setAlignmentX(Component.CENTER_ALIGNMENT);
        jlName.setBorder(BorderFactory.createEmptyBorder(10, 0, 3, 0));

        jlAge = new JLabel();
        jlAge.setFont(new Font(Font.DIALOG,  Font.PLAIN, 10));
        jlAge.setAlignmentX(Component.CENTER_ALIGNMENT);
        jlAge.setBorder(BorderFactory.createEmptyBorder(3, 0, 10, 0));

        jpUserPhoto.add(jlName);
        jpUserPhoto.add(jlAge);
        jpUserPhoto.setBackground(MINDER_PINK);
        add(jpUserPhoto, BorderLayout.NORTH);

    }

    /**
     * Metode que registra un ActionListener al JButton d'edicio de perfil.
     * @param mc controlador associat als components del panell de perfil que soliciten un canvi de panell.
     */
    public void registraAssociatedProfileController(MenuController mc){
        jbEditProfile.addActionListener(mc);
        jbEditProfile.setActionCommand("EDIT");

        jbAccountPreferences.addActionListener(mc);
        jbAccountPreferences.setActionCommand("ACCOUNT PREFERENCES");
    }

    /**
     * Metode que registra i vincula un actionListener del boto que permet tornar al menu principal
     * @param c controlador associat al jbBack.
     */
    public void registraOtherProfileController(OtherUserProfileController c){
        jbBack.addActionListener(c);
        jbBack.setActionCommand("BACK");
    }

    /**
     *  Metode que actualitza la informacio de l'usuari segons la informacio que se li passa com a parametre, de tal
     * manera que omple els camps del perfil amb aquesta informacio.
     * @param associated directori de les imatges.
     * @param username nom d'usuari associat.
     * @param age edat de l'usuari associat.
     * @param description descripcio de l'usuari associat.
     * @param likesJava gust per java de l'usuari associat.
     * @param likesC gust per C de l'usuari associat.
     * @param hobbies hobbies de l'usuari associat.
     * @param song canso preferida de l'usuari associat.
     */
    public void updateInfo(String associated, String username, int age, String description, boolean likesJava, boolean likesC, String hobbies, String song){
        updateNameAge(username, age);
        updateDescription(description);
        updateFavProgramming(likesJava,likesC);
        updateHobbies(hobbies);
        updateSong(song);
        updatePhoto(associated, username);
    }

    /**
     * Metode que actualitza la informacio rebuda de la bbdd. Es converteix l'string en format base64 a imatge (que es
     * guarda al fitxer username.jpg), i s'escala i arrodoneix al disseny desitjat.
     * @param associated directori on es guardara la photo.
     * @param photoName Usuari que ha fet login.
     */
    private void updatePhoto(String associated, String photoName) {
        ImageIcon picture = new ImageIcon(associated+"MinderDownloads/"+photoName+".jpg");
        Image scaleImage = picture.getImage().getScaledInstance(64, 64,Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(scaleImage);
        jlPhoto.setIcon(toCircle(icon));

    }

    /**
     * Metode que permet canviar el format d'una imatge quadrada per una circular.
     * @param icon imatge quadrada a transformar
     * @return Imatge en format Icon circular.
     */
    private Icon toCircle(ImageIcon icon) {
        //Definim un BufferedImage de 64x64 per a escalar la imatge original a aquesta dimensio
        BufferedImage image = new BufferedImage(64, 64, TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.fillOval(1, 1, 148, 148);
        g.setComposite(AlphaComposite.SrcIn);
        g.drawImage(icon.getImage(), 0, 0, null);
        g.dispose();
        //agafem l'amplada de la imatge en questio i arrodonim les cantonades
        int width = image.getWidth();
        BufferedImage circleBuffer = new BufferedImage(width, width, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = circleBuffer.createGraphics();
        g2.setClip(new Ellipse2D.Float(0, 0, width, width));
        g2.drawImage(image, 0, 0, width, width, null);

        return new ImageIcon(circleBuffer);
    }

    /**
     * Metode que actualitza el text del jlabel que mostra la informacio del llenguatge de programacio de l'usuari que
     * ha fet login.
     *
     * @param likesJava boolean que indica si li agrada el Java.
     * @param likesC boolean que indica si li agrada el C.
     */
    private void updateFavProgramming(boolean likesJava, boolean likesC) {
        String fav;
        if(likesJava && likesC){
            fav = "Java and C++";
        }else{
            if(likesJava){
                fav = "Java";
            }else{
                fav = "C++";
            }
        }
        jlFavProgramming.setText(fav);
    }

    /**
     * Metode que actualitza el text del jlabel que mostra la descripcio l'usuari que
     * ha fet login.
     *
     * @param description String que conte el text escrit per l'usuari a la hora de definir/editar el seu perfil i que
     *                    vol que es mostri.
     */
    private void updateDescription(String description) {
        jlDescription.setText(description);

    }

    /**
     * Metode que actualitza el text del jlabel que mostra la informacio del Nom i Edat de l'usuari que
     * ha fet login (i que es mostra a la part superior de la finestra, junt amb la seva imatge de perfil).
     *
     * @param username nom de l'usuari.
     * @param age edat de l'usuari.
     */
    private void updateNameAge(String username, int age) {
        jlName.setText(username);
        jlAge.setText(String.valueOf(age));
    }

    /**
     * Metode que actualitza el text del jlabel que mostra la llista de hobbies seguida de comes de l'usuari que
     * ha fet login.
     *
     * @param hobbies Array de Strings on cadascun representa una accio.
     */
    private void updateHobbies(String hobbies) {
        jlFavHobbies.setText(hobbies);
    }

    /**
     * Metode que actualitza el text del jlabel que mostra la informacio de la canco preferida de l'usuari que
     * ha fet login.
     *
     * @param song nom de la canso a mostrar.
     */
    private void updateSong(String song){
        jlFavSong.setText(song);
    }

}
