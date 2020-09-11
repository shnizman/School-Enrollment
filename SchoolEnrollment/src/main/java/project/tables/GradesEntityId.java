package project.tables;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class GradesEntityId implements java.io.Serializable {

    private long pupilId;
    private String courseName;

    public GradesEntityId() {
    }

    public GradesEntityId(long pupilId, String courseName) {
        this.pupilId = pupilId;
        this.courseName = courseName;
    }

    @Column(name = "PUPIL_ID", nullable = false)
    public long getPupilId() {
        return this.pupilId;
    }

    @Column(name = "COURSE_NAME", nullable = false)
    public String getCourseName() {
        return this.courseName;
    }

    public void setPupilId(long pupilId) {
        this.pupilId = pupilId;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

}
