package com.degea9.android.foodrecipe.common.util

import com.degea9.android.foodrecipe.domain.model.Recipe

const val BASE_RECIPE_IMAGE_URL = "https://spoonacular.com/recipeImages/"
const val RECIPE_IMAGE_SIZE = "480x360"

fun Recipe.getRecipeImage() = BASE_RECIPE_IMAGE_URL+ this.id + "-" + RECIPE_IMAGE_SIZE + "." + this.imageType