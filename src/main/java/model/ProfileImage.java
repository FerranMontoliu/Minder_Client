package model;

import java.awt.*;

public class ProfileImage {
    private Image profilePic;
    private String fullPath;

    public ProfileImage(Image profilePic, String fullPath) {
        this.profilePic = profilePic;
        this.fullPath = fullPath;
    }

    public Image getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(Image profilePic) {
        this.profilePic = profilePic;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }
}
