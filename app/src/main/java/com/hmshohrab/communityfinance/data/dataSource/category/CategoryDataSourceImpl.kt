package com.hmshohrab.communityfinance.data.dataSource.category

import com.haroldadmin.cnradapter.NetworkResponse
import com.hmshohrab.communityfinance.data.model.Category
import com.hmshohrab.communityfinance.data.model.DiscountPartner

/**
 * BISMILLAH HIR RAHMAN NIR RAHIM
 * Created by Mohammad Shohrab on 07,March,2023
 * @Company Data Grid Limited
 * @Address House #14/A(new), Dhanmondi R/A, Dhaka 1209.
 * @Email shohrab.datagridltd@gmail.com
 */
class CategoryDataSourceImpl(private val categoryApiService: CategoryApiService) : CategoryDataSource {

    override suspend fun getCategory(pcId:String): List<Category> {
        return when(val resposne =   categoryApiService.getCategory(pcId =pcId)){
            is NetworkResponse.Success ->{
                resposne.body.categories ?: emptyList()
            }
            else -> {
                emptyList<Category>()
            }
        }
    }

    override suspend fun getDiscountPartner(pcId: String): List<DiscountPartner> {
        return when(val resposne =   categoryApiService.getDiscountPartner(pcId =pcId)){
            is NetworkResponse.Success ->{
                resposne.body.partners ?: emptyList()
            }
            else -> {
                emptyList<DiscountPartner>()
            }
        }
    }
}