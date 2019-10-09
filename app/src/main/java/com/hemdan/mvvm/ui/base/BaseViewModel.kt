package com.hemdan.mvvm.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hemdan.mvvm.data.utils.ErrorModel
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Mohammed Hemdan on 4/14/19.
 * Email : mohammed.hemdan.faraj@gmail.com
 * Github : https://github.com/mhemdan
 */
class BaseViewModel<Repository : BaseRepository> : ViewModel()  {
    @Inject
    lateinit var repository: Repository

    val error = MutableLiveData<ErrorModel>()
    val loading = MutableLiveData<Boolean>()
    val compositeDisposable = CompositeDisposable()

    fun <T> subscribe(
        observable: Observable<T>,
        success: Consumer<T>,
        error: Consumer<Throwable>,
        subscribeScheduler: Scheduler = Schedulers.io(),
        observeOnMainThread: Boolean = true) {

        val observerScheduler =
            if (observeOnMainThread) AndroidSchedulers.mainThread()
            else subscribeScheduler

        compositeDisposable.add(observable
            .subscribeOn(subscribeScheduler)
            .observeOn(observerScheduler)
            .subscribe(success, error))
    }

    fun <T> subscribe(
        observable: Single<T>,
        success: Consumer<T>,
        error: Consumer<Throwable> = Consumer { },
        subscribeScheduler: Scheduler = Schedulers.io(),
        observeOnMainThread: Boolean = true,
        showLoading: Boolean = true) {

        val observerScheduler =
            if (observeOnMainThread) AndroidSchedulers.mainThread()
            else subscribeScheduler

        compositeDisposable.add(observable
            .subscribeOn(subscribeScheduler)
            .observeOn(observerScheduler)
            .compose { single ->
                composeSingle<T>(single, showLoading)
            }
            .subscribe(success, error))
    }

    private fun <T> composeSingle(single: Single<T>, showLoading: Boolean = true): Single<T> {
        return single
            .doOnError {
//                Timber.e(it)
//                getRetrofitError(it)
                loading.postValue(false)
            }
            .doOnSubscribe {
                loading.postValue(showLoading)
            }.doOnSuccess {
                loading.postValue(false)
            }
    }

    fun clearSubscription() {
        if (compositeDisposable.isDisposed.not()) compositeDisposable.clear()
    }

    override fun onCleared() {
        clearSubscription()
        super.onCleared()
    }
}