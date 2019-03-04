/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.recipes.persistence;

import co.edu.uniandes.csw.recipes.entities.RecipeEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author CesarF
 */

@Stateless
public class RecipePersistence {
    private static final Logger LOGGER = Logger.getLogger(RecipePersistence.class.getName());
    
    @PersistenceContext(unitName = "recipesPU")
    protected EntityManager em;
    
  
    /*public List<RecipeEntity> find(){
        return em.find(RecipeEntity.class);
    }*/
    
    
    public RecipeEntity findByName(String name) {
        // Se crea un query para buscar editoriales con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From RecipeEntity e where e.name = :name", RecipeEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("name", name);
        // Se invoca el query se obtiene la lista resultado
        List<RecipeEntity> sameName = query.getResultList();
        RecipeEntity result;
        if (sameName == null) {
            result = null;
        } else if (sameName.isEmpty()) {
            result = null;
        } else {
            result = sameName.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar editorial por nombre ", name);
        return result;
    }
    
    public RecipeEntity find(Long id) {
        return em.find(RecipeEntity.class, id);
    }
    
    //TODO método crear de recipe
    public RecipeEntity createRecipe(RecipeEntity recetaSave)
    {
        em.persist(recetaSave);
         return  recetaSave;
    }
    
    public RecipeEntity createRecipeSinIng(RecipeEntity recetaSave)
    {
        
        em.persist(recetaSave);
         return  recetaSave;
    }
}
