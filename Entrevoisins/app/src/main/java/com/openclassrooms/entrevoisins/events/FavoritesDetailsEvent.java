package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.FavoriteNeighbour;

public class FavoritesDetailsEvent {
    public FavoriteNeighbour favoriteNeighbour;

    public FavoritesDetailsEvent(FavoriteNeighbour favoriteNeighbour){
        this.favoriteNeighbour = favoriteNeighbour;
    }
}
