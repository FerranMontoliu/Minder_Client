package view;

import controller.*;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

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
    private PreferencesPanel jpPreferences;
    private ChatPanel jpChat;
    private MatchPanel jpMatch;
    private ProfilePanel jpOtherProfile;


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

        //creem tots els panells que estaran continguts al CardLayout
        createConnectPanel();
        createProfilePanel();
        createEditPanel();
        createPreferencesPanel();
        createChatPanel();
        createMatchPanel();
        createOtherUserProfilePanel();

        //definim per defecte el panell Connect com el de la pagina principal
        changePanel("CONNECT");
        getContentPane().add(jpSelected);
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
        jpEdit = new EditPanel();
        jpSelected.add("EDIT", jpEdit);
    }

    /**
     * Metode que crida al constructor que crea el panell d'edicio de preferencies.
     */
    private void createPreferencesPanel() {
        jpPreferences = new PreferencesPanel();
        jpSelected.add("ACCOUNT PREFERENCES", jpPreferences);
    }

    /**
     * Metode que crida el constructor que crea el panell de Perfil. Mostra imatge de perfil i dades proporcionades.
     */
    private void createProfilePanel() {
        jpProfile = new ProfilePanel(clMainWindow, "ASSOCIATED");
        jpSelected.add("PROFILE", jpProfile);

    }

    /**
     * Metode que crida al constructor que crea el panell de chat.
     */
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
     * Metode encarregat d'inicialitzar els valors per defecte de la finestra principal.
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
            case "PROFILE":
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

        jpProfile.registraAssociatedProfileController(controller);

        this.addWindowListener(controller);
    }

    /**
     * Metode que mostra la imatge de color mes clar per a indicar que el Chat no esta seleccionat
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
        jpChat.setDefaultText();
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

    /**
     * Getter del panell que els chats entre usuaris que han fet match
     * @return
     */
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

    /**
     * Getter del panell que permet canviar els ajustaments principals del seu compte (usuari, contrassenya, edat, mail i filtre
     * d'edat
     * @return
     */
    public PreferencesPanel getPreferences() {
        return jpPreferences;
    }
    public void setChat(ChatPanel jpChat) {
        this.jpChat = jpChat;
    }

    /**
     * Metode que registra controlador al Connect Panel
     * @param connectController controlador associat
     */
    public void registraConnectController(ConnectController connectController) {
        jpConnect.registraController(connectController);
    }

    /**
     * Metode que registra controlador al Chat Panel
     * @param chatController controlador associat
     */
    public void registraChatController(ChatController chatController) {
        jpChat.registraControlador(chatController);
    }

    /**
     * Metode que registra controlador al Edit Panel
     * @param editController controlador associat
     */
    public void registraEditController(EditController editController) {
        jpEdit.registerController(editController);
    }

    /**
     * Metode que registra controlador al Preferences Panel
     * @param preferencesController controlador associat
     */
    public void registraPreferencesController(PreferencesController preferencesController){
        jpPreferences.registerController(preferencesController);
    }

    /**
     * Metode que vincula les accions dels botons del MatchPanel amb el controlador d'aquest
     * @param matchController controlador associat
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

    /**
     * Metode que inhabilita l'opcio de cancelar edicio de perfil en cas de perfil no completat.
     */
    public void firstEdition() {
        jpEdit.disableCancel();
    }

    /**
     * Metode que inicialitza el panell de Edit Profile amb les dades del User.
     * @param username Nom d'usuari.
     * @param userDescription Descripcio.
     * @param java Li agrada Java.
     * @param c Li agrada C.
     * @param song Canso preferida.
     * @param hobbies Hobbies del user.
     */
    public void initiateEdit(String username, String userDescription, boolean java, boolean c, String song, String hobbies) {
        jpEdit.initiateEdit(username, userDescription, java, c, song, hobbies);
    }

    /**
     * Metode que mostra un JDialog informant d'un error.
     * @param message missatge d'error.
     */
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

    /**
     * Metode que genera la llista de matchs al chat panel.
     * @param matchedUsernames Noms d'usuari dels matchs
     * @param chatController controlador associat al chat panel
     */
    public void generateMatchList(LinkedList<String> matchedUsernames, ChatController chatController) {
        jpChat.generateDynamicMatchButtons(matchedUsernames, chatController);
    }


    /**
     * Metode que actualitza la informacio de l'usuari del qual volem obtenir informacio
     * @param client usuari associat a la compta que esta connectant amb usuaris i que desitja veure el perfil d'un usuari
     * @param username nom de l'usuari del que vol obtenir informacio.
     * @param age edat de l'usuari del que el client vol obtenir informacio.
     * @param userDescription Descripcio de l'usuari del que el client vol obtenir informacio.
     * @param java Indica si a l'usuari li agrada Java o no del que el client vol obtenir informacio.
     * @param c Indica si a l'usuari li agrada C o no del que el client vol obtenir informacio.
     * @param song Canco preferida de l'usuari del que el client vol obtenir informacio.
     * @param hobbies Llista de hobbies de l'usuari del que el client vol obtenir informacio.
     */
    public void loadConnectUserInfo(String client, String username, String userDescription, int age, boolean java, boolean c, String song, String hobbies) {
        jpOtherProfile.updateInfo(client, username, age,userDescription, java, c, hobbies, song);
    }

    /**
     * Metode que permet omplir els camps del edit preferences option, per a mostrar les dades de l'usuari introduides per
     * aquest quan va iniciar sessio
     * @param username nom d'usuari
     * @param email correu electronic
     * @param age edat
     * @param isPremium tipus de compte (Els premium veuen primer els usuaris que ja li han donat like)
     * @param minAge minima edat que han de tenir els usuaris amb els que vol connectar
     * @param maxAge maxima edat que han de tenir els usuaris amb els que vol connectar
     */
    public void initiatePreferences(String username, String email, int age, boolean isPremium, int minAge, int maxAge) {
        boolean noFilter = false;
        if (maxAge == 0){
            jpPreferences.disableFilter();
            minAge = 18;
            maxAge = 99;
            noFilter = true;
        }
        jpPreferences.initiatePreferences(username, email, age, isPremium, minAge, maxAge, noFilter);

    }

    /**
     * Metode que permet habilitar el boto de cancelacio d'edicio de perfil
     */
    public void enableCancel() {
        jpEdit.enableCancel();
    }
}
