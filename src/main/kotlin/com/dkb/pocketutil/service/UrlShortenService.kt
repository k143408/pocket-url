package com.dkb.pocketutil.service

import java.net.URI

interface UrlShortenService {
    fun create(longUrl: String): String?
    fun getLongUrl(shortUrl: String): URI
}
