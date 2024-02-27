package com.nicolas.ecommerce.domain.usecases

import com.nicolas.ecommerce.data.repositories.ProductRepository
import com.nicolas.ecommerce.utils.sampleTestCategories
import junit.framework.TestCase
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class TestGetCategoriesUseCase {

    @Mock
    private lateinit var productRepository: ProductRepository

    private lateinit var getCategoriesUseCase: GetCategoriesUseCase

    @Before
    fun setup() {
        getCategoriesUseCase = GetCategoriesUseCase(productRepository)
    }

    @Test
    fun `when GetCategoriesUseCase return Success`(): Unit =
        runTest {
            val category = sampleTestCategories
            whenever(productRepository.getCategories()).thenReturn(category)

            val result = getCategoriesUseCase()

            TestCase.assertEquals(category, result)
        }
}