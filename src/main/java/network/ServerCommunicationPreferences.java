package network;

import controller.PreferencesController;
import model.ClientConfig;
import model.Json;
import model.entity.User;

import java.io.*;
import java.net.Socket;

/**
 * Comunicacio client-servidor per gestionar les preferencies d'usuari.
 */
public class ServerCommunicationPreferences {
    private static final char EDIT_PREFERENCES = 'l';
    private static final char CHECK_USER = 'a'; //IMPLEMENTACIO IGUAL A LOGIN_USER

    private PreferencesController preferencesController;
    private Socket socketToServer;
    private DataInputStream dataIn;
    private DataOutputStream dataOut;
    private ObjectInputStream objectIn;
    private ObjectOutputStream objectOut;

    /**
     * Constructor del Thread encarregat d'establir la connexió client-servidor.
     * @param preferencesController Controlador de la opcio d'edicio de preferencies del compte on l'usuari pot modificar
     *                              la seva contrassenya, filtre d'edats o acces premium
     */
    public ServerCommunicationPreferences(PreferencesController preferencesController){
        try {
            this.preferencesController = preferencesController;

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
     *
     * @param command Indica que fer.
     *
     * @throws IOException Es tira si hi ha algun problema amb els streams.
     */
    public void startServerComunication(char command) throws IOException {
        switch (command){
            case EDIT_PREFERENCES:
                editPreferences();
                break;
            case CHECK_USER: //IGUAL A LOGIN_USER: volem saber si existeix aquest usuari i contrassenya
                checkUser();
                break;
        }

    }

    /**
     * Metode encarregat de mirar si existeix un usuari i si te la mateixa password.
     */
    private void checkUser() {
        try {
            dataOut.writeChar(CHECK_USER);

            User loginUser = preferencesController.getChekingUser();
            objectOut.writeObject(loginUser);

            boolean existsL = dataIn.readBoolean();

            if(existsL) {
                boolean sameUser = dataIn.readBoolean();
                if(sameUser) {
                    //Llegim tota la informacio de l'usuari autentificat
                    User dataBaseUser = (User) objectIn.readObject();
                    preferencesController.setCorrectLogin(true);
                    preferencesController.setSignInUser(dataBaseUser);
                } else {
                    preferencesController.setCorrectLogin(false);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            preferencesController.setCorrectLogin(false);
        }
    }

    /**
     * Metode que edita les preferencies d'un usuari.
     *
     * @throws IOException Es tira si hi ha problemes amb els streams.
     */
    private void editPreferences() throws IOException{
        dataOut.writeChar(EDIT_PREFERENCES);

        User associatedUser = preferencesController.getAssociatedUser();
        objectOut.writeObject(associatedUser);

        boolean editOK = dataIn.readBoolean();
        if (editOK){

            preferencesController.showEditOk();
        }
        preferencesController.setEditResult(editOK);
    }

}
