package com.example.miniprojecttest.view.favoritemovie

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.miniprojecttest.databinding.FragmentFavoriteBinding
import com.example.miniprojecttest.databinding.ItemMovieBinding
import com.example.miniprojecttest.view.common.BaseFragment
import com.example.miniprojecttest.view.common.item.MovieItem
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.binding.BindingViewHolder
import com.mikepenz.fastadapter.listeners.ClickEventHook

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding, FavoriteViewModel>() {

    private var itemAdapter = ItemAdapter<MovieItem>()
    private var fastAdapter = FastAdapter.with(itemAdapter)

    override fun getUiBinding(): FragmentFavoriteBinding {
        return FragmentFavoriteBinding.inflate(layoutInflater)
    }

    override fun onFirstLaunch(savedInstanceState: Bundle?, view: View) {
        viewBinding?.run {
            favoriteRecycler.let {
                it.layoutManager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
                it.adapter = fastAdapter
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
                viewModel?.deleteFavorite(item.movie, position)
            }
        })
    }

    override fun initMenu(): Int = 0

    override fun observeViewModel(viewModel: FavoriteViewModel) {
        viewModel.moviePageLiveData.observe(this, {
            if (it.isEmpty()) {
                viewBinding?.run {
                    favoriteContentEmpty.root.isVisible = true
                    favoriteRecycler.isVisible = false
                }
                return@observe
            }
            itemAdapter.add(it.map { MovieItem(it.apply { it.isSelected = true }) })
        })

        viewModel.movieDeletePosition.observe(this, {
            itemAdapter.remove(it)
            if (fastAdapter.itemCount == 0) {
                viewBinding?.run {
                    favoriteContentEmpty.root.isVisible = true
                    favoriteRecycler.isVisible = false
                }
            }
        })
    }

    override fun bindViewModel(): FavoriteViewModel {
        return ViewModelProvider(this, viewModelFactory).get(FavoriteViewModel::class.java)
    }
}

