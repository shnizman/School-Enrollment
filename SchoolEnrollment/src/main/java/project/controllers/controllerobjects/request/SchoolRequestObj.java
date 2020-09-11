package project.controllers.controllerobjects.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class SchoolRequestObj {

    private double lat;
    private double lon;
    private int minimumGpa;
    private int maxNumberOfPupils;

    public SchoolRequestObj() {

    }

}
