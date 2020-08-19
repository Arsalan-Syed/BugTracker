package com.github.syed.bugtracker;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.awt.*;
import java.io.IOException;

public class ColorSerializer extends JsonSerializer<Color> {

    @Override
    public void serialize(Color color, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String colorStr = String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());

        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("color", colorStr);
        jsonGenerator.writeEndObject();
    }
}
