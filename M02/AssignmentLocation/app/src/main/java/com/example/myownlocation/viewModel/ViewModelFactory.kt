package com.example.myownlocation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ItemListViewModel::class.java) -> ItemListViewModel() as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}