/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.recipes.test.logic;

import co.edu.uniandes.csw.recipes.ejb.RecipeLogic;
import co.edu.uniandes.csw.recipes.entities.RecipeEntity;
import co.edu.uniandes.csw.recipes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.recipes.persistence.RecipePersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author estudiante
 */
@RunWith(Arquillian.class)
public class RecipeLogicTest {

    private final PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private RecipeLogic recipeLogic;

    /*@Inject
    private UserTransaction utx;
     */
    private List<RecipeEntity> data;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(RecipeEntity.class.getPackage())
                .addPackage(RecipePersistence.class.getPackage())
                .addPackage(RecipeLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Test
    public void createRecipeTest() throws BusinessLogicException {
        RecipeEntity real = factory.manufacturePojo(RecipeEntity.class);
        real.setName("noVacio");
        real.setDescription("noVacia");
        recipeLogic.createRecipe(real);
        Assert.assertNotNull(real);
        Assert.assertEquals(real, recipeLogic.getRecipe(real.getId()));

    }

    @Test
    public void createRecipeTestNombreNull() {
        RecipeEntity real = factory.manufacturePojo(RecipeEntity.class);
        real.setName(null);
        real.setDescription("noVacia");
        try {
            recipeLogic.createRecipe(real);
            fail("no deberia crear");
        } catch (BusinessLogicException ex) {
//no deberia llegar
        }

    }

    @Test
    public void createRecipeTestNombreDoble() {
        RecipeEntity real = factory.manufacturePojo(RecipeEntity.class);
                RecipeEntity segundo = factory.manufacturePojo(RecipeEntity.class);

        real.setName("nombreIgual");
        segundo.setName("nombreIgual");
        real.setDescription("noVacia");
        segundo.setDescription("otraDescripcion");
        try {
            recipeLogic.createRecipe(real);
            recipeLogic.createRecipe(segundo);
            fail("no deberia crear");
        } catch (BusinessLogicException ex) {
//no deberia llegar
        }

    }

    @Test
    public void createRecipeTestDescripcionVacia() {
        RecipeEntity real = factory.manufacturePojo(RecipeEntity.class);
        real.setName("noVacio");
        real.setDescription(null);
        try {
            recipeLogic.createRecipe(real);
            fail("no deberia crear");
        } catch (BusinessLogicException ex) {
//no deberia llegar
        }

    }
    
    
    /*@Test
    public void createRecipeTestSinIngredient() {
        RecipeEntity real = factory.manufacturePojo(RecipeEntity.class);
        real.setName("noVacio");
        real.setDescription("noVacia");
        real.getIngredientes().removeAll(real.getIngredientes());
        try {
            recipeLogic.createRecipe(real);
            fail("no deberia crear");
        } catch (BusinessLogicException ex) {
//no deberia llegar
        }

    }
*/
}
