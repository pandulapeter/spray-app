package com.gyorgyzoltan.sprayApp.presentation.feature.shared

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.gyorgyzoltan.sprayApp.presentation.R
import com.gyorgyzoltan.sprayApp.presentation.databinding.ViewErrorBinding
import com.gyorgyzoltan.sprayApp.presentation.utils.dimension

class ErrorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = ViewErrorBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        context.dimension(R.dimen.large_content_padding).let { setPadding(it, it, it, it) }
    }

    override fun setOnClickListener(onClickListener: OnClickListener?) = binding.tryAgainButton.setOnClickListener(onClickListener)
}