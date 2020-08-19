package com.github.syed.bugtracker;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.awt.*;

@Converter
public class ColorConverter implements AttributeConverter<Color, String> {

    @Override
    public String convertToDatabaseColumn(Color color) {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }

    @Override
    public Color convertToEntityAttribute(String colorStr) {
        return new Color(
                Integer.valueOf(colorStr.substring(1,3), 16),
                Integer.valueOf(colorStr.substring(3,5), 16),
                Integer.valueOf(colorStr.substring(5,7), 16)
        );
    }
}
