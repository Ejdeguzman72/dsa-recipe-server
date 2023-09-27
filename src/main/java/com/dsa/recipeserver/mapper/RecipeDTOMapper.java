package com.dsa.recipeserver.mapper;

import com.dsa.recipeserver.dto.RecipeDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecipeDTOMapper implements RowMapper<RecipeDTO> {
    @Override
    public RecipeDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        RecipeDTO recipe = new RecipeDTO();
        recipe.setRecipeId(rs.getLong("RECIPE_ID"));
        recipe.setName(rs.getString("NAME"));

        Array ingredientsObjectArray = rs.getArray("INGREDIENTS");
        String[] ingredients = (String[]) ingredientsObjectArray.getArray();
        recipe.setIngredients(ingredients);

        Array directionsObjectArray = rs.getArray("DIRECTIONS");
        String[] directions = (String[]) directionsObjectArray.getArray();
        recipe.setDirections(directions);

        recipe.setDescr(rs.getString("DESCR"));
        return recipe;
    }
}
