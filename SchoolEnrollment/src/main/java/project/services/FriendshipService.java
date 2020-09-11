package project.services;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.repositories.FriendshipEntityRepository;
import project.services.mappers.FriendshipEntityMapper;
import project.tables.FriendshipEntity;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class FriendshipService {

    @Autowired
    private FriendshipEntityMapper friendshipEntityMapper;
    @Autowired
    private FriendshipEntityRepository friendshipEntityRepository;

    public void insertFriendship(long pupilIdA, long pupilIdB) {
        FriendshipEntity friendshipEntityA = friendshipEntityMapper.mapToEntity(pupilIdA, pupilIdB);
        FriendshipEntity friendshipEntityB = friendshipEntityMapper.mapToEntity(pupilIdB, pupilIdA);
        friendshipEntityRepository.save(friendshipEntityA);
        friendshipEntityRepository.save(friendshipEntityB);
        log.info("Friendship was saved to DB");
        log.info("Pupil with id: " + pupilIdA + " is Friends with Pupil with id: " + pupilIdB);
    }

    public List<Long> getFriendsList(long id) {
        List<FriendshipEntity> friendshipEntityList = friendshipEntityRepository.findAllById_PupilIA(id);
        List<Long> friendsList = new ArrayList<>();
        for (FriendshipEntity friendshipEntity : friendshipEntityList) {
            friendsList.add(friendshipEntity.getId().getPupilIB());
        }
        return friendsList;
    }
}
