package com.gyorgyzoltan.sprayApp.feature.main.help

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.gyorgyzoltan.sprayApp.BuildConfig
import com.gyorgyzoltan.sprayApp.R
import com.gyorgyzoltan.sprayApp.feature.SprayAppActivity
import com.gyorgyzoltan.sprayApp.feature.main.help.licences.LicencesFragment
import com.gyorgyzoltan.sprayApp.feature.main.help.list.ClickableItemViewHolder
import com.gyorgyzoltan.sprayApp.feature.main.help.list.HelpAdapter
import com.gyorgyzoltan.sprayApp.feature.main.help.list.HelpListItem
import com.gyorgyzoltan.sprayApp.feature.shared.ListFragment
import com.gyorgyzoltan.sprayApp.utils.TransitionType
import com.gyorgyzoltan.sprayApp.utils.handleReplace
import com.gyorgyzoltan.sprayApp.utils.openUrl
import org.koin.androidx.viewmodel.ext.android.viewModel

class HelpFragment : ListFragment<HelpViewModel, HelpListItem>(R.string.main_help) {

    override val viewModel by viewModel<HelpViewModel>()

    override fun createAdapter() = HelpAdapter(
        scope = viewModel.viewModelScope,
        onItemClicked = ::onItemClicked
    )

    private fun onItemClicked(uiModel: ClickableItemViewHolder.UiModel) {
        when (uiModel.textResourceId) {
            R.string.help_show_tutorial -> navigateToTutorial()
            R.string.help_check_for_updates -> openStoreListing()
            R.string.help_call_customer_support -> callCustomerSupport()
            R.string.help_contact_us -> openEmailComposer()
            R.string.help_share -> openShareSheet()
            R.string.help_open_source_licences -> navigateToLicences()
        }
    }

    private fun navigateToTutorial() = (activity as? SprayAppActivity?)?.navigateToTutorial(false)

    private fun openStoreListing() {
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$PACKAGE_NAME")))
        } catch (_: ActivityNotFoundException) {
            binding.recyclerView.openUrl("https://play.google.com/store/apps/details?id=$PACKAGE_NAME")
        }
    }

    private fun callCustomerSupport() = startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:$CUSTOMER_SUPPORT_PHONE_NUMBER")))

    private fun openEmailComposer() = startActivity(
        Intent.createChooser(
            Intent().apply {
                action = Intent.ACTION_SENDTO
                type = "text/plain"
                data = Uri.parse("mailto:$CUSTOMER_SUPPORT_EMAIL_ADDRESS?subject=${Uri.encode(getString(R.string.app_name))}")
            }.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK), null
        )
    )

    private fun openShareSheet() = startActivity(
        Intent.createChooser(
            Intent().apply {
                action = Intent.ACTION_SEND
                type = "text/plain"
            }.putExtra(Intent.EXTRA_TEXT, getString(R.string.help_share_text, WEBSITE_URL)).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK), null
        )
    )

    private fun navigateToLicences() = parentFragment?.childFragmentManager?.handleReplace(
        transitionType = TransitionType.MODAL,
        addToBackStack = true,
        newInstance = LicencesFragment.Companion::newInstance
    ) ?: Unit

    companion object {
        private const val CUSTOMER_SUPPORT_PHONE_NUMBER = "0748545931"
        private const val CUSTOMER_SUPPORT_EMAIL_ADDRESS = "gyorgy3zoltan@yahoo.com"
        private const val PACKAGE_NAME = BuildConfig.APPLICATION_ID
        private const val WEBSITE_URL = "https://play.google.com/store/apps/details?id=$PACKAGE_NAME"

        fun newInstance() = HelpFragment()
    }
}