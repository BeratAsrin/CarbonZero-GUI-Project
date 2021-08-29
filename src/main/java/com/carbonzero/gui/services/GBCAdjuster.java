package com.carbonzero.gui.services;

import org.springframework.stereotype.Service;

import java.awt.*;

@Service
public interface GBCAdjuster {
    static GridBagConstraints gbcAdjuster(int gridWidth, int gridHeight, int gridX, int gridY, int fill, int anchor){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = gridWidth;
        gbc.gridheight = gridHeight;
        gbc.gridx = gridX;
        gbc.gridy = gridY;
        gbc.fill = fill;
        gbc.anchor = anchor;
        gbc.insets = new Insets(10,10,10,10);
        return gbc;
    }
}
