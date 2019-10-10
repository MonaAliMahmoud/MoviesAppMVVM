package com.hemdan.mvvm.di.module

import com.hemdan.mvvm.ui.base.BaseRepository
import com.hemdan.mvvm.ui.main.actorslist.ActorsListRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * Created by Mohammed Hemdan on 4/14/19.
 * Email : mohammed.hemdan.faraj@gmail.com
 * Github : https://github.com/mhemdan
 */
@Module
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindActorsListRepository(actorsListRepository: ActorsListRepository): BaseRepository
}