package project.services.mappers;

import java.util.List;

public interface EntityMapper<S, T> {

    S mapToEntity(T object);

    T mapToModel(S object);

    List<T> mapToModelList(List<S> object);
}
