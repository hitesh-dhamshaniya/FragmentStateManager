import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.devdigital.fragmentstatemanager.R
import com.devdigital.fragmentstatemanager.fragments.BaseFragment
import com.devdigital.fragmentstatemanager.fragments.DashboardFragment
import com.devdigital.fragmentstatemanager.fragments.SettingFragment
import com.devdigital.fragmentstatemanager.fragments.SignUpFragment
import com.devdigital.fragmentstatemanager.utils.BottomMenu
import com.devdigital.fragmentstatemanager.utils.ManagementFragment
import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class FragmentHolder : BaseFragment() {
    override fun getResourceId(): Int {
        return R.layout.fragment_holder
    }

    override fun onFragmentCreate() {
    }


    private var TAG = FragmentHolder::class.java.name

    private val baseFragment: Fragment by lazy {
        when (TAG) {
            FRAGMENT_MANAGEMENT -> ManagementFragment()
            FRAGMENT_DASHBOARD -> DashboardFragment()
            FRAGMENT_SETTING -> SettingFragment()
            FRAGMENT_SIGNUP_LOGIN -> SignUpFragment()
            else -> DashboardFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_holder, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated")
        super.onViewCreated(view, savedInstanceState)

        if (childFragmentManager.findFragmentById(R.id.fragmentHolderFrame) == null) {
            childFragmentManager.beginTransaction()
                .replace(R.id.fragmentHolderFrame, baseFragment)
                .commitAllowingStateLoss()
        }
    }

    fun nextChild(fragment: Fragment) {
        if (isAdded) {
            childFragmentManager.beginTransaction()
                .addToBackStack(null)
                .setReorderingAllowed(true)
                .replace(R.id.fragmentHolderFrame, fragment)
                .commitAllowingStateLoss()
        }
    }

    companion object {
        private val mFragmentHolderMap = HashMap<String, FragmentHolder?>()
        private const val FRAGMENT_DASHBOARD = "FRAGMENT_DASHBOARD"
        private const val FRAGMENT_MANAGEMENT = "FRAGMENT_MANAGEMENT"
        private const val FRAGMENT_SETTING = "FRAGMENT_SETTING"
        private const val FRAGMENT_SIGNUP_LOGIN = "FRAGMENT_SIGNUP_LOGIN"


        val managementFragmentHolder: FragmentHolder get() = getInstance(FRAGMENT_MANAGEMENT)
        val dashboardFragmentHolder: FragmentHolder get() = getInstance(FRAGMENT_DASHBOARD)
        val settingFragmentHolder: FragmentHolder get() = getInstance(FRAGMENT_SETTING)
        val signUpFragmentHolder: FragmentHolder get() = getInstance(FRAGMENT_SIGNUP_LOGIN)


        private fun getInstance(tab: String): FragmentHolder {
            if (mFragmentHolderMap.isEmpty()) {
                mFragmentHolderMap[FRAGMENT_MANAGEMENT] = null
                mFragmentHolderMap[FRAGMENT_DASHBOARD] = null
                mFragmentHolderMap[FRAGMENT_SETTING] = null
                mFragmentHolderMap[FRAGMENT_SIGNUP_LOGIN] = null
            }

            var holder: FragmentHolder? = mFragmentHolderMap[tab]
            if (holder == null) {
                holder = FragmentHolder()
                holder.TAG = tab
                mFragmentHolderMap[tab] = holder
            }

            return holder
        }

        fun getFragmentByPosition(position: Int): FragmentHolder {
            return when (position) {
                BottomMenu.MANAGEMENT.position -> getInstance(FRAGMENT_MANAGEMENT)
                BottomMenu.DASHBOARD.position -> getInstance(FRAGMENT_DASHBOARD)
                BottomMenu.SETTINGS.position -> getInstance(FRAGMENT_SETTING)
                BottomMenu.SIGNUP.position -> getInstance(FRAGMENT_SIGNUP_LOGIN)
                else -> FragmentHolder.dashboardFragmentHolder
            }
        }

        //Clear Fragment stack
        fun clear() {
            mFragmentHolderMap.clear()
        }
    }

}