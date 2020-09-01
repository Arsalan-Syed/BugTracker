package com.github.syed.bugtracker;

import com.github.syed.bugtracker.color.ColorConverter;
import org.junit.Test;

import java.awt.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ColorConverterTest {

    ColorConverter colorConverter = new ColorConverter();

    @Test
    public void shouldConvertColorToString(){
        Color blue = Color.BLUE;
        String hexString = colorConverter.convertToDatabaseColumn(blue);
        assertThat(hexString, is("#0000ff"));
    }

    @Test
    public void shouldConvertColorStringToColor(){
        String blueHexString = "#0000ff";
        Color color = colorConverter.convertToEntityAttribute(blueHexString);
        assertThat(color, is(Color.BLUE));
    }

}