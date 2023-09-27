package com.dsa.recipeserver.dto;

import java.sql.Array;
import java.util.List;

public class RecipeDTO {

    long recipeId;
    String name;
    String[] ingredients;
    String[] directions;
    String descr;

    public RecipeDTO(long recipeId, String name, String[] ingredients, String[] directions, String descr) {
        this.recipeId = recipeId;
        this.name = name;
        this.ingredients = ingredients;
        this.directions = directions;
        this.descr = descr;
    }

    public RecipeDTO() {

    }

    public long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(long recipeId) {
        this.recipeId = recipeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public String[] getDirections() {
        return directions;
    }

    public void setDirections(String[] directions) {
        this.directions = directions;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }
}
