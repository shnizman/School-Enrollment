package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.tables.SchoolEntity;

@Repository
public interface SchoolEntityRepository extends JpaRepository<SchoolEntity, Long> {

}
