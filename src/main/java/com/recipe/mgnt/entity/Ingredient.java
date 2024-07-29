package com.recipe.mgnt.entity;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name ="tbl_ing_dtls")
public class Ingredient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

}

