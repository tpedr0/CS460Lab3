package com.example.movieposter;

/**
 * PosterListener is an interface for handling actions on poster items.
 * This can be used to trigger actions based on poster selection changes.
 */
public interface PosterListener {
    /**
     * Called when the selection state of a poster changes.
     * @param isSelected True if a poster is selected, false if deselected.
     */
    void onPosterAction(Boolean isSelected);
}
