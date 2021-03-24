package com.degea9.android.foodrecipe.repository.mapper

import com.degea9.android.foodrecipe.domain.model.Equipment
import com.degea9.android.foodrecipe.remote.response.EquipmentResponse

class EquipmentDataListMapper : ListMapper<EquipmentResponse, Equipment> {
    override fun map(input: List<EquipmentResponse>?): List<Equipment>? {
        return input?.map {
            Equipment(
                id = it.id,
                name = it.name,
                localizedName = it.localizedName,
                image = it.image
            )
        }
    }
}