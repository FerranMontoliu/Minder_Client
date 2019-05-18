package model;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Json {

    /**
     * Funcio encarregada de parsejar el Json per a extreure'n la informacio.
     *
     * @return Retorna un ServerConfig que conte tota la informacio del Json.
     */
    public static ClientConfig parseJson() {
        ClientConfig c = null;
        try {
            Gson gson = new Gson();
            FileReader nomFitxer = new FileReader("data/config.json");
            c = gson.fromJson(new BufferedReader(nomFitxer), ClientConfig.class);
        } catch (FileNotFoundException error) {
            error.printStackTrace();
        }
        return c;
    }
}