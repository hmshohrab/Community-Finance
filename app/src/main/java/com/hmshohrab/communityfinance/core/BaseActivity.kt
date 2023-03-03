package com.hmshohrab.communityfinance.core

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.viewbinding.BuildConfig
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hmshohrab.communityfinance.R
import com.hmshohrab.communityfinance.utils.hide
import com.hmshohrab.communityfinance.utils.show
import java.lang.reflect.ParameterizedType


abstract class BaseActivity<ViewModel : BaseViewModel> : AppCompatActivity(),
    BaseFragmentCommunicator {

    abstract val viewModel: ViewModel
    private var dialog: Dialog? = null
    var isLoaderShowing  = false
   private var toast:Toast? =null


    override fun onCreate(savedInstanceState: Bundle?) {
        // setVerifiedTheme()
        super.onCreate(savedInstanceState)

    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setUpView()
        observeClickEvents()
        observeViewModelEvents()
    }


    /**
     * Observes Rxview events when implemented
     */
    open fun observeClickEvents() {

    }

    /**
     * Used instead of onCreate
     */
    open fun setUpView() {

    }

    /**
     * Observes livedata from viewmodel
     */
    open fun observeViewModelEvents() {

    }


    override fun startActivity(clz: Class<*>?, bundle: Bundle?) {
        val intent = Intent(this, clz)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun setupActionBar(toolbar: Toolbar, enableBackButton: Boolean) {
        setSupportActionBar(toolbar)
        if (enableBackButton) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setHomeButtonEnabled(true)
        }
    }

    override fun showDebugToast(msg: String) {
        if (BuildConfig.DEBUG) {
            if (toast == null) {
                toast =Toast.makeText(this, null, Toast.LENGTH_LONG)
            }
            toast?.setText(msg)
         //   toast?.cancel()
            toast?.show()
        }
    }

    override fun showToast(msg: String) {
        if (toast == null) {
            toast =Toast.makeText(this, null, Toast.LENGTH_LONG)
        }
        toast?.setText(msg)
      //  toast?.cancel()
        toast?.show()
    }

    private fun getViewModelClass(): Class<ViewModel> {
        val type =
            (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
        return type as Class<ViewModel>
    }
}