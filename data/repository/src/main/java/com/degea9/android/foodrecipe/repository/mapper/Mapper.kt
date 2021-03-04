package com.degea9.android.foodrecipe.repository.mapper

interface Mapper<I, O> {
    fun map(input: I): O
}