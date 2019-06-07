<<<<<<< Updated upstream
package com.openclassrooms.entrevoisins.ui.neighbour_details;


import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;

import java.util.List;


public class NeighboursDetailsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView imageView;
    private FloatingActionButton addToFavorites;
    private NeighbourApiService mApiService;
    private Neighbour neighbour;
    private RecyclerView neighbourInfoRView, aboutNeighboursRView;
    private List<Neighbour> favoriteNeighbourList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbours_details);
        mApiService = DI.getNeighbourApiService();
        favoriteNeighbourList = mApiService.getFavorites();
        getNeighbour();
        setViews();
        setToolbar();
        setDetails();
        addToFavorites();
    }

    public void getNeighbour(){
        neighbour = mApiService.getNeighbour();
    }

    public void setViews(){
        toolbar = (Toolbar) findViewById(R.id.toolbarlayout);
        imageView = (ImageView) findViewById(R.id.neighbour_avatar);
        addToFavorites = (FloatingActionButton) findViewById(R.id.add_favorite_fab);
        neighbourInfoRView = (RecyclerView) findViewById(R.id.neighbour_details_recyclerview);
        LinearLayoutManager linearLayoutRV = new LinearLayoutManager(this);
        neighbourInfoRView.setLayoutManager(linearLayoutRV);
        aboutNeighboursRView = (RecyclerView) findViewById(R.id.neighbour_about_details_recyclerview);
        LinearLayoutManager layoutForAboutDetails = new LinearLayoutManager(this);
        aboutNeighboursRView.setLayoutManager(layoutForAboutDetails);
    }

    public void setToolbar(){
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);
        ab.setDisplayShowTitleEnabled(false);

    }

    public void setDetails(){
        Glide.with(imageView.getContext()).load(neighbour.getAvatarUrl()).apply(RequestOptions.noTransformation()).into(imageView);
        neighbourInfoRView.setAdapter(new NeighbourCardViewAdapter(neighbour));
        aboutNeighboursRView.setAdapter(new NeighbourAboutCardViewAdapter(neighbour));
    }

    public void addToFavorites(){
                    addToFavorites.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (!mApiService.getFavorites().contains(neighbour)) {
                                mApiService.addFavoriteNeighbour(neighbour);
                                Toast.makeText(getApplicationContext(), "Added to favorites", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Already in favorites", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent neighbourListIntent = new Intent(this, ListNeighbourActivity.class);
                startActivity(neighbourListIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
=======
package com.openclassrooms.entrevoisins.ui.neighbour_details;


import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;

import java.util.List;


public class NeighboursDetailsActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbours_details);
        toolbar = (Toolbar) findViewById(R.id.toolbarlayout);
        setToolbar();
    }

    public void setToolbar(){
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);
        ab.setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent neighbourListIntent = new Intent(this, ListNeighbourActivity.class);
                startActivity(neighbourListIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
>>>>>>> Stashed changes
