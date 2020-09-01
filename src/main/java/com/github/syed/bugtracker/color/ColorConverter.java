package com.github.syed.bugtracker.color;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.awt.*;

@Converter
public class ColorConverter implements AttributeConverter<Color, String> {

    @Override
    public String convertToDatabaseColumn(Color color) {
        return ColorUtils.convertToString(color);
    }

    @Override
    public Color convertToEntityAttribute(String colorStr) {
        return ColorUtils.convertToColor(colorStr);
    }
}
