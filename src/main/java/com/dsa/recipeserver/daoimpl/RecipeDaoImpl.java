package com.dsa.recipeserver.daoimpl;

import com.dsa.recipeserver.dao.RecipeDao;
import com.dsa.recipeserver.dto.RecipeDTO;
import com.dsa.recipeserver.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class RecipeDaoImpl implements RecipeDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String GET_ALL_RECIPES_SQL = "SELECT RECIPE_ID, NAME, INGREDIENTS, DIRECTIONS, RECIPE_TYPE_DESCR " +
            "FROM RECIPE R INNER JOIN RECIPE_TYPE RT WHERE R.RECIPE_ID = RT.RECIPE_ID";
    private static final String GET_RECIPES_BY_TYPE_SQL = "SELECT RECIPE_ID, NAME, INGREDIENTS, DIRECTIONS, RECIPE_TYPE_DESCR " +
            "FROM RECIPE R INNER JOIN RECIPE_TYPE RT WHERE R.RECIPE_ID = RT.RECIPE_ID " +
            "WHERE RECIPE_TYPE_ID = ?";
    private static final String GET_RECIPE_BY_NAME_SQL = "SELECT RECIPE_ID, NAME, INGREDIENTS, DIRECTIONS, RECIPE_TYPE_DESCR " +
            "FROM RECIPE R INNER JOIN RECIPE_TYPE RT WHERE R.RECIPE_ID = RT.RECIPE_ID " +
            "WHERE NAME = ?";
    private static final String GET_RECIPE_BY_ID_SQL = "SELECT RECIPE_ID, NAME, INGREDIENTS, DIRECTIONS, RECIPE_TYPE_ID " +
            "FROM RECIPE R";
    private static final String ADD_RECIPE_INFO_SQL = "INSERT INTO RECIPE (NAME, INGREDIENTS, DIRECTIONS, RECIPE_TYPE_ID) " +
            "VALUES (?,?,?,?)";
    private static final String UPDATE_RECIPE_INFO_SQL = "UPDATE RECIPE SET NAME = ?, INGREDIENTS = ?, DIRECTIONS = ?, RECIPE_TYPE_ID = ? " +
            "WHERE RECIPE_ID = ?";
    private static final String DELETE_RECIPE_INFO_SQL = "DELETE FROM RECIPE WHERE RECIPE_ID = ?";

    @Override
    public List<RecipeDTO> getAllRecipes() {
        List<RecipeDTO> list = new ArrayList<>();
        list = jdbcTemplate.query(GET_ALL_RECIPES_SQL, BeanPropertyRowMapper.newInstance(RecipeDTO.class));

        return list;
    }

    @Override
    public List<RecipeDTO> getRecipesByType(int recipeTypeId) {
        List<RecipeDTO> list = new ArrayList<>();
        list = jdbcTemplate.query(GET_RECIPES_BY_TYPE_SQL, ((rs, rowNum) -> new RecipeDTO(
                rs.getLong("RECIPE_ID"),
                rs.getString("NAME"),
                rs.getString("INGREDIENTS"),
                rs.getString("DIRECTIONS"),
                rs.getString("DESCR")
        )), recipeTypeId);

        return list;
    }

    @Override
    public RecipeDTO retrieveRecipeByName(String name) {
        RecipeDTO recipe = new RecipeDTO();
        recipe = jdbcTemplate.queryForObject(GET_RECIPE_BY_NAME_SQL, BeanPropertyRowMapper.newInstance(RecipeDTO.class), name);

        return recipe;
    }

    @Override
    public Recipe retrieveRecipeById(long recipeId) {
        Recipe recipe = new Recipe();
        recipe = jdbcTemplate.queryForObject(GET_RECIPE_BY_ID_SQL, BeanPropertyRowMapper.newInstance(Recipe.class), recipeId);

        return recipe;
    }

    @Override
    public int addRecipeInformation(Recipe recipe) {
        int result = 0;
        String name = recipe.getName();
        String ingredients = Arrays.toString(recipe.getIngredients());
        String directions = Arrays.toString(recipe.getDirections());
        int recipeTypeId = recipe.getRecipeTypeId();

        result = jdbcTemplate.update(ADD_RECIPE_INFO_SQL, new Object[] {
                name,ingredients,directions,recipeTypeId
        });

        return result;
    }

    @Override
    public int updateRecipeInformation(long recipeId, Recipe recipeDetails) {
        int result = 0;
        Recipe recipe = retrieveRecipeById(recipeId);

        if (recipe != null) {
            recipe.setName(recipeDetails.getName());
            recipe.setIngredients(recipeDetails.getIngredients());
            recipe.setDirections(recipeDetails.getDirections());
            recipe.setRecipeTypeId(recipeDetails.getRecipeTypeId());

            result = jdbcTemplate.update(UPDATE_RECIPE_INFO_SQL, new Object[] {
                recipe.getRecipeId(),recipe.getName(),recipe.getIngredients(),recipe.getDirections(),recipe.getRecipeTypeId()
            });
        }

        return result;
    }

    @Override
    public int deleteRecipeInformation(long recipeId) {
        int result = 0;
        if (recipeId > 0) {
            result = jdbcTemplate.update(DELETE_RECIPE_INFO_SQL, recipeId);
        }

        return result;
    }
}
