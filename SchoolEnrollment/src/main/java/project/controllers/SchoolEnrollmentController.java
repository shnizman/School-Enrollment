package project.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.controllers.controllerobjects.request.PupilRequestObj;
import project.controllers.controllerobjects.request.SchoolRequestObj;
import project.controllers.mappers.PupilControllerMapper;
import project.controllers.mappers.SchoolControllerMapper;
import project.model.Pupil;
import project.model.School;
import project.services.PupilService;
import project.services.SchoolService;


@RestController
@Slf4j
public class SchoolEnrollmentController {

    @Autowired
    private PupilService pupilService;
    @Autowired
    private PupilControllerMapper pupilControllerMapper;
    @Autowired
    private SchoolService schoolService;
    @Autowired
    private SchoolControllerMapper schoolControllerMapper;


    @PostMapping(path = "/pupil", produces = "application/json")
    @ResponseBody
    public long createPupil(@RequestBody PupilRequestObj pupilRequestObj) {
        Pupil pupil = pupilControllerMapper.mapToModel(pupilRequestObj);
        long pupilId = pupilService.createPupil(pupil);
        return pupilId;
    }

    @PostMapping(path = "/school", produces = "application/json")
    @ResponseBody
    public long createSchool(@RequestBody SchoolRequestObj schoolRequestObj) {
        School school = schoolControllerMapper.mapToModel(schoolRequestObj);
        long schoolId = schoolService.createSchool(school);
        return schoolId;
    }


    @PostMapping(path = "/setFriendShip/{firstPupilId}/{secondPupilId}", produces = "application/json")
    public void createFriendship(@PathVariable(value = "firstPupilId") long pupilIdA, @PathVariable(value = "secondPupilId") long pupilIdB) {
        pupilService.createFriendship(pupilIdA, pupilIdB);
    }


    @PostMapping(path = "/enroll/{pupilId}", produces = "application/json")
    public void enrollPupil(@PathVariable(value = "pupilId") long pupilId) {
        pupilService.enrollPupil(pupilId);
    }
}
