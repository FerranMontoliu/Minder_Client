package network;

import model.*;
import view.LoginWindow;

import java.io.*;
import java.net.Socket;

public class ServerComunicationLogin extends Thread {

    private static final char LOGIN_USER = 'a';
    private static final char REGISTER_USER = 'b';

    private LoginWindow w;
    private boolean isOn;
    private Socket socketToServer;
    private DataInputStream dataIn;
    private DataOutputStream dataOut;
    private ObjectInputStream objectIn;
    private ObjectOutputStream objectOut;
    private char command;

    /**
     * Constructor del Thread encarregat d'establir la connexió client-servidor.
     *
     * @param w Vista associada on es veuran reflexats els canvis.
     */
    public ServerComunicationLogin(LoginWindow w) {
        try {
            this.isOn = false;
            this.w = w;

            //Configuració inicial del client:
            ClientConfig cc = Json.parseJson();

            this.socketToServer = new Socket(cc.getServerIP(), cc.getServerPort());
            this.dataIn = new DataInputStream(socketToServer.getInputStream());
            this.dataOut = new DataOutputStream(socketToServer.getOutputStream());
            //this.objectIn = new ObjectInputStream(socketToServer.getInputStream());//TODO: WTF PQ MERDA NO VA
            this.objectOut = new ObjectOutputStream(socketToServer.getOutputStream());
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Mètode encarregat d'establir la comunicació client-servidor.
     *
     */
    public void startServerComunication(char command) {
        this.command = command;
        isOn = true;
        this.start();
    }

    /**
     * Mètode encarregat de tancar la comunicació client-servidor.
     *
     */
    public void stopServerComunication() {
        this.isOn = false;
        this.interrupt();
    }

    /**
     * Mètode que s'executa quan es crea el fil d'execució.
     *
     */
    public void run() {
        switch(command) {
            case LOGIN_USER:
                try {
                    dataOut.writeChar(LOGIN_USER);
                    User u = new User(w.getSignInUsername(), w.getSignInPassword());
                    objectOut.writeObject(u);
                    boolean existsL = dataIn.readBoolean();
                    /*if(existsL) {
                        u = (User) objectIn.readObject();
                    }*/
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stopServerComunication();
                break;
            case REGISTER_USER:
                try {
                    dataOut.writeChar(REGISTER_USER);
                    User u = new User(w.getSignUpUsername(), w.getSignUpAgeField(), w.isPremiumSignUp(), w.getSignUpEmail(), w.getSignUpPasswords()[0], w.getSignUpPasswords()[1]);
                    objectOut.writeObject(u);
                    boolean existsL = dataIn.readBoolean();
                    /*if(existsL) {
                        u = (User) objectIn.readObject();
                    }*/
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stopServerComunication();
                break;
        }
    }
}

