package me.prashant.testdrivenshop.domain.repo

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import me.prashant.testdrivenshop.data.mapper.CategoryDtoToCategoryMapper
import me.prashant.testdrivenshop.data.network.CategoryApiService
import me.prashant.testdrivenshop.data.repo.CategoryRepositoryImpl
import me.prashant.testdrivenshop.domain.model.Category
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CategoryRepositoryTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var repository: CategoryRepository
    private lateinit var api: CategoryApiService
    private lateinit var mapper: CategoryDtoToCategoryMapper
    private lateinit var gson: Gson

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        gson = GsonBuilder().create()

        api =
            Retrofit
                .Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CategoryApiService::class.java)

        mapper = mockk<CategoryDtoToCategoryMapper>()
        repository = CategoryRepositoryImpl(api, mapper)
    }

    @Test
    fun `test get categories with mock server`() {
        val jsonString =
            """
            [
                {
                    "slug": "beauty",
                    "name": "Beauty",
                    "url": "https://dummyjson.com/products/category/beauty"
                },
                {
                    "slug": "fragrances",
                    "name": "Fragrances",
                    "url": "https://dummyjson.com/products/category/fragrances"
                }
            ]
            """.trimIndent()

        mockWebServer.enqueue(MockResponse().setBody(jsonString))
        val mockCategory =
            Category("Beauty", "beauty", "https://dummyjson.com/products/category/beauty")

        every { mapper.convert(any()) } returns mockCategory

        val result: List<Category> = runBlocking { repository.getCategories() }

        assertEquals(2, result.size)
        assertEquals("Beauty", result[0].name)
        assertEquals("beauty", result[0].slug)
        assertEquals("https://dummyjson.com/products/category/beauty", result[0].url)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}
