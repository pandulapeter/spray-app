package com.gyorgyzoltan.sprayApp.tutorial.overview

import android.animation.LayoutTransition
import android.os.Bundle
import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.viewpager.widget.ViewPager
import com.gyorgyzoltan.sprayApp.domain.tutorial.SetHasSeenTutorialUseCase
import com.gyorgyzoltan.sprayApp.main.overview.MainFragment
import com.gyorgyzoltan.sprayApp.main.shared.BaseFragment
import com.gyorgyzoltan.sprayApp.main.shared.utilities.consume
import com.gyorgyzoltan.sprayApp.main.shared.utilities.handleReplace
import com.gyorgyzoltan.sprayApp.main.shared.utilities.notInvisible
import com.gyorgyzoltan.sprayApp.main.shared.utilities.withArguments
import com.gyorgyzoltan.sprayApp.tutorial.R
import com.gyorgyzoltan.sprayApp.tutorial.databinding.FragmentTutorialBinding
import com.gyorgyzoltan.sprayApp.tutorial.databinding.ViewTutorialPageBinding
import com.gyorgyzoltan.sprayApp.main.shared.utilities.BundleArgumentDelegate
import org.koin.android.ext.android.inject
import kotlin.math.abs

class TutorialFragment : BaseFragment<FragmentTutorialBinding>(R.layout.fragment_tutorial) {

    private val tutorialPages = listOf(
        TutorialPage(R.string.tutorial_page_1),
        TutorialPage(R.string.tutorial_page_2),
        TutorialPage(R.string.tutorial_page_3),
        TutorialPage(R.string.tutorial_page_4)
    )
    private val decelerateInterpolator = DecelerateInterpolator()
    private var isFirstTutorial = true
    private val setHasSeenTutorial by inject<SetHasSeenTutorialUseCase>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        isFirstTutorial = arguments?.isFirstTutorial == true
        binding.viewPager.adapter = TutorialPagerAdapter(requireContext(), tutorialPages)
        binding.viewPager.setPageTransformer(false) { page, position ->
            (page.tag as? ViewTutorialPageBinding?)?.run {
                textView.run {
                    translationX = decelerateInterpolator.getInterpolation(position) * width * 0.25f
                    alpha = 1 - abs(position * 2f)
                }
            }
        }
        binding.doneButton.setOnClickListener { onDoneButtonPressed() }
        binding.skipButton.setOnClickListener { onSkipButtonPressed() }
        binding.closeButton.setOnClickListener { onCloseButtonPressed() }
        binding.nextButton.setOnClickListener { onNextButtonPressed() }
        binding.skipButton.notInvisible = isFirstTutorial
        binding.closeButton.notInvisible = !isFirstTutorial
        binding.pagerIndicator.setViewPager(binding.viewPager)
        binding.root.post { binding.container.layoutTransition = LayoutTransition() }
    }

    override fun onStart() {
        super.onStart()
        binding.pagerIndicator.setViewPager(binding.viewPager)
        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) = Unit

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) = Unit

            override fun onPageSelected(position: Int) = onTutorialPageSelected(position)
        })
        onTutorialPageSelected(binding.viewPager.currentItem)
    }

    override fun onStop() {
        super.onStop()
        binding.pagerIndicator.releaseViewPager()
        binding.viewPager.clearOnPageChangeListeners()
    }

    override fun onBackPressed() = if (binding.viewPager.currentItem == 0) {
        super.onBackPressed()
    } else consume {
        binding.viewPager.currentItem--
    }

    private fun onDoneButtonPressed() {
        if (isFirstTutorial) {
            onSkipButtonPressed()
        } else {
            onCloseButtonPressed()
        }
    }

    private fun onSkipButtonPressed() {
        setHasSeenTutorial()
        activityFragmentManager?.handleReplace(
            newInstance = MainFragment.Companion::newInstance
        )
    }

    private fun onCloseButtonPressed() {
        activityFragmentManager?.popBackStack()
    }

    private fun onNextButtonPressed() = binding.viewPager.currentItem++

    private fun onTutorialPageSelected(position: Int) {
        binding.nextButton.notInvisible = position != tutorialPages.lastIndex
        binding.skipButton.notInvisible = isFirstTutorial && position != tutorialPages.lastIndex
        binding.doneButton.notInvisible = position == tutorialPages.lastIndex
    }

    companion object {
        private var Bundle.isFirstTutorial by BundleArgumentDelegate.Boolean("isFirstTutorial")

        fun newInstance(isFirstTutorial: Boolean) = TutorialFragment().withArguments {
            it.isFirstTutorial = isFirstTutorial
        }
    }
}