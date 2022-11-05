import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { Recipe } from 'src/app/model/Recipe';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  private url = 'http://localhost:8080';  // URL to REST API

  constructor(private http: HttpClient) { }

  /** GET users from the server */
  getRecipes(){
    return this.http.get(this.url+'/getRecipes').pipe(map(response => JSON.stringify(response)));  
  }
  

    searchRecipe(search :any){
      console.log(this.url+`searchRecipe?recipe_name="${search}"`);
      return this.http.get(this.url+`/searchRecipe?recipe_name="${search}"`).pipe(map(response => JSON.stringify(response)));  
    }

    /** Most Rated recipes from the server */
   getMostRatedRecipes(){
    return this.http.get(this.url+'/most-rated-recipes').pipe(map(response => JSON.stringify(response)));  
 
    }
  
    getDinnerRecipes(){
      return this.http.get(this.url+'/get-dinner-recipes').pipe(map(response => JSON.stringify(response)));  
   
      }


      getLunchRecipes(){
        return this.http.get(this.url+'/get-lunch-recipes').pipe(map(response => JSON.stringify(response)));  
     
        }


        getRecipeSteps(search :any){
          console.log(this.url+`get-recipes-steps?recipe_name="${search}"`);
          return this.http.get(this.url+`/get-recipes-steps?recipe_name="${search}"`).pipe(map(response => JSON.stringify(response)));  
        }

        getRecipeIngredients(search :any){
          console.log(this.url+`get-recipes-steps?recipe_name="${search}"`);
          return this.http.get(this.url+`/get-recipes-ingredients?recipe_name="${search}"`).pipe(map(response => JSON.stringify(response)));  
        }
}