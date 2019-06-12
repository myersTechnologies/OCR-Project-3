package com.openclassrooms.entrevoisins.ui.favorites_list;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteFavoriteEvent;
import com.openclassrooms.entrevoisins.events.DetailsActivityEvent;
import com.openclassrooms.entrevoisins.events.FavoritesDetailsEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class FavoritesFragment extends Fragment {

    private RecyclerView mFavoritesRecyclerView;
    private NeighbourApiService mFavoriteNeighbourApi;
    private List<Neighbour> mFavoriteNeighbourList;
    private MyFavoriteNeighbourRecyclerViewAdapter rViewAdapter;

    public static FavoritesFragment newInstance() {
        FavoritesFragment fragment = new FavoritesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFavoriteNeighbourApi = DI.getNeighbourApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_favorite_neighbours, container, false);
        Context context = view.getContext();
        mFavoritesRecyclerView = (RecyclerView) view;
        mFavoritesRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        initFavoritesList();
        return view;
    }

    private void initFavoritesList() {
        mFavoriteNeighbourList = mFavoriteNeighbourApi.getFavorites();
        rViewAdapter = new MyFavoriteNeighbourRecyclerViewAdapter(mFavoriteNeighbourList);
        mFavoritesRecyclerView.setAdapter(rViewAdapter);
    }

    public void onResume() {
        rViewAdapter.notifyDataSetChanged();
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onDeleteFavoriteNeighbour(DeleteFavoriteEvent event) {
        mFavoriteNeighbourApi.deleteFavoriteNeighbour(event.neighbour);
        initFavoritesList();
    }

    @Subscribe
    public void onStartDetailsActivity(FavoritesDetailsEvent event){
        mFavoriteNeighbourApi.setFavoriteToShowInDetails(event.favoriteNeighbour);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getFragmentManager().beginTransaction().attach(this).commit();
        } else {
            getFragmentManager().beginTransaction().detach(this).commit();
        }
    }
}

