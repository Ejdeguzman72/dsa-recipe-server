package com.dsa.recipeserver.jpa;

import jakarta.persistence.*;
import org.springframework.web.bind.annotation.CrossOrigin;

@Entity
@Table(name = "recipe")
@CrossOrigin
public class RecipeJpa {

    long recipeId;
    String name;
    String[] ingredients;
    String[] directions;
    RecipeTypeJpa recipeType;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    public long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(long recipeId) {
        this.recipeId = recipeId;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "ingredients", columnDefinition = "text", length = 255)
    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    @Column(name = "directions", columnDefinition = "text", length = 255)
    public String[] getDirections() {
        return directions;
    }

    public void setDirections(String[] directions) {
        this.directions = directions;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_type_id")
    public RecipeTypeJpa getRecipeType() {
        return recipeType;
    }

    public void setRecipeType(RecipeTypeJpa recipeType) {
        this.recipeType = recipeType;
    }
}
