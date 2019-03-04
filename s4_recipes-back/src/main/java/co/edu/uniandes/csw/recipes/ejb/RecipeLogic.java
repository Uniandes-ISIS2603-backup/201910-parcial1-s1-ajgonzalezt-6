/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.recipes.ejb;

import co.edu.uniandes.csw.recipes.entities.RecipeEntity;
import co.edu.uniandes.csw.recipes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.recipes.persistence.RecipePersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author CesarF
 */
@Stateless
public class RecipeLogic {

    @Inject
    private RecipePersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    public RecipeEntity getRecipe(Long id) {
        return persistence.find(id);
    }

    public RecipeEntity createRecipe(RecipeEntity recipCrea) throws BusinessLogicException {
        if (recipCrea.getName() == null || recipCrea.getName().equals("") || recipCrea.getName().length() > 30) {
            throw new BusinessLogicException("el nombre no es valido ");
        }
        if (recipCrea.getDescription() == null || recipCrea.getDescription().equals("") || recipCrea.getDescription().length() > 150) {
            throw new BusinessLogicException("La descripcion no es valida ");
        }
        if (persistence.findByName(recipCrea.getName()) != null) {
            throw new BusinessLogicException("El nombre de receta ya existe");
        }

        if (recipCrea.getIngredientes() == null || recipCrea.getIngredientes().isEmpty()) {
            throw new BusinessLogicException("La receta no tiene ningun ingrediente");
        }

        return persistence.createRecipe(recipCrea);

    }
    //TODO crear el método createRecipe

}
