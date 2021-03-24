package com.degea9.android.foodrecipe.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.degea9.android.foodrecipe.local.entity.SuggestionKeywordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SuggestionKeywordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertKeyword(keywordEntity: SuggestionKeywordEntity)

    @Query("SELECT * FROM suggestionKeyword ORDER BY search_time DESC LIMIT 5 ")
    fun getSuggestionKeyword(): Flow<List<SuggestionKeywordEntity>>

}