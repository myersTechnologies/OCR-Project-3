package com.openclassrooms.entrevoisins.ui.neighbour_details;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.openclassrooms.entrevoisins.R;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class NeighbourAboutCardViewAdapter extends RecyclerView.Adapter<NeighbourAboutCardViewAdapter.CardViewHolder> {

    Neighbour neighbour;
    public NeighbourAboutCardViewAdapter(Neighbour neighbour){
        this.neighbour = neighbour;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.about_neighbour_card_view, viewGroup, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CardViewHolder cardViewHolder, int i) {
        cardViewHolder.aboutTextView.setText(neighbour.getAboutNeighbour());
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder{
        TextView aboutTextView;
        public CardViewHolder(View itemView) {
            super(itemView);
            aboutTextView = (TextView) itemView.findViewById(R.id.neighbour_a_propos_text);
        }
    }

}
