package project.services;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.model.Grades;
import project.model.Pupil;
import project.repositories.GradesEntityRepository;
import project.services.mappers.GradesMapper;
import project.tables.GradesEntity;

import java.util.List;

@Slf4j
@Service
public class GradeService {

    @Autowired
    private GradesMapper gradesMapper;
    @Autowired
    private GradesEntityRepository gradesEntityRepository;

    public void insertGrades(List<Grades> gradesList, long pupilId) {

        List<GradesEntity> gradesEntityList = gradesMapper.mapToEntityList(gradesList, pupilId);
        gradesEntityRepository.saveAll(gradesEntityList);
        log.info("Grades was saved to DB");

    }

    public List<Grades> getPupilGrades(Pupil pupil) {
        List<GradesEntity> gradesEntityList = gradesEntityRepository.findAllById_PupilId(pupil.getId());
        return gradesMapper.mapToModelList(gradesEntityList);
    }
}
