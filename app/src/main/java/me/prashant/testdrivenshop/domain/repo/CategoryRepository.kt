package me.prashant.testdrivenshop.domain.repo

import kotlinx.coroutines.flow.Flow
import me.prashant.testdrivenshop.domain.model.Category
import me.prashant.testdrivenshop.util.Resource

interface CategoryRepository {
    suspend fun getCategories(): Flow<Resource<List<Category>>>
}
