package com.example.msmovie.model.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMovieRequest {

    private String title;
    private String director;
    private Integer releaseYear;
    private String genre;

    @DecimalMin(value = "0.0", message = "This imdb is wrong")
    @DecimalMax(value = "10.0", message = "This imdb is wrong")
    private Double imdbRating;
}
