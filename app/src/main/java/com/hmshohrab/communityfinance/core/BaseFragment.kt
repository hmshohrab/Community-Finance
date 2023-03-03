package com.hmshohrab.communityfinance.core

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<ViewModel : BaseViewModel> : Fragment() {

    abstract val viewModel: ViewModel

    private lateinit var communicator: BaseFragmentCommunicator


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseFragmentCommunicator) {
            communicator = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         setUpView()
        observeClickEvents()
        observeViewModelEvents()

    }

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


    fun startActivity(clz: Class<*>?, bundle: Bundle?) {
        communicator.startActivity(clz, bundle)
    }

    fun setupActionBar(toolbar: Toolbar, enableBackButton: Boolean) {
        communicator.setupActionBar(toolbar, enableBackButton)
    }

    fun showDebugToast(msg: String) {
        communicator.showDebugToast(msg)
    }

    fun showToast(msg: String) {
        communicator.showToast(msg)
    }

    private fun getViewModelClass(): Class<ViewModel> {
        val type =
            (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]   // index of 0 means first argument of Base class param
        return type as Class<ViewModel>
    }

}