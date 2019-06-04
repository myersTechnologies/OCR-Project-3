package com.openclassrooms.entrevoisins.service;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_list.MyNeighbourRecyclerViewAdapter;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    void setNeighbourToShowInDetails(Neighbour neighbour);

    Neighbour getNeighbour();

    List<Neighbour> getFavorites();

    void deleteFavoriteNeighbour(Neighbour neighbour);

    void addFavoriteNeighbour(Neighbour neighbour);

    void setFavoriteToShowInDetails(Neighbour neighbour);

    Neighbour getFavoriteNeighbour();

}
