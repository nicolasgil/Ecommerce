package com.nicolas.ecommerce.utils

import com.google.gson.Gson
import com.nicolas.ecommerce.domain.models.Product
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class TestExtension {

    @Mock
    private lateinit var gsonMock: Gson

    @Before
    fun setUp() {
        gsonMock = mock(Gson::class.java)
    }

    @Test
    fun `Test dummyProducts`() {
        `when`(
            gsonMock.fromJson(
                anyString(),
                eq(Product::class.java)
            )
        ).thenReturn(sampleTestProduct)

        val products = listOf(sampleTestProduct)

        assertNotNull(products)
        assertEquals(1, products.size)
    }

    @Test
    fun `Test dummyCategories`() {
        val categories = sampleTestCategories
        assertNotNull(categories)
    }

    @Test
    fun `Test applySorting`() {
        val products = listOf(sampleTestProduct)
        val sortBy = SortBy.PRICE_LOW_TO_HIGH
        val sortedProducts = applySorting(products, sortBy)

        assertNotNull(sortedProducts)
    }
}