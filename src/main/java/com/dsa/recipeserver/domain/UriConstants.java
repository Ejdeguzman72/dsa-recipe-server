package com.dsa.recipeserver.domain;

public class UriConstants {

    public static final String GET_ALL_RECIPE_INFO_URI = "/app/recipes/all";
    public static final String GET_RECIPES_BY_TYPE_URI = "/app/recipes/all/types/{recipeTypeId}";
    public static final String GET_RECIPE_BY_NAME_URI = "/app/recipes/search/name/{name}";
    public static final String GET_RECIPE_BY_ID_URI = "/app/recipes/search/recipe/id/{recipeId}";
    public static final String ADD_RECIPE_INFORMATION_URI = "/app/recipes/add";
    public static final String UPDATE_RECIPE_INFORMATON_URI = "/app/recipes/update/{recipeId}";
    public static final String DELETE_RECIPE_INFORMATION_URI = "/app/recipes/delete/{recipeId}";
}
