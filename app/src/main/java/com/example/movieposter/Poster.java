package com.example.movieposter;

/**
 * Poster represents a movie poster with details such as name, creator, story, image resource, and rating.
 * Used in the app to display and manage movie posters in a list.
 */
public class Poster {
    // the name of the movie, director and description
    String name, createdBy, story;
    // the image resource for the movie poster
    int image;
    // boolean indicating whether or not the movie is selected
    Boolean isSelected = false;
    //the rating of the movie
    float rating;
}
