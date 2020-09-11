package project.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.tables.FriendshipEntity;
import project.tables.FriendshipEntityId;

import java.util.List;

@Repository
public interface FriendshipEntityRepository extends JpaRepository<FriendshipEntity, FriendshipEntityId> {

    List<FriendshipEntity> findAllById_PupilIA(long pupilIdA);
}
