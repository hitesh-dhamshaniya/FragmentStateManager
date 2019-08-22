package com.devdigital.fragmentstatemanager.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import com.devdigital.fragmentstatemanager.R
import kotlinx.android.synthetic.main.fragment_sign_up.view.*

/**
 * A simple [Fragment] subclass.
 *
 */
class SignUpFragment : BaseFragment() {

    companion object {
        val KEY_EXTRA_TITLE = "key.extra.title"
        fun newInstance(message: String) = SignUpFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_EXTRA_TITLE, message)
            }
        }
    }

    override fun onFragmentCreate() {
        arguments?.let {
            mRootView.tvSingTitle.text = it.getString(DashboardFragment.KEY_EXTRA_TITLE)
        }
    }

    override fun getResourceId() = R.layout.fragment_sign_up
}
