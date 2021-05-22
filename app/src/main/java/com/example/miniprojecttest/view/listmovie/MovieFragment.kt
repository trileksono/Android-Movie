package com.example.miniprojecttest.view.listmovie

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.miniprojecttest.R
import com.example.miniprojecttest.databinding.FragmentMoviesBinding
import com.example.miniprojecttest.databinding.ItemMovieBinding
import com.example.miniprojecttest.domain.model.Failure
import com.example.miniprojecttest.domain.model.RoomFailure
import com.example.miniprojecttest.view.common.BaseFragment
import com.example.miniprojecttest.view.common.item.LoadingItem
import com.example.miniprojecttest.view.common.item.MovieItem
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.FastItemAdapter
import com.mikepenz.fastadapter.adapters.GenericFastItemAdapter
import com.mikepenz.fastadapter.adapters.GenericItemAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.binding.BindingViewHolder
import com.mikepenz.fastadapter.listeners.ClickEventHook
import com.mikepenz.fastadapter.scroll.EndlessRecyclerOnScrollListener

class MovieFragment : BaseFragment<FragmentMoviesBinding, MovieViewModel>() {

    private lateinit var fastAdapter: GenericFastItemAdapter
    private lateinit var footerAdapter: GenericItemAdapter
    private lateinit var endlessScroll: EndlessRecyclerOnScrollListener

    override fun getUiBinding(): FragmentMoviesBinding {
        return FragmentMoviesBinding.inflate(layoutInflater)
    }

    override fun onFirstLaunch(savedInstanceState: Bundle?, view: View) {
        initRecyclerView()
        viewModel?.getMoviePage(1)
        viewModel?.getFavorite()
    }

    private fun initRecyclerView() {
        viewBinding?.run {
            val layoutManager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)

            fastAdapter = FastItemAdapter()
            footerAdapter = ItemAdapter.items()
            fastAdapter.addAdapter(1, footerAdapter)

            endlessScroll =
                object : EndlessRecyclerOnScrollListener(layoutManager, 10, footerAdapter) {
                    override fun onLoadMore(currentPage: Int) {
                        viewModel?.getMoviePage(currentPage + 1)
                    }

                    override fun onScrollStateChanged(
                        recyclerView: RecyclerView,
                        newState: Int
                    ) {
                        super.onScrollStateChanged(recyclerView, newState)
                        when (newState) {
                            RecyclerView.SCROLL_STATE_IDLE -> movieFabFavorite.show()
                            RecyclerView.SCROLL_STATE_DRAGGING -> movieFabFavorite.hide()
                        }
                    }
                }
            movieRecycler.let {
                it.layoutManager = layoutManager.apply {
                    spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                        override fun getSpanSize(position: Int): Int {
                            return when (fastAdapter.getItemViewType(position)) {
                                R.id.item_loading_id -> 2
                                R.id.item_movie_id -> 1
                                else -> -1
                            }
                        }
                    }
                }
                it.adapter = fastAdapter
                it.addOnScrollListener(endlessScroll)
            }
        }
    }

    override fun initUiListener() {
        fastAdapter.addEventHook(object : ClickEventHook<MovieItem>() {
            override fun onBind(viewHolder: RecyclerView.ViewHolder): View? {
                if (viewHolder is BindingViewHolder<*>) {
                    if (viewHolder.binding is ItemMovieBinding) {
                        return (viewHolder.binding as ItemMovieBinding).itemMovieLinearLayout
                    }
                } else {
                    viewHolder.itemView
                }
                return null
            }

            override fun onClick(
                v: View,
                position: Int,
                fastAdapter: FastAdapter<MovieItem>,
                item: MovieItem
            ) {
                if (!item.movie.isSelected) {
                    viewModel?.setMovieFavorite(item.movie, position)
                } else {
                    viewModel?.deleteMovieFavorite(item.movie, position)
                }
            }
        })

        viewBinding?.run {
            movieFabFavorite.setOnClickListener {
                getController()?.navigate(
                    R.id.action_movieFragment_to_favoriteFragment
                )
            }

            movieContentRetry.contentErrorBtnRetry.setOnClickListener {
                viewBinding?.run {
                    movieContentRetry.root.isVisible = false
                    movieRecycler.isVisible = true
                }
                viewModel?.getMoviePage(1)
            }
        }
    }

    override fun handleFailure(failure: Failure?) {
        when (failure) {
            is RoomFailure.DeleteFailure -> showToast("Gagal menghapus dari favorit")
            is RoomFailure.InsertFailure -> showToast("Gagal menambahnak film ke favorit")
            is Failure.NetworkConnection -> {
                if (fastAdapter.adapterItemCount == 0) {
                    viewBinding?.run {
                        movieContentRetry.root.isVisible = true
                        movieRecycler.isVisible = false
                    }
                    return
                }
                showToast("Koneksi error, mohon periksa kembali jaringan anda")
            }
            is Failure.ServerError -> {
                if (fastAdapter.adapterItemCount == 0) {
                    viewBinding?.run {
                        movieContentRetry.root.isVisible = true
                        movieRecycler.isVisible = false
                    }
                    return
                }
                showToast(failure.message)
            }
            else -> super.handleFailure(failure)
        }
    }

    override fun initMenu(): Int = 0

    override fun observeViewModel(viewModel: MovieViewModel) {
        viewModel.failureLiveData.observe(this, ::handleFailure)
        viewModel.moviePageLiveData.observe(this, { movieList ->
            fastAdapter.add(movieList.map { MovieItem(it) })
        })

        viewModel.isLoadingLiveData.observe(this, {
            footerAdapter.clear()
            if (it) footerAdapter.add(LoadingItem()) else footerAdapter.clear()
        })

        viewModel.itemPositionLiveData.observe(this, {
            val movie = (fastAdapter.getItem(it) as MovieItem).movie.apply {
                isSelected = !isSelected
            }
            fastAdapter.notifyAdapterItemChanged(it, movie)
        })
    }

    override fun bindViewModel(): MovieViewModel {
        return ViewModelProvider(this, viewModelFactory).get(MovieViewModel::class.java)
    }
}
