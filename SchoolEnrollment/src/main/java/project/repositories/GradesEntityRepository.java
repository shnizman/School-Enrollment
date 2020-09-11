package project.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.tables.GradesEntity;
import project.tables.GradesEntityId;

import java.util.List;

@Repository
public interface GradesEntityRepository extends JpaRepository<GradesEntity, GradesEntityId> {

    List<GradesEntity> findAllById_PupilId(long id);
}
