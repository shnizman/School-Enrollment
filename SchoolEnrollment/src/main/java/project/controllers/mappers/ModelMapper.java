package project.controllers.mappers;

public interface ModelMapper<S, T> {

    S mapToModel(T object);
}
