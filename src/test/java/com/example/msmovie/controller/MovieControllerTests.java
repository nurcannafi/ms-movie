package com.example.msmovie.controller;

import com.example.msmovie.service.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.example.msmovie.common.MovieTestConstants.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MovieController.class)
public class MovieControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MovieService movieService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllMovies_ShouldReturnAllMovies() throws Exception {
        given(movieService.getAllMovies(MOVIE_TITLE, GENRE, RELEASE_YEAR, 1.0, 9.9))
                .willReturn(List.of(MOVIE_DTO));

        mockMvc.perform(get("/api/v1/movies")
                        .param("title", MOVIE_TITLE)
                        .param("genre", GENRE)
                        .param("releaseYear", String.valueOf(RELEASE_YEAR))
                        .param("minRating", "1.0")
                        .param("maxRating", "9.2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(MOVIE_DTO.getTitle()));

        then(movieService).should(times(1))
                .getAllMovies(MOVIE_TITLE, GENRE, RELEASE_YEAR, 1.0, 9.9);

    }

}

