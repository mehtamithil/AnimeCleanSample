package com.anime_clean_sample.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.anime_clean_sample.presentation.R
import com.anime_clean_sample.presentation.databinding.RvRowPrgbrBinding

class ProgressStateAdapter : LoadStateAdapter<ProgressStateAdapter.LoadStateVH>() {

    override fun onBindViewHolder(holder: LoadStateVH, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) = LoadStateVH(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.rv_row_prgbr,
            parent,
            false
        )
    )

    class LoadStateVH(private val rvRowPrgbrBinding: RvRowPrgbrBinding) :
        ViewHolder(rvRowPrgbrBinding.root) {

        fun bind(loadState: LoadState) {
            rvRowPrgbrBinding.isLoading = loadState == LoadState.Loading
        }
    }
}