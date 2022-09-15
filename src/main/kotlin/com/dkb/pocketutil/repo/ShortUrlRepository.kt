package com.dkb.pocketutil.repo

import com.dkb.pocketutil.domain.ShortUrl
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ShortUrlRepository : CrudRepository<ShortUrl, Long> {

    fun findByShortUrl(shortUrl: String): Optional<ShortUrl>
}
