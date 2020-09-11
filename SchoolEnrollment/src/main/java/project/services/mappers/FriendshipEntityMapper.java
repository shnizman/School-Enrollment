package project.services.mappers;

import org.springframework.stereotype.Service;
import project.tables.FriendshipEntity;
import project.tables.FriendshipEntityId;

@Service
public class FriendshipEntityMapper {

    public FriendshipEntity mapToEntity(long pupilIdA, long pupilIdB) {
        FriendshipEntityId friendshipEntityId = new FriendshipEntityId();
        friendshipEntityId.setPupilIA(pupilIdA);
        friendshipEntityId.setPupilIB(pupilIdB);
        FriendshipEntity friendshipEntity = new FriendshipEntity();
        friendshipEntity.setId(friendshipEntityId);
        return friendshipEntity;
    }


}
