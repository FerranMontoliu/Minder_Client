package view;

import controller.MenuController;
import controller.OtherUserProfileController;
import model.entity.User;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class ProfilePanel extends JPanel {
    private static final String DESCRIPTION_TAG = "Description: ";
    private static final String HOBBIES_TAG = "Favorite Hobbies: ";
    private static final String PROGRAMMING_TAG = "Favorite programming language: ";
    private static final String SONG_TAG = "Favorite song: ";
    private static final Color MINDER_PINK = new Color(202, 123, 148);

    private JLabel jlName;
    private JLabel jlPhoto;
    private JLabel jlAge;
    private JLabel jlDescription;
    private JLabel jlFavHobbies;
    private JLabel jlFavProgramming;
    private JLabel jlFavSong;
    private JButton jbEditProfile;
    private JButton jbBack;

    /**
     * Constructor del panell principal de connexions entre usuaris. aquest panell es genera en dos casos: per a mostrar
     * la informacio de l'usuari associat a la compta, o be per a mostrar els usuaris dels que demana informació addicional.
     * Ho distingim a partir de l'String que passem per paràmetre
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
        //jpButton.setBackground(new Color(202, 123, 148));
        jpButton.add(jbBack);

        add(jpButton, BorderLayout.SOUTH);
    }

    /**
     * Metode que crea i col·loca el JButton d'edicio de perfil.
     */
    private void createEditButton() {
        JPanel jpButton = new JPanel(new FlowLayout());
        jbEditProfile = new JButton("Edit Profile", new ImageIcon("icons/edit_profile_icon.png"));
        jbEditProfile.setBackground(Color.WHITE);
        jpButton.add(jbEditProfile);

        add(jpButton, BorderLayout.SOUTH);
    }

    /**
     * Metode que es crida des del constructor per a crear els jlabels fixats ("Name: ", "Age: ", "Hobbies: ")
     * i crear (Sense fixar cap valor) per la informacio de l'usuari que acompanyara els jLabels anteriors. Aquesta info
     * s'incloura al metode includeUserInfo on se li passara la informacio de l'usuari en concret
     */
    private void createUserInfo() {
        //TODO: omplir els camps amb la informacio de l'usuari en questio

        TitledBorder border = new TitledBorder("Basic Information");
        border.setTitleColor(MINDER_PINK);
        border.setTitleJustification(TitledBorder.LEFT);
        border.setTitlePosition(TitledBorder.TOP);

        JPanel jpUserInfo = new JPanel();
        jpUserInfo.setLayout(new BoxLayout(jpUserInfo, BoxLayout.PAGE_AXIS));
        jpUserInfo.setBorder(border);

        jlDescription = new JLabel("Description:"); //omplire amb la informacio de l'usuari
        jlDescription.setIcon(new ImageIcon("icons/DescriptionIcon.png"));
        jlDescription.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        jpUserInfo.add (jlDescription);

        jlFavHobbies = new JLabel("Favorite Hobbies:"); //omplire amb la informacio de l'usuari
        jlFavHobbies.setIcon(new ImageIcon("icons/HobbiesIcon.png"));
        jlFavHobbies.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        jpUserInfo.add (jlFavHobbies);

        jlFavProgramming = new JLabel("Favorite programming language:"); //omplire amb la informacio de l'usuari
        jlFavProgramming.setIcon(new ImageIcon("icons/programming-code.png"));
        jlFavProgramming.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        jpUserInfo.add (jlFavProgramming);


        jlFavSong = new JLabel("Favorite song: ");
        jlFavSong.setIcon(new ImageIcon("icons/music-player.png"));
        jlFavSong.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        jpUserInfo.add(jlFavSong);

        add(jpUserInfo, BorderLayout.CENTER);


    }

    /**
     * Metode que es crida des del constructor per a crear el jlabel que contindra la foto principal, sense incloure cap
     * imatge, ja que aquesta nomes es posara quan es cridi a la funcio IncludePhoto amb la info de l'usuari
     */
    private void createPhoto() {
        //TODO: aquest metode nomes crea els jlabels per a que a la funcio de AddUserInfo s'atualitzi la informacio de les
        //TODO: etiquetes
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
     * Metode que registra un ActionListener al JButton d'edicio de perfil
     */
    public void registraAssociatedProfileController(MenuController mc){
        jbEditProfile.addActionListener(mc);
        jbEditProfile.setActionCommand("EDIT");
    }

    public void registraOtherProfileController(OtherUserProfileController c){
        jbBack.addActionListener(c);
        jbBack.setActionCommand("BACK");
    }

    /**
     * Metode que actualitza la informacio de l'usuari segons la informacio que se li passa com a parametre, de tal
     * manera que omple els camps del perfil amb aquesta informacio
     * @param user: usuari que ha iniciat sessio i que vol accedir a la seva informacio
     */
    public void updateInfo(User user){
        //TODO: cridar la funcio des del controller amb l'usuari per paramtre
        /*jlName.setText("Pol Rayos");
        jlAge.setText("19");
        jlFavProgramming.setText ("Java");
        jlFavHobbies.setText("Destrossar el github, Fer cafès");
        jlDescription.setText("M'agrada passar hores al lab de compus");
*/
        //TODO: Opció DINÀMICA
        //TODO ALBA: descomentar les dues instruccions inferiors per a convertir la imatge en base 64 de l'usuari


        updateNameAge(user.getUsername(), user.getAge());
        updateDescription(user.getDescription());
        updateFavProgramming(user.getLikesJava(), user.getLikesC());
        updateHobbies(user.getHobbies());
        updateSong(user.getFavSong());
        updatePhoto(user);
    }

    private void updatePhoto(User user) {
        ImageIcon picture = new ImageIcon("data/imageConverted.jpg");
        user.base64ToImage(user.getPhoto());
        Image scaleImage = picture.getImage().getScaledInstance(64, 64,Image.SCALE_DEFAULT);
        ImageIcon icon = new ImageIcon(scaleImage);
        jlPhoto.setIcon(toCircle(icon));

    }

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
        jlFavProgramming.setText(PROGRAMMING_TAG + fav);
    }

    private void updateDescription(String description) {
        jlDescription.setText(DESCRIPTION_TAG+description);

    }

    private void updateNameAge(String username, int age) {
        jlName.setText(username);
        jlAge.setText(String.valueOf(age));
    }

    private void updateHobbies(String[] hobbies) {
        String text = "";
        for(int i=0; i < hobbies.length;i++){
            text = text + hobbies[i];
            if (i+1 < hobbies.length){
                text = text + ", ";
            }
        }
        jlFavHobbies.setText(HOBBIES_TAG+text);
    }

    private void updateSong(String song){
        jlFavSong.setText(SONG_TAG+song);
    }

}
