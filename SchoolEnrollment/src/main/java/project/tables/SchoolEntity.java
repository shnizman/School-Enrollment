package project.tables;


import javax.persistence.*;

@Entity
@Table(name = "SCHOOL")
public class SchoolEntity implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double latitude;
    private double longitude;
    private int minimumGpa;
    private int maxNumberOfPupils;
    private int enrolled;


    public SchoolEntity() {
    }

    public SchoolEntity(long id, double latitude, double longitude, int minimumGpa, int maxNumberOfPupils, int enrolled) {

        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.minimumGpa = minimumGpa;
        this.maxNumberOfPupils = maxNumberOfPupils;
        this.enrolled = enrolled;
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

    @Column(name = "MINIMUM_GPA", nullable = false)
    public int getMinimumGpa() {
        return this.minimumGpa;
    }

    @Column(name = "MAX_NUMBER_OF_PUPILS", nullable = false)
    public int getMaxNumberOfPupils() {
        return this.maxNumberOfPupils;
    }

    @Column(name = "ENROOLED")
    public int getEnrolled() {
        return this.enrolled;
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

    public void setMinimumGpa(int minimumGpa) {
        this.minimumGpa = minimumGpa;
    }

    public void setMaxNumberOfPupils(int maxNumberOfPupils) {
        this.maxNumberOfPupils = maxNumberOfPupils;
    }

    public void setEnrolled(int enrolled) {
        this.enrolled = enrolled;
    }
}
