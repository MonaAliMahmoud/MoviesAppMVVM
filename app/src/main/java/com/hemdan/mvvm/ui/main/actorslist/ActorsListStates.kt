package com.hemdan.mvvm.ui.main.actorslist

import com.hemdan.mvvm.data.model.PopularInfo

sealed class ActorsListStates{
    object Loading : ActorsListStates()
    data class Error(val message: String?) : ActorsListStates()
    data class ActorsList(val workouts: ArrayList<PopularInfo>) : ActorsListStates()
}