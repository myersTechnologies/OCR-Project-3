package com.openclassrooms.entrevoisins.service;

import android.content.Intent;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_details.NeighboursDetailsActivity;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;
    private Neighbour neighbourToCheck;
    private Neighbour favoriteNeighbour;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
        neighbourToCheck = service.getNeighbours().get(0);
        favoriteNeighbour = service.getNeighbours().get(1);
        service.getFavorites();
        service.addFavoriteNeighbour(favoriteNeighbour);
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourAndFavoriteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
        assertFalse(service.getFavorites().contains(neighbourToDelete));
    }

    @Test
    public void getNeighbourDetailsWithSuccess(){
        service.setNeighbourToShowInDetails(neighbourToCheck);
        assertTrue(service.getNeighbour().getName().contains(neighbourToCheck.getName()));
        assertTrue(service.getNeighbour().getId().equals(neighbourToCheck.getId()));
        assertTrue(service.getNeighbour().getAvatarUrl().contains(neighbourToCheck.getAvatarUrl()));

    }

    @Test
    public void addNeighbourToFavorites(){
        Neighbour favorite = service.getNeighbours().get(1);
        service.addFavoriteNeighbour(favorite);
        assertTrue(service.getFavorites().contains(favorite));
    }

    @Test
    public void checkIfFavoriteNeighbourIsAlreadyAdded(){
        List<Neighbour> favorites = service.getFavorites();
        service.addFavoriteNeighbour(favorites.get(0));
        assertEquals(favoriteNeighbour, favorites.get(0));
    }

    @Test
    public void deleteFavoriteNeighbour(){
        service.deleteFavoriteNeighbour(favoriteNeighbour);
        assertFalse(service.getFavorites().contains(favoriteNeighbour));
    }

    @Test
    public void getFavoriteNeighbourDetailsWithSuccess(){
        assertTrue(service.getFavoriteNeighbour().getName().contains(favoriteNeighbour.getName()));
        assertTrue(service.getFavoriteNeighbour().getAvatarUrl().contains(favoriteNeighbour.getAvatarUrl()));
        assertEquals(service.getFavoriteNeighbour().getId(), favoriteNeighbour.getId());
    }
}
