package org.example.totastykotlin.domain.common

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

@MappedSuperclass
abstract class BaseEntity protected constructor(
    @CreatedDate
    @Column(updatable = false)
    val createdAt: LocalDateTime? = null,

    @LastModifiedDate
    val updatedAt: LocalDateTime? = null,

    var deletedAt: LocalDateTime? = null
) {
    fun softDelete() {
        this.deletedAt = LocalDateTime.now()
    }
}
