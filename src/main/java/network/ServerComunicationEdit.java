package network;

import controller.EditController;
import model.ClientConfig;
import model.Json;
import model.entity.User;

import java.io.*;
import java.net.Socket;

public class ServerComunicationEdit {
    private static final char EDIT_PROFILE = 'c';

    private EditController editController;
    private Socket socketToServer;
    private DataInputStream dataIn;
    private DataOutputStream dataOut;
    private ObjectInputStream objectIn;
    private ObjectOutputStream objectOut;

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
