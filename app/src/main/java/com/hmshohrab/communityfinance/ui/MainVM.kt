package com.hmshohrab.communityfinance.ui

import android.location.Address
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hmshohrab.communityfinance.RetrofitConnect
import com.hmshohrab.communityfinance.core.BaseViewModel
import com.hmshohrab.communityfinance.data.dataSource.category.CategoryDataSource
import com.hmshohrab.communityfinance.data.dataSource.category.CategoryDataSourceImpl
import com.hmshohrab.communityfinance.data.model.Category
import com.hmshohrab.communityfinance.data.model.DiscountPartner
import com.hmshohrab.communityfinance.data.repository.category.CategoryRepository
import com.hmshohrab.communityfinance.data.repository.category.CategoryRepositoryImpl
import com.jomibaba.jomibaba.core.Resource
import kotlinx.coroutines.launch

/**
 * BISMILLAH HIR RAHMAN NIR RAHIM
 * Created by Mohammad Shohrab on 01,March,2023
 * @Company Data Grid Limited
 * @Address House #14/A(new), Dhanmondi R/A, Dhaka 1209.
 * @Email shohrab.datagridltd@gmail.com
 */

class MainVM(
    private val categoryDataSource: CategoryDataSource = CategoryDataSourceImpl(RetrofitConnect.getCategoryApiService()),
    private val categoryRepository: CategoryRepository = CategoryRepositoryImpl(categoryDataSource)
) : BaseViewModel() {
    val loading: MutableLiveData<Boolean> = MutableLiveData()
     var address: Address? = null


    val categoryListLiveData: MutableLiveData<Resource<List<Category>, Any>> = MutableLiveData()
    val partnersLiveData: MutableLiveData<Resource<List<DiscountPartner>, Any>> = MutableLiveData()

    fun getCategory(pcId: String) = viewModelScope.launch {
        categoryListLiveData.postValue(Resource.loading(null))
        categoryListLiveData.postValue(Resource.success(categoryRepository.getCategory(pcId)))
    }

    fun getDiscountPartner(pcId: String) = viewModelScope.launch {
        partnersLiveData.postValue(Resource.loading(null))
        partnersLiveData.postValue(Resource.success(categoryRepository.getDiscountPartner(pcId)))
    }

}