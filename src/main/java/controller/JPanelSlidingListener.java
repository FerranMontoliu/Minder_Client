package controller;

import view.JPanelSlider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JPanelSlidingListener implements ActionListener {
    Component hidePanel;
    Component showPanel;
    JPanelSlider jPanelSlider;
    int steps;
    int step = 0;
    public Timer timer;
    boolean isNext;

    public JPanelSlidingListener(int steps, Component hidePanel, Component panel, boolean isNext, JPanelSlider jPanelSlider) {
        this.steps = steps;
        this.hidePanel = hidePanel;
        this.showPanel = panel;
        this.isNext = isNext;
        this.jPanelSlider = jPanelSlider;
    }

    public void actionPerformed(ActionEvent e) {
        Rectangle bounds = this.hidePanel.getBounds();
        int shift = bounds.width / this.steps;
        if (!this.isNext) {
            this.hidePanel.setLocation(bounds.x - shift, bounds.y);
            this.showPanel.setLocation(bounds.x - shift + bounds.width, bounds.y);
        } else {
            this.hidePanel.setLocation(bounds.x + shift, bounds.y);
            this.showPanel.setLocation(bounds.x + shift - bounds.width, bounds.y);
        }

        jPanelSlider.repaint();
        ++this.step;
        if (this.step == this.steps) {
            this.timer.stop();
            this.hidePanel.setVisible(false);
        }

    }
}
