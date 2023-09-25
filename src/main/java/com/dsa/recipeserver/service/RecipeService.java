package com.dsa.recipeserver.service;

import com.dsa.recipeserver.daoimpl.RecipeDaoImpl;
import com.dsa.recipeserver.domain.*;
import com.dsa.recipeserver.dto.RecipeDTO;
import com.dsa.recipeserver.model.Recipe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecipeService.class);
    @Autowired
    private RecipeDaoImpl recipeDaoImpl;

    public RecipeListResponse getAllRecipeInfo() {
        RecipeListResponse response = new RecipeListResponse();
        List<RecipeDTO> list = recipeDaoImpl.getAllRecipes();
        response.setList(list);

        return response;
    }

    public RecipeListResponse getRecipesByType(int recipeTypeId) {
        RecipeListResponse response = new RecipeListResponse();
        List<RecipeDTO> list = recipeDaoImpl.getRecipesByType(recipeTypeId);
        response.setList(list);

        return response;
    }

    public RecipeDTOSearchResponse getRecipeByName(String name) {
        RecipeDTOSearchResponse response = new RecipeDTOSearchResponse();
        RecipeDTO recipe = new RecipeDTO();
        try {
            recipe = recipeDaoImpl.retrieveRecipeByName(name);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error("Empty data result set: " + e.toString());
        }

        response.setRecipe(recipe);

        return response;
    }

    public RecipeSearchResponse getRecipeById(long recipeId) {
        RecipeSearchResponse response = new RecipeSearchResponse();
        Recipe recipe = recipeDaoImpl.retrieveRecipeById(recipeId);
        response.setRecipe(recipe);

        return response;
    }

    public RecipeAddUpdateResponse addRecipeInformation(RecipeAddUpdateRequest request) {
        RecipeAddUpdateResponse response = new RecipeAddUpdateResponse();
        Recipe recipe = new Recipe();
        int count = 0;

        count = recipeDaoImpl.addRecipeInformation(request);
        if (count > 0) {
            recipe.setName(request.getName());
            recipe.setIngredients(request.getIngredients());
            recipe.setDirections(request.getDirections());
            recipe.setRecipeTypeId(request.getRecipeTypeId());
        }

        response.setRecipe(recipe);
        return response;
    }

    public RecipeAddUpdateResponse updateRecipeInformation(long recipeId, RecipeAddUpdateRequest request) {
        RecipeAddUpdateResponse response = new RecipeAddUpdateResponse();
        Recipe recipe = new Recipe();
        recipe = recipeDaoImpl.retrieveRecipeById(recipeId);
        int count = 0;

        count = recipeDaoImpl.updateRecipeInformation(request.getRecipeId(), request);
        if (count > 0) {
            recipe.setName(request.getName());
            recipe.setIngredients(request.getIngredients());
            recipe.setDirections(request.getDirections());
            recipe.setRecipeTypeId(request.getRecipeTypeId());
        }

        response.setRecipe(recipe);
        return response;
    }

    public RecipeSearchResponse deleteRecipeInformation(long recipeId) {
        RecipeSearchResponse response = new RecipeSearchResponse();
        Recipe recipe = new Recipe();
        int count = 0;
        recipe = recipeDaoImpl.retrieveRecipeById(recipeId);
        count = recipeDaoImpl.deleteRecipeInformation(recipeId);

        if (count > 0) {
            response.setRecipe(recipe);
        }

        return response;
    }
}
