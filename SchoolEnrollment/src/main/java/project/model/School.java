package project.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class School {

    private long id;
    private double latitude;
    private double longitude;
    private int minimumGpa;
    private int maxNumberOfPupils;
    private int enrolled;

    public School() {

    }

}
