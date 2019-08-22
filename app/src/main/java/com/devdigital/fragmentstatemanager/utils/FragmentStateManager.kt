package com.devdigital.fragmentstatemanager.utils

import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.devdigital.fragmentstatemanager.R

abstract class FragmentStateManager(private var container: ViewGroup, fm: FragmentManager) {

    private var mFragmentManager: FragmentManager = fm

    /*fun setNavigationListener(view: TabLayout) {
        //Change Fragment on navigation tab click
        TabSelectedListener(this, view)
    }*/

    fun setNavigationChangeListener(view: View) {
        val fragmentStateManager: FragmentStateManager = this
        when (view.id) {
            R.id.btnManagement -> {
                fragmentStateManager.changeFragment(BottomMenu.MANAGEMENT.position)
            }
            R.id.btnDashboard -> {
                fragmentStateManager.changeFragment(BottomMenu.DASHBOARD.position)
            }
            R.id.btnSetting -> {
                fragmentStateManager.changeFragment(BottomMenu.SETTINGS.position)
            }
        }
    }

    /**
     * Return the Fragment associated with a specified position.
     */
    abstract fun getItem(position: Int): Fragment?

    /**
     * Shows fragment at position and detaches previous fragment if exists. If fragment is found in
     * fragment manager, it is reattached else added.
     *
     * @param position
     * @return fragment at position
     */
    fun changeFragment(position: Int): Fragment {
        val tag = makeFragmentName(container.id, getItemId(position))
        val fragmentTransaction = mFragmentManager.beginTransaction()

        /*
          If fragment manager doesn't have an instance of the fragment, get an instance
          and add it to the transaction. Else, attach the instance to transaction.
         */
        var fragment = mFragmentManager.findFragmentByTag(tag)
        if (fragment == null) {
            fragment = getItem(position)
            fragmentTransaction.add(container.id, fragment!!, tag)
        } else {
            fragmentTransaction.attach(fragment)
        }

        // Detach existing primary fragment
        val curFrag = mFragmentManager.primaryNavigationFragment
        if (curFrag != null) {
            fragmentTransaction.detach(curFrag)
        }

        // Set fragment as primary navigator for child manager back stack to be handled by system
        fragmentTransaction.setPrimaryNavigationFragment(fragment)
        fragmentTransaction.setReorderingAllowed(true)
        fragmentTransaction.commitNowAllowingStateLoss()

        return fragment
    }

    /**
     * Removes Fragment from Fragment Manager and clears all saved states. Call to changeFragment()
     * will restart fragment from fresh state.
     *
     * @param position Fragment Position
     */
    fun removeFragment(position: Int) {
        val fragment =
            mFragmentManager.findFragmentByTag(makeFragmentName(container.id, getItemId(position)))
        if (fragment != null) {
            val fragmentTransaction = mFragmentManager.beginTransaction()
            fragmentTransaction.remove(fragment)
            fragmentTransaction.commitNowAllowingStateLoss()
        }
    }

    /**
     * Return a unique identifier for the item at the given position.
     *
     *
     *
     * The default implementation returns the given position.
     * Subclasses should override this method if the positions of items can change.
     *
     * @param position Position within this adapter
     * @return Unique identifier for the item at position
     */
    fun getItemId(position: Int): Long {
        return position.toLong()
    }

    private fun makeFragmentName(viewId: Int, id: Long): String {
        return "android:switcher:$viewId:$id"
    }

    /**
     * Method to invoke selected menu item.
     * Not in use but may be useful.
     */
    /*fun getNavPositionFromMenuItem(menuItemId: Int): Int {
        return when (menuItemId) {
            R.id.navigation_dashboard -> BottomMenu.HOME.position
            R.id.navigation_food -> BottomMenu.FOOD.position
            R.id.navigation_hyderation -> BottomMenu.HYDRATION.position
            R.id.navigation_medicine -> BottomMenu.MED_REMINDER.position
            R.id.navigation_side_effect -> BottomMenu.SIDE_EFFECT.position
            R.id.navigation_side_menu -> BottomMenu.SETTINGS.position
            else -> -1
        }
    }*/

    /*class TabSelectedListener(manager: FragmentStateManager, tabLayout: TabLayout) :
        TabLayout.OnTabSelectedListener {

        private var fragmentStateManager: FragmentStateManager = manager

        init {
            tabLayout.addOnTabSelectedListener(this)
        }

        override fun onTabReselected(tab: TabLayout.Tab) {
            val position = tab.position
            if (position != -1) {
                fragmentStateManager.removeFragment(position)
                fragmentStateManager.changeFragment(position)
            }
        }

        override fun onTabUnselected(tab: TabLayout.Tab) {
        }

        override fun onTabSelected(tab: TabLayout.Tab) {
            val position = tab.position
            if (position != -1) {
                fragmentStateManager.changeFragment(position)
            }
        }
    }*/
}