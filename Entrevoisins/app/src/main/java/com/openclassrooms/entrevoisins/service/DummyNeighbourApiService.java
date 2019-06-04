package com.openclassrooms.entrevoisins.service;


import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.openclassrooms.entrevoisins.di.DI;
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

    private List<Neighbour> favoriteNeighbours;

    private Neighbour neighbour;


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
            for (int j = 0; j < favoriteNeighbours.size(); j++){
                if (neighbour.getName().compareTo(favoriteNeighbours.get(j).getName()) == 0){
                    favoriteNeighbours.remove(favoriteNeighbours.get(j));
                }
            }
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
    public List<Neighbour> getFavorites() {
        if (favoriteNeighbours == null){
            favoriteNeighbours = new ArrayList<>();
        }
        return favoriteNeighbours;
    }

    @Override
    public void deleteFavoriteNeighbour(Neighbour favoriteNeighbour) {
        favoriteNeighbours.remove(favoriteNeighbour);
    }

    @Override
    public void addFavoriteNeighbour(Neighbour neighbour) {
        favoriteNeighbours.add(neighbour);
        this.neighbour = neighbour;

    }


    @Override
    public void setFavoriteToShowInDetails(Neighbour favoriteNeighbour) {
        this.neighbour = favoriteNeighbour;
    }

    @Override
    public Neighbour getFavoriteNeighbour() {
        return neighbour;
    }


}
