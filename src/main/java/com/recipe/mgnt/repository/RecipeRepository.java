package com.recipe.mgnt.repository;

import com.recipe.mgnt.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
