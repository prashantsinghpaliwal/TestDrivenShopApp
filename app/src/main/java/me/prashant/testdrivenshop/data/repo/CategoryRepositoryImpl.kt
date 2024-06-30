package me.prashant.testdrivenshop.data.repo

import me.prashant.testdrivenshop.data.mapper.CategoryDtoToCategoryMapper
import me.prashant.testdrivenshop.data.network.CategoryApiService
import me.prashant.testdrivenshop.domain.model.Category
import me.prashant.testdrivenshop.domain.repo.CategoryRepository
import javax.inject.Inject

class CategoryRepositoryImpl
    @Inject
    constructor(
        private val api: CategoryApiService,
        private val mapper: CategoryDtoToCategoryMapper,
    ) : CategoryRepository {
        override suspend fun getCategories(): List<Category> = api.getCategories().map { mapper.convert(it) }
    }
