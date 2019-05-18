package model;

import java.awt.*;

/**
 * Classe encarregada de guardar una imatge junt amb el seu path per a poder ser convertida posteriorment.
 */
public class ProfileImage {
    private Image profilePic;
    private String fullPath;

    /**
     * Constructor per parametres.
     * @param profilePic imatge del tipus Image.
     * @param fullPath fulll path de l'arxiu.
     */
    public ProfileImage(Image profilePic, String fullPath) {
        this.profilePic = profilePic;
        this.fullPath = fullPath;
    }

    /**
     * Getter de la imatge.
     * @return profilePic
     */
    public Image getProfilePic() {
        return profilePic;
    }

    /**
     * Getter del fullPath
     * @return fullPath
     */
    public String getFullPath() {
        return fullPath;
    }

}
