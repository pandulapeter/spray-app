package com.gyorgyzoltan.sprayApp.presentation.feature.shared

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.annotation.StringRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.gyorgyzoltan.sprayApp.presentation.BR
import com.gyorgyzoltan.sprayApp.presentation.R
import com.gyorgyzoltan.sprayApp.presentation.databinding.FragmentListBinding
import com.gyorgyzoltan.sprayApp.presentation.feature.shared.list.BaseAdapter
import com.gyorgyzoltan.sprayApp.presentation.feature.shared.list.ListItem

abstract class ListFragment<VM : ListViewModel<LI>, LI : ListItem>(
    @StringRes private val titleResourceId: Int
) : BaseFragment<FragmentListBinding>(R.layout.fragment_list) {

    protected abstract val viewModel: VM
    protected open val actions: List<Pair<Int, () -> Unit>> = emptyList()
    private val onScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) = binding.appBar.updateAppBarLiftedState()
    }

    protected abstract fun createAdapter(): BaseAdapter<LI>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postponeEnterTransition()
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.setVariable(BR.viewModel, viewModel)
        binding.root.setBackgroundColor(Color.TRANSPARENT)
        binding.appBar.setup()
        binding.swipeRefreshLayout.setup()
        binding.recyclerView.setup()
    }

    override fun onResume() {
        super.onResume()
        binding.recyclerView.addOnScrollListener(onScrollListener)
    }

    override fun onPause() {
        super.onPause()
        binding.recyclerView.removeOnScrollListener(onScrollListener)
    }

    private fun AppBarView.setup() = setup(
        titleResourceId = titleResourceId,
        actions = actions,
        isRoot = parentFragment?.childFragmentManager?.backStackEntryCount ?: 0 <= 1,
        activity = requireActivity()
    )

    private fun SwipeRefreshLayout.setup() = setOnRefreshListener { viewModel.loadData(true) }

    private fun RecyclerView.setup() {
        val listAdapter = createAdapter()
        viewModel.items.observe(viewLifecycleOwner) { listAdapter.submitList(it, ::onListUpdated) }
        adapter = listAdapter
        layoutManager = LinearLayoutManager(context)
        setHasFixedSize(true)
        post { startPostponedEnterTransition() }
    }

    private fun AppBarView.updateAppBarLiftedState() {
        isLifted = binding.recyclerView.computeVerticalScrollOffset() != 0
    }

    private fun onListUpdated() {
        try {
            binding.appBar.run {
                postDelayed({
                    try {
                        updateAppBarLiftedState()
                    } catch (_: IllegalStateException) {
                    }
                }, 300)
            }
        } catch (_: IllegalStateException) {
        }
    }
}