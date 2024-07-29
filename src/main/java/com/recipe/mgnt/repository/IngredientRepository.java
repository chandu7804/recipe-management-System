package com.recipe.mgnt.repository;

import com.recipe.mgnt.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}

