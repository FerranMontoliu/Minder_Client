package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DownloadsManager {

    public static void createDirectory(){
        String fileName = "MinderDownloads";

        Path path = Paths.get(fileName);

        try {
            Files.deleteIfExists(path);
            Files.createDirectory(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static  void deleteDirectory(){
        File directory = new File("MinderDownloads");
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
