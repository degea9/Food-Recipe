package com.degea9.android.food.foodrecipe.recipe_detail

import com.airbnb.epoxy.TypedEpoxyController
import com.degea9.android.foodrecipe.domain.model.Instruction
import timber.log.Timber

class InstructionController : TypedEpoxyController<Instruction?>() {
    override fun buildModels(data: Instruction?) {
        Timber.d("buildModels data $data")
        data?.steps?.map {
            InstructionStepBindingModel_()
                .id(it.number)
                .step(it)
                .addTo(this)
        }

    }
}