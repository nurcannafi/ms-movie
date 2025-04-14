package com.example.msmovie.service;

import com.example.msmovie.domain.entity.MovieEntity;
import com.example.msmovie.domain.repository.MovieRepository;
import com.example.msmovie.domain.repository.MovieSpecification;
import com.example.msmovie.exception.MovieNotFoundException;
import com.example.msmovie.mapper.MovieMapper;
import com.example.msmovie.model.dto.MovieDto;
import com.example.msmovie.model.request.CreateMovieRequest;
import com.example.msmovie.model.request.UpdateMovieRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    public List<MovieDto> getAllMovies(String title, String genre, String releaseYear,
                                       Double minRating, Double maxRating) {
        Specification<MovieEntity> specification = Specification.where(MovieSpecification.hasTitle(title))
                .and(MovieSpecification.hasGenre(genre))
                .and(MovieSpecification.hasReleaseYear(releaseYear))
                .and(MovieSpecification.hasRatingBetween(minRating, maxRating));

        return movieRepository.findAll(specification)
                .stream()
                .map(movieMapper::toDto)
                .toList();
    }

    public MovieDto getMovieById(Long id) {
        MovieEntity movieEntity = getMovieEntity(id);
        return movieMapper.toDto(movieEntity);
    }

    public List<MovieDto> getAllMoviesByImdbRatingDesc() {
        return movieRepository.findAllByOrderByImdbRatingDesc().stream()
                .map(movieMapper::toDto)
                .toList();
    }

    public MovieDto createMovie(CreateMovieRequest createMovieRequest) {
        MovieEntity movieEntity = movieMapper.toEntity(createMovieRequest);
        return movieMapper.toDto(movieRepository.save(movieEntity));
    }

    public MovieDto updateMovie(Long id, UpdateMovieRequest updateMovieRequest) {
        MovieEntity movieEntity = getMovieEntity(id);

        movieEntity.setTitle(updateMovieRequest.getTitle());
        movieEntity.setDirector(updateMovieRequest.getDirector());
        movieEntity.setGenre(updateMovieRequest.getGenre());
        movieEntity.setReleaseYear(updateMovieRequest.getReleaseYear());
        movieEntity.setImdbRating(updateMovieRequest.getImdbRating());

        movieRepository.save(movieEntity);
        return movieMapper.toDto(movieEntity);
    }

    public void deleteMovie(Long id) {
        if (!movieRepository.existsById(id)) {
            throw new MovieNotFoundException("Movie not found");
        }
        movieRepository.deleteById(id);
    }

    private MovieEntity getMovieEntity(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found"));
    }
}
