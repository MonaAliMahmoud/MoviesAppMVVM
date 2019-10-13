package com.hemdan.mvvm.ui.main.actordetails

import com.hemdan.mvvm.data.api.RetrofitApi
import com.hemdan.mvvm.data.model.PopularProfile
import com.hemdan.mvvm.ui.base.BaseRepository
import io.reactivex.Single
import javax.inject.Inject

class ActorDetailsRepository @Inject constructor(): BaseRepository() {

    @Inject
    lateinit var retrofitApi: RetrofitApi

    fun getActorPictures(popularId: String): Single<PopularProfile> {
        return retrofitApi.getProfiles(popularId)
    }
}