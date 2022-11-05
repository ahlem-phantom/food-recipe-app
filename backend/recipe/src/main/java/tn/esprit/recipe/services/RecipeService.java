package tn.esprit.recipe.services;

import net.minidev.json.JSONObject;
import tn.esprit.recipe.tools.JenaEngine;
import java.util.ArrayList;
import java.util.List;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;

public class RecipeService {
    
    public static List<JSONObject> getRecipe(String queryString) {
        ResultSet resultSet = JenaEngine.execQuery(queryString);
        List<JSONObject> list = new ArrayList<>();
        int x=0;
        while (resultSet.hasNext()) {
            x++;
            JSONObject obj = new JSONObject();
            QuerySolution solution = resultSet.nextSolution();
            obj.put("id",x);
            obj.put("recipe_name",solution.get("name").toString().split("#")[0]);
            obj.put("cook_time",solution.get("cookTime").toString().split("#")[0]);
            obj.put("prep_time",solution.get("prepTime").toString().split("#")[0]);
            obj.put("url",solution.get("url").toString().split("#")[0]);
            obj.put("totTime",solution.get("totTime").toString().split("#")[0]);
            obj.put("servings",solution.get("servings").toString().split("#")[0]);
            obj.put("rating",solution.get("rating").toString().split("#")[1]);
            obj.put("description",solution.get("description").toString().split("#")[0]);
            obj.put("author",solution.get("author").toString().split("#")[1]);

            list.add(obj);
        }

        // Important ‑ free up resources used running the query
        //qe.close();
        return list;
    }
    

    public static List<JSONObject> getRecipeIngredients(String queryString) {
        ResultSet resultSet = JenaEngine.execQuery(queryString);
        List<JSONObject> list = new ArrayList<>();
        int x=0;
        while (resultSet.hasNext()) {
            x++;
            JSONObject obj = new JSONObject();
            QuerySolution solution = resultSet.nextSolution();
            obj.put("id",x);
            obj.put("recipe_name",solution.get("recipe").toString().split("#")[1]);
            for(int i=1;i<solution.get("ingredients").toString().split(",").length;i++){
                String s=solution.get("ingredients").toString().split("#")[i];
                System.out.println("*****"+s.replaceAll(", http://www.semanticweb.org/ahlem/ontologies/2022/10/recipe", ""));
              obj.put("ingredient"+i,s.replaceAll(", http://www.semanticweb.org/ahlem/ontologies/2022/10/recipe", ""));
            }

            list.add(obj);
        }

        // Important ‑ free up resources used running the query
        //qe.close();
        return list;

    }


    public static List<JSONObject> getRecipeSteps(String queryString) {
        ResultSet resultSet = JenaEngine.execQuery(queryString);
        List<JSONObject> list = new ArrayList<>();
        int x=0;
        while (resultSet.hasNext()) {
            x++;
            JSONObject obj = new JSONObject();
            QuerySolution solution = resultSet.nextSolution();
            obj.put("id",x);
            obj.put("recipe_name",solution.get("recipe").toString().split("#")[1]);
            for(int i=1;i<solution.get("steps").toString().split(",").length+1;i++){
                String s=solution.get("steps").toString().split("#")[i];
                System.out.println("*****"+s.replaceAll(", http://www.semanticweb.org/ahlem/ontologies/2022/10/recipe", ""));
              obj.put("step"+i,s.replaceAll(", http://www.semanticweb.org/ahlem/ontologies/2022/10/recipe", ""));
            }

            list.add(obj);
        }

        // Important ‑ free up resources used running the query
        //qe.close();
        return list;

    }

    public static List<JSONObject> searchRecipe(String queryString) {
        ResultSet resultSet = JenaEngine.execQuery(queryString);
        List<JSONObject> list = new ArrayList<>();
        int x=0;
        while (resultSet.hasNext()) {
            x++;
            JSONObject obj = new JSONObject();
            QuerySolution solution = resultSet.nextSolution();
            obj.put("id",x);
            obj.put("recipe_name",solution.get("name").toString().split("#")[0]);
            obj.put("cook_time",solution.get("cookTime").toString().split("#")[0]);
            obj.put("prep_time",solution.get("prepTime").toString().split("#")[0]);
            obj.put("url",solution.get("url").toString().split("#")[0]);
            obj.put("totTime",solution.get("totTime").toString().split("#")[0]);
            obj.put("servings",solution.get("servings").toString().split("#")[0]);
            obj.put("description",solution.get("description").toString().split("#")[0]);
            obj.put("rating",solution.get("rating").toString().split("#")[1]);
            obj.put("author",solution.get("author").toString().split("#")[1]);
            list.add(obj);
        }

        // Important ‑ free up resources used running the query
        //qe.close();
        return list;
    }
    
    
    public static List<JSONObject> getMostRatedRecipes(String queryString) {
        ResultSet resultSet = JenaEngine.execQuery(queryString);
        List<JSONObject> list = new ArrayList<>();
        int x=0;
        while (resultSet.hasNext()) {
            x++;
            JSONObject obj = new JSONObject();
            QuerySolution solution = resultSet.nextSolution();
            obj.put("id",x);
            obj.put("recipe_name",solution.get("name").toString().split("#")[0]);
            obj.put("url",solution.get("url").toString().split("#")[0]);
            obj.put("rating",solution.get("rating").toString().split("#")[1]);
            obj.put("author",solution.get("author").toString().split("#")[1]);

            list.add(obj);
        }

        // Important ‑ free up resources used running the query
        //qe.close();
        return list;
    }


    public static List<JSONObject> getDinnerRecipes(String queryString) {
        ResultSet resultSet = JenaEngine.execQuery(queryString);
        List<JSONObject> list = new ArrayList<>();
        int x=0;
        while (resultSet.hasNext()) {
            x++;
            JSONObject obj = new JSONObject();
            QuerySolution solution = resultSet.nextSolution();
            obj.put("id",x);
            obj.put("recipe_name",solution.get("name").toString().split("#")[0]);
            obj.put("url",solution.get("url").toString().split("#")[0]);
            obj.put("rating",solution.get("rating").toString().split("#")[1]);
            obj.put("author",solution.get("author").toString().split("#")[1]);

            list.add(obj);
        }

        // Important ‑ free up resources used running the query
        //qe.close();
        return list;
    }


    public static List<JSONObject> getLunchRecipes(String queryString) {
        ResultSet resultSet = JenaEngine.execQuery(queryString);
        List<JSONObject> list = new ArrayList<>();
        int x=0;
        while (resultSet.hasNext()) {
            x++;
            JSONObject obj = new JSONObject();
            QuerySolution solution = resultSet.nextSolution();
            obj.put("id",x);
            obj.put("recipe_name",solution.get("name").toString().split("#")[0]);
            obj.put("url",solution.get("url").toString().split("#")[0]);
            obj.put("rating",solution.get("rating").toString().split("#")[1]);
            obj.put("author",solution.get("author").toString().split("#")[1]);

            list.add(obj);
        }

        // Important ‑ free up resources used running the query
        //qe.close();
        return list;
    }

    public static List<JSONObject> getRecipeByStep(String queryString) {
        ResultSet resultSet = JenaEngine.execQuery(queryString);
        List<JSONObject> list = new ArrayList<>();
        int x=0;
        while (resultSet.hasNext()) {
            x++;
            JSONObject obj = new JSONObject();
            QuerySolution solution = resultSet.nextSolution();
            obj.put("id",x);
            for(int i=1;i<solution.get("steps").toString().split(",").length+1;i++){
                String s=solution.get("steps").toString().split("#")[i];
                System.out.println("*****"+s.replaceAll(", http://www.semanticweb.org/ahlem/ontologies/2022/10/recipe", ""));
              obj.put("step"+i,s.replaceAll(", http://www.semanticweb.org/ahlem/ontologies/2022/10/recipe", ""));
            }

            list.add(obj);
        }

        // Important ‑ free up resources used running the query
        //qe.close();
        return list;
    }


    public static List<JSONObject> getRecipeByIngredient(String queryString) {
        ResultSet resultSet = JenaEngine.execQuery(queryString);
        List<JSONObject> list = new ArrayList<>();
        int x=0;
        while (resultSet.hasNext()) {
            x++;
            JSONObject obj = new JSONObject();
            QuerySolution solution = resultSet.nextSolution();
            obj.put("id",x);
            for(int i=1;i<solution.get("ingredients").toString().split(",").length+1;i++){
                String s=solution.get("ingredients").toString().split("#")[i];
                System.out.println("*****"+s.replaceAll(", http://www.semanticweb.org/ahlem/ontologies/2022/10/recipe", ""));
              obj.put("ingredient"+i,s.replaceAll(", http://www.semanticweb.org/ahlem/ontologies/2022/10/recipe", ""));
            }

            list.add(obj);
        }

        // Important ‑ free up resources used running the query
        //qe.close();
        return list;
    }
}
