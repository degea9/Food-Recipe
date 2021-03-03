package com.degea9.android.foodrecipe.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class RecipeEntity (
    @PrimaryKey  val id: Int,

    @ColumnInfo(name = "title") val title: String?,

    @ColumnInfo(name = "image")  val image: String?,

)