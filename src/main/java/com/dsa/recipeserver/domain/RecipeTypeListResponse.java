package com.dsa.recipeserver.domain;

import com.dsa.recipeserver.model.RecipeType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@JsonIgnoreProperties(ignoreUnknown = true)
@CrossOrigin
public class RecipeTypeListResponse {
    List<RecipeType> list;

    public List<RecipeType> getList() {
        return list;
    }

    public void setList(List<RecipeType> list) {
        this.list = list;
    }
}
