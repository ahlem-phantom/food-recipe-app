import { Component, OnInit } from '@angular/core';
import { RecipeService } from 'src/app/service/recipe/recipe.service';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { Recipe } from 'src/app/model/Recipe';

@Component({
  selector: 'app-recipe',
  templateUrl: './recipe.component.html',
  styleUrls: ['./recipe.component.css']
})
export class RecipeComponent implements OnInit {
  r : Recipe;
  steps : any = [];
  ingredients : any = [];
  par :any;
  nb_i :any;
  numbers: any= [];
  constructor( private recipeService: RecipeService, private route:ActivatedRoute) { }

  ngOnInit(): void {
    this.recipeService.searchRecipe(this.route.snapshot.paramMap.get('recipe_name')).subscribe(data => { 
      this.r = JSON.parse(data)[0];
      this.par = this.route.snapshot.paramMap.get('recipe_name');
      console.log(JSON.parse(data)[0]);
   //  console.log(this.recipes['results']['bindings'][1]['subject'].value.split("#", 2));
    });


    this.recipeService.getRecipeSteps(this.route.snapshot.paramMap.get('recipe_name')).subscribe(data => { 
      this.steps = JSON.parse(data)[0];
      this.par = this.route.snapshot.paramMap.get('recipe_name');
      console.log(this.steps);
   //  console.log(this.recipes['results']['bindings'][1]['subject'].value.split("#", 2));
    });



    this.recipeService.getRecipeIngredients(this.route.snapshot.paramMap.get('recipe_name')).subscribe(data => { 
      this.ingredients = JSON.parse(data)[0];
      this.nb_i = Object.keys(this.ingredients).length;
      this.numbers =Array.from({length:this.nb_i},(v,k)=>k+1)
      this.par = this.route.snapshot.paramMap.get('recipe_name');
      console.log(this.numbers);
   //  console.log(this.recipes['results']['bindings'][1]['subject'].value.split("#", 2));
    });
  }

}
