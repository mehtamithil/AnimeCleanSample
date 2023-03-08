package com.anime_clean_sample.presentation.fragment.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.anime_clean_sample.presentation.NavGraphDirections
import com.anime_clean_sample.presentation.R
import kotlinx.coroutines.cancelChildren

abstract class BaseFragment<BNDNG : ViewDataBinding, out VM : ViewModel> : Fragment() {

    protected lateinit var binding: BNDNG

    protected abstract val vm: VM

    protected abstract val layoutResId: Int

    protected open val requiresFavoriteMenu
        get() = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = DataBindingUtil.inflate<BNDNG>(
        inflater, layoutResId, container, false
    ).apply {
        binding = this
        lifecycleOwner = this@BaseFragment
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Toolbar>(R.id.tlbr)?.let { tlbr ->
            manageBackPress(tlbr)
            manageFavoriteMenu(tlbr)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        lifecycleScope.coroutineContext.cancelChildren()
    }

    private fun manageFavoriteMenu(tlbr: Toolbar) {
        if (requiresFavoriteMenu) {
            tlbr.inflateMenu(R.menu.menu_favorite)
            tlbr.setOnMenuItemClickListener {
                (it.itemId == R.id.mnfavorite).apply {
                    if (this) findNavController().navigate(NavGraphDirections.actionMoveToFragFavoriteAnime())
                }
            }
        }
    }

    private fun manageBackPress(tlbr: Toolbar) {
        parentFragment?.let { navHostFrag ->
            if (navHostFrag.childFragmentManager.backStackEntryCount > 0) {
                tlbr.setNavigationIcon(R.drawable.ic_back)
                tlbr.setNavigationOnClickListener {
                    findNavController().navigateUp()
                }
            }
        }
    }
}