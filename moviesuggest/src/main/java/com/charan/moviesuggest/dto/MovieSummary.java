package com.charan.moviesuggest.dto;

public record MovieSummary(
        long id,
        String title,
        String posterUrl,
        double rating,
        String releaseYear,
        String overview
) {}