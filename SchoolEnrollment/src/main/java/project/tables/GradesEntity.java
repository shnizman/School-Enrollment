package project.tables;


import javax.persistence.*;

@Entity
@Table(name = "GRADES")
public class GradesEntity implements java.io.Serializable {


    private GradesEntityId id;
    private int grade;

    public GradesEntity() {
    }

    public GradesEntity(GradesEntityId id, int grade) {
        this.id = id;
        this.grade = grade;
    }

    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "pupilId", column = @Column(name = "PUPIL_ID", nullable = false)),
            @AttributeOverride(name = "courseName", column = @Column(name = "COURSE_NAME", nullable = false))})
    public GradesEntityId getId() {
        return this.id;
    }

    @Column(name = "GRADE", nullable = false)
    public int getGrade() {
        return this.grade;
    }

    public void setId(GradesEntityId id) {
        this.id = id;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

}
