package com.devdigital.fragmentstatemanager.fragments


import FragmentHolder
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.devdigital.fragmentstatemanager.R
import kotlinx.android.synthetic.main.fragment_dashboard.view.*

/**
 * A simple [Fragment] subclass.
 *
 */
class DashboardFragment : BaseFragment() {

    companion object {
        var count = 0
        val KEY_EXTRA_TITLE = "key.extra.title"
        fun newInstance(message: String) = DashboardFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_EXTRA_TITLE, message)
            }
        }
    }

    override fun onFragmentCreate() {

        arguments?.let {
            mRootView.tvTitle.text = it.getString(KEY_EXTRA_TITLE)
        }

        mRootView.btnNextChild.setOnClickListener {
            FragmentHolder.dashboardFragmentHolder.nextChild(newInstance("Next Child ${++count}"))
        }

        mRootView.newFirstName.setHint("First Name")
        mRootView.newSecondName.setHint("Last Name")
    }

    override fun getResourceId() = R.layout.fragment_dashboard


}
