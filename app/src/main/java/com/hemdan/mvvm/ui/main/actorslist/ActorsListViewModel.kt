package com.hemdan.mvvm.ui.main.actorslist

import com.hemdan.mvvm.ui.base.BaseViewModel
import io.reactivex.functions.Consumer
import javax.inject.Inject

class ActorsListViewModel @Inject constructor(): BaseViewModel<ActorsListRepository>() {
    var pageno = 1

    @Inject
    lateinit var actorsListRepository: ActorsListRepository

    fun getActors(){
        subscribe(actorsListRepository.getUrl(pageno),
            Consumer { actorList->

            }
        )
    }
}