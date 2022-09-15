package com.dkb.pocketutil.service.impl

import com.dkb.pocketutil.domain.ShortUrl
import com.dkb.pocketutil.repo.ShortUrlRepository
import com.dkb.pocketutil.service.ShortUrlGenerator
import com.dkb.pocketutil.service.UrlShortenService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.net.URI
import java.net.URL

@Service
class UrlShortenServiceImpl(
    private val shortUrlRepository: ShortUrlRepository,
    private val shortUrlGenerator: ShortUrlGenerator,
    @Value("\${app.base.url}") private val baseUrl: String
) : UrlShortenService {
    val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun create(longUrl: String): String? {
        logger.info("Creating shorten url for {}", longUrl)
        val url = URL(longUrl)
        val urlWithoutProtocol = url.authority.plus(url.file)
        val shortenUrl = shortUrlGenerator.generate(longUrl)
        val shortUrl = ShortUrl(shortenUrl, urlWithoutProtocol)
        shortUrlRepository.save(shortUrl)
        return baseUrl.plus(shortUrl.shortUrl)
    }

    override fun getLongUrl(shortUrl: String): URI {
        return shortUrlRepository.findByShortUrl(shortUrl)
            .map { s ->
                URI(s.longUrl)
            }
            .orElseThrow {
                NotFoundException()
            }
    }
}
