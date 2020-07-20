package com.gyorgyzoltan.sprayApp.feature.main.help

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.gyorgyzoltan.sprayApp.R
import com.gyorgyzoltan.sprayApp.feature.SprayAppActivity
import com.gyorgyzoltan.sprayApp.feature.main.help.list.ClickableItemViewHolder
import com.gyorgyzoltan.sprayApp.feature.main.help.list.HelpAdapter
import com.gyorgyzoltan.sprayApp.feature.main.help.list.HelpListItem
import com.gyorgyzoltan.sprayApp.feature.shared.ListFragment
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
            R.string.help_call_customer_support -> callCustomerSupport()
            R.string.help_contact_us -> openEmailComposer()
        }
    }

    private fun navigateToTutorial() = (activity as? SprayAppActivity?)?.navigateToTutorial(false)

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

    companion object {
        private const val CUSTOMER_SUPPORT_PHONE_NUMBER = "0748545931"
        private const val CUSTOMER_SUPPORT_EMAIL_ADDRESS = "gyorgy3zoltan@yahoo.com"

        fun newInstance() = HelpFragment()
    }
}