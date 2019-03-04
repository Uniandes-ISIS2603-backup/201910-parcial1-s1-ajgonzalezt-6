/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.recipes.dtos;

import co.edu.uniandes.csw.recipes.entities.IngredientEntity;
import co.edu.uniandes.csw.recipes.entities.RecipeEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CesarF
 */
public class RecipeDetailDTO extends RecipeDTO {
    
    private List<IngredientDTO> ingredientes = new ArrayList<IngredientDTO>();

    
    public RecipeDetailDTO(){
    
    }
    
    public RecipeDetailDTO(RecipeEntity entity){
        super(entity);
        for (IngredientEntity ingrediente : entity.getIngredientes()) {
            ingredientes.add(new IngredientDTO(ingrediente));
        }
    }
    
    public RecipeEntity toEntity()
    {
       RecipeEntity receta = super.toEntity();
        for (IngredientDTO ingrediente : ingredientes) {
            receta.getIngredientes().add(ingrediente.toEntity());
        }
        return receta;
    }
    
}
