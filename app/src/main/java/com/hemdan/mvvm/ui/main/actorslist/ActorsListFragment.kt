package com.hemdan.mvvm.ui.main.actorslist

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hemdan.mvvm.R
import com.hemdan.mvvm.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_actors_list.*
import javax.inject.Inject

class ActorsListFragment : BaseFragment() {

    private var listAdapter = ActorsAdapter()

    override fun getLayoutResourceId(): Int = R.layout.fragment_actors_list

    @Inject
    lateinit var actorsListViewModel: ActorsListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupList()
        observeViewModel()
        actorsListViewModel.getActors()
    }

    private fun observeViewModel(){
        actorsListViewModel.getActorList()
            .observe(this, Observer{ listOfActors->
                listAdapter.addItems(listOfActors)
            })
    }

    private fun setupList() {
        actorsList.adapter = listAdapter
        actorsList.setHasFixedSize(true)
        actorsList.setItemViewCacheSize(20)
        val listLayoutManager = LinearLayoutManager(context)
        actorsList.layoutManager = listLayoutManager

        actorsList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val currentItems = listLayoutManager.childCount
                val scrolledItems = listLayoutManager.findFirstCompletelyVisibleItemPosition()
                val totalItems = listLayoutManager.itemCount
                if (currentItems + scrolledItems == totalItems) {
                    actorsListViewModel.loadNextPage()
                }
            }
        })
    }
}
