package com.dsa.recipeserver.controller;

import com.dsa.recipeserver.domain.*;
import com.dsa.recipeserver.dto.RecipeDTO;
import com.dsa.recipeserver.model.Recipe;
import com.dsa.recipeserver.service.RecipeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @ApiOperation(value = "Get All Recipe Information")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Invalid"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @GetMapping(value = UriConstants.GET_ALL_RECIPE_INFO_URI)
    @CrossOrigin(origins = "*", maxAge = 3600)
    public RecipeListResponse getAllRecipeInformation() {
        RecipeListResponse response = recipeService.getAllRecipeInfo();
        return response;
    }

    @ApiOperation(value = "Get Recipes By Type")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Invalid"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @GetMapping(value = UriConstants.GET_RECIPES_BY_TYPE_URI)
    @CrossOrigin(origins = "*", maxAge = 3600)
    public RecipeListResponse getAllRecipesByType(int recipeTypeId) {
        RecipeListResponse response = recipeService.getRecipesByType(recipeTypeId);
        return response;
    }

    @ApiOperation(value = "Get Recipes By Name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Invalid"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @GetMapping(value = UriConstants.GET_RECIPE_BY_NAME_URI)
    @CrossOrigin(origins = "*", maxAge = 3600)
    public RecipeDTOSearchResponse getRecipeByName(String name) {
        RecipeDTOSearchResponse response = recipeService.getRecipeByName(name);
        return response;
    }

    @ApiOperation(value = "Get Recipes By ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Invalid"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @GetMapping(value = UriConstants.GET_RECIPE_BY_ID_URI)
    @CrossOrigin(origins = "*", maxAge = 3600)
    public RecipeSearchResponse getRecipeById(long recipeId) {
        RecipeSearchResponse response = recipeService.getRecipeById(recipeId);
        return response;
    }

    @ApiOperation(value = "Add Recipe Information")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Invalid"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @PostMapping(value = UriConstants.ADD_RECIPE_INFORMATION_URI)
    @CrossOrigin(origins = "*", maxAge = 3600)
    public RecipeAddUpdateResponse addRecipeInformation(RecipeAddUpdateRequest request) {
        RecipeAddUpdateResponse response = recipeService.addRecipeInformation(request);
        return response;
    }

    @ApiOperation(value = "Update Recipe Information")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Invalid"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @PutMapping(value = UriConstants.UPDATE_RECIPE_INFORMATON_URI)
    @CrossOrigin(origins = "*", maxAge = 3600)
    public RecipeAddUpdateResponse updateRecipeInformation(RecipeAddUpdateRequest request) {
        RecipeAddUpdateResponse response = recipeService.updateRecipeInformation(request);
        return response;
    }

    @ApiOperation(value = "Update Recipe Information")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Invalid"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @DeleteMapping(value = UriConstants.DELETE_RECIPE_INFORMATION_URI)
    @CrossOrigin(origins = "*", maxAge = 3600)
    public RecipeSearchResponse deleteRecipeInformation(long recipeId) {
        RecipeSearchResponse response = recipeService.deleteRecipeInformation(recipeId);
        return response;
    }
}
