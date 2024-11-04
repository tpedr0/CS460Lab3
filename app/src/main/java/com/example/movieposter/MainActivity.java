package com.example.movieposter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * MainActivity is the main entry point of the app where movie posters are displayed.
 * It initializes the list of posters, sets up the RecyclerView, and handles interactions such as adding posters to the watchlist.
 */
public class MainActivity extends AppCompatActivity implements PosterListener {

    private Button buttonAddToWatchlist;

    /**
     * Called when the activity is starting. Sets up the UI components and initializes the poster list.
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView postersRecyclerView = findViewById(R.id.posterRecyclerView);
        buttonAddToWatchlist = findViewById(R.id.buttonAddToWatchlist);

        // Adding posters to the list
        List<Poster> posterList = new ArrayList<>();
        Poster movie127hours = new Poster();
        movie127hours.image = R.drawable.movie127hours;
        movie127hours.name = "127 Hours";
        movie127hours.createdBy = "Danny Boyle";
        movie127hours.rating = 3.75f;
        movie127hours.story = "A mountain climber becomes trapped under a boulder while canyoneering alone near Moab, Utah and resorts to desperate measures in order to survive.";
        posterList.add(movie127hours);

        Poster movieDeadPoolWolverine = new Poster();
        movieDeadPoolWolverine.image = R.drawable.deadpoolwolverine;
        movieDeadPoolWolverine.name = "Deadpool & Wolverine";
        movieDeadPoolWolverine.createdBy = "Shawn Levy";
        movieDeadPoolWolverine.rating = 4f;
        movieDeadPoolWolverine.story = "Deadpool is offered a place in the Marvel Cinematic Universe by the Time Variance Authority, but instead recruits a variant of Wolverine to save his universe from extinction.";
        posterList.add(movieDeadPoolWolverine);

        Poster movieFreeSolo = new Poster();
        movieFreeSolo.image = R.drawable.freesolo;
        movieFreeSolo.name = "Free Solo";
        movieFreeSolo.createdBy = "Jimmy Chin";
        movieFreeSolo.rating = 4f;
        movieFreeSolo.story = "Alex Honnold faces the biggest challenge of his career, climbing El Capitan in Yosemite National Park. He pursues it Free Solo, which means climbing without a rope and alone.";
        posterList.add(movieFreeSolo);

        Poster movieJoker = new Poster();
        movieJoker.image = R.drawable.joker;
        movieJoker.name = "Joker: Folie à Deux";
        movieJoker.createdBy = "Todd Phillips";
        movieJoker.rating = 4f;
        movieJoker.story = "Struggling with his dual identity, failed comedian Arthur Fleck meets the love of his life, Harley Quinn, while incarcerated at Arkham State Hospital.";
        posterList.add(movieJoker);

        Poster movieParasite = new Poster();
        movieParasite.image = R.drawable.parasite;
        movieParasite.name = "Parasite";
        movieParasite.createdBy = "Bong Joon Ho";
        movieParasite.rating = 4.5f;
        movieParasite.story = "Greed and class discrimination threaten the newly formed symbiotic relationship between the wealthy Park family and the destitute Kim clan.";
        posterList.add(movieParasite);

        Poster movieSocialNetwork = new Poster();
        movieSocialNetwork.image = R.drawable.socialnetwork;
        movieSocialNetwork.name = "The Social Network";
        movieSocialNetwork.createdBy = "David Fincher";
        movieSocialNetwork.rating = 4f;
        movieSocialNetwork.story = "As Harvard student Mark Zuckerberg creates the social networking site that would become known as Facebook, he is sued by the twins who claimed he stole their idea and by the co-founder who was later squeezed out of the business.";
        posterList.add(movieSocialNetwork);

        Poster movieTerrifier3 = new Poster();
        movieTerrifier3.image = R.drawable.terrifier3;
        movieTerrifier3.name = "Terrifier 3";
        movieTerrifier3.createdBy = "Damien Leone";
        movieTerrifier3.rating = 3f;
        movieTerrifier3.story = "Art the Clown is set to unleash chaos on the unsuspecting residents of Miles County as they peacefully drift off to sleep on Christmas Eve.";
        posterList.add(movieTerrifier3);

        Poster movieTheWildRobot = new Poster();
        movieTheWildRobot.image = R.drawable.thewildrobot;
        movieTheWildRobot.name = "The Wild Robot";
        movieTheWildRobot.createdBy = "Chris Sanders";
        movieTheWildRobot.rating = 4.5f;
        movieTheWildRobot.story = "After a shipwreck, an intelligent robot called Roz is stranded on an uninhabited island. To survive the harsh environment, Roz bonds with the island's animals and cares for an orphaned baby goose.";
        posterList.add(movieTheWildRobot);

        Poster movieTransformersOne = new Poster();
        movieTransformersOne.image = R.drawable.transformersone;
        movieTransformersOne.name = "Transformers One";
        movieTransformersOne.createdBy = "Josh Cooley";
        movieTransformersOne.rating = 4.5f;
        movieTransformersOne.story = "The untold origin story of Optimus Prime and Megatron, better known as sworn enemies, but who once were friends bonded like brothers who changed the fate of Cybertron forever.";
        posterList.add(movieTransformersOne);

        Poster movieVenomTheLastDance = new Poster();
        movieVenomTheLastDance.image = R.drawable.venom;
        movieVenomTheLastDance.name = "Venom: The Last Dance";
        movieVenomTheLastDance.createdBy = "Kelly Marcel";
        movieVenomTheLastDance.rating = 3f;
        movieVenomTheLastDance.story = "Eddie and Venom, on the run, face pursuit from both worlds. As circumstances tighten, they're compelled to make a heart-wrenching choice that could mark the end of their symbiotic partnership.";
        posterList.add(movieVenomTheLastDance);

        final PosterAdapter posterAdapter = new PosterAdapter(posterList, this);
        postersRecyclerView.setAdapter(posterAdapter);

        buttonAddToWatchlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Poster> selectPoster = posterAdapter.getSelectedPosters();

                StringBuilder posterName = new StringBuilder();
                for(int i = 0; i < selectPoster.size(); i++){
                    if(i==0){
                        posterName.append(selectPoster.get(i).name);
                    } else{
                        posterName.append("\n").append(selectPoster.get(i).name);
                    }
                }
                Toast.makeText(MainActivity.this, posterName.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * This method is called when a poster is selected or deselected.
     * It updates the visibility of the "Add to Watchlist" button based on selection status.
     * @param isSelected Boolean indicating if a poster has been selected.
     */

    @Override
    public void onPosterAction(Boolean isSelected) {
        if(isSelected){
            buttonAddToWatchlist.setVisibility(View.VISIBLE);
        } else{
            buttonAddToWatchlist.setVisibility(View.GONE);
        }
    }
}