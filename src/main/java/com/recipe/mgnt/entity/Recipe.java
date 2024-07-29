package com.recipe.mgnt.entity;

import javax.persistence.*;
import lombok.Data;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data
@Entity
@Table(name ="tbl_recipe_dtls")
public class Recipe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private LocalDateTime creationDate;
	private boolean vegetarian;
	private int suitableFor;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Ingredient> ingredients = new ArrayList<>();
	private String instructions;

}

