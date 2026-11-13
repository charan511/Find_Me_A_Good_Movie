package com.charan.moviesuggest.client;


import java.util.List;

public record TmdbResponse(List<TmdbMovie> results) {}