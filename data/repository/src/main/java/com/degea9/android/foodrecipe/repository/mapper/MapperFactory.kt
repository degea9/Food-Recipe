package com.degea9.android.foodrecipe.repository.mapper

/**
 * Create [Mapper] or [ListMapper] using Reflection api and factory pattern
 */
object MapperFactory {

    inline fun <reified T : Mapper<*, *>> createMapper(): T {
        return T::class.java.getDeclaredConstructor().newInstance()
    }

    inline fun <reified T : ListMapper<*,*>> createListMapper(): T {
        return T::class.java.getDeclaredConstructor().newInstance()
    }
}