package com.dsa.recipeserver.dao;

import com.dsa.recipeserver.dto.RecipeDTO;
import com.dsa.recipeserver.model.Recipe;
import com.dsa.recipeserver.model.RecipeType;

import java.util.List;

public interface RecipeDao {

    List<RecipeDTO> getAllRecipes();
    List<RecipeDTO> getRecipesByType(int recipeTypeId);
    RecipeDTO retrieveRecipeByName(String name);
    Recipe retrieveRecipeById(long recipeId);
    int addRecipeInformation(Recipe recipe);
    int updateRecipeInformation(long recipeId, Recipe recipeDetails);
    int deleteRecipeInformation(long recipeId);
    public List<RecipeType> getAllRecipeTypes();
}
