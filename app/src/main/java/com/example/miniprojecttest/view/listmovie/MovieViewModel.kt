package com.example.miniprojecttest.view.listmovie

import com.example.miniprojecttest.domain.model.Failure
import com.example.miniprojecttest.domain.model.Movie
import com.example.miniprojecttest.domain.model.RoomFailure
import com.example.miniprojecttest.domain.usecase.FavoriteUseCase
import com.example.miniprojecttest.domain.usecase.MovieUseCase
import com.example.miniprojecttest.helper.NetworkHandler
import com.example.miniprojecttest.helper.SingleLiveEvent
import com.example.miniprojecttest.helper.TagInjection
import com.example.miniprojecttest.helper.disposedBy
import com.example.miniprojecttest.view.common.BaseViewModel
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

class MovieViewModel @Inject constructor(
    @Named(TagInjection.UI_Scheduler) uiSchedulers: Scheduler,
    @Named(TagInjection.IO_Scheduler) ioScheduler: Scheduler,
    networkHandler: NetworkHandler,
    private val movieUseCase: MovieUseCase,
    private val favoriteUseCase: FavoriteUseCase
) : BaseViewModel(uiSchedulers, ioScheduler, networkHandler) {

    val moviePageLiveData = SingleLiveEvent<List<Movie>>()
    val itemPositionLiveData = SingleLiveEvent<Int>()
    private val favorites = mutableListOf<Movie>()

    fun getMoviePage(page: Int) {
        executeJob {
            movieUseCase.getMoviePage(page)
                .compose(applySchedulers())
                .doOnSubscribe { isLoadingLiveData.value = true }
                .doOnTerminate { isLoadingLiveData.value = false }
                .subscribe(
                    {
                        val favoriteId = favorites.map { it.id }
                        moviePageLiveData.value = it.also { list ->
                            list.map {
                                it.apply {
                                    isSelected = favoriteId.contains(it.id)
                                }
                            }
                        }
                    },
                    {
                        handleFailure(Failure.ServerError(it?.message.orEmpty()))
                    }
                )
                .disposedBy(disposable)
        }
    }

    fun getFavorite() {
        favoriteUseCase.getAllFavorite()
            .compose(applySchedulers())
            .subscribe({
                favorites.apply {
                    clear()
                    addAll(it)
                }
            }, {})
            .disposedBy(disposable)
    }

    fun setMovieFavorite(movie: Movie, position: Int) {
        favoriteUseCase.insertFavorite(movie)
            .subscribeOn(ioScheduler)
            .observeOn(uiSchedulers)
            .subscribe({
                itemPositionLiveData.value = position
            }, {
                handleFailure(RoomFailure.InsertFailure())
            })
            .disposedBy(disposable)
    }

    fun deleteMovieFavorite(movie: Movie, position: Int) {
        favoriteUseCase.deleteFavorite(movie)
            .subscribeOn(ioScheduler)
            .observeOn(uiSchedulers)
            .subscribe({
                itemPositionLiveData.value = position
            }, {
                handleFailure(RoomFailure.DeleteFailure())
            })
            .disposedBy(disposable)
    }

}
