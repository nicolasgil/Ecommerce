package com.nicolas.ecommerce.domain.usecases

import com.nicolas.ecommerce.data.repositories.ProductRepository
import com.nicolas.ecommerce.utils.sampleTestProduct
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever


@RunWith(MockitoJUnitRunner::class)
class TestGetProductsUseCase {


    @Mock
    private lateinit var productRepository: ProductRepository

    private lateinit var getProductsUseCase: GetProductsUseCase

    @Before
    fun setup() {
        getProductsUseCase = GetProductsUseCase(productRepository)
    }

    @Test
    fun `when GetProductsUseCase return Success`(): Unit =
        runTest {
            val getAllProducts = GetProductsUseCase(productRepository)
            val product = listOf(sampleTestProduct.copy(id = 1))
            whenever(productRepository.getAllProducts()).thenReturn(product)

            val result = getAllProducts()

            assertEquals(product, result)
        }
}