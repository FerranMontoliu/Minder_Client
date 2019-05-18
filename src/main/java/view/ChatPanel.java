package view;

import controller.ChatController;


import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.LinkedList;

/**
 * Classe encarregada de generar i gestionar el panell de Chat.
 */
public class ChatPanel extends JPanel {

    private JTextField jtfMissatge;
    private JButton jbSend;
    private JScrollPane jspMatches;
    private JScrollPane jspCentre;
    private JPanel jpMatches;
    private JPanel jpMessage; //Panell dels missatges a enviar
    private LinkedList<JButton> jbMatches;
    private JTextArea jtaChat; //Lloc dels missatges
    private boolean chosen; //Es posara a true quan s'entri a la conversa d'algun match
    private JLabel jlNoMatchs;
    private JButton jbInfo;


    /**
     * Constructor.
     * @param clMainWindow cardLayout al qual pertany el panell.
     */
    public ChatPanel(CardLayout clMainWindow) {
        super(clMainWindow);
        createChatPanel();
    }


    /**
     * Metode que crea el panell.
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

        showUsers();

        //Inicialitzacio de la finestra inferior

        jpMessage = new JPanel(new BorderLayout());
        jtfMissatge = new JTextField(50);
        jtfMissatge.setEnabled(false);

        jbInfo = new JButton(new ImageIcon("icons/info.png"));
        jbInfo.setEnabled(false);
        jpMessage.add(jbInfo, BorderLayout.WEST);
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

    /**
     * Metode que registra ActionListeners als diferents components del panell.
     * @param controller parametre que controlara els components.
     */
    public void registraControlador(ChatController controller) {

        jbSend.addActionListener(controller);
        jtfMissatge.addFocusListener(controller);
        jtfMissatge.addActionListener(controller);
        jtfMissatge.setActionCommand("TEXT");
        jbSend.setActionCommand("SEND");
        jbInfo.addActionListener(controller);
        jbInfo.setActionCommand("INFO");

    }

    /**
     * Metode que registra ActionListener i MouseListener als diferents JButtons que representen els chats.
     * @param controller controlador que implementa MouseListener i ActionListener
     */
    public void registraButtons(ChatController controller){
        for(int i=0;i<jbMatches.size();i++) {
            jbMatches.get(i).addActionListener(controller);
            jbMatches.get(i).addMouseListener(controller);
            jbMatches.get(i).setActionCommand(jbMatches.get(i).getText());
        }
    }

    /**
     * Metode que inicialitza la seccio on es mostraran els chats disponibles.
     */
    public void showUsers() {
        jpMatches = new JPanel();
        jbMatches = new LinkedList<>();

        jlNoMatchs = new JLabel("You don't have any chats");
        jpMatches.add(jlNoMatchs);
        jspMatches.getViewport().add(jpMatches);
    }

    /**
     * Metode que genera els JButtons a partir d'una llista de noms d'usuari.
     * @param userMatchs llista que conte els noms d'usuari amb qui hi ha chats oberts.
     * @param controller controlador al qual es registraran els nous JButtons.
     */
    public void generateDynamicMatchButtons(LinkedList<String> userMatchs, ChatController controller){

        for(JButton jb: jbMatches){
            jb.removeActionListener(controller);
            jb.setVisible(false);
        }

        if(userMatchs.size() > 0){
            jlNoMatchs.setText("");
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
     * Retorna el missatge a enviar.
     * @return contingut del jtfMissatge.
     */
    public String retrieveTextToSend() {
        return jtfMissatge.getText();
    }

    /**
     * Insereix icona d'enviar negre-negre.
     */
    public void setSentIcon() {
        ImageIcon iSent = new ImageIcon("icons/sent.png");
        jbSend.setIcon(iSent);
    }

    /**
     * Insereix icona d'enviar blanca-negre.
     */
    public void setSendIcon() {
        ImageIcon iSend = new ImageIcon("icons/send.png");
        jbSend.setIcon(iSend);
    }

    /**
     * Escriu un missatge inicial al JTextField d'inserir missatge.
     */
    public void setTextFieldMessage() {
        jtfMissatge.setText(" Write a Message... ");
    }

    /**
     * Neteja el JTextField d'inserir missatge.
     */
    public void resetJTextField() {
        jtfMissatge.setText("");
    }

    /**
     * Retorna si l'usuari ha entrat en algun xat o no.
     * @return valor de l'atribut chosen.
     */
    public boolean isChosen() {
        return chosen;
    }

    /**
     * Setter de l'atribut chosen.
     * @param chosen nou valor de l'atribut chosen.
     */
    public void setChosen(boolean chosen) {
        this.chosen = chosen;
    }

    /**
     * Escriu el nom de la persona amb qui s'esta xatejant al Border.
     * @param name nom de l'usuari amb qui es xateja.
     */
    public void changeBorderName(String name) {
        TitledBorder tb2 = new TitledBorder("In chat with:  "+name);
        tb2.setBorder(BorderFactory.createLineBorder(Color.black)); //Color del border total
        jtaChat.setBorder(tb2);
        resetJTextArea();
    }

    /**
     * Metode que escriu un missatge predeterminat abans de clicar sobre una conversa.
     */
    public void setDefaultText() {
        jtaChat.setText("\n\n\n\n\n\n\n\t      Here you can chat with your matches");
        TitledBorder tb2 = new TitledBorder("Chat");
        tb2.setBorder(BorderFactory.createLineBorder(Color.black)); //Color del border total
        jtaChat.setBorder(tb2);
        jtfMissatge.setEnabled(false);
        jbSend.setEnabled(false);
        jbInfo.setEnabled(false);
    }

    /**
     * Metode que mostra un missatge d'error si algun element o accio no es correcte.
     */
    public void throwErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Metode que escriu el nou missatge enviat/rebut al TextPane.
     * @param generatedMessage missatge nou.
     */
    public void writeChat(String generatedMessage) {
        jtaChat.append("\n"+generatedMessage);
    }

    /**
     * Metode que inhabilita el JButton i JTextField d'enviar missatge i el JButton de mostrar mes info.
     */
    public void disableSend(){
        jbSend.setEnabled(false);
        jtfMissatge.setEnabled(false);
        jbInfo.setEnabled(false);
    }

    /**
     * Metode que habilita el JButton i JTextField d'enviar missatge i mostrar mes info.
     */
    public void enableSend(){
        jtfMissatge.setEnabled(true);
        jbSend.setEnabled(true);
        jbInfo.setEnabled(true);
    }

    /**
     * Metode que carrega tot el chat quan es fa click a un User.
     * @param sb StringBuilder amb tot el chat carregat.
     */
    public void writeFullChat(StringBuilder sb) {
        jtaChat.setText(sb.toString());
    }

    /**
     * Metode que neteja el TextAre del chat.
     */
    public void resetJTextArea() {
        jtaChat.setText("");
    }
}


