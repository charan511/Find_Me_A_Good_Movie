package com.charan.moviesuggest.client;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TmdbMovie(
        long id,
        String title,
        String overview,
        @JsonProperty("poster_path") String posterPath,
        @JsonProperty("vote_average") double voteAverage,
        @JsonProperty("release_date") String releaseDate
) {}