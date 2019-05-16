package network;

import controller.LoginController;
import model.*;
import model.entity.User;

import java.io.*;
import java.net.Socket;

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
     * Constructor del Thread encarregat d'establir la connexió client-servidor.
     * @param controller controlador que inicia la comunicacio
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
     */
    public void startServerComunication(char command) {
        switch(command) {
            case LOGIN_USER:
                try {
                    dataOut.writeChar(LOGIN_USER);
                    User loginUser = loginController.getLoginUser();
                    objectOut.writeObject(loginUser);
                    boolean existsL = dataIn.readBoolean();
                    if(existsL) {
                        boolean sameUser = dataIn.readBoolean();
                        if(sameUser){
                            User dataBaseUser = (User) objectIn.readObject();
                            loginController.setCorrectLogin(true);
                            loginController.setSignInUser(dataBaseUser);
                        }else{
                            loginController.setCorrectLogin(false);
                        }
                    }
                } catch (IOException | ClassNotFoundException e) {
                    loginController.setCorrectLogin(false);
                }
                break;
            case REGISTER_USER:
                try {
                    dataOut.writeChar(REGISTER_USER);
                    User newUser = loginController.getRegisteredUser();
                    objectOut.writeObject(newUser);
                    boolean registerOK = dataIn.readBoolean();
                    loginController.setCorrectRegister(registerOK);
                } catch (IOException e) {
                    loginController.setCorrectRegister(false);
                }
                break;
        }
        stopServerComunication();
    }

    /**
     * Metode encarregat de tancar la comunicacio client-servidor.
     */
    public void stopServerComunication() {
        try {
            dataOut.close();
        } catch (IOException e) {}
        try {
            objectOut.close();
        } catch (IOException e) {}
        try {
            dataIn.close();
        } catch (IOException e) {}
        try {
            objectIn.close();
        } catch (IOException e) {}
        try {
            socketToServer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

