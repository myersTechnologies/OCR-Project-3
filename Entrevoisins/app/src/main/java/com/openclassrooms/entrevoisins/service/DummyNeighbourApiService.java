package com.openclassrooms.entrevoisins.service;


import android.content.Intent;
import android.content.SharedPreferences;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.FavoriteNeighbour;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_details.NeighboursDetailsActivity;
import com.openclassrooms.entrevoisins.ui.neighbour_list.MyNeighbourRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();

    private List<FavoriteNeighbour> favoriteNeighbours;

    private Neighbour neighbour;

    private FavoriteNeighbour favoriteNeighbour;


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     used to send neighbour to details activity
     **/
    @Override
    public void setNeighbourToShowInDetails(Neighbour neighbour) {
        this.neighbour = neighbour;
    }

    @Override
    public Neighbour getNeighbour() {
        return neighbour;
    }


    @Override
    public List<FavoriteNeighbour> getFavorites() {
        if (favoriteNeighbours == null){
            favoriteNeighbours = new ArrayList<>();
        }
        return favoriteNeighbours;
    }

    @Override
    public void deleteFavoriteNeighbour(FavoriteNeighbour favoriteNeighbour) {
        favoriteNeighbours.remove(favoriteNeighbour);
    }

    @Override
    public void addFavoriteNeighbour(FavoriteNeighbour favoriteNeighbour) {
        if (favoriteNeighbours == null){
            favoriteNeighbours = new ArrayList<>();
        }
        favoriteNeighbours.add(favoriteNeighbour);
        this.favoriteNeighbour = favoriteNeighbour;
    }

    @Override
    public void setFavoriteToShowInDetails(FavoriteNeighbour favoriteNeighbour) {
        int id = favoriteNeighbour.getId();
        String name = favoriteNeighbour.getName();
        String pic = favoriteNeighbour.getAvatarUrl();
        Neighbour neighbour = new Neighbour(id, name, pic);
        this.neighbour = neighbour;
    }

    @Override
    public FavoriteNeighbour getFavoriteNeighbour() {
        return favoriteNeighbour;
    }


}
