package project.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import project.model.School;
import project.repositories.SchoolEntityRepository;
import project.services.mappers.SchoolMapper;
import project.tables.SchoolEntity;
import project.util.ApiErrorHandler;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static project.util.SharedConsts.ERROR_WHILE_TRYING_TO_SAVE_NEW_SCHOOL_TO_DB;

@Service
@Slf4j
public class SchoolService {

    @Autowired
    private SchoolMapper schoolMapper;
    @Autowired
    private SchoolEntityRepository schoolEntityRepository;
    @Autowired
    private ApiErrorHandler apiErrorHandler;


    public long createSchool(School school) {

        SchoolEntity schoolEntity = schoolMapper.mapToEntity(school);
        try {
            schoolEntity = schoolEntityRepository.save(schoolEntity);
            log.info("School with id: " + schoolEntity.getId() + " was saved to DB");
        } catch (Exception e) {
            apiErrorHandler.throwError(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_WHILE_TRYING_TO_SAVE_NEW_SCHOOL_TO_DB, e);
        }
        return schoolEntity.getId();
    }

    public List<School> getAllRelevantSchools(double gpa) {
        List<SchoolEntity> schoolEntityList = schoolEntityRepository.findAll();
        List<SchoolEntity> filteredSchoolEntityList = schoolEntityList
                .stream()
                .filter(schoolEntity -> schoolEntity.getMinimumGpa() < gpa)
                .filter(schoolEntity -> schoolEntity.getEnrolled() < schoolEntity.getMaxNumberOfPupils())
                .collect(Collectors.toList());
        return schoolMapper.mapToModelList(filteredSchoolEntityList);
    }

    public void updateSchoolEnrollment(School school) {
        Optional<SchoolEntity> schoolEntity = schoolEntityRepository.findById(school.getId());
        if (schoolEntity.isPresent()) {
            schoolEntity.get().setEnrolled(school.getEnrolled());
            schoolEntityRepository.save(schoolEntity.get());
            log.info("school Pupil enrollment was updated");
        }
    }
}
