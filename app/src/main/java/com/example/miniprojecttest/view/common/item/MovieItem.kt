package com.example.miniprojecttest.view.common.item

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.miniprojecttest.R
import com.example.miniprojecttest.databinding.ItemMovieBinding
import com.example.miniprojecttest.domain.model.Movie
import com.example.miniprojecttest.helper.bindImagePalette
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class MovieItem(val movie: Movie) : AbstractBindingItem<ItemMovieBinding>() {

    private val PREFIX_URl_IMAGE = "https://image.tmdb.org/t/p/w342"

    override val type: Int get() = R.id.item_movie_id

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): ItemMovieBinding {
        return ItemMovieBinding.inflate(inflater, parent, false)
    }

    override fun bindView(binding: ItemMovieBinding, payloads: List<Any>) {
        binding.run {
            itemMovieTvTitle.text = movie.title
            itemMovieImgPoster.bindImagePalette(
                PREFIX_URl_IMAGE.plus(movie.posterPath),
                itemMovieTvTitle
            )
            itemMovieChkFavorite.isChecked = movie.isSelected
        }
    }
}
