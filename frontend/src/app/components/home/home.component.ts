import { Component, OnInit } from '@angular/core';
import { RecipeService } from 'src/app/service/recipe/recipe.service';
import { Router } from '@angular/router';
import { Recipe } from 'src/app/model/Recipe';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  recipes : any = [];
  mostRated : Recipe [] = [];
  dinnerRecipes : Recipe [] = [];
  lunchRecipes : Recipe [] = [];

  constructor(private recipeService: RecipeService, private router : Router) { }

  ngOnInit(): void {
    this.recipeService.getRecipes().subscribe(data => { 
      this.recipes = JSON.parse(data);
      console.log(this.recipes);
        
   //  console.log(this.recipes['results']['bindings'][1]['subject'].value.split("#", 2));
    });

    this.recipeService.getMostRatedRecipes().subscribe(data => { 
      this.mostRated = JSON.parse(data);
      console.log(this.mostRated);
      console.log("**************"+Object.keys(this.mostRated).length);

        
   //  console.log(this.recipes['results']['bindings'][1]['subject'].value.split("#", 2));
    });
    
    this.recipeService.getDinnerRecipes().subscribe(data => { 
      this.dinnerRecipes = JSON.parse(data);
      console.log(this.dinnerRecipes);
        
   //  console.log(this.recipes['results']['bindings'][1]['subject'].value.split("#", 2));
    });

    this.recipeService.getLunchRecipes().subscribe(data => { 
      this.lunchRecipes = JSON.parse(data);
      console.log(this.lunchRecipes);
      
        
   //  console.log(this.recipes['results']['bindings'][1]['subject'].value.split("#", 2));
    });
    
  }

  onSubmit(event: any) {
    console.log(event.target.search.value);
    this.router.navigate(["/recipes-search/"+event.target.search.value]);

 }

}
