package project.controllers.controllerobjects.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class GradesRequestObj {

    private String courseName;
    private int grade;

    public GradesRequestObj() {

    }
}
