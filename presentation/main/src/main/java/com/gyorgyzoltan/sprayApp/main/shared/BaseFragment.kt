package com.gyorgyzoltan.sprayApp.main.shared

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.gyorgyzoltan.sprayApp.main.R
import com.gyorgyzoltan.sprayApp.main.shared.utilities.AutoClearedValue

abstract class BaseFragment<B : ViewDataBinding>(
    @LayoutRes private val layoutResourceId: Int
) : Fragment() {

    protected var binding by AutoClearedValue<B>()
        private set
    protected val currentFragment get() = childFragmentManager.findFragmentById(R.id.fragment_container) as? BaseFragment<*>?
    protected val activityFragmentManager get() = activity?.supportFragmentManager
    protected val navigator get() = activity as? Navigator

    final override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        DataBindingUtil.inflate<B>(inflater, layoutResourceId, container, false).also {
            it.lifecycleOwner = viewLifecycleOwner
            binding = it
        }.root

    open fun onBackPressed(): Boolean = if (currentFragment?.onBackPressed() != true) childFragmentManager.popBackStackImmediate() else true
}