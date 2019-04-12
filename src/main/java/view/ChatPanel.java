package view;

import controller.ChatController;
import model.entity.User;


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
    private JButton[] jbMatches;
    private JTextPane jtpane; //Lloc dels missatges
    private boolean chosen; //Es posara a true quan s'entri a la conversa d'algun match



    /**
     * Constructor
     * @param clMainWindow
     */
    public ChatPanel(CardLayout clMainWindow, LinkedList<String> userMatchs) {
        super(clMainWindow);
        createChatPanel(userMatchs);
    }


    /**
     * TEMPORAL: Cal rebre tots els usuari amb qui s'ha fet match, extreure'n la foto i el nom
     * Quan es clica sobre un d'ells, s'obre el xat
     * amb aquella persona
     * @param userMatchs
     */
    public void createChatPanel(LinkedList<String> userMatchs) {

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
        jspCentre = new JScrollPane();
        jspCentre = new JScrollPane();

        jspCentre.createVerticalScrollBar();
        jtpane = new JTextPane();
        jtpane.setEditable(false);
        jtpane.setToolTipText("You can chat with your matches here");

        //Afegeixo JPanels de matches

        showUserPhotos(userMatchs);

        //Inicialitzacio de la finestra inferior

        jpMessage = new JPanel(new BorderLayout());
        jtfMissatge = new JTextField(50);

        jpMessage.add(jtfMissatge,BorderLayout.CENTER);
        ImageIcon iSend = new ImageIcon("icons/send.png");
        jbSend = new JButton(iSend); //Inicialitzacio boto d'enviar
        jpMessage.add(jbSend,BorderLayout.EAST);

        //Afegeixo:
        //1: JScrollPane superior de les fotos dels matches
        //2: JTextPane amb la conversa en si
        //3: JTextField per escriure-hi i botó d'enviar

        this.add(jspMatches,BorderLayout.NORTH);
        this.add(jtpane,BorderLayout.CENTER);
        this.add(jpMessage,BorderLayout.SOUTH);
        this.setTextFieldMessage();
    }

    public void registraControlador(ChatController controller) {

        jbSend.addActionListener(controller);
        jtfMissatge.addFocusListener(controller);
        jtfMissatge.addActionListener(controller);
        jtfMissatge.setActionCommand("TEXT");
        jbSend.setActionCommand("SEND");

        for(int i=0;i<jbMatches.length;i++) {
            jbMatches[i].addActionListener(controller);
            jbMatches[i].addMouseListener(controller);
            jbMatches[i].setActionCommand("Z"+String.valueOf(i));
        }

    }

    /**
     *
     * @param userMatchs
     */
    //TODO: CANVIAR!!
    public void showUserPhotos(LinkedList<String> userMatchs) {
        jpMatches = new JPanel();

        //Opcio ESTÀTICA
        jbMatches = new JButton[2]; //Array de matches, per les seves fotos
        ImageIcon[] Send = new ImageIcon[2];
        Send[0] = new ImageIcon("icons/userDark.png");
        Send[1] = new ImageIcon("icons/userLight.png");
        jbMatches[0] = new JButton("Pene",Send[0]);
        jbMatches[1] = new JButton("Nepe",Send[1]);
        for(int i=0;i<jbMatches.length;i++) {
            jpMatches.add(jbMatches[i]);
        }

        //Opció DINÀMICA
        /*
        if((userMatchs == null)||(userMatchs.size() == 0)){
            JLabel jlNoMatches = new JLabel("You don't have any chats");
            jpMatches.add(jlNoMatches);
        }else{
            //TODO: Parlar amb el Ferran per a saber com rebre les imatges de perfil
            int size = userMatchs.size();
            jbMatches = new JButton[size];
            for(int i = 0; i < size; i++){
                jbMatches[i] = new JButton(userMatchs.get(i));
                //Falta fer el setIcon
                jpMatches.add(jbMatches[i]);
            }
        }*/

        jspMatches.getViewport().add(jpMatches);
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
        return jbMatches.length;
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
     * Posa la conversa entre els matches al JTextPane, en funcio del numero de match que se li passa
     * @param index
     */
    //TODO: CANVIAR
    public void showMatchConversation(int index) { //String de missatges
        if(index == 0) {
            jtpane.setText("\n\n\n\n\t \n\nPol: Em vull morir");
            changeBorderName("La vida");
        }
        if(index == 1) {
            jtpane.setText("\nFerran: Sóc de Balaguer i m'agraden els tractors");
            changeBorderName("Ferran");

        }
    }

    /**
     * Escriu el nom de la persona amb qui s'esta xatejant al Border
     * @param name
     */
    public void changeBorderName(String name) {
        TitledBorder tb2 = new TitledBorder("In chat with:  "+name);
        tb2.setBorder(BorderFactory.createLineBorder(Color.black)); //Color del border total
        jtpane.setBorder(tb2);
    }

    /**
     * Escriu un missatge predeterminat abans de clicar sobre una conversa
     */
    public void setDefaultText() {
        jtpane.setText("\n\n\n\n\n\n\n\t      Here you can chat with your matches");
        TitledBorder tb2 = new TitledBorder("Chat");
        tb2.setBorder(BorderFactory.createLineBorder(Color.black)); //Color del border total
        jtpane.setBorder(tb2);

    }

    /**
     * Llança missatge d'error si algun element no es correcte
     */
    public void throwErrorMessage() {
        JOptionPane.showMessageDialog(this, "You have not chosen a chat!", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public boolean throwUnmatchMessage() {
        int dialogButton  = 0;
        dialogButton = JOptionPane.showConfirmDialog (null, "Are you sure you want to unmatch this user?","Warning", dialogButton);
        if(dialogButton == JOptionPane.NO_OPTION) {
            remove(dialogButton);
            return true;
        }
        return false;


    }

    public void noTextMessageError() {
        JOptionPane.showMessageDialog(this, "You have not written anything!!", "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Metode que escriu el chat obtingut del client al TextPane
     * @param generatedChat missatges a escriure.
     */
    public void writeChat(String generatedChat) {
        jtpane.setText(generatedChat);
    }
}


