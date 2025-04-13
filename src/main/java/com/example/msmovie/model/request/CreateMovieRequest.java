package com.example.model.request;

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
}
