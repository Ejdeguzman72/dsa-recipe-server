package com.dsa.recipeserver.service;

import com.dsa.recipeserver.daoimpl.RecipeDaoImpl;
import com.dsa.recipeserver.domain.RecipeDTOSearchResponse;
import com.dsa.recipeserver.domain.RecipeListResponse;
import com.dsa.recipeserver.domain.RecipeSearchResponse;
import com.dsa.recipeserver.dto.RecipeDTO;
import com.dsa.recipeserver.model.Recipe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        RecipeDTO recipe = recipeDaoImpl.retrieveRecipeByName(name);
        response.setRecipe(recipe);

        return response;
    }

    public RecipeSearchResponse getRecipeById(long recipeId) {
        RecipeSearchResponse response = new RecipeSearchResponse();
        Recipe recipe = recipeDaoImpl.retrieveRecipeById(recipeId);
        response.setRecipe(recipe);

        return response;
    }
}
