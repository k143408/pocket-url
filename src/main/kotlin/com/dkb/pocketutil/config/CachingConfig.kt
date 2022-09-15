package com.dkb.pocketutil.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.concurrent.ConcurrentMapCacheManager
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDateTime


@Configuration
@EnableCaching
@EnableScheduling
class CachingConfig {
    val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @Component
    class SimpleCacheCustomizer :
        CacheManagerCustomizer<ConcurrentMapCacheManager?> {
        override fun customize(cacheManager: ConcurrentMapCacheManager?) {
            cacheManager?.setCacheNames(listOf("shortUrls", "longUrls"))
        }
    }

    @CacheEvict(allEntries = true, cacheNames = ["shortUrls", "longUrls"])
    @Scheduled(fixedDelayString = "P1D")
    fun reportCacheEvict() {
        logger.info("All caches are evicted at {}", LocalDateTime.now())
    }
}
