package com.dkb.pocketutil.api

import com.dkb.pocketutil.service.UrlShortenService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/")
class UrlRedirectApi(private val urlShortenService: UrlShortenService) {

    @GetMapping("{shortUrl}")
    fun create(@PathVariable shortUrl: String): ResponseEntity<Void> {
       return ResponseEntity.status(HttpStatus.FOUND)
            .location(urlShortenService.getLongUrl(shortUrl))
            .build();
    }
}
