package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//A physical sensation experienced in a particular body part
//at the time of the journal entry
public class Sensation extends Observation {
    private String bodyPart;
    private String sensationType;
    private static final List<String> BODYPARTS = new ArrayList<>(Arrays.asList("head", "neck", "chest", "stomach",
            "right shoulder", "left shoulder", "right arm", "left arm", "upper back", "lower back", "right hip",
            "left hip", "right leg", "left leg", "right foot", "left foot"));


    //REQUIRES bodyPart has to be one of available BODYPARTS
    //EFFECTS creates a new physical sensation with a body part and no sensation
    public Sensation(String bodyPart) {
        this.bodyPart = bodyPart;
    }

    //getters
    public String getBodyPart() {
        return this.bodyPart;
    }

    //REQUIRES sensationType has to be one of: pleasant, unpleasant, neutral
    //MODIFIES this
    //EFFECTS adds a type of sensation
    public void setSensationType(String sensationType) {
        this.sensationType = sensationType;
    }

    public String getSensationType() {
        return this.sensationType;
    }


}
