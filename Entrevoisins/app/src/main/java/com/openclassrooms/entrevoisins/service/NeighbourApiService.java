package com.openclassrooms.entrevoisins.service;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.openclassrooms.entrevoisins.model.FavoriteNeighbour;
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

    List<FavoriteNeighbour> getFavorites();

    void deleteFavoriteNeighbour(FavoriteNeighbour favoriteNeighbour);

    void addFavoriteNeighbour(FavoriteNeighbour favoriteNeighbour);

    void setFavoriteToShowInDetails(FavoriteNeighbour favoriteNeighbour);

    FavoriteNeighbour getFavoriteNeighbour();

}
