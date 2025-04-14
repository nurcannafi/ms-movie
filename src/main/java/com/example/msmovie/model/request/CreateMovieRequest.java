package com.example.msmovie.model.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMovieRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String director;

    @NotNull
    private Integer releaseYear;

    @NotBlank
    private String genre;

    @NotNull
    @DecimalMin(value = "0.0", message = "this imdb is wrong")
    @DecimalMax(value = "10.0", message = "this imdb is wrong")
    private Double imdbRating;

}
