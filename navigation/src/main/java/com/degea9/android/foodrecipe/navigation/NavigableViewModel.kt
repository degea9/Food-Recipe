package com.degea9.android.foodrecipe.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlin.coroutines.suspendCoroutine
import kotlinx.coroutines.CoroutineScope

/**
 * Convenient interface to identity a [ViewModel]
 * that will be able to properly handle the navigation.
 */
interface NavigableViewModel<T : NavDirection> {
    val router: LiveEvent<NavigationCommand>

    val navigableScope: CoroutineScope
        get() = (this as ViewModel).viewModelScope

    // region Navigation Extensions

    fun MutableLiveEvent<NavigationCommand>.navigateTo(
        direction: NavDirection
    ) {
        publish(NavigationCommand.To(direction))
    }

    suspend fun MutableLiveEvent<NavigationCommand>.navigateForResult(direction: NavDirection): NavigationResult =
        suspendCoroutine { continuation ->
            publish(NavigationCommand.ForResult(direction, continuation))
        }

    fun MutableLiveEvent<NavigationCommand>.navigateToPrevious(direction: T) {
        publish(NavigationCommand.ToPrevious(direction))
    }

    fun MutableLiveEvent<NavigationCommand>.navigateBack() {
        publish(NavigationCommand.Back)
    }

    fun MutableLiveEvent<NavigationCommand>.finish(results: Any? = null) {
        publish(NavigationCommand.Finish(results))
    }

    // endregion
}