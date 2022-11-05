import { Component, OnInit } from '@angular/core';
import { Recipe } from 'src/app/model/Recipe';
import { RecipeService } from 'src/app/service/recipe/recipe.service';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  page: number = 1;
  count: number = 0;
  listSize: number = 7;
  listsSizes: any = [3, 6, 9, 12];
  recipes : Recipe[] = [];
  nb : any;
  par :any;

  constructor(private recipeService: RecipeService, private route:ActivatedRoute) { }
   
  ngOnInit(): void {
    this.recipeService.searchRecipe(this.route.snapshot.paramMap.get('recipe_name')).subscribe(data => { 
      this.recipes = JSON.parse(data);
      this.nb = Object.keys(this.recipes).length;
      this.par = this.route.snapshot.paramMap.get('recipe_name');
      console.log(this.recipes);

   //  console.log(this.recipes['results']['bindings'][1]['subject'].value.split("#", 2));
    });
  }

  onListDataChange(event: any) {
    this.page = event;
    //this.getShipments();
  }
  onListSizeChange(event: any): void {
    this.listSize = event.target.value;
    this.page = 1;
   // this.getShipments();
  }

}
