package com.openclassrooms.entrevoisins.ui.neighbour_details;

import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.openclassrooms.entrevoisins.R;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class NeighbourCardViewAdapter extends RecyclerView.Adapter<NeighbourCardViewAdapter.CardViewHolder> {

    Neighbour neighbour;
    public NeighbourCardViewAdapter(Neighbour neighbour){
        this.neighbour = neighbour;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_layout, viewGroup, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CardViewHolder cardViewHolder, int i) {
        cardViewHolder.neighbourName.setText(neighbour.getName());
        cardViewHolder.neighbourInfoRView.setAdapter(new DetailsRecyclerViewAdapter(neighbour));
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder{

        CardView neighbourCardView;
        TextView neighbourName;
        RecyclerView neighbourInfoRView;

        public CardViewHolder(View itemView) {
            super(itemView);
            neighbourCardView = (CardView) itemView.findViewById(R.id.neighbour_card_view);
            neighbourName = (TextView) itemView.findViewById(R.id.user_details_name);
            neighbourInfoRView = (RecyclerView) itemView.findViewById(R.id.neighbour_info_recyclerview);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext());
            neighbourInfoRView.setLayoutManager(linearLayoutManager);

        }
    }
}
