package com.gyorgyzoltan.sprayApp.help.overview

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.gyorgyzoltan.sprayApp.help.licences.LicencesFragment
import com.gyorgyzoltan.sprayApp.help.overview.list.HelpAdapter
import com.gyorgyzoltan.sprayApp.help.overview.list.HelpClickableItemViewHolder
import com.gyorgyzoltan.sprayApp.help.overview.list.HelpListItem
import com.gyorgyzoltan.sprayApp.main.R
import com.gyorgyzoltan.sprayApp.main.shared.list.ListFragment
import com.gyorgyzoltan.sprayApp.main.shared.utilities.TransitionType
import com.gyorgyzoltan.sprayApp.main.shared.utilities.handleReplace
import com.gyorgyzoltan.sprayApp.main.shared.utilities.openUrl
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class HelpFragment : ListFragment<HelpViewModel, HelpListItem>(R.string.main_help) {

    override val viewModel by viewModel<HelpViewModel>()
    private val packageName get() = context?.applicationInfo?.packageName.orEmpty()

    override fun createAdapter() = HelpAdapter(
        scope = viewModel.viewModelScope,
        onItemClicked = ::onItemClicked
    )

    private fun onItemClicked(uiModel: HelpClickableItemViewHolder.UiModel) {
        when (uiModel.textResourceId) {
            R.string.help_show_tutorial -> navigateToTutorial()
            R.string.help_check_for_updates -> openStoreListing()
            R.string.help_call_customer_support -> callCustomerSupport()
            R.string.help_contact_us -> openEmailComposer()
            R.string.help_share -> openShareSheet()
            R.string.help_open_source_licences -> navigateToLicences()
        }
    }

    private fun navigateToTutorial() = navigator?.navigateToTutorial(false)

    private fun openStoreListing() {
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(DEEPLINK_URL_PREFIX + packageName)))
        } catch (_: ActivityNotFoundException) {
            binding.recyclerView.openUrl(FULL_URL_PREFIX + packageName)
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
            }.putExtra(Intent.EXTRA_TEXT, getString(R.string.help_share_text, FULL_URL_PREFIX + packageName)).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK), null
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
        private const val FULL_URL_PREFIX = "https://play.google.com/store/apps/details?id="
        private const val DEEPLINK_URL_PREFIX = "market://details?id="

        fun newInstance() = HelpFragment()
    }
}