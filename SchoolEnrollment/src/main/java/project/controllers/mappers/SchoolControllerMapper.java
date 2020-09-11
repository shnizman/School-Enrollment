package project.controllers.mappers;

import org.springframework.stereotype.Service;
import project.controllers.controllerobjects.request.SchoolRequestObj;
import project.model.School;

@Service
public class SchoolControllerMapper implements ModelMapper<School, SchoolRequestObj> {

    @Override
    public School mapToModel(SchoolRequestObj schoolRequestObj) {
        School school = new School();
        school.setLatitude(schoolRequestObj.getLat());
        school.setLongitude(schoolRequestObj.getLon());
        school.setMinimumGpa(schoolRequestObj.getMinimumGpa());
        school.setMaxNumberOfPupils(schoolRequestObj.getMaxNumberOfPupils());
        return school;
    }
}
