package com.hmshohrab.communityfinance.data.repository.category

import com.hmshohrab.communityfinance.data.dataSource.category.CategoryDataSource
import com.hmshohrab.communityfinance.data.model.Category
import com.hmshohrab.communityfinance.data.model.DiscountPartner

/**
 * BISMILLAH HIR RAHMAN NIR RAHIM
 * Created by Mohammad Shohrab on 07,March,2023
 * @Company Data Grid Limited
 * @Address House #14/A(new), Dhanmondi R/A, Dhaka 1209.
 * @Email shohrab.datagridltd@gmail.com
 */
class CategoryRepositoryImpl(private val categoryDataSource: CategoryDataSource) :
    CategoryRepository {
    override suspend fun getCategory(pcId: String): List<Category> {
        return categoryDataSource.getCategory(pcId)
    }

    override suspend fun getDiscountPartner(pcId: String): List<DiscountPartner> {
         return categoryDataSource.getDiscountPartner(pcId)
    }
}