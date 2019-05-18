package network;

import controller.EditController;
import model.ClientConfig;
import model.Json;
import model.entity.User;

import java.io.*;
import java.net.Socket;

/**
 * Comunicacio client-servidor per gestionar l'edicio de perfil.
 */
public class ServerComunicationEdit {
    private static final char EDIT_PROFILE = 'c';

    private EditController editController;
    private Socket socketToServer;
    private DataInputStream dataIn;
    private DataOutputStream dataOut;
    private ObjectInputStream objectIn;
    private ObjectOutputStream objectOut;


    /**
     * Constructor per parametres.
     * @param editController controlador d'edit panel associat.
     */
    public ServerComunicationEdit(EditController editController){
        try {
            this.editController = editController;

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
    public void startServerComunication(char command) throws IOException {
        if(command == EDIT_PROFILE){
            dataOut.writeChar(EDIT_PROFILE);
            User editedUser = editController.getUser();
            objectOut.writeObject(editedUser);
            boolean editOK = dataIn.readBoolean();
            editController.setEditResult(editOK);
        }

    }

}
