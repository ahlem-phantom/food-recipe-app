import { NgModule } from '@angular/core';
import { CommonModule, } from '@angular/common';
import { BrowserModule  } from '@angular/platform-browser';
import { Routes, RouterModule, CanActivate } from '@angular/router';
import { RecipeComponent } from './components/recipe/recipe.component';
import { HomeComponent } from './components/home/home.component';
import { SearchComponent } from './components/search/search.component';
const routes: Routes =[

  {
    path: '',
    component: HomeComponent
  },
  {
    path: 'recipes-search/:recipe_name',
    component: SearchComponent
  },
  {
    path: 'recipes/:recipe_name',
    component: RecipeComponent
  },
];

@NgModule({
  imports: [
    CommonModule,
    BrowserModule,
    RouterModule.forRoot(routes)
  ],
  exports: [
  ],
})
export class AppRoutingModule { }
