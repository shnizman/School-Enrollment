package project.services.mappers;

import org.springframework.stereotype.Service;
import project.model.Pupil;
import project.tables.PupilEntity;

import java.util.ArrayList;
import java.util.List;


@Service
public class PupilMapper implements EntityMapper<PupilEntity, Pupil> {

    @Override
    public PupilEntity mapToEntity(Pupil pupil) {
        PupilEntity pupilEntity = new PupilEntity();
        pupilEntity.setId(pupil.getId());
        pupilEntity.setLatitude(pupil.getLatitude());
        pupilEntity.setLongitude(pupil.getLongitude());
        return pupilEntity;
    }

    @Override
    public Pupil mapToModel(PupilEntity pupilEntity) {
        Pupil pupil = new Pupil();
        pupil.setId(pupilEntity.getId());
        pupil.setLatitude(pupilEntity.getLatitude());
        pupil.setLongitude(pupilEntity.getLongitude());
        pupil.setSchoolId(pupilEntity.getSchoolId());
        return pupil;
    }

    @Override
    public List<Pupil> mapToModelList(List<PupilEntity> pupilEntityList) {
        List<Pupil> pupilList = new ArrayList<>();
        for (PupilEntity pupilEntity : pupilEntityList) {
            Pupil pupil = new Pupil();
            pupil.setId(pupilEntity.getId());
            pupil.setLatitude(pupilEntity.getLatitude());
            pupil.setLongitude(pupilEntity.getLongitude());
            pupil.setSchoolId(pupilEntity.getSchoolId());
            pupilList.add(pupil);
        }
        return pupilList;
    }
}
