package view;

import controller.MenuController;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame{
    private JMenuItem menuConnect;
    private JMenuItem menuChat;
    private JMenuItem menuLogout;
    private JMenuItem menuProfile;
    private String selected;
    private JPanel jpSelected;
    private CardLayout clMainWindow;
    private ConnectPanel jpConnect;
    private ProfilePanel jpProfile;

    //private EditPanel jpEditProfile;


    /**
     * Constructor de la vista de la pantalla principal del programa. Es crea la barra de menú superior i s'inicialitzen
     * tots els panells que es mostraran a la part central de la pantalla, mostrant unicament al principi la pantalla de
     * Connect (like o dislike d'usuaris)
     */
    public MainWindow(String s){
        createMenu();
        createContentPanels();
        windowPreferences();
        changePanel(s);
    }

    /**
     * Metode que crea el CardLayout que permetra mostrar un panell o un altre. Aqui, tambe es creen els panells que
     * aquest conte, mostrant per defecte el panell Connect.
     */
    public void createContentPanels() {
        clMainWindow = new CardLayout();
        jpSelected = new JPanel(clMainWindow);
        createConnectPanel();
        createProfilePanel();
        //aqui crear tants panells com opcions del menu: profile, chat, connect...
        changePanel("CONNECT");
        getContentPane().add(jpSelected);
    }

    private void createProfilePanel() {
        jpProfile = new ProfilePanel(clMainWindow);
        jpSelected.add("PROFILE", jpProfile);

    }

    /**
     * Es crida al constructor que inicialitza aquest panell de connexio entre usuaris, mostrant la foto, nom i edat de
     * l'usuari amb 3 botons: acceptar, declinar o obtenir més informacio
     */
    private void createConnectPanel() {
        jpConnect = new ConnectPanel(clMainWindow);
        //Gestiono el panell pel CardLayout
        jpSelected.add("CONNECT", jpConnect);
    }

    /**
     * Metode que mostra el panell desitjat pel card layout principal
     * @param panelName String que conte el nom del panell a mostrar. Poden ser: "CONNECT", "CHAT", "PROFILE" o "LOGOUT"
     */
    public void changePanel(String panelName) {
        clMainWindow.show(jpSelected, panelName);
    }

    /**
     * Mètode encarregat d'inicialitzar els valors per defecte de la finestra principal.
     *
     */
    private void windowPreferences() {
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(420, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Minder");
    }

    /**
     * Metode que serveix per generar la barra de menu superior que sera visible i accessible des de qualsevol finestra
     * de l'aplicacio
     */
    private void createMenu() {
        //El JMenuBar es com un panell que agrupa els JMenuItems
        JMenuBar menuBar;
        menuBar = new JMenuBar();

        //un JMenuItem simula un boto que s'inclou al JMenuBar i que canvia el color del text quan aquest es prem, i
        //permet incloure submenus i iconImages facilment
        menuChat = new JMenuItem("Chat");

        menuConnect = new JMenuItem("Connect");
        selected = "CONNECT";

        menuProfile = new JMenuItem("Profile");

        menuLogout = new JMenuItem("Log Out");

        menuBar.setForeground(new Color(255, 88, 100));

        //L'unic IconImage que ha d'estar seleccionat es el del panell per defecte: ConnectPanel. Tots els altres
        //han d'estar desseleccionats
        deselectLogout();
        deselectProfile();
        deselectChat();
        selectConnect();

        menuBar.add(menuChat);
        menuBar.add(menuConnect);
        menuBar.add(menuProfile);
        menuBar.add(menuLogout);

        getContentPane().add(menuBar, BorderLayout.PAGE_START);

    }

    /**
     * Metode que vincula events de cada un dels JMenuItem al controlador del menu
     * @param controller Controlador associat a la vista.
     */
    public void registraController(MenuController controller) {
        menuChat.addActionListener(controller);
        menuChat.setActionCommand("CHAT");

        menuConnect.addActionListener(controller);
        menuConnect.setActionCommand("CONNECT");

        menuProfile.addActionListener(controller);
        menuProfile.setActionCommand("PROFILE");

        menuLogout.addActionListener(controller);
        menuLogout.setActionCommand("CONNECT");
    }

    /**
     * Es mostra la imatge de color mes clar per a indicar que el Chat no esta seleccionat
     */
    public void deselectChat(){
        menuChat.setIcon(new ImageIcon("icons/chatLight.png"));
    }

    /**
     * Es mostra la imatge de color mes clar per a indicar que el Profile no esta seleccionat
     */
    public void deselectProfile(){
        menuProfile.setIcon(new ImageIcon("icons/userLight.png"));
    }

    /**
     * Es mostra la imatge de color mes clar per a indicar que el Connect (la pagina principal) no esta seleccionat
     */
    public void deselectConnect(){
        menuConnect.setIcon(new ImageIcon("icons/minderLight.png"));
    }

    /**
     * Es mostra la imatge de color mes clar per a indicar que el Logout no esta seleccionat
     */
    public void deselectLogout(){
        menuLogout.setIcon(new ImageIcon("icons/logoutLight.png"));
    }

    /**
     * Es mostra la imatge activa del Chat i es canvien totes les altres imatges a colors clars, i s'actualitza
     * l'atribut selected al menu corresponent
     */
    public void selectChat(){
        selected = "CHAT";
        menuChat.setIcon(new ImageIcon("icons/chatDark.png"));

        deselectLogout();
        deselectProfile();
        deselectConnect();
    }

    /**
     * Es mostra la imatge activa del Profile i es canvien totes les altres imatges a colors clars, i s'actualitza
     * l'atribut selected al menu corresponent
     */
    public void selectProfile(){
        selected = "PROFILE";
        menuProfile.setIcon(new ImageIcon("icons/userDark.png"));

        deselectLogout();
        deselectChat();
        deselectConnect();
    }

    /**
     * Es mostra la imatge activa del Connect i es canvien totes les altres imatges a colors clars, i s'actualitza
     * l'atribut selected al menu corresponent
     */
    public void selectConnect(){
        selected = "CONNECT";
        menuConnect.setIcon(new ImageIcon("icons/minderDark.png"));

        deselectLogout();
        deselectChat();
        deselectProfile();
    }

    /**
     * Es mostra la imatge activa del Logout i es canvien totes les altres imatges a colors clars, i s'actualitza
     * l'atribut selected al menu corresponent
     */
    public void selectLogout(){
        selected = "LOGOUT";
        menuLogout.setIcon(new ImageIcon("icons/logoutDark.png"));

        deselectProfile();
        deselectChat();
        deselectConnect();
    }

    public boolean isSelected(String menu){
        return menu.equals(selected);
    }
}
