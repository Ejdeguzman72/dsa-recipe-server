package com.dsa.recipeserver.domain;

import com.dsa.recipeserver.dto.RecipeDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@JsonIgnoreProperties(ignoreUnknown = true)
@CrossOrigin
public class RecipeListResponse {

    List<RecipeDTO> list = new ArrayList<>();

    public List<RecipeDTO> getList() {
        return list;
    }

    public void setList(List<RecipeDTO> list) {
        this.list = list;
    }
}
