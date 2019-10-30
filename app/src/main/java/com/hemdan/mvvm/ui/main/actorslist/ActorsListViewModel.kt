package com.hemdan.mvvm.ui.main.actorslist

import androidx.lifecycle.LiveData
import com.hemdan.mvvm.ui.base.BaseViewModel
import javax.inject.Inject
import androidx.lifecycle.MutableLiveData
import io.reactivex.functions.Consumer

class ActorsListViewModel @Inject constructor(): BaseViewModel<ActorsListRepository>() {

    private var pageNumber = 1
    private var popularInfo = MutableLiveData<ActorsListStates>()
    var state : LiveData<ActorsListStates> = popularInfo

    @Inject
    lateinit var actorsListRepository: ActorsListRepository

    fun getActors(){
        popularInfo.value = ActorsListStates.Loading
        subscribe(actorsListRepository.getUrl(pageNumber),
            Consumer { actorList ->
               popularInfo.value = actorList.results.let { it?.let { list ->
                   ActorsListStates.ActorsList(
                       list
                   )}
               }
           },
           Consumer{
                   listError -> popularInfo.value = ActorsListStates.ActorListError(listError.message)
           }
        )
    }

    fun getActorLiveDataList(): MutableLiveData<ActorsListStates> {
        return popularInfo
    }

    fun loadNextPage(){
        pageNumber++
        getActors()
    }

    fun refreshList(){
        popularInfo.value = ActorsListStates.Loading
        getActors()
    }

    fun getSearchList(searchStr: String) {
//        popularInfo.value = ArrayList()
//        subscribe(actorsListRepository.getSearchResult(searchStr, pageNumber),
//            Consumer { searchList->
//                popularInfo.value = searchList.results
//            }
//        )
    }

    fun loadNextSearchPage(searchStr: String){
        pageNumber++
        getSearchList(searchStr)
    }
}