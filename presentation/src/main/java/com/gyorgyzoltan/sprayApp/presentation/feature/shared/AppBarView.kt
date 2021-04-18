package com.gyorgyzoltan.sprayApp.presentation.feature.shared

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.FragmentActivity
import com.google.android.material.appbar.AppBarLayout
import com.gyorgyzoltan.sprayApp.presentation.R
import com.gyorgyzoltan.sprayApp.presentation.databinding.ViewAppBarBinding
import com.gyorgyzoltan.sprayApp.presentation.utils.dimension
import com.gyorgyzoltan.sprayApp.presentation.utils.visible

internal class AppBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppBarLayout(context, attrs, defStyleAttr) {

    private val binding = ViewAppBarBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        isLiftOnScroll = true
        val typedValue = TypedValue()
        context.theme.resolveAttribute(android.R.attr.windowBackground, typedValue, true)
        if (typedValue.type >= TypedValue.TYPE_FIRST_COLOR_INT && typedValue.type <= TypedValue.TYPE_LAST_COLOR_INT) {
            setBackgroundColor(typedValue.data)
        }
    }

    fun setup(
        @StringRes titleResourceId: Int,
        actions: List<Pair<Int, () -> Unit>>,
        isRoot: Boolean,
        activity: FragmentActivity
    ) {
        binding.toolbar.title = context.getString(titleResourceId)
        binding.backButton.run {
            visible = !isRoot
            setOnClickListener { activity.onBackPressed() }
        }
        binding.actionsContainer.run {
            val smallContentPadding = context.dimension(R.dimen.small_content_padding)
            if (actions.isNotEmpty()) {
                setPadding(0, 0, context.dimension(R.dimen.content_padding), 0)
            }
            actions.forEach { action ->
                addView(AppCompatImageView(context).apply {
                    setImageResource(action.first)
                    setPadding(smallContentPadding, smallContentPadding, smallContentPadding, smallContentPadding)
                    val outValue = TypedValue()
                    context.theme.resolveAttribute(android.R.attr.selectableItemBackgroundBorderless, outValue, true)
                    setBackgroundResource(outValue.resourceId)
                    setOnClickListener { action.second() }
                }, LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT))
            }
        }
    }
}