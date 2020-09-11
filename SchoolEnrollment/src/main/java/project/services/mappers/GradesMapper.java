package project.services.mappers;

import org.springframework.stereotype.Service;
import project.model.Grades;
import project.tables.GradesEntity;
import project.tables.GradesEntityId;

import java.util.ArrayList;
import java.util.List;

@Service
public class GradesMapper implements EntityMapper<GradesEntity, Grades> {

    public List<GradesEntity> mapToEntityList(List<Grades> gradesList, long pupilId) {
        List<GradesEntity> gradesEntityArrayList = new ArrayList<>();
        for (Grades grades : gradesList) {

            GradesEntityId gradesEntityId = new GradesEntityId();
            gradesEntityId.setPupilId(pupilId);
            gradesEntityId.setCourseName(grades.getCourseName());
            GradesEntity gradesEntity = new GradesEntity();
            gradesEntity.setId(gradesEntityId);
            gradesEntity.setGrade(grades.getGrade());
            gradesEntityArrayList.add(gradesEntity);
        }
        return gradesEntityArrayList;
    }

    @Override
    public GradesEntity mapToEntity(Grades object) {
        return null;
    }

    @Override
    public Grades mapToModel(GradesEntity object) {
        return null;
    }

    @Override
    public List<Grades> mapToModelList(List<GradesEntity> gradesEntityList) {
        List<Grades> gradesList = new ArrayList<>();
        for (GradesEntity gradesEntity : gradesEntityList) {
            Grades grades = new Grades();
            grades.setCourseName(gradesEntity.getId().getCourseName());
            grades.setGrade(gradesEntity.getGrade());
            gradesList.add(grades);
        }
        return gradesList;
    }
}
