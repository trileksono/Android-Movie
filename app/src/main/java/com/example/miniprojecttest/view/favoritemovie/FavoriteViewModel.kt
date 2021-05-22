package com.example.miniprojecttest.view.favoritemovie

import com.example.miniprojecttest.domain.model.Movie
import com.example.miniprojecttest.domain.usecase.FavoriteUseCase
import com.example.miniprojecttest.helper.NetworkHandler
import com.example.miniprojecttest.helper.SingleLiveEvent
import com.example.miniprojecttest.helper.TagInjection
import com.example.miniprojecttest.helper.disposedBy
import com.example.miniprojecttest.view.common.BaseViewModel
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

class FavoriteViewModel @Inject constructor(
    @Named(TagInjection.UI_Scheduler) uiSchedulers: Scheduler,
    @Named(TagInjection.IO_Scheduler) ioScheduler: Scheduler,
    networkHandler: NetworkHandler,
    private val favoriteUseCase: FavoriteUseCase
) : BaseViewModel(uiSchedulers, ioScheduler, networkHandler) {

    val moviePageLiveData = SingleLiveEvent<List<Movie>>()
    val movieDeletePosition = SingleLiveEvent<Int>()

    init {
        getAllFavorite()
    }

    fun getAllFavorite() {
        favoriteUseCase.getAllFavorite()
            .compose(applySchedulers())
            .subscribe({
                moviePageLiveData.value = it
            }, {})
            .disposedBy(disposable)
    }

    fun deleteFavorite(movie: Movie, adapterPosition: Int) {
        favoriteUseCase.deleteFavorite(movie)
            .subscribeOn(ioScheduler)
            .observeOn(uiSchedulers)
            .subscribe({
                movieDeletePosition.value = adapterPosition
            }, {})
            .disposedBy(disposable)
    }
}
