package com.devdigital.fragmentstatemanager.components

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import com.devdigital.fragmentstatemanager.R
import kotlinx.android.synthetic.main.component_newedit_text.view.*

/**
 * @author Hitesh
 * @version 1.0
 * @since 22-08-2019
 */
class NewEditText : LinearLayout {

    lateinit var edtBox: AppCompatEditText
    lateinit var tvHint: AppCompatTextView

    @JvmOverloads
    constructor(mContext: Context) : super(mContext) {
        init(mContext)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @JvmOverloads
    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(context)
    }

    private fun init(mContext: Context) {
        val mRootView = LayoutInflater.from(mContext).inflate(
            R.layout.component_newedit_text, this, true
        )

        edtBox = mRootView.edtBox
        tvHint = mRootView.tvHint

    }

    fun setHint(hint: String) {
        tvHint.text = hint
    }

    fun getHint() = tvHint.hint

}