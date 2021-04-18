package com.gyorgyzoltan.sprayApp.presentation.utils

import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.ViewCompat
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import coil.load
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.transition.Hold
import com.google.android.material.transition.MaterialFadeThrough
import com.google.android.material.transition.MaterialSharedAxis
import com.gyorgyzoltan.sprayApp.presentation.R
import com.gyorgyzoltan.sprayApp.utils.Consumable

internal fun Context.animatedDrawable(@DrawableRes drawableId: Int) = AnimatedVectorDrawableCompat.create(this, drawableId)!!

internal fun Context.color(@ColorRes colorResourceId: Int) = ContextCompat.getColor(this, colorResourceId)

internal fun Context.dimension(@DimenRes dimensionResourceId: Int) = resources.getDimensionPixelSize(dimensionResourceId)

internal fun Context.drawable(@DrawableRes drawableResId: Int) = AppCompatResources.getDrawable(this, drawableResId)

internal fun Context.tintedDrawable(@DrawableRes drawableResourceId: Int, @ColorInt tint: Int) = drawable(drawableResourceId)?.let { drawable ->
    DrawableCompat.wrap(drawable.mutate()).apply {
        DrawableCompat.setTint(this, tint)
        DrawableCompat.setTintMode(this, PorterDuff.Mode.SRC_IN)
    }
}

internal fun View.hideKeyboard() {
    clearFocus()
    (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(windowToken, 0)
}

internal fun View.showSnackbar(
    @StringRes messageResourceId: Int,
    @StringRes actionResourceId: Int? = null,
    action: (() -> Unit)? = null
) = Snackbar.make(this, messageResourceId, Snackbar.LENGTH_SHORT).apply {
    if (actionResourceId != null && action != null) {
        setAction(actionResourceId) { action() }
    }
}.show()

internal fun View.openUrl(url: String) = Intent(Intent.ACTION_VIEW, Uri.parse(url)).let { intent ->
    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    } else {
        showSnackbar(R.string.app_not_found)
    }
}

internal inline fun <T> LiveData<Consumable<T>>.observeEvents(viewLifecycleOwner: LifecycleOwner, crossinline blockNonNullValueFunction: (T) -> Unit) {
    observe(viewLifecycleOwner, Observer {
        val value: T = it.consume() ?: return@Observer
        blockNonNullValueFunction(value)
    })
}

internal inline fun <T : Fragment> T.withArguments(bundleOperations: (Bundle) -> Unit): T = apply {
    arguments = Bundle().apply { bundleOperations(this) }
}

inline fun <reified T : Fragment> FragmentManager.handleReplace(
    tag: String = T::class.java.name,
    addToBackStack: Boolean = false,
    sharedElements: List<View>? = null,
    transitionType: TransitionType? = TransitionType.SIBLING,
    @IdRes containerId: Int = R.id.fragment_container,
    crossinline newInstance: () -> T
) {
    beginTransaction().apply {
        val currentFragment = findFragmentById(containerId)
        val newFragment = findFragmentByTag(tag) ?: newInstance()
        when (transitionType) {
            TransitionType.SIBLING -> {
                currentFragment?.let {
                    currentFragment.exitTransition = MaterialFadeThrough()
                    currentFragment.reenterTransition = MaterialFadeThrough()
                    newFragment.enterTransition = MaterialFadeThrough()
                    newFragment.returnTransition = MaterialFadeThrough()
                }
            }
            TransitionType.MODAL -> {
                if (sharedElements.isNullOrEmpty()) {
                    currentFragment?.exitTransition = MaterialSharedAxis(MaterialSharedAxis.Y, true)
                    currentFragment?.reenterTransition = MaterialSharedAxis(MaterialSharedAxis.Y, false)
                    newFragment.enterTransition = MaterialSharedAxis(MaterialSharedAxis.Y, true)
                    newFragment.returnTransition = MaterialSharedAxis(MaterialSharedAxis.Y, false)
                } else {
                    currentFragment?.exitTransition = Hold()
                    currentFragment?.reenterTransition = Hold()
                }
            }
            null -> Unit
        }
        sharedElements?.forEach { sharedElement -> ViewCompat.getTransitionName(sharedElement)?.let { addSharedElement(sharedElement, it) } }
        replace(containerId, newFragment, tag)
        if (addToBackStack) {
            addToBackStack(null)
        }
        setReorderingAllowed(true)
        commitAllowingStateLoss()
    }
}

internal inline fun <T> LiveData<T>.observe(owner: LifecycleOwner, crossinline onChanged: (T) -> Unit) = Observer<T> { t -> onChanged.invoke(t) }.also {
    observe(owner, it)
}

@set:BindingAdapter("android:visibility")
internal var View.visible
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }

internal var View.notInvisible
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.INVISIBLE
    }

@BindingAdapter("url")
internal fun ImageView.loadUrl(url: String?) = load(url)