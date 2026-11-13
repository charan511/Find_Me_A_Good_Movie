package com.charan.moviesuggest.controller;

import com.charan.moviesuggest.client.TmdbClient;
import com.charan.moviesuggest.dto.MovieSummary;
import com.charan.moviesuggest.service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/genre/{genre}")
    public List<MovieSummary> byGenre(@PathVariable String genre) {
        return movieService.getByGenre(genre);
    }
}