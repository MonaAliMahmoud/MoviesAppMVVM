package com.hemdan.mvvm.di.builder

import com.hemdan.mvvm.ui.main.actorslist.ActorsListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Mohammed Hemdan on 4/14/19.
 * Email : mohammed.hemdan.faraj@gmail.com
 * Github : https://github.com/mhemdan
 */
@Module
abstract class MainActivityFragmentBuilderModule {
    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): ActorsListFragment
}