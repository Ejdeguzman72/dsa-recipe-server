package com.dsa.recipeserver.service;

import com.dsa.recipeserver.dao.RecipeJpaDao;
import com.dsa.recipeserver.daoimpl.RecipeDaoImpl;
import com.dsa.recipeserver.domain.*;
import com.dsa.recipeserver.dto.RecipeDTO;
import com.dsa.recipeserver.jpa.RecipeJpa;
import com.dsa.recipeserver.model.Recipe;
import com.dsa.recipeserver.model.RecipeType;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RecipeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecipeService.class);
    @Autowired
    private RecipeDaoImpl recipeDaoImpl;
    @Autowired
    private RecipeJpaDao recipeJpaDao;

    public RecipeListResponse getAllRecipeInfo() {
        RecipeListResponse response = new RecipeListResponse();
        List<RecipeDTO> list = recipeDaoImpl.getAllRecipes();
        response.setList(list);

        return response;
    }

    public ResponseEntity<Map<String, Object>> getAllRecipesPagination(
            @RequestParam(required = false) String name, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        try {
            List<RecipeJpa> recipes = recipeJpaDao.findAll();
            Pageable paging = PageRequest.of(page,size);
            Page<RecipeJpa> pageRecipe = null;

            if (name == null) {
                pageRecipe = recipeJpaDao.findAll(paging);
            } else {
                // pageRecipe = recipeJpaDao.findRecipeContaining(name, paging);
            }

            recipes = pageRecipe.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("recipes", recipes);
            response.put("currentPage", pageRecipe.getNumber());
            response.put("totalItems",pageRecipe.getTotalElements());
            response.put("totalPages", pageRecipe.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Error mapping data to pages: " + e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
        Recipe recipe = new Recipe();
        try {
            recipe = recipeDaoImpl.retrieveRecipeById(recipeId);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error("Empty data set: " + e.toString());
        }

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

        count = recipeDaoImpl.updateRecipeInformation(recipeId, request);
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

        try {
            recipe = recipeDaoImpl.retrieveRecipeById(recipeId);
            count = recipeDaoImpl.deleteRecipeInformation(recipeId);

            if (count > 0) {
                response.setRecipe(recipe);
            }
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error("Empty data set: " + e.toString());
        }

        return response;
    }

    public RecipeTypeListResponse getAllRecipeTypes() {
        RecipeTypeListResponse response = new RecipeTypeListResponse();
        List<RecipeType> list = recipeDaoImpl.getAllRecipeTypes();
        response.setList(list);

        return response;
    }
}
