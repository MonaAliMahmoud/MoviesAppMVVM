package com.hemdan.mvvm.data.api

import com.hemdan.mvvm.data.model.PopularList
import com.hemdan.mvvm.data.model.PopularProfile
import com.hemdan.mvvm.di.module.NetworkModule.Companion.POPULAR_PEOPLE_URL
import com.hemdan.mvvm.di.module.NetworkModule.Companion.SEARCH_URL
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitApi {
    @GET(POPULAR_PEOPLE_URL)
    fun getPopularData(@Query("page") page: Int): Single<PopularList>

    @GET(SEARCH_URL)
    fun searchForActors(@Query ("query") searchStr: String,
                        @Query ("page") page: Int): Single<PopularList>

    @GET("person/{popularId}/images?")
    fun getProfiles(@Path("popularId") popularId: String): Single<PopularProfile>
}