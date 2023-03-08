package com.anime_clean_sample.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.anime_clean_sample.presentation.databinding.RvRowAnimeBinding
import com.anime_clean_sample.presentation.ui.state.AnimeListItemUiState

class AnimeRVAdapter(private val onClick: (id: Int) -> Unit) :
    PagingDataAdapter<AnimeListItemUiState, AnimeRVAdapter.AnimeVH>(COMPARATOR) {

    override fun onBindViewHolder(holder: AnimeVH, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AnimeVH(
        RvRowAnimeBinding.inflate(LayoutInflater.from(parent.context))
    )

    inner class AnimeVH(private val animeBinding: RvRowAnimeBinding) :
        ViewHolder(animeBinding.root) {

        fun bind(animeListItemUiState: AnimeListItemUiState) {
            animeBinding.apply {
                root.setOnClickListener {
                    onClick(animeListItemUiState.id)
                }
                anime = animeListItemUiState
            }
        }
    }

    companion object {
        val COMPARATOR = object : DiffUtil.ItemCallback<AnimeListItemUiState>() {

            override fun areItemsTheSame(
                oldItem: AnimeListItemUiState,
                newItem: AnimeListItemUiState
            ) = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: AnimeListItemUiState,
                newItem: AnimeListItemUiState
            ) = oldItem.id == newItem.id
        }
    }
}