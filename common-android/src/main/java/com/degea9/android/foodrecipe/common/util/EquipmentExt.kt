package com.degea9.android.foodrecipe.common.util

import com.degea9.android.foodrecipe.domain.model.Equipment

const val EQUIPMENT_IMAGE_SIZE = "250x250"
const val EQUIPMENT_BASE_URL = "https://spoonacular.com/cdn/equipment_"

fun Equipment.getEquipmentImageURL() = EQUIPMENT_BASE_URL+ EQUIPMENT_IMAGE_SIZE+"/"+ this.image

