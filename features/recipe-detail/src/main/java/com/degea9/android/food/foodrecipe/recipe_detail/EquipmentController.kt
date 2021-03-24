package com.degea9.android.food.foodrecipe.recipe_detail

import com.airbnb.epoxy.TypedEpoxyController
import com.degea9.android.foodrecipe.domain.model.Equipment
import com.degea9.android.foodrecipe.domain.model.Instruction

class EquipmentController: TypedEpoxyController<Instruction>() {
    override fun buildModels(data: Instruction?) {
        val map = HashMap<Int?, Equipment>()
        data?.steps?.map { step ->
            step.equipment?.map {
                if (!map.containsKey(it.id)){
                    map[it.id] = it
                }
            }
        }
        for(item in map){
            EquipmentBindingModel_()
                .id(item.key)
                .equipment(item.value)
                .addTo(this)
        }
    }
}