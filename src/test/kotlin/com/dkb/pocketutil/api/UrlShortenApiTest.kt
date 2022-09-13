package com.dkb.pocketutil.api

import com.dkb.pocketutil.data.UrlShortenRequest
import com.dkb.pocketutil.service.UrlShortenService
import com.fasterxml.jackson.databind.ObjectMapper
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@WebMvcTest(controllers = [UrlShortenApi::class])
internal class UrlShortenApiTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    private lateinit var urlShortenService: UrlShortenService

    @Test
    fun shouldCreateResponseWithEmpty() {

        Mockito.`when`(urlShortenService.create(anyString())).thenReturn("http://pocketurl")
        val requestBody = UrlShortenRequest("https://google.com/")
        mockMvc.perform(
            post("/api/create")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsBytes(requestBody)))
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.url").value("http://pocketurl"))
    }
}
