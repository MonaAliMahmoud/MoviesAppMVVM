package com.hemdan.mvvm.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hemdan.mvvm.di.helper.ViewModelKey
import com.hemdan.mvvm.ui.base.ViewModelFactory
import com.hemdan.mvvm.ui.main.actordetails.ActorDetailsViewModel
import com.hemdan.mvvm.ui.main.actorslist.ActorsListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Mohammed Hemdan on 4/14/19.
 * Email : mohammed.hemdan.faraj@gmail.com
 * Github : https://github.com/mhemdan
 */
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ActorsListViewModel::class)
    abstract fun bindActorsListViewModel(actorsListViewModel: ActorsListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ActorDetailsViewModel::class)
    abstract fun bindActorDetailsViewModel(actorDetailsViewModel: ActorDetailsViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory) : ViewModelProvider.Factory
}