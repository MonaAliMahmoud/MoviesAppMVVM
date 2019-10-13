package com.hemdan.mvvm.ui.main.actorslist

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hemdan.mvvm.R
import com.hemdan.mvvm.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_actors_list.*
import javax.inject.Inject

class ActorsListFragment : BaseFragment(), android.widget.SearchView.OnQueryTextListener{

    private var searchView: android.widget.SearchView? = null
    private var isSearchAction = false
    private var searchStr = ""

    private var listAdapter = ActorsAdapter()

    override fun getLayoutResourceId(): Int = R.layout.fragment_actors_list

    @Inject
    lateinit var actorsListViewModel: ActorsListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupList()
        observeViewModel()
        actorsListViewModel.getActors()

        swipeRefresh.setOnRefreshListener {
            Handler().postDelayed({
                swipeRefresh.isRefreshing = false
                actorsListViewModel.refreshList()
            }, 1000)
        }
    }

    private fun observeViewModel(){
        actorsListViewModel.getActorLiveDataList()
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
                    if (isSearchAction) {
                        actorsListViewModel.loadNextSearchPage(searchStr)
                    } else {
                        actorsListViewModel.loadNextPage()
                    }
                }
            }
        })
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        searchStr = query!!
        if (searchStr != "") {
            isSearchAction = true
            actorsListViewModel.getSearchList(searchStr)
        }
        searchView!!.clearFocus()
        return true    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (searchView!!.query.isEmpty()) {
            isSearchAction = false
            searchView!!.clearFocus()
            actorsListViewModel.refreshList()
        }
        return true
    }
}
