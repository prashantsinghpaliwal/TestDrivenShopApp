package me.prashant.testdrivenshop.data.network

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import me.prashant.testdrivenshop.data.remote.CategoryItemDTO
import org.junit.Assert.assertEquals
import org.junit.Test

class CategoryApiTest {
    private val mockApi = mockk<CategoryApiService>()

    @Test
    fun `test getCategories returns expected data`() =
        runBlocking {
            // Arrange
            val mockResponse =
                listOf(
                    CategoryItemDTO(
                        name = "Electronics",
                        slug = "1",
                        url = "http://example.com/electronics.jpg",
                    ),
                    CategoryItemDTO(
                        name = "Books",
                        slug = "2",
                        url = "http://example.com/books.jpg",
                    ),
                )
            coEvery { mockApi.getCategories() } returns mockResponse

            // Act
            val response = mockApi.getCategories()

            // Assert
            assertEquals(2, response.size)
            assertEquals("Electronics", response[0].name)
            assertEquals("Books", response[1].name)
        }
}
