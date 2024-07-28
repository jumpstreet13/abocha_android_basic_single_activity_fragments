package com.example.cupcake.di

import android.app.Application
import com.example.cupcake.MainActivity
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [
    ViewModelModule::class
])
interface ApplicationComponent {
    fun inject(activity: MainActivity)

    @Component.Factory
    interface ApplicationComponentFactory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}