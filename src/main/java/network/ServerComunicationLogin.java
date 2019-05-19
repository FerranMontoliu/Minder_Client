package network;

import controller.LoginController;
import model.*;
import model.entity.User;

import java.io.*;
import java.net.Socket;

/**
 * Comunicacio client-servidor per gestionar el login i register.
 */
public class ServerComunicationLogin {

    private static final char LOGIN_USER = 'a';
    private static final char REGISTER_USER = 'b';

    private LoginController loginController;
    private Socket socketToServer;
    private DataInputStream dataIn;
    private DataOutputStream dataOut;
    private ObjectInputStream objectIn;
    private ObjectOutputStream objectOut;

    /**
     * Constructor per parametres.
     *
     * @param controller controlador que inicia la comunicacio
     *
     * @throws IOException Es tira si hi ha algun problema amb els streams
     */
    public ServerComunicationLogin(LoginController controller) throws IOException {
        this.loginController = controller;

            //Configuració inicial del client:
        ClientConfig cc = Json.parseJson();

        this.socketToServer = new Socket(cc.getServerIP(), cc.getServerPort());

        this.dataOut = new DataOutputStream(socketToServer.getOutputStream());
        this.dataIn = new DataInputStream(socketToServer.getInputStream());
        this.objectOut = new ObjectOutputStream(socketToServer.getOutputStream());
        this.objectIn = new ObjectInputStream(socketToServer.getInputStream());

    }

    /**
     * Metode encarregat d'establir la comunicacio client-servidor.
     *
     * @param command Indica si ha de fer login o registrar un usuari.
     */
    public void startServerComunication(char command) {
        switch(command) {
            case LOGIN_USER:
                login();
                break;
            case REGISTER_USER:
                register();
                break;
        }
    }

    /**
     * Metode encarregat de fer login a un usuari.
     */
    private void login() {
        try {
            dataOut.writeChar(LOGIN_USER);
            User loginUser = loginController.getLoginUser();
            objectOut.writeObject(loginUser);
            boolean existsL = dataIn.readBoolean();
            if(existsL) {
                boolean sameUser = dataIn.readBoolean();
                if(sameUser) {
                    User dataBaseUser = (User) objectIn.readObject();
                    loginController.setCorrectLogin(true);
                    loginController.setSignInUser(dataBaseUser);
                } else {
                    loginController.setCorrectLogin(false);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            loginController.setCorrectLogin(false);
        }
    }

    /**
     * Metode encarregat de registrar un usuari.
     */
    private void register() {
        try {
            dataOut.writeChar(REGISTER_USER);
            User newUser = loginController.getRegisteredUser();
            objectOut.writeObject(newUser);
            boolean registerOK = dataIn.readBoolean();
            loginController.setCorrectRegister(registerOK);
        } catch (IOException e) {
            loginController.setCorrectRegister(false);
        }
    }

}

