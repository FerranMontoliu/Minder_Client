package network;

import controller.LoginController;
import model.*;
import view.LoginWindow;

import java.io.*;
import java.net.Socket;

public class ServerComunicationLogin extends Thread {

    private static final char LOGIN_USER = 'a';
    private static final char REGISTER_USER = 'b';

    private LoginWindow w;
    private LoginController loginController;
    private boolean isOn;
    private Socket socketToServer;
    private DataInputStream dataIn;
    private DataOutputStream dataOut;
    private ObjectInputStream objectIn;
    private ObjectOutputStream objectOut;
    private char command;

    /**
     * Constructor del Thread encarregat d'establir la connexió client-servidor.
     * @param controller controlador que inicia la comunicacio
     * @param w Vista associada on es veuran reflexats els canvis.
     */
    public ServerComunicationLogin(LoginWindow w, LoginController controller) { //TODO: No pots passar la vista al Network. Trenques paradigmes.
        try {
            this.isOn = false;
            this.w = w;
            this.loginController = controller;

            //Configuració inicial del client:
            ClientConfig cc = Json.parseJson();

            this.socketToServer = new Socket(cc.getServerIP(), cc.getServerPort());

            this.dataOut = new DataOutputStream(socketToServer.getOutputStream());
            this.dataIn = new DataInputStream(socketToServer.getInputStream());
            this.objectOut = new ObjectOutputStream(socketToServer.getOutputStream());
            this.objectIn = new ObjectInputStream(socketToServer.getInputStream());
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metode encarregat d'establir la comunicacio client-servidor.
     */
    public void startServerComunication(char command) {
        this.command = command;
        isOn = true;
        this.start();
    }

    /**
     * Metode encarregat de tancar la comunicació client-servidor.
     */
    public void stopServerComunication() {
        this.isOn = false;
        this.interrupt();
    }

    /**
     * Metode que s'executa quan es crea el fil d'execució.
     */
    public void run() {
        switch(command) {
            case LOGIN_USER:
                try {
                    dataOut.writeChar(LOGIN_USER);
                    User u = new User(w.getSignInUsername(), w.getSignInPassword());
                    objectOut.writeObject(u);
                    boolean existsL = dataIn.readBoolean();
                   /* if(existsL) {
                        u = (User) objectIn.readObject();
                        loginController.setSignInUser(u);
                    }*/
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stopServerComunication();
                break;
            case REGISTER_USER:
                try {
                    dataOut.writeChar(REGISTER_USER); //TODO: Per a no trencar paradigmes s'hauria de demanar el User al controller no?
                    User newUser = loginController.getRegisteredUser();
                    objectOut.writeObject(newUser);
                    boolean existsR = dataIn.readBoolean();
                    if(existsR) {
                        //Això perque es fa?? Si existeix tenim problemes i cal avisar al controller per a que no obri la seguent finestra
                        newUser = (User) objectIn.readObject();
                    }else{

                    }
                } catch (ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                }
                stopServerComunication();
                break;
        }
    }
}

