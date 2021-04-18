package com.gyorgyzoltan.sprayApp.main.shared.list

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.annotation.StringRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.gyorgyzoltan.sprayApp.main.BR
import com.gyorgyzoltan.sprayApp.main.R
import com.gyorgyzoltan.sprayApp.main.databinding.FragmentListBinding
import com.gyorgyzoltan.sprayApp.main.shared.BaseFragment
import com.gyorgyzoltan.sprayApp.main.shared.view.AppBarView
import com.gyorgyzoltan.sprayApp.main.shared.utilities.showSnackbar

abstract class ListFragment<VM : ListViewModel<LI>, LI : ListItem>(
    @StringRes private val titleResourceId: Int,
    @StringRes private val subtitleResourceId: Int? = null
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

    protected fun showErrorSnackbar() = binding.root.showSnackbar(
        messageResourceId = R.string.general_error,
        actionResourceId = R.string.general_try_again,
        action = { viewModel.loadData(true) }
    )

    private fun AppBarView.setup() = setup(
        titleResourceId = titleResourceId,
        subtitleResourceId = subtitleResourceId,
        actions = actions,
        isRoot = parentFragment?.childFragmentManager?.backStackEntryCount ?: 0 <= 1,
        navigateBack = { parentFragment?.childFragmentManager?.popBackStack() }
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