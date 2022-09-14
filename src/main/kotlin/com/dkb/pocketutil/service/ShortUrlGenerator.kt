package com.dkb.pocketutil.service

interface ShortUrlGenerator {

    fun generate(url:String?): String
}
