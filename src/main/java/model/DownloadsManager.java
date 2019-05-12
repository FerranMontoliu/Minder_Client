package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DownloadsManager {

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
