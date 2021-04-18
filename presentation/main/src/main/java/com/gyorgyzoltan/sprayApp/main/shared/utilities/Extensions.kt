package com.gyorgyzoltan.sprayApp.main.shared.utilities

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
import coil.load
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.transition.Hold
import com.google.android.material.transition.MaterialFadeThrough
import com.google.android.material.transition.MaterialSharedAxis
import com.gyorgyzoltan.sprayApp.main.R

fun Context.color(@ColorRes colorResourceId: Int) = ContextCompat.getColor(this, colorResourceId)

fun Context.dimension(@DimenRes dimensionResourceId: Int) = resources.getDimensionPixelSize(dimensionResourceId)

fun Context.drawable(@DrawableRes drawableResId: Int) = AppCompatResources.getDrawable(this, drawableResId)

fun Context.tintedDrawable(@DrawableRes drawableResourceId: Int, @ColorInt tint: Int) = drawable(drawableResourceId)?.let { drawable ->
    DrawableCompat.wrap(drawable.mutate()).apply {
        DrawableCompat.setTint(this, tint)
        DrawableCompat.setTintMode(this, PorterDuff.Mode.SRC_IN)
    }
}

fun View.hideKeyboard() {
    clearFocus()
    (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(windowToken, 0)
}

fun View.showSnackbar(
    @StringRes messageResourceId: Int,
    @StringRes actionResourceId: Int? = null,
    action: (() -> Unit)? = null
) = Snackbar.make(this, messageResourceId, Snackbar.LENGTH_SHORT).apply {
    if (actionResourceId != null && action != null) {
        setAction(actionResourceId) { action() }
    }
}.show()

fun View.openUrl(url: String) = Intent(Intent.ACTION_VIEW, Uri.parse(url)).let { intent ->
    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    } else {
        showSnackbar(R.string.app_not_found)
    }
}

inline fun <T> LiveData<Consumable<T>>.observeEvents(viewLifecycleOwner: LifecycleOwner, crossinline blockNonNullValueFunction: (T) -> Unit) {
    observe(viewLifecycleOwner, Observer {
        val value: T = it.consume() ?: return@Observer
        blockNonNullValueFunction(value)
    })
}

inline fun <T : Fragment> T.withArguments(bundleOperations: (Bundle) -> Unit): T = apply {
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

@set:BindingAdapter("android:visibility")
var View.visible
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }

var View.notInvisible
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.INVISIBLE
    }

@BindingAdapter("url")
fun ImageView.loadUrl(url: String?) = load(url) { crossfade(true) }