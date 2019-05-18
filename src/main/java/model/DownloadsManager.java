package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Classe encarregada de gestionar les descarregues del client i de eliminar-les un cops es faci logout.
 */
public class DownloadsManager {

    /**
     * Crea un directory pel client amb nom usernameMinderDownloads.
     * @param destination username.
     */
    public static void createDirectory(String destination){
        String fileName = destination+"MinderDownloads";

        Path path = Paths.get(fileName);

        try {
            deleteDirectory(destination);
            Files.createDirectory(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Elimina un directory d'un client determinat de nom usernameMinderDownloads.
     * @param destination username.
     */
    public static  void deleteDirectory(String destination){
        File directory = new File(destination+"MinderDownloads");
        if(directory.exists()){
            String[] entries = directory.list();
            if((entries == null) || (entries.length > 0)){
                for(String s: entries){
                    File currentFile = new File(directory.getPath(),s);
                    currentFile.delete();
                }
            }
            directory.delete();
        }
    }

}
