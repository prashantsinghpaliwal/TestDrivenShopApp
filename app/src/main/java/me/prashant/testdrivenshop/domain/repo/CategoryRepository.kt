package me.prashant.testdrivenshop.domain.repo

import me.prashant.testdrivenshop.domain.model.Category

interface CategoryRepository {
    suspend fun getCategories(): List<Category>
}
