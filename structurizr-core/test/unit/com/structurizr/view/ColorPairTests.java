package com.structurizr.view;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ColorPairTests {

    @Test
    void construction() {
        ColorPair colorPair = new ColorPair("#ffffff", "#000000");
        assertEquals("#ffffff", colorPair.getBackground());
        assertEquals("#000000", colorPair.getForeground());
    }

    @Test
    void setBackground_WithAValidHtmlColorCode() {
        ColorPair colorPair = new ColorPair();
        colorPair.setBackground("#ffffff");
        assertEquals("#ffffff", colorPair.getBackground());
    }

    @Test
    void setBackground_ThrowsAnException_WhenANullHtmlColorCodeIsSpecified() {
        try {
            ColorPair colorPair = new ColorPair();
            colorPair.setBackground(null);
            fail();
        } catch (IllegalArgumentException iae) {
            assertEquals("'null' is not a valid hex color code.", iae.getMessage());
        }
    }

    @Test
    void setBackground_ThrowsAnException_WhenAnEmptyHtmlColorCodeIsSpecified() {
        try {
            ColorPair colorPair = new ColorPair();
            colorPair.setBackground("");
            fail();
        } catch (IllegalArgumentException iae) {
            assertEquals("'' is not a valid hex color code.", iae.getMessage());
        }
    }

    @Test
    void setBackground_ThrowsAnException_WhenAnInvalidHtmlColorCodeIsSpecified() {
        try {
            ColorPair colorPair = new ColorPair();
            colorPair.setBackground("ffffff");
            fail();
        } catch (IllegalArgumentException iae) {
            assertEquals("'ffffff' is not a valid hex color code.", iae.getMessage());
        }
    }

    @Test
    void setForeground_WithAValidHtmlColorCode() {
        ColorPair colorPair = new ColorPair();
        colorPair.setForeground("#000000");
        assertEquals("#000000", colorPair.getForeground());
    }

    @Test
    void setForeground_ThrowsAnException_WhenANullHtmlColorCodeIsSpecified() {
        try {
            ColorPair colorPair = new ColorPair();
            colorPair.setForeground(null);
            fail();
        } catch (IllegalArgumentException iae) {
            assertEquals("'null' is not a valid hex color code.", iae.getMessage());
        }
    }

    @Test
    void setForeground_ThrowsAnException_WhenAnEmptyHtmlColorCodeIsSpecified() {
        try {
            ColorPair colorPair = new ColorPair();
            colorPair.setForeground("");
            fail();
        } catch (IllegalArgumentException iae) {
            assertEquals("'' is not a valid hex color code.", iae.getMessage());
        }
    }

    @Test
    void setForeground_ThrowsAnException_WhenAnInvalidHtmlColorCodeIsSpecified() {
        try {
            ColorPair colorPair = new ColorPair();
            colorPair.setForeground("000000");
            fail();
        } catch (IllegalArgumentException iae) {
            assertEquals("'000000' is not a valid hex color code.", iae.getMessage());
        }
    }

}
