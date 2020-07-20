package com.gyorgyzoltan.sprayApp.feature.tutorial

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import com.gyorgyzoltan.sprayApp.R
import com.gyorgyzoltan.sprayApp.databinding.ViewTutorialPageBinding

class TutorialPagerAdapter(
    private val context: Context,
    private val onBoardingSlides: List<TutorialPage>
) : PagerAdapter() {

    override fun isViewFromObject(view: View, obj: Any) = view == obj

    override fun getCount() = onBoardingSlides.size

    override fun instantiateItem(collection: ViewGroup, position: Int): Any =
        DataBindingUtil.inflate<ViewTutorialPageBinding>(LayoutInflater.from(context), R.layout.view_tutorial_page, collection, true).apply {
            viewModel = onBoardingSlides[position]
            root.tag = this
        }.root

    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) = collection.removeView(view as View)

}