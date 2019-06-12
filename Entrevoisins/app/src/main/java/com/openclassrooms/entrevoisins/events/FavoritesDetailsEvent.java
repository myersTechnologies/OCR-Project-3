package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class FavoritesDetailsEvent {
    public Neighbour favoriteNeighbour;

    public FavoritesDetailsEvent(Neighbour favoriteNeighbour){
        this.favoriteNeighbour = favoriteNeighbour;
    }
}

