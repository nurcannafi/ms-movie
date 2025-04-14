package com.example.msmovie.controller;

import com.example.msmovie.model.dto.MovieDto;
import com.example.msmovie.model.request.CreateMovieRequest;
import com.example.msmovie.model.request.UpdateMovieRequest;
import com.example.msmovie.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/movies")
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieDto>> getAllMovies(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String releaseYear,
            @RequestParam(required = false) Double minRating,
            @RequestParam(required = false) Double maxRating) {
        return ResponseEntity.ok(movieService.getAllMovies(title, genre, releaseYear, minRating, maxRating));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    @GetMapping("/top-rated")
    public ResponseEntity<List<MovieDto>> getAllMoviesByImdbRatingDesc() {
        return ResponseEntity.ok(movieService.getAllMoviesByImdbRatingDesc());
    }

    @PostMapping
    public ResponseEntity<MovieDto> createMovie(@RequestBody @Valid CreateMovieRequest movieRequest) {
        return ResponseEntity.ok(movieService.createMovie(movieRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDto> updateMovie(@PathVariable Long id, @RequestBody UpdateMovieRequest movieRequest) {
        return ResponseEntity.ok(movieService.updateMovie(id, movieRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MovieDto> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
}
