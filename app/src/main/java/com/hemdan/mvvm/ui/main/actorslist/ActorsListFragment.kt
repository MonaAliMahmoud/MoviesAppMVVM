package com.hemdan.mvvm.ui.main.actorslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.hemdan.mvvm.R
import com.hemdan.mvvm.ui.base.BaseFragment

class ActorsListFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_actors_list, container, false)
    }


}
