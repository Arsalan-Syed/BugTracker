package com.github.syed.bugtracker;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.awt.*;
import java.io.IOException;

public class ColorDeserializer extends JsonDeserializer<Color> {

    @Override
    public Color deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String colorStr = node.asText();

        return new Color(
                Integer.valueOf(colorStr.substring(1,3), 16),
                Integer.valueOf(colorStr.substring(3,5), 16),
                Integer.valueOf(colorStr.substring(5,7), 16)
        );
    }
}
