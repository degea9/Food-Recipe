package com.degea9.android.foodrecipe.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "suggestionKeyword")
data class SuggestionKeywordEntity(
        @PrimaryKey
        @ColumnInfo(name = "keyword")
        val keyword: String,

        @ColumnInfo(name = "search_time")
        val searchTime: Long
)