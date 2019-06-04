package com.openclassrooms.entrevoisins.ui.neighbour_details;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.R;


import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsRecyclerViewAdapter extends RecyclerView.Adapter<DetailsRecyclerViewAdapter.ViewHolder> {

    private Neighbour neighbour;

    public DetailsRecyclerViewAdapter(Neighbour neighbour){
        this.neighbour = neighbour;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.neighbour_details_list, viewGroup, false);
        return new DetailsRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mNeighbourAdress.setText("14 Avenue de Paris, 71100 Chalon sur Saone");
        holder.mNeighbourContact.setText("06 58 95 72 15");
        holder.mNeighbourSite.setText("https://www.facebook.com/some.user.902");
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.neighbour_adress)
    public TextView mNeighbourAdress;
    @BindView(R.id.neighbour_contact)
    public TextView mNeighbourContact;
    @BindView(R.id.neighbour_site)
    public TextView mNeighbourSite;

    public ViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
}
