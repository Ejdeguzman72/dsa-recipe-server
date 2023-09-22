package com.dsa.recipeserver.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Arrays;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@JsonIgnoreProperties(ignoreUnknown = true)
@CrossOrigin
public class Recipe {

    long recipeId;
    String name;
    String[] ingredients;
    String[] directions;
    int recipeTypeId;

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

    public int getRecipeTypeId() {
        return recipeTypeId;
    }

    public void setRecipeTypeId(int recipeTypeId) {
        this.recipeTypeId = recipeTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return recipeId == recipe.recipeId && recipeTypeId == recipe.recipeTypeId && Objects.equals(name, recipe.name) && Arrays.equals(ingredients, recipe.ingredients) && Arrays.equals(directions, recipe.directions);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(recipeId, name, recipeTypeId);
        result = 31 * result + Arrays.hashCode(ingredients);
        result = 31 * result + Arrays.hashCode(directions);
        return result;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "recipeId=" + recipeId +
                ", name='" + name + '\'' +
                ", ingredients=" + Arrays.toString(ingredients) +
                ", directions=" + Arrays.toString(directions) +
                ", recipeTypeId=" + recipeTypeId +
                '}';
    }

    public Recipe() {
    }

    public Recipe(String name, String[] ingredients, String[] directions, int recipeTypeId) {
        this.name = name;
        this.ingredients = ingredients;
        this.directions = directions;
        this.recipeTypeId = recipeTypeId;
    }
}
