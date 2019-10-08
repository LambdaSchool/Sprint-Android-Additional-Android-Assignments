package com.example.myownlocation.viewModel

import androidx.lifecycle.ViewModelProvider

object Injection {

    fun provideViewModelFactory(): ViewModelProvider.Factory {
        return ViewModelFactory()
    }
}