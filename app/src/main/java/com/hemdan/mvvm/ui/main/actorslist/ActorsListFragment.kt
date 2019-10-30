package com.hemdan.mvvm.ui.main.actorslist

import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hemdan.mvvm.R
import com.hemdan.mvvm.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_actors_list.*
import javax.inject.Inject

class ActorsListFragment : BaseFragment(){

    private var isSearchAction = false
    private var searchStr = ""

    private var listAdapter = ActorsAdapter()

    override fun getLayoutResourceId(): Int = R.layout.fragment_actors_list

    @Inject
    lateinit var actorsListViewModel: ActorsListViewModel

//    @Inject
//    lateinit var viewModelFactory: ViewModelFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        activity?.let {
//            actorsListViewModel = ViewModelProviders.of(it, viewModelFactory)
//            .get(ActorsListViewModel::class.java)
//        }

        setupList()
        observeViewModel()
        actorsListViewModel.getActors()

        swipeRefresh.setOnRefreshListener {
            Handler().postDelayed({
                actorsListViewModel.refreshList()
            }, 1000)
        }
    }

    private fun showError(message: String){
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    private fun showLading(){
        swipeRefresh.isRefreshing = true
        Toast.makeText(activity, "isLoading", Toast.LENGTH_SHORT).show()
    }

    private fun observeViewModel(){
        actorsListViewModel.state.observe(this, Observer {
                state -> when(state){
                    is ActorsListStates.Loading -> showLading()
                    is ActorsListStates.ActorListError -> showError(state.message!!)
                    is ActorsListStates.ActorsList ->{
                        swipeRefresh.isRefreshing = false
                        listAdapter.addItems(state.actorList)
                    }
                }
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

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        activity?.menuInflater?.inflate(R.menu.search, menu)
        val searchView = menu?.findItem(R.id.search_item)?.actionView as SearchView

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    searchStr = query
                }
                if (searchStr.isNotEmpty()) {
                    isSearchAction = true
                    actorsListViewModel.getSearchList(searchStr)
                }
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (searchView.query.isEmpty()) {
                    isSearchAction = false
                    searchView.clearFocus()
                    actorsListViewModel.refreshList()
                }
                return true
            }
        })

        searchView.clearFocus()
    }

//    fun goToDetailsScreen(){
//        activity?.let {
//            var actorDetailsViewModel: ActorDetailsViewModel = ViewModelProviders
//                .of(it, viewModelFactory)
//                .get(ActorDetailsViewModel::class.java)
//        }
//        activity?.supportFragmentManager?.beginTransaction()
//            ?.replace(R.id.list_fragment, ActorDetailsFragment())?.commit()
//    }
}
