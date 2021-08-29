package com.carbonzero.gui.services;

import org.springframework.stereotype.Service;

import java.awt.*;

@Service
public interface IFont {
    Font labelFont = new Font("Verdana", Font.BOLD, 18);
    Font textFont = new Font("Verdana",Font.PLAIN,16);
    Font applicationMainScreenButtonFont = new Font("Verdana",Font.BOLD,16);
    Font buttonFont = new Font("Verdana",Font.BOLD,14); // Also used for RegisterScreen
    Font tableHeaderFont = new Font("Verdana", Font.BOLD,12);
    Font tableRowFont = new Font("Verdana", Font.PLAIN,12);
}
