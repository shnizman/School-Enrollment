package project.controllers.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.controllers.controllerobjects.request.GradesRequestObj;
import project.controllers.controllerobjects.request.PupilRequestObj;
import project.model.Pupil;

import java.util.ArrayList;

@Service
public class PupilControllerMapper implements ModelMapper<Pupil, PupilRequestObj> {

    @Autowired
    private GradesControllerMapper gradesControllerMapper;

    @Override
    public Pupil mapToModel(PupilRequestObj pupilRequestObj) {
        Pupil pupil = new Pupil();
        pupil.setLatitude(pupilRequestObj.getLat());
        pupil.setLongitude(pupilRequestObj.getLon());
        pupil.setGrades(gradesControllerMapper.mapToModelList(pupilRequestObj.getGrades() == null ? new ArrayList<GradesRequestObj>() : pupilRequestObj.getGrades()));
        return pupil;
    }
}
