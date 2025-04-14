package com.example.msmovie.domain.repository;

import com.example.msmovie.domain.entity.MovieEntity;
import org.springframework.data.jpa.domain.Specification;

public class MovieSpecification {

    public static Specification<MovieEntity> hasTitle(String title) {
        return (root, query, cb) ->
                title == null ? null : cb.like(root.get("title"), "%" + title + "%");
    }

    public static Specification<MovieEntity> hasReleaseYear(String releaseYear) {
        return (root, query, cb) ->
                releaseYear == null ? null : cb.like(root.get("releaseYear"), "%" + releaseYear + "%");
    }

    public static Specification<MovieEntity> hasGenre(String genre) {
        return (root, query, cb) ->
                genre == null ? null : cb.like(root.get("genre"), "%" + genre + "%");
    }

    public static Specification<MovieEntity> hasRatingBetween(Double minRating, Double maxRating) {
        return (root, query, cb) -> {
            if (minRating == null && maxRating == null) return null;
            if (minRating == null) return cb.lessThanOrEqualTo(root.get("rating"), maxRating);
            if (maxRating == null) return cb.greaterThanOrEqualTo(root.get("rating"), minRating);
            return cb.between(root.get("rating"), minRating, maxRating);
        };
    }
}
