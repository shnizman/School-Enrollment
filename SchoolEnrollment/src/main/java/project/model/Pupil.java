package project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Pupil {


    private long id;
    private double latitude;
    private double longitude;
    private List<Grades> grades;
    private long schoolId;

    public Pupil() {

    }
}
