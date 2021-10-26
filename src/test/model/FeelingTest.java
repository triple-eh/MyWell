package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FeelingTest {
    Feeling f1;
    Feeling f2;
    Feeling f3;

    @BeforeEach
    public void setup() {
        f1 = new Feeling("lonely");
        f2 = new Feeling("sad");
        f3 = new Feeling("lonely");
    }

    @Test
    public void feelingTest() {
        Assertions.assertEquals("lonely", f1.getFeelingName());
        Assertions.assertEquals("sad", f2.getFeelingName());
    }

    @Test
    public void setIntensityTest() {
        f1.setIntensity(1);
        Assertions.assertEquals(1,f1.getIntensity());
        f2.setIntensity(5);
        Assertions.assertEquals(5,f2.getIntensity());
    }

    @Test
    public void setNoteTest() {
        String note1 = "Too much work right now";
        String note2 = "Feeling super energized after the bike ride";
        f1.setNote(note1);
        Assertions.assertEquals(note1,f1.getNote());
        f2.setNote(note2);
        Assertions.assertEquals(note2,f2.getNote());
    }

    @Test
    public void equalsTest() {
        Assertions.assertFalse(f1.equals(null));
        Assertions.assertFalse(f1.equals(new Sensation("lonely")));
        Assertions.assertTrue(f1.equals(f3));
        Assertions.assertFalse(f1.equals(f2));
    }

    @Test
    public void hashCodeTest() {
        Assertions.assertNotEquals(f1.hashCode(),f2.hashCode());
        Assertions.assertEquals(f1.hashCode(),f3.hashCode());
    }
}
