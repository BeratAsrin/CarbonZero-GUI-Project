package com.carbonzero.gui.services;

import org.springframework.stereotype.Service;

import javax.swing.*;

@Service
public interface UIManagerAdjuster {
    public static void adjustUI(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
}
