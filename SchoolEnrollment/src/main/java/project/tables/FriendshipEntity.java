package project.tables;


import javax.persistence.*;

@Entity
@Table(name = "FRIENDSHIP")
public class FriendshipEntity implements java.io.Serializable {


    private FriendshipEntityId id;

    public FriendshipEntity() {
    }

    public FriendshipEntity(FriendshipEntityId id) {
        this.id = id;
    }

    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "pupilA", column = @Column(name = "PUPILID_A", nullable = false)),
            @AttributeOverride(name = "pupilB", column = @Column(name = "PUPILID_B", nullable = false))})
    public FriendshipEntityId getId() {
        return this.id;
    }

    public void setId(FriendshipEntityId id) {
        this.id = id;
    }


}
