package tn.esprit.recipe.controllers;


import net.minidev.json.JSONObject;
import tn.esprit.recipe.model.Recipe;
import tn.esprit.recipe.services.RecipeService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class RecipeController {

    @CrossOrigin
    @RequestMapping("/getRecipes")
    public List<JSONObject> getRecipes() {

        String queryString =
        "PREFIX recipology: <http://www.semanticweb.org/ahlem/ontologies/2022/10/recipe#>"+
        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+
       "SELECT ?recipe_id ?name ?cookTime ?prepTime ?url ?totTime ?servings ?rating ?description ?author "+
        "WHERE {"+
        "?recipe_id rdf:type recipology:Recipe;"+
        " recipology:name ?name;"+
         "recipology:cookTime ?cookTime;"+
         "recipology:prepTime ?prepTime;"+
         "recipology:url ?url ;"+
         "recipology:totalTime ?totTime ;"+
         "recipology:servings ?servings;"+
         "recipology:hasRating ?rating ;"+
         "recipology:description ?description;"+
         "recipology:authoredBy ?author .}";
       
        List<JSONObject> resultSet = RecipeService.getRecipe(queryString);
        System.out.println(queryString);
        return resultSet;
    }

    

    @CrossOrigin
    @RequestMapping("/getRecipeIngredients")
    public List<JSONObject> getRecipeIngredients() {

        String queryString =
        "PREFIX recipology: <http://www.semanticweb.org/ahlem/ontologies/2022/10/recipe#>"+
        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+
        "SELECT ?recipe (group_concat(?ingredient;separator=', ') as ?ingredients) WHERE{"+
         "?recipe recipology:hasIngredient ?ingredient"+
         "}"+
        "GROUP BY ?recipe";
       
        List<JSONObject> resultSet = RecipeService.getRecipeIngredients(queryString);
        System.out.println(queryString);
        return resultSet;
    }


    @CrossOrigin
    @RequestMapping("/getRecipeSteps")
    public List<JSONObject> getRecipeSteps() {

        String queryString =
        "PREFIX recipology: <http://www.semanticweb.org/ahlem/ontologies/2022/10/recipe#>"+
        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+
        "SELECT ?recipe (group_concat(?step;separator=', ') as ?steps) WHERE{"+
         "?recipe recipology:hasStep ?step"+
         "}"+
        "GROUP BY ?recipe";
       
        List<JSONObject> resultSet = RecipeService.getRecipeSteps(queryString);
        System.out.println(queryString);
        return resultSet;
    }


    @CrossOrigin
    @RequestMapping(value ="/findRecipeby",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<JSONObject> searchRecipe(@RequestBody Recipe recipe) {
        boolean allEmpty = true;
        System.out.println(recipe.getRecipe_name());
        String queryString =
        "PREFIX recipology: <http://www.semanticweb.org/ahlem/ontologies/2022/10/recipe#> "+
        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "+
        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
        "SELECT ?recipe_id ?cookTime ?prepTime ?url ?totTime ?servings ?rating ?name ?author WHERE {";
        if (recipe.getRecipe_name() != null && !recipe.getRecipe_name().equals("")) {
            allEmpty = false;
            queryString += " ?recipe_id rdf:type recipology:Recipe;"+
            " recipology:name '" + recipe.getRecipe_name() + "' ; "+
            "recipology:name ?name;"+
            "recipology:cookTime ?cookTime;"+ 
            "recipology:prepTime ?prepTime;" +
            "recipology:url ?url ;" +
            "recipology:authoredBy ?author ;" +
            "recipology:totalTime ?totTime;" +
            "recipology:servings ?servings;" +
          "recipology:hasRating ?rating . } ";
        }

        if (recipe.getAuthor()!= null && !recipe.getAuthor().equals("")) {
            allEmpty = false;
            queryString += " ?recipe_id rdf:type recipology:Recipe;"+
            "recipology:name ?name;"+
            "recipology:cookTime ?cookTime;"+ 
            "recipology:prepTime ?prepTime;" +
            "recipology:url ?url ;" +
            " recipology:authoredBy '" + recipe.getAuthor() + "' ; "+
            "recipology:authoredBy ?author ;" +
            "recipology:totalTime ?totTime;" +
            "recipology:servings ?servings;" +
          "recipology:hasRating ?rating . } ";
        }
        System.out.println(queryString);
        if (allEmpty) {
            List<JSONObject> emptyArray = new ArrayList<>();
            return emptyArray;
        }
       
         
        List<JSONObject> resultSet = RecipeService.searchRecipe(queryString);
        System.out.println(queryString);
        return resultSet;
    }


    @CrossOrigin
    @RequestMapping("/searchRecipe")
    public List<JSONObject> searchRecipe(@RequestParam String recipe_name) {

        String queryString =
        "PREFIX recipology: <http://www.semanticweb.org/ahlem/ontologies/2022/10/recipe#> "+
        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "+
        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
        "SELECT ?recipe_id ?cookTime ?prepTime ?url ?totTime ?servings ?rating ?name ?author ?description WHERE {" +
          "?recipe_id rdf:type recipology:Recipe;" +
           "recipology:name "+recipe_name+";"+ 
           "recipology:name ?name;"+
          "recipology:cookTime ?cookTime;"+ 
          "recipology:prepTime ?prepTime;" +
          "recipology:url ?url ;" +
          "recipology:authoredBy ?author ;" +
          "recipology:totalTime ?totTime;" +
          "recipology:description ?description;" +
          "recipology:servings ?servings;" +
        "recipology:hasRating ?rating . } ";
       
         
        List<JSONObject> resultSet = RecipeService.searchRecipe(queryString);
        System.out.println(queryString);
        return resultSet;
    }


    @CrossOrigin
    @RequestMapping("/most-rated-recipes")
    public List<JSONObject> getMostRated() {

        String queryString =
        "PREFIX recipology: <http://www.semanticweb.org/ahlem/ontologies/2022/10/recipe#> "+
        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "+
        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "+
        "SELECT ?name ?url ?author ?rating  where {" +
        "?recipe recipology:hasRating ?rating . "+ 
        "?recipe recipology:name ?name . "+
        "?recipe recipology:url ?url . "+
        "?recipe recipology:authoredBy ?author } "+
        "ORDER BY DESC(?rating) LIMIT 4 ";
       
        List<JSONObject> resultSet = RecipeService.getMostRatedRecipes(queryString);
        System.out.println(queryString);
        return resultSet;
    }


    @CrossOrigin
    @RequestMapping("/get-dinner-recipes")
    public List<JSONObject> getDinnerRecipes() {

        String queryString =

        "PREFIX recipology: <http://www.semanticweb.org/ahlem/ontologies/2022/10/recipe#> "+
        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "+
        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "+
        "SELECT ?name ?url ?author ?rating  where { "+
       " ?recipe rdf:type recipology:Dinner . "+
        "?recipe recipology:name ?name . "+
        "?recipe recipology:url ?url ."+
        "?recipe recipology:authoredBy ?author . "+
        "?recipe recipology:hasRating ?rating }"+
        "ORDER BY DESC(?name) LIMIT 3";
       
        List<JSONObject> resultSet = RecipeService.getDinnerRecipes(queryString);
        System.out.println(queryString);
        return resultSet;
    }


    @CrossOrigin
    @RequestMapping("/get-lunch-recipes")
    public List<JSONObject> getLunchRecipes() {

        String queryString =

        "PREFIX recipology: <http://www.semanticweb.org/ahlem/ontologies/2022/10/recipe#> "+
        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "+
        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "+
        "SELECT ?name ?url ?author ?rating  where { "+
       " ?recipe rdf:type recipology:Lunch . "+
        "?recipe recipology:name ?name . "+
        "?recipe recipology:url ?url ."+
        "?recipe recipology:authoredBy ?author . "+
        "?recipe recipology:hasRating ?rating }"+
        "ORDER BY DESC(?name) LIMIT 3";
       
        List<JSONObject> resultSet = RecipeService.getLunchRecipes(queryString);
        System.out.println(queryString);
        return resultSet;
    }


    @CrossOrigin
    @RequestMapping("/get-recipes-steps")
    public List<JSONObject> getRecipeByStep(@RequestParam String recipe_name) {

        String queryString =

        "PREFIX recipology: <http://www.semanticweb.org/ahlem/ontologies/2022/10/recipe#> "+
        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "+
        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "+
         "SELECT ?recipe (group_concat(?step;separator=', ') as ?steps)  { "+
        "?recipe recipology:name "+recipe_name+" ; recipology:hasStep ?step .} GROUP BY ?recipe";
       
        List<JSONObject> resultSet = RecipeService.getRecipeByStep(queryString);
        System.out.println(queryString);
        return resultSet;
    }

    @CrossOrigin
    @RequestMapping("/get-recipes-ingredients")
    public List<JSONObject> getRecipeBIngredient(@RequestParam String recipe_name) {

        String queryString =

        "PREFIX recipology: <http://www.semanticweb.org/ahlem/ontologies/2022/10/recipe#> "+
        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "+
        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "+
         "SELECT ?recipe (group_concat(?ingredient;separator=', ') as ?ingredients)  { "+
        "?recipe recipology:name "+recipe_name+" ; recipology:hasIngredient ?ingredient .} GROUP BY ?recipe";
       
        List<JSONObject> resultSet = RecipeService.getRecipeByIngredient(queryString);
        System.out.println(queryString);
        return resultSet;
    }
}


