package com.recipe.mgnt.controller;

import com.recipe.mgnt.dto.RecipeDTO;
import com.recipe.mgnt.services.RecipeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

	@Autowired
	private RecipeService recipeService;

	@GetMapping("/getAllRecipes")
	public ResponseEntity<List<RecipeDTO>> getAllRecipes() {
		return ResponseEntity.ok(recipeService.getAllRecipes());
	}

	@GetMapping("/getRecipeById/{id}")
	public ResponseEntity<RecipeDTO> getRecipeById(@PathVariable Long id) {
		RecipeDTO recipeDTO = recipeService.getRecipeById(id);
		return recipeDTO != null ? ResponseEntity.ok(recipeDTO) : ResponseEntity.notFound().build();
	}

	@PostMapping("/saveRecipe")
	public ResponseEntity<RecipeDTO> createRecipe(@Valid @RequestBody RecipeDTO recipeDTO) {
		RecipeDTO createdRecipe = recipeService.createRecipe(recipeDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdRecipe);
	}

	@PutMapping("/updateRecipe/{id}")
	public ResponseEntity<RecipeDTO> updateRecipe(@PathVariable Long id, @Valid @RequestBody RecipeDTO recipeDTO) {
		RecipeDTO updatedRecipe = recipeService.updateRecipe(id, recipeDTO);
		return updatedRecipe != null ? ResponseEntity.ok(updatedRecipe) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/deleteRecipeById/{id}")
	public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
		recipeService.deleteRecipe(id);
		return ResponseEntity.noContent().build();
	}
}

