package com.dkb.pocketutil.service.impl

import com.dkb.pocketutil.repo.ShortUrlRepository
import com.dkb.pocketutil.service.ShortUrlGenerator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class UrlShortenServiceImplTest {

    private lateinit var urlShortenServiceImpl: UrlShortenServiceImpl
    private lateinit var shortUrlRepository: ShortUrlRepository
    private lateinit var generator: ShortUrlGenerator
    private val baseUrl: String = "http://hello.com/"

    @BeforeAll
    fun initialize() {

        this.shortUrlRepository = mock(ShortUrlRepository::class.java)
        this.generator = mock(ShortUrlGenerator::class.java)
        this.urlShortenServiceImpl =
            UrlShortenServiceImpl(this.shortUrlRepository, this.generator, baseUrl)
    }

    @Test
    fun shouldThrowException() {
        `when`(this.shortUrlRepository.save(any())).thenReturn(null)
        `when`(this.generator.generate(anyString())).thenReturn("ABCD1234")
        val createdUrl = this.urlShortenServiceImpl.create("http://localhost")
        assertEquals(baseUrl.plus("ABCD1234"), createdUrl)
    }
}
