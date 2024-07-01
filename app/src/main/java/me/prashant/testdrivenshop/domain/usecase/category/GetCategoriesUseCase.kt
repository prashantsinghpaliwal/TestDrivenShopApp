package me.prashant.testdrivenshop.domain.usecase.category

import kotlinx.coroutines.flow.Flow
import me.prashant.testdrivenshop.domain.model.Category
import me.prashant.testdrivenshop.domain.repo.CategoryRepository
import me.prashant.testdrivenshop.util.Resource
import javax.inject.Inject

class GetCategoriesUseCase
    @Inject
    constructor(
        private val categoryRepository: CategoryRepository,
    ) {
        suspend operator fun invoke(): Flow<Resource<List<Category>>> = categoryRepository.getCategories()
    }
