package project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Grades {

    private String courseName;
    private int grade;

    public Grades() {

    }
}
