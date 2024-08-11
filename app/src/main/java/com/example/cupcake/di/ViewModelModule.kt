package com.example.cupcake.di

import androidx.lifecycle.ViewModel
import com.example.cupcake.model.OrderViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(OrderViewModel::class)
    fun bindLoginViewModel(viewModel: OrderViewModel): ViewModel
}