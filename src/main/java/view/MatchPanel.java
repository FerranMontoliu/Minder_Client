package view;

import javax.swing.*;
import java.awt.*;

public class MatchPanel extends JPanel {
    /**
     * Constructor del panell que es mostrara en cas d'haver-hi un match entre dos usuaris.
     * @param clMainWindow CardLayout que el contindra i mostrara en cas desitjat
     */
    public MatchPanel(CardLayout clMainWindow){
        super(clMainWindow);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        createTitle();
        createPhotoMatch();
        createOptions();
    }

    private void createOptions() {
    }

    private void createPhotoMatch() {

    }

    private void createTitle() {
        JLabel jlTitle = new JLabel();
        jlTitle.setIcon(new ImageIcon("icons/itsAMatch.png"));
        add(jlTitle);
    }
}
