package com.javarush.smirnov;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class SceneTest {

    @Test
    void testGetId() {
        Scene scene = new Scene(1, "Test text", List.of(new Option(2, "Choice")));
        assertEquals(1, scene.getId());
    }

    @Test
    void testGetText() {
        Scene scene = new Scene(1, "Test text", List.of(new Option(2, "Choice")));
        assertEquals("Test text", scene.getText());
    }

    @Test
    void testGetOptions() {
        List<Option> options = List.of(new Option(2, "Choice"));
        Scene scene = new Scene(1, "Test text", options);
        assertEquals(options, scene.getOptions());
    }

    @Test
    void testIsGameOver_WhenRegularScene() {
        Scene scene = new Scene(1, "Test text", List.of(new Option(2, "Choice")));
        assertFalse(scene.isGameOver());
    }

    @Test
    void testIsGameOver_WhenGameOverScene() {
        Scene scene = new Scene(1, "Game over", "win");
        assertTrue(scene.isGameOver());
    }

    @Test
    void testGetEndType_WhenRegularScene() {
        Scene scene = new Scene(1, "Test text", List.of(new Option(2, "Choice")));
        assertNull(scene.getEndType());
    }

    @Test
    void testGetEndType_WhenWinScene() {
        Scene scene = new Scene(1, "You win!", "win");
        assertEquals("win", scene.getEndType());
    }

    @Test
    void testGetEndType_WhenLoseScene() {
        Scene scene = new Scene(1, "You lose!", "lose");
        assertEquals("lose", scene.getEndType());
    }

    @Test
    void testGetOptions_WhenGameOverScene() {
        Scene scene = new Scene(1, "Game over", "win");
        assertNull(scene.getOptions());
    }
}