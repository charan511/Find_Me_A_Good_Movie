package com.charan.moviesuggest.service;

import com.charan.moviesuggest.client.TmdbClient;
import com.charan.moviesuggest.client.TmdbMovie;
import com.charan.moviesuggest.dto.MovieSummary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MovieService {

    private static final String POSTER_BASE = "https://image.tmdb.org/t/p/w500";

    private static final Map<String, Integer> GENRES = Map.of(
            "action", 28, "comedy", 35, "drama", 18,
            "horror", 27, "romance", 10749, "scifi", 878, "thriller", 53
    );

    private final TmdbClient tmdbClient;

    public MovieService(TmdbClient tmdbClient) {
        this.tmdbClient = tmdbClient;
    }

    public List<MovieSummary> getByGenre(String genre) {
        Integer genreId = GENRES.get(genre.toLowerCase());
        if (genreId == null) {
            throw new IllegalArgumentException("Unknown genre: " + genre);
        }

        return tmdbClient.getMoviesByGenre(genreId).results()
                .stream()
                .map(this::toSummary)
                .toList();
    }

    private MovieSummary toSummary(TmdbMovie m) {
        String year = (m.releaseDate() == null || m.releaseDate().isBlank())
                ? "N/A" : m.releaseDate().substring(0, 4);
        String poster = (m.posterPath() == null)
                ? null : POSTER_BASE + m.posterPath();
        return new MovieSummary(m.id(), m.title(), poster, m.voteAverage(), year, m.overview());
    }
}