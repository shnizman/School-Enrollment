package project.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.tables.PupilEntity;

@Repository
public interface PupilEntityRepository extends JpaRepository<PupilEntity, Long> {

//    PupilEntity findById(long pupilId);

}
