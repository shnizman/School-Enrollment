package project.tables;


import javax.persistence.*;

@Entity
@Table(name = "PUPIL")
public class PupilEntity implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double latitude;
    private double longitude;
    private long schoolId;


    public PupilEntity() {
    }

    public PupilEntity(long id, double latitude, double longitude, long schoolId) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.schoolId = schoolId;
    }

    @Column(name = "ID", nullable = false)
    public long getId() {
        return this.id;
    }

    @Column(name = "LATITUDE", nullable = false)
    public double getLatitude() {
        return this.latitude;
    }

    @Column(name = "LONGITUDE", nullable = false)
    public double getLongitude() {
        return this.longitude;
    }

    @Column(name = "SCHOOL_ID")
    public long getSchoolId() {
        return this.schoolId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setSchoolId(long schoolId) {
        this.schoolId = schoolId;
    }


}
