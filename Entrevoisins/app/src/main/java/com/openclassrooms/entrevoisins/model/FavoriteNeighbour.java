package com.openclassrooms.entrevoisins.model;

public class FavoriteNeighbour {


    private String name;
    private String avatarUrl;
    private int id;

    public FavoriteNeighbour(Neighbour neighbour){
        this.name = neighbour.getName();
        this.avatarUrl = neighbour.getAvatarUrl();
        this.id = neighbour.getId();
    }

    public String getName(){
        return name;
    }
    public String getAvatarUrl(){
        return avatarUrl;
    }
    public  int getId(){
        return id;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setAvatarUrl(String avatarUrl){
        this.avatarUrl = avatarUrl;
    }
    public void setId(int id){
        this.id = id;
    }
}
