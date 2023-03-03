package com.hmshohrab.communityfinance.core

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView


interface BaseFragmentCommunicator {

    fun startActivity(clz: Class<*>?, bundle: Bundle?)
    fun setupActionBar(toolbar: Toolbar, enableBackButton: Boolean)
    fun showDebugToast(msg: String)
    fun showToast(msg: String)

}