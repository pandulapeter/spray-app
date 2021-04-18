package com.gyorgyzoltan.sprayApp.main.shared

interface Navigator {

    fun navigateToMain()

    fun navigateToTutorial(isFirstTutorial: Boolean)

    fun createWorkFragmentInstance() : BaseFragment<*>

    fun createStatisticsFragmentInstance() : BaseFragment<*>

    fun createHelpFragmentInstance() : BaseFragment<*>
}