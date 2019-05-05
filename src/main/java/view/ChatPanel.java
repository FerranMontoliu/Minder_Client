package view;

import controller.ChatController;


import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.LinkedList;


//TODO: Boto per remove usuari de la llista de matches
//TODO: Quan tinguem usuaris, provar xat

public class ChatPanel extends JPanel {

    private JTextField jtfMissatge;
    private JButton jbSend;
    private JPanel jpImages[];
    private JScrollPane jspMatches;
    private JScrollPane jspCentre;
    private JPanel jpMatches;
    private JPanel jpMessage; //Panell dels missatges a enviar
    private LinkedList<JButton> jbMatches;
    private JTextArea jtaChat; //Lloc dels missatges
    private boolean chosen; //Es posara a true quan s'entri a la conversa d'algun match
    private JLabel jlNoMatchs;


    /**
     * Constructor
     * @param clMainWindow
     */
    public ChatPanel(CardLayout clMainWindow) {
        super(clMainWindow);
        createChatPanel();
    }


    /**
     * TEMPORAL: Cal rebre tots els usuari amb qui s'ha fet match, extreure'n la foto i el nom
     * Quan es clica sobre un d'ells, s'obre el xat
     * amb aquella persona
     */
    public void createChatPanel() {

        //Assigno un borderlayout pel chat
        this.setLayout(new BorderLayout());
        jspMatches = new JScrollPane();
        TitledBorder tb = new TitledBorder("Matches");
        tb.setBorder(BorderFactory.createLineBorder(Color.black)); //Color del border total
        jspMatches.setBorder(tb);
        jspMatches.createVerticalScrollBar(); //Scroll d'imatges amb els matches
        jspMatches.setPreferredSize(new Dimension(80,70));
        jspMatches.setToolTipText("These are the users you have made match with. Right-Click to unmatch the user");


        //ScrollPane de la conversa


        jtaChat = new JTextArea();
        jtaChat.setEditable(false);
        jtaChat.setToolTipText("You can chat with your matches here");
        jtaChat.setLineWrap(true);
        jtaChat.setWrapStyleWord(true);
        jspCentre = new JScrollPane(jtaChat);
        jspCentre.createVerticalScrollBar();

        //Afegeixo JPanels de matches

        showUserPhotos();

        //Inicialitzacio de la finestra inferior

        jpMessage = new JPanel(new BorderLayout());
        jtfMissatge = new JTextField(50);
        jtfMissatge.setEnabled(false);      //TODO: Quan es cliqui a un chat, habilitar el jtf i el jbutton send

        jpMessage.add(jtfMissatge,BorderLayout.CENTER);
        ImageIcon iSend = new ImageIcon("icons/send.png");
        jbSend = new JButton(iSend); //Inicialitzacio boto d'enviar
        disableSend();
        jpMessage.add(jbSend,BorderLayout.EAST);

        //Afegeixo:
        //1: JScrollPane superior de les fotos dels matches
        //2: JTextPane amb la conversa en si
        //3: JTextField per escriure-hi i botó d'enviar

        this.add(jspMatches,BorderLayout.NORTH);
        this.add(jspCentre,BorderLayout.CENTER);
        this.add(jpMessage,BorderLayout.SOUTH);
        this.setTextFieldMessage();
    }

    public void registraControlador(ChatController controller) {

        jbSend.addActionListener(controller);
        jtfMissatge.addFocusListener(controller);
        jtfMissatge.addActionListener(controller);
        jtfMissatge.setActionCommand("TEXT");
        jbSend.setActionCommand("SEND");

    }

    public void registraButtons(ChatController controller){
        for(int i=0;i<jbMatches.size();i++) {
            jbMatches.get(i).addActionListener(controller);
            jbMatches.get(i).addMouseListener(controller);
            jbMatches.get(i).setActionCommand(jbMatches.get(i).getText());
        }
    }

    public void showUserPhotos() {
        jpMatches = new JPanel();
        jbMatches = new LinkedList<>();

        jlNoMatchs = new JLabel("You don't have any chats");
        jpMatches.add(jlNoMatchs);
        jspMatches.getViewport().add(jpMatches);
    }

    public void generateDynamicMatchButtons(LinkedList<String> userMatchs, ChatController controller){

        //Opció DINÀMICA
        //jpMatches.removeAll();

        for(JButton jb: jbMatches){
            jb.removeActionListener(controller);
            jb.setVisible(false);
        }

        if(userMatchs.size() > 0){
            jlNoMatchs.setText("");
            //TODO: Parlar amb el Ferran per a saber com rebre les imatges de perfil
            jbMatches.clear();
            int size = userMatchs.size();
            jbMatches = new LinkedList<>();
            for(int i = 0; i < size; i++){
                JButton match = new JButton(userMatchs.get(i));
                jbMatches.add(match);
                jpMatches.add(match);
            }
            registraButtons(controller);
        }else{
            jlNoMatchs.setText("You don't have any chats");
        }
        jpMatches.add(jlNoMatchs);
    }

    /**
     * Retorna el missatge a enviar
     * @return
     */
    public String retrieveTextToSend() {
        return jtfMissatge.getText();
    }

    /**
     * Insereix icona d'enviar negra-negra
     */
    public void setSentIcon() {
        ImageIcon iSent = new ImageIcon("icons/sent.png");
        jbSend.setIcon(iSent);
    }

    /**
     * Insereix icona d'enviar blanca-negra
     */
    public void setSendIcon() {
        ImageIcon iSend = new ImageIcon("icons/send.png");
        jbSend.setIcon(iSend);
    }

    /**
     * Escriu un missatge inicial al JTextField
     */
    public void setTextFieldMessage() {
        jtfMissatge.setText("Write a Message... ");
    }

    /**
     * Neteja el JTextField
     */
    public void resetJTextField() {
        jtfMissatge.setText("");
    }

    public int numberOfMatches() {
        return jbMatches.size();
    }

    /**
     * Retorna si l'usuari ha entrat en algun xat o no
     * @return
     */
    public boolean isChosen() {
        return chosen;
    }

    /**
     * Setter de l'atribut chosen
     * @param chosen
     */
    public void setChosen(boolean chosen) {
        this.chosen = chosen;
    }

    /**
     * Escriu el nom de la persona amb qui s'esta xatejant al Border
     * @param name
     */
    public void changeBorderName(String name) {
        TitledBorder tb2 = new TitledBorder("In chat with:  "+name);
        tb2.setBorder(BorderFactory.createLineBorder(Color.black)); //Color del border total
        jtaChat.setBorder(tb2);
    }

    /**
     * Escriu un missatge predeterminat abans de clicar sobre una conversa
     */
    public void setDefaultText() {
        jtaChat.setText("\n\n\n\n\n\n\n\t      Here you can chat with your matches");
        TitledBorder tb2 = new TitledBorder("Chat");
        tb2.setBorder(BorderFactory.createLineBorder(Color.black)); //Color del border total
        jtaChat.setBorder(tb2);
        jtfMissatge.setEnabled(false);
        jbSend.setEnabled(false);
    }

    /**
     * Llança missatge d'error si algun element no es correcte
     */
    public void throwErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Metode que mostra un JDialog d'error informant que l'usuari no ha escrit res al JTextFiled Missatge
     */
    public void noTextMessageError() {
        JOptionPane.showMessageDialog(this, "You have not written anything!!", "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Metode que escriu el chat obtingut del client al TextPane
     * @param generatedChat missatges a escriure.
     */
    public void writeChat(String generatedChat) {
        jtaChat.append("\n"+generatedChat);
    }

    /**
     * Metode que inhabilita el JButton i JTextField d'enviar missatge d'enviar missatge
     */
    public void disableSend(){
        jbSend.setEnabled(false);
        jtfMissatge.setEnabled(false);
    }

    /**
     * Metode que habilita el JButton i JTextField d'enviar missatge
     */
    public void enableSend(){
        jtfMissatge.setEnabled(true);
        jbSend.setEnabled(true);
    }

    /**
     * metode que es crida per a incloure la notificacio al lloc on estigui el frame principal
     * @return localitzacio del frame principal
     */
    public Point getLocations() {
        return this.getLocation();
    }

    public void writeFullChat(StringBuilder sb) {
        jtaChat.setText(sb.toString());
    }
}


