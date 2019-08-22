package com.devdigital.fragmentstatemanager

import FragmentHolder
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.devdigital.fragmentstatemanager.fragments.DashboardFragment
import com.devdigital.fragmentstatemanager.utils.BottomMenu
import com.devdigital.fragmentstatemanager.utils.FragmentStateManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var fragmentStateManager: FragmentStateManager
    private val INSTANCE_TAB_ID: String = "TAB_ID"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initStateManager()
        var index = BottomMenu.DASHBOARD.position
        if (savedInstanceState != null) {
            //Remove fragment if already present, happen when activity open from background
            index = savedInstanceState.getInt(INSTANCE_TAB_ID, 1)
            fragmentStateManager.removeFragment(index)
        }

        btnManagement.setOnClickListener {
            fragmentStateManager.changeFragment(BottomMenu.MANAGEMENT.position)
        }
        btnDashboard.setOnClickListener {
            fragmentStateManager.changeFragment(BottomMenu.DASHBOARD.position)
        }
        btnSetting.setOnClickListener {
            fragmentStateManager.changeFragment(BottomMenu.SETTINGS.position)
        }

    }

    private fun initStateManager() {
        fragmentStateManager =
            object : FragmentStateManager(container, supportFragmentManager) {

                override fun getItem(position: Int): Fragment? {
                    // A switch case should be here for showing different fragments for
                    // different positions which is omitted for simplicity
                    return FragmentHolder.getFragmentByPosition(position)
                }
            }
        fragmentStateManager.setNavigationChangeListener(btnDashboard)

    }

    private fun loadFragment() {
        val manager = supportFragmentManager
        val beginTransaction = manager.beginTransaction()
        beginTransaction.add(R.id.container, DashboardFragment())
        beginTransaction.addToBackStack("Dashboard")
        beginTransaction.commit()
    }
}
