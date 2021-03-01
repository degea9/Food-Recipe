package com.degea9.android.foodrecipe.common.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.degea9.android.foodrecipe.domain.model.Ingredient

const val INGREDIENT_IMAGE_SIZE = "250x250"
const val INGREDIENT_BASE_URL = "https://spoonacular.com/cdn/ingredients_"


fun Ingredient.getIngredientImageUrl() = INGREDIENT_BASE_URL+INGREDIENT_IMAGE_SIZE+"/"+this.image

@BindingAdapter("ingredientAmount")
fun setIngredientAmount(view: TextView, ingredient: Ingredient?){
    if(ingredient?.amount == 1f){
        view.text = "${ingredient?.amount} ${ingredient?.measures?.metric?.unitShort}"
    }
    else view.text = "${ingredient?.amount} ${ingredient?.measures?.metric?.unitLong}"
}