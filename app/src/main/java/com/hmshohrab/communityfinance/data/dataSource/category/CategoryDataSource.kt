package com.hmshohrab.communityfinance.data.dataSource.category

import com.haroldadmin.cnradapter.NetworkResponse
import com.hmshohrab.communityfinance.data.model.Category
import com.hmshohrab.communityfinance.data.model.DataResponse
import com.hmshohrab.communityfinance.data.model.DiscountPartner
import retrofit2.http.Query

/**
 * BISMILLAH HIR RAHMAN NIR RAHIM
 * Created by Mohammad Shohrab on 07,March,2023
 * @Company Data Grid Limited
 * @Address House #14/A(new), Dhanmondi R/A, Dhaka 1209.
 * @Email shohrab.datagridltd@gmail.com
 */
interface CategoryDataSource {

   suspend fun getCategory(pcId:String) : List<Category>

   suspend fun getDiscountPartner( pcId: String) : List<DiscountPartner>
}