package com.example.parayo.common.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(
    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val creator: Provider<out ViewModel>? =
            creators[modelClass] ?: creators.asIterable().firstOrNull {
                modelClass.isAssignableFrom(it.key)
            }?.value

        @Suppress("UNCHECKED_CAST")
        return creator?.get() as T
    }
}