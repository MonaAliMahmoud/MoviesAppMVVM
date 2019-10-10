package com.hemdan.mvvm.ui.main.actorslist

import android.os.Bundle
import android.view.View
import com.hemdan.mvvm.R
import com.hemdan.mvvm.ui.base.BaseFragment
import javax.inject.Inject

class ActorsListFragment : BaseFragment() {

    override fun getLayoutResourceId(): Int = R.layout.fragment_actors_list

    @Inject
    lateinit var actorsListViewModel: ActorsListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
