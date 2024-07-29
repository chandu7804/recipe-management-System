package com.recipe.mgnt.services;

import com.recipe.mgnt.dto.IngredientDTO;
import com.recipe.mgnt.dto.RecipeDTO;
import com.recipe.mgnt.entity.Ingredient;
import com.recipe.mgnt.entity.Recipe;
import com.recipe.mgnt.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecipeService {

	@Autowired
	private RecipeRepository recipeRepository;

	public List<RecipeDTO> getAllRecipes() {
		return recipeRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
	}

	public RecipeDTO getRecipeById(Long id) {
		Optional<Recipe> recipe = recipeRepository.findById(id);
		return recipe.map(this::convertToDto).orElse(null);
	}

	public RecipeDTO createRecipe(RecipeDTO recipeDTO) {
		Recipe recipe = convertToEntity(recipeDTO);
		recipe.setCreationDate(LocalDateTime.now());
		return convertToDto(recipeRepository.save(recipe));
	}

	public RecipeDTO updateRecipe(Long id, RecipeDTO recipeDTO) {

			Optional<Recipe> existingRecipe = recipeRepository.findById(id);
			if (existingRecipe.isPresent()) {
				Recipe recipe = existingRecipe.get();
				recipe.setName(recipeDTO.getName());
				recipe.setVegetarian(recipeDTO.isVegetarian());
				recipe.setSuitableFor(recipeDTO.getSuitableFor());
				recipe.setIngredients(recipeDTO.getIngredients().stream().map(this::convertToEntity).collect(Collectors.toList()));
				recipe.setInstructions(recipeDTO.getInstructions());
				return convertToDto(recipeRepository.save(recipe));
			}
			return null;
	}

	public void deleteRecipe(Long id) {
		recipeRepository.deleteById(id);
	}

	private RecipeDTO convertToDto(Recipe recipe) {
		RecipeDTO recipeDTO = new RecipeDTO();
		recipeDTO.setId(recipe.getId());
		recipeDTO.setName(recipe.getName());
		recipeDTO.setCreationDate(recipe.getCreationDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
		recipeDTO.setVegetarian(recipe.isVegetarian());
		recipeDTO.setSuitableFor(recipe.getSuitableFor());
		recipeDTO.setIngredients(recipe.getIngredients().stream().map(this::convertToDto).collect(Collectors.toList()));
		recipeDTO.setInstructions(recipe.getInstructions());
		return recipeDTO;
	}

	private Recipe convertToEntity(RecipeDTO recipeDTO) {
		Recipe recipe = new Recipe();
		recipe.setName(recipeDTO.getName());
		recipe.setVegetarian(recipeDTO.isVegetarian());
		recipe.setSuitableFor(recipeDTO.getSuitableFor());
		recipe.setIngredients(recipeDTO.getIngredients().stream().map(this::convertToEntity).collect(Collectors.toList()));
		recipe.setInstructions(recipeDTO.getInstructions());
		return recipe;
	}

	private IngredientDTO convertToDto(Ingredient ingredient) {
		IngredientDTO ingredientDTO = new IngredientDTO();
		ingredientDTO.setId(ingredient.getId());
		ingredientDTO.setName(ingredient.getName());
		return ingredientDTO;
	}

	private Ingredient convertToEntity(IngredientDTO ingredientDTO) {
		Ingredient ingredient = new Ingredient();
		ingredient.setName(ingredientDTO.getName());
		return ingredient;
	}
}
