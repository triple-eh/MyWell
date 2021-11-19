package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SensationTest {
    Sensation s1;
    Sensation s2;
    Sensation s3;

    @BeforeEach
    public void setup () {
        s1 = new Sensation("right arm");
        s2 = new Sensation("head");
        s3 = new Sensation("right arm");
    }

    @Test
    public void sensationTest() {
        Assertions.assertEquals("right arm",s1.getBodyPart());
        Assertions.assertEquals("head",s2.getBodyPart());
    }

    @Test
    public void setSensationTypeTest() {
        s1.setSensationType("pleasant");
        Assertions.assertEquals("pleasant",s1.getSensationType());
        s2.setSensationType("unpleasant");
        Assertions.assertEquals("unpleasant",s2.getSensationType());
        s2.setSensationType("neutral");
        Assertions.assertEquals("neutral",s2.getSensationType());
    }

    @Test
    public void equalsTest() {
        Assertions.assertFalse(s1.equals(null));
        Assertions.assertFalse(s1.equals(new Feeling("right arm")));
        Assertions.assertTrue(s1.equals(s3));
        Assertions.assertFalse(s1.equals(s2));
        s1.setSensationType("pleasant");
        Assertions.assertFalse(s1.equals(s3));
        s3.setSensationType("unpleasant");
        Assertions.assertFalse(s1.equals(s3));
        s3.setSensationType("pleasant");
        Assertions.assertTrue(s1.equals(s3));
        s1.setNote("note");
        Assertions.assertFalse(s1.equals(s3));
        s3.setNote("other note");
        Assertions.assertFalse(s1.equals(s3));
        s3.setNote("note");
        Assertions.assertTrue(s1.equals(s3));
        s1.setIntensity(5);
        Assertions.assertFalse(s1.equals(s3));
        s3.setIntensity(4);
        Assertions.assertFalse(s1.equals(s3));
        s3.setIntensity(5);
        Assertions.assertTrue(s1.equals(s3));
    }

    @Test
    public void hashCodeTest() {
        Assertions.assertNotEquals(s1.hashCode(), s2.hashCode());
        Assertions.assertEquals(s1.hashCode(), s3.hashCode());
        s1.setSensationType("pleasant");
        Assertions.assertNotEquals(s1.hashCode(), s3.hashCode());
        s3.setSensationType("pleasant");
        Assertions.assertEquals(s1.hashCode(), s3.hashCode());
        s1.setIntensity(5);
        Assertions.assertNotEquals(s1.hashCode(), s3.hashCode());
        s3.setIntensity(4);
        Assertions.assertNotEquals(s1.hashCode(), s3.hashCode());
        s3.setIntensity(5);
        Assertions.assertEquals(s1.hashCode(), s3.hashCode());

        s1.setNote("note");
        Assertions.assertNotEquals(s1.hashCode(), s3.hashCode());
        s3.setNote("not a note");
        Assertions.assertNotEquals(s1.hashCode(), s3.hashCode());
        s3.setNote("note");
        Assertions.assertEquals(s1.hashCode(), s3.hashCode());
    }

    @Test
    public void printTest() {
        Sensation s = new Sensation("chest");
        s.setSensationType("neutral");
        s.setIntensity(3);
        String printSensation = "Noted sensation in: chest" + "\n" + "The sensation felt: neutral" + "\n" +
                "The intensity was: 3 out of 5";
        Assertions.assertEquals(printSensation,s.print());
        s.setNote("test");
        printSensation += "\nYou added the following note:\ntest";
        Assertions.assertEquals(printSensation,s.print());
    }
}
