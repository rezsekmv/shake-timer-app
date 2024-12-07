package com.example.beerapp

import android.content.Context
import com.example.beerapp.datasource.network.BeerService
import com.example.beerapp.model.dto.BeerDTO
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import retrofit2.HttpException
import java.io.IOException
import java.nio.charset.Charset
import javax.inject.Inject

@HiltAndroidTest
@Config(application = HiltTestApplication::class, manifest=Config.NONE)
@RunWith(RobolectricTestRunner::class)
class NetworkModuleTest {

    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltAndroidRule.inject()
    }

    @Inject
    lateinit var service: BeerService

    @Inject
    lateinit var mockWebServer: MockWebServer

    @ApplicationContext
    @Inject
    lateinit var context: Context

    private fun readFileFromAssets(context: Context, fileName: String): String? = try {
        val source = context.assets.open(fileName).source().buffer()
        source.readByteString().string(Charset.forName("utf-8"))
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getBeersFromApiTest() = runTest {
        // Arrange
        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(readFileFromAssets(context, "beer.json").orEmpty()))

        // Act
        val actualDto = service.getBeers()

        // Assert
        val expectedDto = listOf(BeerDTO(
            id = 1,
            name = "Soproni",
            year = "02/1998",
            description = "Ez egy olcsó magyar sör, minden kocsmában nagyjából ezt csapolják.",
            image = "https://image-url.hu/image.jpg"
        ))
        assertEquals(expectedDto, actualDto)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getBeerFromApiTest() = runTest {
        // Arrange
        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(readFileFromAssets(context, "beer.json").orEmpty()))

        // Act
        val id = 1
        val actualDto = service.getBeerById(id)

        // Assert
        val expectedDto = listOf(BeerDTO(
            id = id,
            name = "Soproni",
            year = "02/1998",
            description = "Ez egy olcsó magyar sör, minden kocsmában nagyjából ezt csapolják.",
            image = "https://image-url.hu/image.jpg"
        ))
        assertEquals(expectedDto, actualDto)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

}