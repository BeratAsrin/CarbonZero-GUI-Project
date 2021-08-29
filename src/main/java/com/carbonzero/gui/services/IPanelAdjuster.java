package com.carbonzero.gui.services;

import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;

@Service
public interface IPanelAdjuster {

    static void initPanel(JPanel panelToAdjust){
        panelToAdjust.setLayout(new GridBagLayout());
    }

}
