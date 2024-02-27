package com.nicolas.ecommerce.data.repositories

import com.nicolas.ecommerce.data.datasources.local.LocalDataSource
import com.nicolas.ecommerce.data.datasources.remote.RemoteDataSource
import com.nicolas.ecommerce.utils.sampleTestCategories
import com.nicolas.ecommerce.utils.sampleTestProduct
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever


@RunWith(MockitoJUnitRunner::class)
class ProductRepositoryImplTest {

    @Mock
    lateinit var localDataSource: LocalDataSource

    @Mock
    lateinit var remoteDataSource: RemoteDataSource

    @Mock
    private lateinit var productRepository: ProductRepositoryImpl


    @Before
    fun setUp() {
        productRepository = ProductRepositoryImpl(remoteDataSource, localDataSource)
    }

    @Test
    fun `When get products local and return Success`(): Unit = runTest {
        val products = listOf(sampleTestProduct.copy(1))
        whenever(localDataSource.getProductList()).thenReturn(products)

        val result = productRepository.getAllProducts()

        assertEquals(products, result)
    }

    @Test
    fun `When get products local and return Empty`(): Unit = runTest {
        whenever(localDataSource.getProductList()).thenReturn(emptyList())

        val remoteProducts = listOf(sampleTestProduct)
        whenever(remoteDataSource.getAllProducts()).thenReturn(remoteProducts)

        val result = productRepository.getAllProducts()

        verify(remoteDataSource).getAllProducts()

        verify(localDataSource).saveProductList(remoteProducts)

        assertEquals(remoteProducts, result)
    }


    @Test
    fun `When get categories local and return Success`(): Unit = runTest {
        val categories = sampleTestCategories
        whenever(localDataSource.getCategoriesList()).thenReturn(categories)

        val result = productRepository.getCategories()

        assertEquals(categories, result)
    }

    @Test
    fun `When get categories local and return Empty`(): Unit = runTest {
        whenever(localDataSource.getCategoriesList()).thenReturn(emptyList())

        val remoteCategories = sampleTestCategories
        whenever(remoteDataSource.getCategories()).thenReturn(remoteCategories)

        val result = productRepository.getCategories()

        verify(remoteDataSource).getCategories()

        verify(localDataSource).saveCategoriesList(remoteCategories)

        assertEquals(remoteCategories, result)
    }


}