package com.degea9.android.foodrecipe.repository.mapper

interface Mapper<I, O> {
    fun mapTo(input: I): O
    fun mapFrom(input: O) :I
}