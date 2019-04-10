package network;

import controller.LoginController;
import model.*;
import view.LoginWindow;

import java.io.*;
import java.net.Socket;

public class ServerComunicationLogin extends Thread {

    private static final char LOGIN_USER = 'a';
    private static final char REGISTER_USER = 'b';

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
     */
    public ServerComunicationLogin(LoginController controller) { //TODO: No pots passar la vista al Network. Trenques paradigmes.
        try {
            this.isOn = false;
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
                    User loginUser = loginController.getLoginUser();
                    objectOut.writeObject(loginUser);
                    boolean existsL = dataIn.readBoolean();
                    if(existsL) {
                        User possibleUser = (User) objectIn.readObject();
                        //Comparar els Hash
                        //boolean sameUser = compareHash(loginUser.getPassword(), possibleUser.getPassword());
                        boolean sameUser = true; //TODO: Canviar amb el comentari de sobre quan estigui implementat (10/04/2019)
                        dataOut.writeBoolean(sameUser);
                        if(sameUser){
                            loginController.setCorrectLogin(true);
                            loginUser = (User) objectIn.readObject();
                            loginController.setSignInUser(loginUser);
                        }else{
                            loginController.setCorrectLogin(false);
                        }
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                stopServerComunication();
                break;
            case REGISTER_USER:
                try {
                    dataOut.writeChar(REGISTER_USER);
                    User newUser = loginController.getRegisteredUser();
                    //TODO:El newUSer ja té el Hash aplicat
                    objectOut.writeObject(newUser);
                    boolean existsR = dataIn.readBoolean();
                    loginController.setCorrectRegister(existsR);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stopServerComunication();
                break;
        }
    }
}

