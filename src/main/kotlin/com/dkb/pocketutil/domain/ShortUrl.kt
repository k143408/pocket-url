package com.dkb.pocketutil.domain

import org.hibernate.Hibernate
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
data class ShortUrl(
    var shortUrl: String?,
    var longUrl: String?,
    val visible: Boolean = true,
    val createTime: LocalDateTime = LocalDateTime.now(),
    val expirationTime: LocalDateTime? = null,
    val updateTime: LocalDateTime = LocalDateTime.now(),
    val deleted: Boolean = false,
    @Id @GeneratedValue var id: Long? = null,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(
                other
            )
        ) return false
        other as ShortUrl

        return id != null && id == other.id
    }

    override fun hashCode(): Int = Objects.hash(id, shortUrl)

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , shortUrl = $shortUrl )"
    }
}

