package com.openclassrooms.entrevoisins.model;

import java.util.Objects;

/**
 * Model object representing a Neighbour
 */
public class Neighbour {

    /** Identifier */
    private Integer id;

    /** Full name */
    private String name;

    /** Avatar */
    private String avatarUrl;

    /**
     * Constructor
     * @param id
     * @param name
     * @param avatarUrl
     */
    public Neighbour(Integer id, String name, String avatarUrl) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Neighbour neighbour = (Neighbour) o;
        return Objects.equals(id, neighbour.id);
    }

    public String getAboutNeighbour(){
        return "À ses débuts, il était le guitariste d'un groupe nommé Road Crew." +
                " Toutefois, il est surtout connu pour être le guitariste soliste du groupe de hard rock Guns N' Roses avec Axl Rose 1985 à 1996," +
                " puis de nouveau à partir de 2016, et le leader de Slash's Snakepit de 1995 à 2001. " +
                "Il a ensuite fondé Velvet Revolver avec Duff McKagan et Matt Sorum. En 2010, il se lance dans une carrière solo et sort son premier album intitulé Slash qui réunit plusieurs" +
                " artistes notamment le chanteur et guitariste Myles Kennedy avec qui il réalise ensuite les albums solo Apocalyptic Love, " +
                "World on Fire et Living the Dream.";
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
