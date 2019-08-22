package com.devdigital.fragmentstatemanager.fragments

import FragmentHolder
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.devdigital.fragmentstatemanager.R
import com.devdigital.fragmentstatemanager.utils.MyLogger
import kotlinx.android.synthetic.main.fragment_setting.view.*

/**
 * A simple [Fragment] subclass.
 *
 */
class SettingFragment : BaseFragment() {

    companion object {
        var count = 1
        val KEY_EXTRA_TITLE = "key.extra.title"
        fun newInstance(message: String) = SettingFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_EXTRA_TITLE, message)
            }
        }
    }

    override fun onFragmentCreate() {
        arguments?.let {
            mRootView.tvSettingTitle.text = it.getString(KEY_EXTRA_TITLE)
        }

        mRootView.btnNextSetting.setOnClickListener {
            FragmentHolder.settingFragmentHolder.nextChild(newInstance("Setting New Instance ${++count}"))
        }

        MyLogger().logWithLink("Setting Fragment", "Hello Setting")
    }

    override fun getResourceId() = R.layout.fragment_setting


}
