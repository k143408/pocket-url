package com.dkb.pocketutil.service

interface UrlShortenService {
    fun create(url: String): String
}
