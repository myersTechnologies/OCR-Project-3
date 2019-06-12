package com.openclassrooms.entrevoisins.ui.favorites_list;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.events.DeleteFavoriteEvent;
import com.openclassrooms.entrevoisins.events.DetailsActivityEvent;
import com.openclassrooms.entrevoisins.events.FavoritesDetailsEvent;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_details.NeighboursDetailsActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyFavoriteNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<MyFavoriteNeighbourRecyclerViewAdapter.ViewHolder> {
    private List<Neighbour> favoriteNeighbours;

    public MyFavoriteNeighbourRecyclerViewAdapter(List<Neighbour> favoriteNeighbours){
        this.favoriteNeighbours = favoriteNeighbours;
    }

    @Override
    public MyFavoriteNeighbourRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_neighbour, parent, false);
        return new MyFavoriteNeighbourRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyFavoriteNeighbourRecyclerViewAdapter.ViewHolder viewHolder, int position) {
        Neighbour favoriteNeighbour = favoriteNeighbours.get(position);
        viewHolder.mNeighbourName.setText(favoriteNeighbour.getName());
        Glide.with(viewHolder.mNeighbourAvatar.getContext())
                .load(favoriteNeighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(viewHolder.mNeighbourAvatar);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new DetailsActivityEvent(favoriteNeighbour));
                Intent intent = new Intent(viewHolder.itemView.getContext(), NeighboursDetailsActivity.class);
                viewHolder.itemView.getContext().startActivity(intent);
            }
        });
        viewHolder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new DeleteFavoriteEvent(favoriteNeighbour));
            }
        });
    }

    @Override
    public int getItemCount() {
        return favoriteNeighbours.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_list_avatar)
        public ImageView mNeighbourAvatar;
        @BindView(R.id.item_list_name)
        public TextView mNeighbourName;
        @BindView(R.id.item_list_delete_button)
        public ImageButton mDeleteButton;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

