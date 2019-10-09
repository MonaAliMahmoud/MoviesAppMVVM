package com.hemdan.mvvm.di.component

import android.app.Application
import com.hemdan.mvvm.AppInstance
import com.hemdan.mvvm.di.module.ActivityModule
import com.hemdan.mvvm.di.module.AppModule
import com.hemdan.mvvm.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by Mohammed Hemdan on 4/14/19.
 * Email : mohammed.hemdan.faraj@gmail.com
 * Github : https://github.com/mhemdan
 */
@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivityModule::class, NetworkModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun baseUrl(url : String) : AppComponent.Builder

        fun build(): AppComponent
    }

    fun inject(appInstance: AppInstance)
}