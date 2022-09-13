package com.dkb.pocketutil.service.impl

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes=[UrlShortenServiceImpl::class])
internal class UrlShortenServiceImplTest {

    @Autowired
    private lateinit var urlShortenServiceImpl: UrlShortenServiceImpl

    @Test
    fun shouldThrowException() {
        assertThrows(NotImplementedError::class.java) {
            urlShortenServiceImpl.create("dummy-url")
        }

    }
}
