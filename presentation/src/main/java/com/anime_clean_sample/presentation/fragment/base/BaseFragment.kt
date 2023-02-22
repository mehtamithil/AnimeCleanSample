package com.anime_clean_sample.presentation.fragment.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.anime_clean_sample.presentation.R

abstract class BaseFragment<BNDNG : ViewDataBinding, out VM : ViewModel> : Fragment() {

    protected lateinit var binding: BNDNG

    protected abstract val vm: VM

    protected abstract val layoutResId: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = DataBindingUtil.inflate<BNDNG>(
        inflater,
        layoutResId,
        container,
        false
    ).apply {
        binding = this
        lifecycleOwner = this@BaseFragment
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Toolbar>(R.id.tlbr)?.let { tlbr ->
            val activity = requireActivity()
            if (activity is AppCompatActivity) {
                activity.setSupportActionBar(tlbr)
                parentFragment?.let { navHostFrag ->
                    if (navHostFrag.childFragmentManager.backStackEntryCount > 0) {
                        activity.supportActionBar?.let {
                            it.setDisplayHomeAsUpEnabled(true)
                            it.setDisplayShowHomeEnabled(true)
                        }
                        tlbr.setNavigationOnClickListener {
                            findNavController().navigateUp()
                        }
                    }
                }
            }
        }
    }
}