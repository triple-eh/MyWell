package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SensationTest {
    Sensation s1;
    Sensation s2;

    @BeforeEach
    public void setup () {
        s1 = new Sensation("right arm");
        s2 = new Sensation("head");
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
}
