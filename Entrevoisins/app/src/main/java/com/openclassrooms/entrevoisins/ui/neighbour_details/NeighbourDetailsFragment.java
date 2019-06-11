<<<<<<< Updated upstream
package com.openclassrooms.entrevoisins.ui.neighbour_details;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.openclassrooms.entrevoisins.R;



public class NeighbourDetailsFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_neighbour_details, container, false);
        return view;
    }



}
=======
package com.openclassrooms.entrevoisins.ui.neighbour_details;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.favorites_list.FavoritesFragment;

import java.util.List;


public class NeighbourDetailsFragment extends Fragment {

    private ImageView imageView;
    private FloatingActionButton addToFavorites;
    private NeighbourApiService mApiService;
    private Neighbour neighbour;
    private RecyclerView neighbourInfoRView, aboutNeighboursRView;
    private List<Neighbour> favoriteNeighbourList;

    public static NeighbourDetailsFragment newInstance() {
        NeighbourDetailsFragment details = new NeighbourDetailsFragment();
        return details;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_neighbour_details, container, false);
        mApiService = DI.getNeighbourApiService();
        favoriteNeighbourList = mApiService.getFavorites();
        getNeighbour();
        setViews(view);
        setDetails();
        addToFavorites();
        return view;
    }

    public void getNeighbour(){
        neighbour = mApiService.getNeighbour();
    }

    public void setViews(View parent){
        imageView = (ImageView) parent.findViewById(R.id.neighbour_avatar);
        addToFavorites = (FloatingActionButton) parent.findViewById(R.id.add_favorite_fab);
        neighbourInfoRView = (RecyclerView) parent.findViewById(R.id.neighbour_details_recyclerview);
        LinearLayoutManager linearLayoutRV = new LinearLayoutManager(getActivity());
        neighbourInfoRView.setLayoutManager(linearLayoutRV);
        aboutNeighboursRView = (RecyclerView) parent.findViewById(R.id.neighbour_about_details_recyclerview);
        LinearLayoutManager layoutForAboutDetails = new LinearLayoutManager(getActivity());
        aboutNeighboursRView.setLayoutManager(layoutForAboutDetails);
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
                if (!favoriteNeighbourList.contains(neighbour)) {
                    mApiService.addFavoriteNeighbour(neighbour);
                    Toast.makeText(getContext(), "Added to favorites", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Already in favorites", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
>>>>>>> Stashed changes
