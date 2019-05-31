package com.openclassrooms.entrevoisins.events;


import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_list.MyNeighbourRecyclerViewAdapter;


public class DetailsActivityEvent {

   public Neighbour neighbour;

    public DetailsActivityEvent(Neighbour neighbour){
        this.neighbour = neighbour;
    }
}
