package com.dsa.recipeserver.mapper;

import com.dsa.recipeserver.model.Recipe;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecipeMapper implements RowMapper<Recipe> {
    @Override
    public Recipe mapRow(ResultSet rs, int rowNum) throws SQLException {
        Recipe recipe = new Recipe();
        recipe.setRecipeId(rs.getLong("RECIPE_ID"));
        recipe.setName(rs.getString("NAME"));

        Array ingredientsArray = rs.getArray("INGREDIENTS");
        String[] ingredients = (String[]) ingredientsArray.getArray();
        recipe.setIngredients(ingredients);

        Array directionsArray = rs.getArray("DIRECTIONS");
        String[] directions = (String[]) directionsArray.getArray();
        recipe.setDirections(directions);

        recipe.setRecipeTypeId(rs.getInt("RECIPE_TYPE_ID"));

        return recipe;
    }
}
