package com.hemdan.mvvm.ui.main.actorslist

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.hemdan.mvvm.R
import com.hemdan.mvvm.data.model.PopularInfo
import com.hemdan.mvvm.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_actors_list.*
import java.util.ArrayList
import javax.inject.Inject

class ActorsListFragment : BaseFragment() {

    private lateinit var listAdapter: ActorsAdapter
    private var listLayoutManager: LinearLayoutManager? = null

    private var popularInfos = ArrayList<PopularInfo>()

    override fun getLayoutResourceId(): Int = R.layout.fragment_actors_list

    @Inject
    lateinit var actorsListViewModel: ActorsListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configRecycleView(popularInfos)
        observeViewModel()
        actorsListViewModel.getActors()
    }

    private fun observeViewModel(){
        actorsListViewModel.getActorList()
            .observe(this, Observer{ listOfActors->
                listAdapter.add(listOfActors)
            })
    }

    private fun configRecycleView(popularInfos: ArrayList<PopularInfo>) {
        listAdapter = ActorsAdapter(popularInfos, activity!!)
        actorsList.adapter = listAdapter
        actorsList.setHasFixedSize(true)
        actorsList.setItemViewCacheSize(20)
        actorsList.layoutManager = listLayoutManager
    }
}
