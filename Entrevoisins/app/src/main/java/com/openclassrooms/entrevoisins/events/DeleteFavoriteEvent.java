package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.FavoriteNeighbour;

public class DeleteFavoriteEvent {

    public FavoriteNeighbour favoriteNeighbour;

    public DeleteFavoriteEvent(FavoriteNeighbour favoriteNeighbour){
        this.favoriteNeighbour = favoriteNeighbour;
    }
}
