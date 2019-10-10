package com.hemdan.mvvm.ui.main.actorslist

import com.hemdan.mvvm.data.api.RetrofitApi
import com.hemdan.mvvm.data.model.PopularList
import com.hemdan.mvvm.ui.base.BaseRepository
import io.reactivex.Single
import javax.inject.Inject

class ActorsListRepository @Inject constructor() : BaseRepository(){

    @Inject
    lateinit var retrofitApi: RetrofitApi

    fun getUrl(pageNum: Int): Single<PopularList> {
        return retrofitApi.getPopularData(pageNum)
    }
}