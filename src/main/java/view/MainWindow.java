package view;

import controller.*;

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
    private EditPanel jpEdit;
    private ChatPanel jpChat;
    private MatchPanel jpMatch;
    private LogoutPanel jpLogOut;
    private ProfilePanel jpOtherProfile;

    //private EditPanel jpEditProfile;


    /**
     * Constructor de la vista de la pantalla principal del programa. Es crea la barra de menú superior i s'inicialitzen
     * tots els panells que es mostraran a la part central de la pantalla, mostrant unicament al principi la pantalla de
     * Connect (like o dislike d'usuaris)
     */
    public MainWindow(String s){
        createMenu(s);
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
        createEditPanel();
        createChatPanel();
        createMatchPanel();
        createLogoutPanel();
        createOtherUserProfilePanel();
        //aqui crear tants panells com opcions del menu: profile, chat, connect...
        changePanel("CONNECT");
        getContentPane().add(jpSelected);
    }

    private void createLogoutPanel() {
        jpLogOut = new LogoutPanel(clMainWindow);
        jpSelected.add("LOGOUT", jpLogOut);
    }

    /**
     * Metode que crida al constructor que crea el panell de "It's a Match!". Mostrara els dos usuaris associats i dues
     * opcions per a xatejar o seguir conectant amb altres usuaris
     */
    private void createMatchPanel() {
        jpMatch = new MatchPanel(clMainWindow);
        jpSelected.add("MATCH", jpMatch);
    }

    /**
     * Metode que crida al constructor que crea el panell d'edicio de perfil.
     */
    private void createEditPanel() {
        jpEdit = new EditPanel(clMainWindow);
        jpSelected.add("EDIT", jpEdit);
    }

    /**
     * Metode que crida el constructor que crea el panell de Perfil. Mostra imatge de perfil i dades proporcionades.
     */
    private void createProfilePanel() {
        jpProfile = new ProfilePanel(clMainWindow, "ASSOCIATED");
        jpSelected.add("PROFILE", jpProfile);

    }
    private void createChatPanel() {
        jpChat = new ChatPanel(clMainWindow);
        jpSelected.add("CHAT",jpChat);
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
     * Metode que crida al constructor que inicialitza el panell de conte la informacio addicional de l'usuari del que
     * es vol saber més informacio des del panell de connect
     */
    private void createOtherUserProfilePanel(){
        jpOtherProfile = new ProfilePanel(clMainWindow, "OTHER");
        //Gestiono el panell pel CardLayout
        jpSelected.add("OTHER-USER PROFILE", jpOtherProfile);
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
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setTitle("Minder");
    }

    /**
     * Metode que serveix per generar la barra de menu superior que sera visible i accessible des de qualsevol finestra
     * de l'aplicacio
     * @param initialSelection string que determina quin JMenuItem seleccionar en el moment inicial.
     */
    private void createMenu(String initialSelection) {
        //El JMenuBar es com un panell que agrupa els JMenuItems
        JMenuBar menuBar;
        menuBar = new JMenuBar();

        //un JMenuItem simula un boto que s'inclou al JMenuBar i que canvia el color del text quan aquest es prem, i
        //permet incloure submenus i iconImages facilment
        menuChat = new JMenuItem("Chat");

        menuConnect = new JMenuItem("Connect");
        selected = initialSelection;

        menuProfile = new JMenuItem("Profile");

        menuLogout = new JMenuItem("Log Out");

        menuBar.setForeground(new Color(255, 88, 100));

        deselectLogout();
        deselectProfile();
        deselectChat();
        deselectConnect();
        switch (initialSelection){
            case "CONNECT":
                selectConnect();
                break;
            case "EDIT":
                selectProfile();
                break;
        }


        menuBar.add(menuChat);
        menuBar.add(menuConnect);
        menuBar.add(menuProfile);
        menuBar.add(menuLogout);

        getContentPane().add(menuBar, BorderLayout.PAGE_START);

    }

    /**
     * Metode que vincula events de cada un dels JMenuItem i al boto d'edicio de perfil al controlador del menu.
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
        menuLogout.setActionCommand("LOGOUT");

        jpLogOut.registerController(controller);
        jpProfile.registraAssociatedProfileController(controller);
        //jpOtherProfile.registraOtherProfileController(controller);


        this.addWindowListener(controller);
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

    /**
     * Getter del Panel Connect
     * @return Panel Connect
     */
    public ConnectPanel getConnect() {
        return jpConnect;
    }

    /**
     * Getter del Panel Profile
     * @return Panel Profile
     */
    public ProfilePanel getProfile() {
        return jpProfile;
    }

    /**
     * Getter del Panel Edit Profile
     * @return Panel Edit Profile
     */
    public EditPanel getEdit() {
        return jpEdit;
    }

    /**
     * Getter del Panel Match
     * @return Match Panell
     */
    public MatchPanel getMatch() {
        return jpMatch;
    }

    public ChatPanel getChat() {
        return jpChat;
    }

    /**
     * Getter del panell que conte la info addicional de l'usuari del que es vol saber mes informacio des del connect panel
     * @return
     */
    public ProfilePanel getOtherUserProfile() {
        return jpOtherProfile;
    }

    public void setChat(ChatPanel jpChat) {
        this.jpChat = jpChat;
    }

    public void registraConnectController(ConnectController connectController) {
        jpConnect.registraController(connectController);
    }
    public void registraChatController(ChatController chatController) {
        jpChat.registraControlador(chatController);
    }
    public void registraEditController(EditController editController) {
        jpEdit.registerController(editController);
    }

    /**
     * Metode que defineix l'atribut imatge de perfil seleccionada del EditPanel
     * @param userImage imatge guardada de l'usuari.
     */
    public void setSelectedImage(Image userImage) {
        jpEdit.setNewProfilePic(userImage);
    }

    /**
     * Metode que vincula les accions dels botons del MatchPanel amb el controlador d'aquest
     * @param matchController
     */
    public void registraMatchController(MatchController matchController) {
        jpMatch.registraController(matchController);
    }

    /**
     * Metode que vincula el controlador del OtherUserProfile amb la seva vista
     */
    public void registraOtherProfileController(OtherUserProfileController otherUserProfileController){
        jpOtherProfile.registraOtherProfileController(otherUserProfileController);
    }

    public void firstEdition() {
        jpEdit.disableCancel();
    }


    public void initiateEdit(Image profilePicture, String userDescription, boolean java, boolean c, String song, String hobbies) {
        jpEdit.initiateEdit(profilePicture, userDescription, java, c, song, hobbies);
    }

    public void showWarning(String message) {
        JOptionPane.showMessageDialog(null, message,"Warning", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * metode que es crida per a incloure la notificacio al lloc on estigui el frame principal
     * @return localitzacio del frame principal
     */
    public Point getLocations() {
        return this.getLocation();
    }

}
