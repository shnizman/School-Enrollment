package project.controllers.mappers;

import org.springframework.stereotype.Service;
import project.controllers.controllerobjects.request.GradesRequestObj;
import project.model.Grades;

import java.util.ArrayList;
import java.util.List;

@Service
public class GradesControllerMapper implements ModelMapper<Grades, GradesRequestObj> {

    public List<Grades> mapToModelList(List<GradesRequestObj> gradesRequestObjList) {
        List<Grades> gradesList = new ArrayList<>();
        for (GradesRequestObj gradesRequestObj : gradesRequestObjList) {
            Grades grades = new Grades();
            grades.setCourseName(gradesRequestObj.getCourseName());
            grades.setGrade(gradesRequestObj.getGrade());
            gradesList.add(grades);
        }
        return gradesList;
    }

    @Override
    public Grades mapToModel(GradesRequestObj object) {
        return null;
    }
}
