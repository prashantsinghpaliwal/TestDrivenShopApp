package me.prashant.testdrivenshop.presentation.mapper

import me.prashant.testdrivenshop.domain.model.Category
import me.prashant.testdrivenshop.presentation.model.CategoryUIModel
import me.prashant.testdrivenshop.util.mapper.Mapper
import javax.inject.Inject

class CategoryUIModelMapper
    @Inject
    constructor() : Mapper<Category, CategoryUIModel> {
        override fun convert(input: Category): CategoryUIModel =
            CategoryUIModel(
                slug = input.slug,
                name = input.name,
                url = input.url,
            )
    }
