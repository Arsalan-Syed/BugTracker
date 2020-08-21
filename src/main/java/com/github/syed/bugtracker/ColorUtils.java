package com.github.syed.bugtracker;

import java.awt.*;

public class ColorUtils {

    public static String convertToString(Color color) {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }

    public static Color convertToColor(String colorStr) {
        return new Color(
                Integer.valueOf(colorStr.substring(1,3), 16),
                Integer.valueOf(colorStr.substring(3,5), 16),
                Integer.valueOf(colorStr.substring(5,7), 16)
        );
    }
}