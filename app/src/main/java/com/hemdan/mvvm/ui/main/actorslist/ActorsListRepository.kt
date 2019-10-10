package com.hemdan.mvvm.ui.main.actorslist

import com.hemdan.mvvm.data.api.RetrofitApi
import com.hemdan.mvvm.data.model.PopularList
import io.reactivex.Single
import javax.inject.Inject

class ActorsListRepository {

    @Inject
    lateinit var retrofitApi: RetrofitApi

    fun getUrl(pageNum: Int): Single<PopularList> {
        return retrofitApi.getPopularData(pageNum)
    }
}