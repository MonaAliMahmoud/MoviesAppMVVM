package com.hemdan.mvvm.ui.main.actorslist

import android.util.Log
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
        subscribe(actorsListRepository.getUrl(pageNumber),
            Consumer { actorList->
                Log.i("data", "Actors")
                popularInfo.value = actorList.results
            }
        )
    }

    fun getActorList(): MutableLiveData<List<PopularInfo>> {
        return popularInfo
    }

    fun loadNextPage(){
        pageNumber++
        getActors()
    }
}