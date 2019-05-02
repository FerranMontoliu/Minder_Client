package controller;

import model.entity.User;
import view.ProfilePanel;

public class ProfileController {
    private ProfilePanel profilePanel;
    /**
     * Constructor del controlador associat a la pantalla de Log-In.
     *
     */
    public ProfileController(ProfilePanel profilePanel) {
        this.profilePanel = profilePanel;
    }

    public void showUser(User associatedUser) {
        if (associatedUser.getHobbies().length == 0){
            String[] hobbies = new String[0];
            hobbies[0] = "-";
            associatedUser.setHobbies(hobbies);
        }if (associatedUser.getFavSong().isEmpty()){
            associatedUser.setFavSong("-");
        }
        profilePanel.updateInfo(associatedUser.getUsername(), associatedUser.getAge(), associatedUser.getDescription(),
                associatedUser.getLikesJava(), associatedUser.getLikesC(), associatedUser.getHobbies(), associatedUser.getFavSong());
    }
}
