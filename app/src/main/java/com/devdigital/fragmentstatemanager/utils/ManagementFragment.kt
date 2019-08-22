package com.devdigital.fragmentstatemanager.utils


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.devdigital.fragmentstatemanager.R
import com.devdigital.fragmentstatemanager.fragments.BaseFragment


/**
 * A simple [Fragment] subclass.
 *
 */
class ManagementFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_management, container, false)
    }

    override fun onFragmentCreate() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getResourceId() = R.layout.fragment_management
}
