package com.hmshohrab.communityfinance.data.dataSource.category

import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param
import com.haroldadmin.cnradapter.NetworkResponse
import com.hmshohrab.communityfinance.data.Endpoints
import com.hmshohrab.communityfinance.data.model.Category
import com.hmshohrab.communityfinance.data.model.DataResponse
import com.hmshohrab.communityfinance.data.model.DiscountPartner
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * BISMILLAH HIR RAHMAN NIR RAHIM
 * Created by Mohammad Shohrab on 07,March,2023
 * @Company Data Grid Limited
 * @Address House #14/A(new), Dhanmondi R/A, Dhaka 1209.
 * @Email shohrab.datagridltd@gmail.com
 */
interface CategoryApiService {
    @GET(Endpoints.categoryUrl)
    suspend fun getCategory(@Query("type")benefit :String= "benefit", @Query("parent_id")  pcId: String) : NetworkResponse<DataResponse<Category>,Any>
    @GET(Endpoints.discountUrl)
    suspend fun getDiscountPartner( @Query("pc_id")  pcId: String) : NetworkResponse<DataResponse<DiscountPartner>,Any>



}