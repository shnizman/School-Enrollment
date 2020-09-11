package project.services;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import project.model.Grades;
import project.model.Pupil;
import project.model.School;
import project.repositories.PupilEntityRepository;
import project.services.mappers.PupilMapper;
import project.tables.PupilEntity;
import project.util.ApiErrorHandler;
import project.util.HaversineDistance;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static project.util.SharedConsts.*;


@Slf4j
@Service
public class PupilService {


    @Autowired
    private PupilMapper pupilMapper;
    @Autowired
    private PupilEntityRepository pupilEntityRepository;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private FriendshipService friendshipService;
    @Autowired
    private SchoolService schoolService;
    @Autowired
    private HaversineDistance haversineDistance;
    @Autowired
    private ApiErrorHandler apiErrorHandler;


    public long createPupil(Pupil pupil) {

        apiErrorHandler.validate(pupil.getGrades().size() != 0, HttpStatus.BAD_REQUEST, PUPIL_MUST_HAVE_AT_LEAST_ONE_GRADE);
        PupilEntity pupilEntity = pupilMapper.mapToEntity(pupil);
        try {
            pupilEntity = pupilEntityRepository.save(pupilEntity);
            log.info("Pupil with id: " + pupilEntity.getId() + " was saved to DB");
            gradeService.insertGrades(pupil.getGrades(), pupilEntity.getId());
        } catch (Exception e) {
            apiErrorHandler.throwError(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_WHILE_TRYING_TO_SAVE_NEW_PUPIL_TO_DB, e);
        }

        return pupilEntity.getId();
    }

    public void createFriendship(long pupilIdA, long pupilIdB) {

        apiErrorHandler.validate(pupilExistsInDb(pupilIdA), HttpStatus.NOT_FOUND, "pupil Id Not found");
        apiErrorHandler.validate(pupilExistsInDb(pupilIdB), HttpStatus.NOT_FOUND, "pupil Id Not found");
        try {
            friendshipService.insertFriendship(pupilIdA, pupilIdB);
        } catch (Exception e) {
            apiErrorHandler.throwError(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_WHILE_TRYING_TO_SAVE_FRIENDSHIP_BETWEEN_PUPILS_TO_DB, e);
        }
    }

    public void enrollPupil(long pupilId) {
        Optional<PupilEntity> pupilEntity = null;
        try {
            pupilEntity = pupilEntityRepository.findById(pupilId);
        } catch (Exception e) {
            apiErrorHandler.throwError(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_WHILE_TRYING_TO_GET_PUPIL, e);
        }
        apiErrorHandler.validate(pupilEntity.isPresent(), HttpStatus.NOT_FOUND, "pupil Id Not found");
        Pupil pupil = pupilMapper.mapToModel(pupilEntity.get());
        try {
            pupil.setGrades(gradeService.getPupilGrades(pupil));
        } catch (Exception e) {
            apiErrorHandler.throwError(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_WHILE_TRYING_TO_FIND_GRADES_FROM_DB, e);
        }
        List<School> schoolList = schoolService.getAllRelevantSchools(getPupilGpa(pupil));
        apiErrorHandler.validate(schoolList.size() > 0, HttpStatus.NOT_FOUND, "No Matching Schools were Found");
        List<Pupil> pupilFriendsList = getFriends(pupil);
        enrollToSchool(schoolList, pupilFriendsList, pupil, pupilEntity.get());
    }

    private double getPupilGpa(Pupil pupil) {
        double sum = 0;
        for (Grades grades : pupil.getGrades()) {
            sum += grades.getGrade();
        }
        return sum / (pupil.getGrades().size());
    }

    private List<Pupil> getFriends(Pupil pupil) {
        List<Long> friendsList = null;
        try {
            friendsList = friendshipService.getFriendsList(pupil.getId());
        } catch (Exception e) {
            apiErrorHandler.throwError(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_WHILE_TRYING_TO_FIND_FRIENDS_FROM_DB, e);
        }
        List<PupilEntity> pupilEntityFriendsList = getPupilEntityListFromIdsList(friendsList);
        return pupilMapper.mapToModelList(pupilEntityFriendsList);
    }

    private List<PupilEntity> getPupilEntityListFromIdsList(List<Long> friendsList) {

        List<PupilEntity> pupilEntityFriendsList = new ArrayList<>();
        try {
            for (Long id : friendsList) {
                Optional<PupilEntity> pupilEntity = pupilEntityRepository.findById(id);
                pupilEntityFriendsList.add(pupilEntity.get());
            }
        } catch (Exception e) {
            apiErrorHandler.throwError(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_WHILE_TRYING_TO_FIND_FRIENDS_FROM_DB, e);
        }
        return pupilEntityFriendsList;
    }

    private void enrollToSchool(List<School> schoolList, List<Pupil> pupilFriendsList, Pupil pupil, PupilEntity pupilEntity) {

        double max = 0;
        School schoolToEnroll = null;

        for (School school : schoolList) {
            List<Pupil> schoolFriendsList = pupilFriendsList
                    .stream()
                    .filter(schoolPupil -> schoolPupil.getSchoolId() == school.getId())
                    .collect(Collectors.toList());
            double result = formula(schoolFriendsList.size(), distanceFromSchool(school, pupil));
            if (result > max) {
                max = result;
                schoolToEnroll = school;
            }
        }

        if (schoolToEnroll == null) {
            double min = 1;
            schoolToEnroll = getClosestSchool(schoolList, pupil, schoolToEnroll, min);
        }

        try {
            schoolToEnroll.setEnrolled(schoolToEnroll.getEnrolled() + 1);
            schoolService.updateSchoolEnrollment(schoolToEnroll);
            pupilEntity.setSchoolId(schoolToEnroll.getId());
            pupilEntityRepository.save(pupilEntity);
            log.info("Pupil with id: " + pupilEntity.getId() + " Enrolled to School with id: " + schoolToEnroll.getId());
        } catch (Exception e) {
            apiErrorHandler.throwError(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_WHILE_TRYING_TO_UPDATE_ENROLLMENT, e);
        }
    }

    private School getClosestSchool(List<School> schoolList, Pupil pupil, School schoolToEnroll, double min) {
        for (School school : schoolList) {
            double result = distanceFromSchool(school, pupil);
            if (result < min) {
                min = result;
                schoolToEnroll = school;
            }
        }
        return schoolToEnroll;
    }

    private double distanceFromSchool(School school, Pupil pupil) {
        return haversineDistance.distance(pupil.getLatitude(), pupil.getLongitude(), school.getLatitude(), school.getLongitude());
    }

    private double formula(int numberOfFriends, double distanceFromSchool) {
        double fraction = 1 / distanceFromSchool;
        return numberOfFriends * fraction;
    }

    private boolean pupilExistsInDb(long id) {
        boolean exists = false;
        try {
            exists = pupilEntityRepository.existsById(id);
        } catch (Exception e) {
            apiErrorHandler.throwError(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_WHILE_TRYING_TO_CONNECT_TO_DB, e);
        }
        return exists;
    }


}
