package project.services.mappers;

import org.springframework.stereotype.Service;
import project.model.School;
import project.tables.SchoolEntity;

import java.util.ArrayList;
import java.util.List;


@Service
public class SchoolMapper implements EntityMapper<SchoolEntity, School> {

    @Override
    public SchoolEntity mapToEntity(School school) {
        SchoolEntity schoolEntity = new SchoolEntity();
        schoolEntity.setLatitude(school.getLatitude());
        schoolEntity.setLongitude(school.getLongitude());
        schoolEntity.setMinimumGpa(school.getMinimumGpa());
        schoolEntity.setMaxNumberOfPupils(school.getMaxNumberOfPupils());
        schoolEntity.setEnrolled(school.getEnrolled());
        return schoolEntity;
    }

    @Override
    public School mapToModel(SchoolEntity schoolEntity) {
        return null;
    }

    public List<School> mapToModelList(List<SchoolEntity> schoolEntityList) {
        List<School> schoolList = new ArrayList<>();
        for (SchoolEntity schoolEntity : schoolEntityList) {
            School school = new School();
            school.setId(schoolEntity.getId());
            school.setLatitude(schoolEntity.getLatitude());
            school.setLongitude(schoolEntity.getLongitude());
            school.setMinimumGpa(schoolEntity.getMinimumGpa());
            school.setMaxNumberOfPupils(schoolEntity.getMaxNumberOfPupils());
            school.setEnrolled(schoolEntity.getEnrolled());
            schoolList.add(school);
        }
        return schoolList;
    }
}
