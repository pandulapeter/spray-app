package com.gyorgyzoltan.sprayApp.main.shared.list

import androidx.annotation.CallSuper
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.gyorgyzoltan.sprayApp.main.BR

abstract class BaseViewHolder<B : ViewDataBinding, M : ListItem>(protected val binding: B) : RecyclerView.ViewHolder(binding.root) {

    @CallSuper
    open fun bind(uiModel: M) = binding.run {
        setVariable(BR.uiModel, uiModel)
        executePendingBindings()
    }
}