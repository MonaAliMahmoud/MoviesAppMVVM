package com.hemdan.mvvm.ui.main.actorslist

import com.hemdan.mvvm.data.model.PopularInfo
import com.hemdan.mvvm.ui.base.BaseViewModel
import io.reactivex.functions.Consumer
import javax.inject.Inject
import androidx.lifecycle.MutableLiveData

class ActorsListViewModel @Inject constructor(): BaseViewModel<ActorsListRepository>() {

    private var pageNumber = 1
    private var popularInfo = MutableLiveData<List<PopularInfo>>()

    @Inject
    lateinit var actorsListRepository: ActorsListRepository

    fun getActors(){
        popularInfo.value = ArrayList()
        subscribe(actorsListRepository.getUrl(pageNumber),
            Consumer { actorList->
                popularInfo.value = actorList.results
            }
        )
    }

    fun getActorLiveDataList(): MutableLiveData<List<PopularInfo>> {
        return popularInfo
    }

    fun loadNextPage(){
        pageNumber++
        getActors()
    }

    fun refreshList(){
        popularInfo.value = ArrayList()
        getActors()
    }

    fun getSearchList(searchStr: String) {
        popularInfo.value = ArrayList()
        subscribe(actorsListRepository.getSearchResult(searchStr, pageNumber),
            Consumer { searchList->
                popularInfo.value = searchList.results
            }
        )
    }

    fun loadNextSearchPage(searchStr: String){
        pageNumber++
        getSearchList(searchStr)
    }
}