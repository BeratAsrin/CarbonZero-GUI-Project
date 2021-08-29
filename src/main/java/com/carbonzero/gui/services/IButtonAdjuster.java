package com.carbonzero.gui.services;

import javax.swing.*;
import java.awt.*;

public interface IButtonAdjuster {
    static void initButton(JButton button,Font font, Color foregroundColor, Color backgroundColor){
        button.setFont(font);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorderPainted(false);
        button.setForeground(foregroundColor);
        button.setBackground(backgroundColor);
    }
}
