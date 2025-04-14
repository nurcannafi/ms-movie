package com.example.msmovie.mapper;

import com.example.msmovie.domain.entity.MovieEntity;
import com.example.msmovie.model.dto.MovieDto;
import com.example.msmovie.model.request.CreateMovieRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface MovieMapper {

    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    @Mapping(target = "id", ignore = true)
    MovieEntity toEntity(MovieDto movieDto);

    MovieDto toDto(MovieEntity movieEntity);

    @Mapping(target = "id", ignore = true)
    MovieEntity toEntity(CreateMovieRequest createMovieRequest);
}
