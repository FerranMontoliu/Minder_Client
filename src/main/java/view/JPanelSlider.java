package view;

import controller.JPanelSlidingListener;

import javax.swing.*;
import java.awt.*;


public class JPanelSlider extends JPanel {
    public static final boolean left = false;
    public static final boolean right = true;

    public JPanelSlider() {
        this.setLayout(new CardLayout());
        this.setBorder(BorderFactory.createEtchedBorder());
        new Dimension(this.getWidth(), this.getHeight());
    }

    public void nextPanel(Component panel) {
        Component currentComp = this.getCurrentComponent(this);
        Rectangle b = currentComp.getBounds();
        panel.setVisible(true);
        JPanelSlidingListener sl = new JPanelSlidingListener(10, currentComp, panel, true, this);
        Timer t = new Timer(40, sl);
        sl.timer = t;
        t.start();
    }

    public void nextPanel(int panelSpeed, Component panel, boolean direction) {
        Component comp = this.getCurrentComponent(this);
        comp.getBounds();
        panel.setVisible(true);
        JPanelSlidingListener jsl = new JPanelSlidingListener(panelSpeed, comp, panel, direction,this);
        Timer t = new Timer(40, jsl);
        jsl.timer = t;
        t.start();
    }

    public void nextPanel(int panelSpeed, int timeSpeed, Component panel, boolean direction) {
        Component comp = this.getCurrentComponent(this);
        comp.getBounds();
        panel.setVisible(true);
        JPanelSlidingListener jsl = new JPanelSlidingListener(panelSpeed, comp, panel, direction,this);
        Timer t = new Timer(timeSpeed, jsl);
        jsl.timer = t;
        t.start();
    }

    public Component getCurrentComponent(Container parent) {
        Component comp = null;
        int count = parent.getComponentCount();

        for(int i = 0; i < count; ++i) {
            comp = parent.getComponent(i);
            if (comp.isVisible()) {
                return comp;
            }
        }

        return comp;
    }

    public String getCurrentComponentShow(Container parent) {
        String panelName = null;
        Component comp = null;
        int count = parent.getComponentCount();

        for(int i = 0; i < count; ++i) {
            comp = parent.getComponent(i);
            if (comp.isVisible()) {
                panelName = comp.getName();
                return panelName;
            }
        }

        return panelName;
    }

    public void refresh() {
        this.revalidate();
        this.repaint();
    }
}
