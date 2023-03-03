package com.hmshohrab.communityfinance.ui

import android.location.Address
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hmshohrab.communityfinance.core.BaseViewModel

/**
 * BISMILLAH HIR RAHMAN NIR RAHIM
 * Created by Mohammad Shohrab on 01,March,2023
 * @Company Data Grid Limited
 * @Address House #14/A(new), Dhanmondi R/A, Dhaka 1209.
 * @Email shohrab.datagridltd@gmail.com
 */
class MainVM : BaseViewModel() {
    val loading: MutableLiveData<Boolean> = MutableLiveData()
    var address :Address? = null

}