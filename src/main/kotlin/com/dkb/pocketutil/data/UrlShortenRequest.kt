package com.dkb.pocketutil.data

import javax.validation.constraints.Pattern

data class UrlShortenRequest(
    @field:Pattern(
        regexp = "^(https?|http)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]",
        message = "Url should be valid"
    )
    val url: String
)
