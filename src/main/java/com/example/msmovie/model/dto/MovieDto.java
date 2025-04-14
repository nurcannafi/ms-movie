package com.example.msmovie.model.dto;

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
public class MovieDto {

    @NotBlank
    private String title;

    @NotBlank
    private String director;

    @NotNull
    private Integer releaseYear;

    @NotBlank
    private String genre;

    @NotNull
    @DecimalMin(value = "0.0")
    @DecimalMax(value = "10.0")
    private Double imdbRating;
}
