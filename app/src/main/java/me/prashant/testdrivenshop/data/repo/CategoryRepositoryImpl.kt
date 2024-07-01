package me.prashant.testdrivenshop.data.repo

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.prashant.testdrivenshop.data.mapper.CategoryDtoToCategoryMapper
import me.prashant.testdrivenshop.data.network.CategoryApiService
import me.prashant.testdrivenshop.domain.model.Category
import me.prashant.testdrivenshop.domain.repo.CategoryRepository
import me.prashant.testdrivenshop.util.Resource
import javax.inject.Inject

class CategoryRepositoryImpl
    @Inject
    constructor(
        private val api: CategoryApiService,
        private val mapper: CategoryDtoToCategoryMapper,
    ) : CategoryRepository {
        override suspend fun getCategories(): Flow<Resource<List<Category>>> =
            flow {
                emit(Resource.Loading(true))
                try {
                    val response = api.getCategories()
                    val domainModel = response.map { mapper.convert(it) }
                    emit(Resource.Success(domainModel))
                } catch (e: Exception) {
                    emit(Resource.Error(Exception(e.localizedMessage ?: "An error occurred")))
                }

                api.getCategories().map { mapper.convert(it) }
            }
    }
