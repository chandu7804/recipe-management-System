package com.recipe.mgnt.dto;

import lombok.Data;

import java.util.List;

@Data
	public class RecipeDTO {
		private Long id;
		private String name;
		private String creationDate;
		private boolean vegetarian;
		private int suitableFor;
		private List<IngredientDTO> ingredients;
		private String instructions;

		// Getters and Setters
	}
