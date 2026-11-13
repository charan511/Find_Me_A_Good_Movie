package com.charan.moviesuggest.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class TmdbClient {

    private final RestClient restClient;
    private final String apiKey;

    public TmdbClient(@Value("${tmdb.base-url}") String baseUrl,
                      @Value("${tmdb.api.key}") String apiKey) {
        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("User-Agent", "MovieSuggestApp/1.0")   // ← REQUIRED by TMDB
                .build();
        this.apiKey = apiKey;
    }

    public TmdbResponse getMoviesByGenre(int genreId) {
        String uri = UriComponentsBuilder
                .fromPath("/discover/movie")
                .queryParam("with_genres", genreId)
                .queryParam("sort_by", "popularity.desc")
                .queryParam("api_key", apiKey)
                .toUriString();

        return restClient.get()
                .uri(uri)
                .retrieve()
                .body(TmdbResponse.class);
    }
}