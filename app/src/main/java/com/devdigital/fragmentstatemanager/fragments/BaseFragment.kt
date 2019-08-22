package com.devdigital.fragmentstatemanager.fragments


import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * A simple [Fragment] subclass.
 *
 */
abstract class BaseFragment : Fragment() {

    lateinit var mRootView: View
    lateinit var mActivity: Activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.mActivity = activity!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mRootView = inflater.inflate(getResourceId(), container, false)
        onFragmentCreate()
        return mRootView
    }

    abstract fun onFragmentCreate()

    abstract fun getResourceId(): Int
}
