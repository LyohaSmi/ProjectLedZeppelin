package com.javarush.smirnov;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DataTest {

    @Test
    void testGet() {
        Scene scene = Data.get(1);
        assertNotNull(scene);
        assertEquals(1, scene.getId());
        assertEquals("Вы стоите перед мрачным фасадом усадьбы. Входная дверь заперта. Справа от неё выбито окно в подвал, но из темноты оттуда доносится настойчивый шелест, будто кто-то перебирает бумаги. Ваша тень на стене тянется к окну, словно манит туда.", scene.getText());
        assertFalse(scene.isGameOver());
        assertNotNull(scene.getOptions());
        assertEquals(2, scene.getOptions().size());
    }

    @Test
    void testGetNonExistentScene() {
        Scene scene = Data.get(999);
        assertNull(scene);
    }

    @Test
    void testGetLoseScene() {
        Scene scene = Data.get(2);
        assertNotNull(scene);
        assertEquals(2, scene.getId());
        assertTrue(scene.isGameOver());
        assertEquals("lose", scene.getEndType());
        assertNull(scene.getOptions());
    }

    @Test
    void testGetWinScene() {
        Scene scene = Data.get(8);
        assertNotNull(scene);
        assertEquals(8, scene.getId());
        assertTrue(scene.isGameOver());
        assertEquals("win", scene.getEndType());
        assertNull(scene.getOptions());
    }
}