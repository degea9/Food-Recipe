package com.degea9.android.foodrecipe.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "suggestionKeyword")
data class SuggestionKeywordEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "search_time")
    val searchTime: Long,

    @ColumnInfo(name = "keyword")
    val keyword: String,
)