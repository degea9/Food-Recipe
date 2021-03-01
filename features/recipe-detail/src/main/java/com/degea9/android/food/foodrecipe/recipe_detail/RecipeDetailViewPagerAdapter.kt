package com.degea9.android.food.foodrecipe.recipe_detail

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

const val INSTRUCTION_PAGE_INDEX = 0
const val INGREDIENT_PAGE_INDEX = 1
const val EQUIPMENT_PAGE_INDEX = 2

class RecipeDetailViewPagerAdapter(private val fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    /**
     * Mapping of the ViewPager page indexes to their respective Fragments
     */
    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        INSTRUCTION_PAGE_INDEX to { RecipeInstructionFragment() },
        INGREDIENT_PAGE_INDEX to { IngredientFragment() },
        EQUIPMENT_PAGE_INDEX to { EquipmentFragment() }
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}