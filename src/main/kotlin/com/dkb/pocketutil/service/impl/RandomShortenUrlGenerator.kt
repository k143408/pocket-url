package com.dkb.pocketutil.service.impl

import com.dkb.pocketutil.service.ShortUrlGenerator
import org.springframework.stereotype.Service
import java.util.*
import java.util.Collections.shuffle
import java.util.function.Consumer

@Service
class RandomShortenUrlGenerator : ShortUrlGenerator {
    private var stringPool =
        StringBuilder("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ")

    // First, after confusing the characters in the StringPool string pool, randomize
    private val shuffleNum = 5

    override fun generate(url: String?): String {
        shufflePool()
        return this.createWithRandomStringPool(8)
    }

    private fun createWithRandomStringPool(length: Int): String {
        val random = Random()
        val sb: StringBuilder = StringBuilder()

        for (i in 0 until length) {
            sb.append(this.stringPool.get(random.nextInt(this.stringPool.length)))
        }
        return sb.toString()
    }

    private fun shufflePool() {
        val strPool = this.stringPool.split("")
        for (i in 0 until shuffleNum) {
            shuffle(strPool)
        }
        val sb = StringBuilder()
        strPool.forEach(Consumer { str: String? ->
            sb.append(
                str
            )
        })
        stringPool = sb
    }

}
