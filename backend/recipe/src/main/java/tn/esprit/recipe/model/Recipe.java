package tn.esprit.recipe.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
    
    private String recipe_name;
    private String recipe_ingredient;
    private String author;
   
}
