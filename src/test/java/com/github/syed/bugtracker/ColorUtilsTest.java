package com.github.syed.bugtracker;

import org.junit.Test;

import java.awt.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ColorUtilsTest {

    @Test
    public void shouldConvertColorToString(){
        Color blue = Color.BLUE;
        String hexString = ColorUtils.convertToString(blue);
        assertThat(hexString, is("#0000ff"));
    }

    @Test
    public void shouldConvertColorStringToColor(){
        String blueHexString = "#0000ff";
        Color color = ColorUtils.convertToColor(blueHexString);
        assertThat(color, is(Color.BLUE));
    }

}