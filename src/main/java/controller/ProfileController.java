package controller;

import model.entity.User;
import view.ProfilePanel;

/**
 * Controlador del panell de perfil d'usuari.
 */
public class ProfileController {
    private ProfilePanel profilePanel;

    /**
     * Constructor del controlador associat a la pantalla de Profile.
     */
    public ProfileController(ProfilePanel profilePanel) {
        this.profilePanel = profilePanel;
    }

    /**
     * Metode que actualitza pel panell del perfil de l'usuari la seva informacio i reemplasa amb el caracter '-' en cas de que
     * no tingui informacio (no obligatoria) especificada
     *
     * @param associatedUser Usuari vinculat al compte i del que vol veure la seva propia informacio
     */
    public void showUser(User associatedUser) {
        if ((associatedUser.getHobbies() == null) || (associatedUser.getHobbies() == "")){
            String[] hobbies = new String[0];
            hobbies[0] = "-";
            associatedUser.setHobbies(hobbies);
        }if (associatedUser.getFavSong().isEmpty()){
            associatedUser.setFavSong("-");
        }
        profilePanel.updateInfo(associatedUser.getUsername(), associatedUser.getUsername(), associatedUser.getAge(), associatedUser.getDescription(),
                associatedUser.getLikesJava(), associatedUser.getLikesC(), associatedUser.getHobbies(), associatedUser.getFavSong());
    }
}
