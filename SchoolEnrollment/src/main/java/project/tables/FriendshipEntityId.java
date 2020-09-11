package project.tables;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FriendshipEntityId implements java.io.Serializable {

    private long pupilIA;
    private long pupilIB;

    public FriendshipEntityId() {
    }

    public FriendshipEntityId(long pupilIA, long pupilIB) {
        this.pupilIA = pupilIA;
        this.pupilIB = pupilIB;
    }

    @Column(name = "PUPIL_A", nullable = false)
    public long getPupilIA() {
        return this.pupilIA;
    }

    @Column(name = "PUPIL_B", nullable = false)
    public long getPupilIB() {
        return this.pupilIB;
    }

    public void setPupilIA(long pupilIA) {
        this.pupilIA = pupilIA;
    }

    public void setPupilIB(long pupilIB) {
        this.pupilIB = pupilIB;
    }


}
