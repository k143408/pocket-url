package com.dkb.pocketutil.api

import com.dkb.pocketutil.repo.ShortUrlRepository
import com.dkb.pocketutil.service.UrlShortenService
import com.dkb.pocketutil.service.impl.NotFoundException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.net.URI

@ExtendWith(SpringExtension::class)
@WebMvcTest(controllers = [UrlRedirectApi::class])
internal class UrlRedirectApiTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private val shortUrlRepository: ShortUrlRepository? = null

    @MockBean
    private lateinit var urlShortenService: UrlShortenService

    @Test
    fun shouldCreateResponseWithEmpty() {
        Mockito.`when`(urlShortenService.getLongUrl(anyString()))
            .thenReturn(URI("http://pocketurl"))
        mockMvc.perform(
            get("/AASDV123"))
            .andExpect(status().is3xxRedirection)
    }

    @Test
    fun shouldGiveNotFoundRequestStatus() {
        Mockito.`when`(urlShortenService.getLongUrl(anyString()))
            .thenThrow(NotFoundException())
        mockMvc.perform(
            get("/AASDV123"))
            .andExpect(status().is4xxClientError)
    }
}
