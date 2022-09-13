package com.dkb.pocketutil.api

import com.dkb.pocketutil.data.UrlShortenRequest
import com.dkb.pocketutil.data.UrlShortenResponse
import com.dkb.pocketutil.service.UrlShortenService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api")
class UrlShortenApi(private val urlShortenService: UrlShortenService) {

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody urlRequest: UrlShortenRequest): UrlShortenResponse {
        return UrlShortenResponse(urlShortenService.create(urlRequest.url))
    }
}
