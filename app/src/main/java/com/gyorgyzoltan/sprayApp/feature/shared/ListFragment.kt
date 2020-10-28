package com.gyorgyzoltan.sprayApp.feature.shared

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.gyorgyzoltan.sprayApp.BR
import com.gyorgyzoltan.sprayApp.R
import com.gyorgyzoltan.sprayApp.databinding.FragmentListBinding
import com.gyorgyzoltan.sprayApp.feature.shared.list.BaseAdapter
import com.gyorgyzoltan.sprayApp.feature.shared.list.ListItem

abstract class ListFragment<VM : ListViewModel<LI>, LI : ListItem>(
    @StringRes private val titleResourceId: Int
) : BaseFragment<FragmentListBinding>(R.layout.fragment_list) {

    protected abstract val viewModel: VM
    protected open val actions: List<Pair<Int, () -> Unit>> = emptyList()

    protected abstract fun createAdapter(): BaseAdapter<LI>

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.setVariable(BR.viewModel, viewModel)
        binding.root.setBackgroundColor(Color.TRANSPARENT)
        binding.appBar.setup(titleResourceId, actions, parentFragment?.childFragmentManager?.backStackEntryCount ?: 0 <= 1, requireActivity())
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val listAdapter = createAdapter()
        viewModel.items.observe(viewLifecycleOwner, Observer { listAdapter.submitList(it, ::onListUpdated) })
        binding.recyclerView.run {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    private fun onListUpdated() {
        try {
            binding.appBar.run {
                postDelayed({
                    try {
                        setLifted(binding.recyclerView.computeVerticalScrollOffset() != 0)
                    } catch (_: IllegalStateException) {
                    }
                }, 300)
            }
        } catch (_: IllegalStateException) {
        }
    }
}