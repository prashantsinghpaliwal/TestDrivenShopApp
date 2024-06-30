package me.prashant.testdrivenshop.data.mapper

import me.prashant.testdrivenshop.data.remote.CategoryItemDTO
import me.prashant.testdrivenshop.domain.model.Category
import me.prashant.testdrivenshop.util.mapper.Mapper
import javax.inject.Inject

class CategoryDtoToCategoryMapper @Inject constructor(): Mapper<CategoryItemDTO, Category> {
    override fun convert(input: CategoryItemDTO): Category =
        Category(
            slug = input.slug,
            name = input.name,
            url = input.url,
        )
}
