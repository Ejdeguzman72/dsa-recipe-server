package com.dsa.recipeserver.dao;

import com.dsa.recipeserver.jpa.RecipeJpa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeJpaDao extends JpaRepository<RecipeJpa, Long> {

    @Query(value =  "SELECT * FROM RECIPE WHERE NAME = ?1", nativeQuery = true)
    RecipeJpa findRecipeByName(String name);

//    Page<RecipeJpa> findAll(Pageable pageable);
//
//    Page<RecipeJpa> findRecipeContaining(String name, Pageable pageable);
}
