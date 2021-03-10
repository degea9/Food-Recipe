package com.degea9.android.foodrecipe.core

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

open class BaseFragment: Fragment() {

    fun showKeyboard(){
        val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE)  as InputMethodManager?
        imm?.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }
    fun hideKeyboard(){
        val view = requireView()
        view.let {
            val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE)  as InputMethodManager?
            imm?.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }
}