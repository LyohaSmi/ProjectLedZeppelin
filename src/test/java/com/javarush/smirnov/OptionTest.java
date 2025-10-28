package com.javarush.smirnov;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OptionTest {
    @Test
    void testGetText() {
        Option option = new Option(3, "Go to scene 3");
        assertEquals("Go to scene 3", option.getText());
    }

    @Test
    void testGetNextSceneId() {
        Option option = new Option(4, "Go to scene 4");
        assertEquals(4, option.getNextSceneId());
    }
}
