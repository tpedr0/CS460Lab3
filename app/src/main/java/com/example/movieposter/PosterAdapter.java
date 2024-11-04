package com.example.movieposter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * PosterAdapter is a RecyclerView Adapter for displaying a list of movie posters.
 * It binds the data from each Poster object to the views in the RecyclerView.
 */

public class PosterAdapter extends RecyclerView.Adapter<PosterAdapter.PosterViewHolder> {

    /**
     * Creates a new ViewHolder for a poster.
     * @param parent The parent view group.
     * @param viewType The type of view.
     * @return A new PosterViewHolder instance.
     */
    @NonNull
    @Override
    public PosterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PosterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container, parent, false));
    }

    /**
     * Binds the data from a Poster to the views in the ViewHolder.
     * @param holder The ViewHolder to bind data to.
     * @param position The position of the item within the adapter's data set.
     */

    @Override
    public void onBindViewHolder(@NonNull PosterViewHolder holder, int position) {
        holder.bindPosters(posterList.get(position));
    }

    /**
     * Gets the total number of items in the adapter.
     * @return The number of items.
     */
    @Override
    public int getItemCount() {
        return posterList.size();
    }

    private List<Poster> posterList;
    private PosterListener postersListener;

    /**
     * Returns a list of posters that have been selected.
     * @return A list of selected posters.
     */

    public List<Poster>  getSelectedPosters(){
        List<Poster> selectedPosters = new ArrayList<>();
        for(Poster poster : this.posterList){
            if(poster.isSelected){
                selectedPosters.add(poster);
            }
        }
        return selectedPosters;
    }

    /**

     * Constructor for PosterAdapter.
     * @param posterList List of posters to be displayed.
     * @param postersListener Listener for handling poster selection actions.
     */
    public PosterAdapter(List<Poster> posterList, PosterListener postersListener) {
        this.posterList = posterList;
        this.postersListener = postersListener;
    }

    /**
     * ViewHolder class for holding and binding poster data.
     */

    class PosterViewHolder extends RecyclerView.ViewHolder{

        ConstraintLayout layoutPosters;
        View viewBackground;

        RoundedImageView imagePoster;
        TextView textName, textCreatedBy, textStory;

        RatingBar ratingBar;

        ImageView imageSelected;

        /**
         * Initializes the view components of the ViewHolder.
         * @param itemView The item view for each poster.
         */
        public PosterViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutPosters = itemView.findViewById(R.id.layoutPosters);
            viewBackground = itemView.findViewById(R.id.viewBackground);
            imagePoster = itemView.findViewById(R.id.imagePosters);
            textName = itemView.findViewById(R.id.textName);
            textCreatedBy = itemView.findViewById(R.id.textCreatedBy);
            textStory = itemView.findViewById(R.id.textStory);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            imageSelected = itemView.findViewById(R.id.imageSelected);

        }



        /**
         * Binds a Poster object to the ViewHolder.
         * @param poster The Poster object to bind.
         */
        void bindPosters(final Poster poster){
            imagePoster.setImageResource(poster.image);
            textName.setText(poster.name);
            textCreatedBy.setText(poster.createdBy);
            textStory.setText(poster.story);
            ratingBar.setRating(poster.rating);
            if(poster.isSelected){
                viewBackground.setBackgroundResource(R.drawable.poster_selected_background);
                imageSelected.setVisibility(View.VISIBLE);
            } else{
                viewBackground.setBackgroundResource(R.drawable.poster_background);
                imageSelected.setVisibility(View.GONE);
            }
            layoutPosters.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(poster.isSelected){
                        viewBackground.setBackgroundResource(R.drawable.poster_background);
                        imageSelected.setVisibility(View.GONE);
                        poster.isSelected = false;
                        if(getSelectedPosters().isEmpty()){
                            postersListener.onPosterAction(false);
                        }
                    }else {
                        viewBackground.setBackgroundResource(R.drawable.poster_selected_background);
                        imageSelected.setVisibility(View.VISIBLE);
                        poster.isSelected = true;
                        postersListener.onPosterAction(true);
                    }
                }
            });
        }
    }
}
