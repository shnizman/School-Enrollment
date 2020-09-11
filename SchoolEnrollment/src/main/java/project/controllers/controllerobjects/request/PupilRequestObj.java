package project.controllers.controllerobjects.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PupilRequestObj {


    private double lat;
    private double lon;
    private List<GradesRequestObj> grades;

    public PupilRequestObj() {

    }


}
