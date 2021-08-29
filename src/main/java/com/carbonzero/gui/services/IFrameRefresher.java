package com.carbonzero.gui.services;

import org.springframework.stereotype.Service;

import javax.swing.*;

@Service
public interface IFrameRefresher {
    static void refreshFrame(JFrame frame){
        frame.invalidate();
        frame.validate();
        frame.repaint();
    }
}
