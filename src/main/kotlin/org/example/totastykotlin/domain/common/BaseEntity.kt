package org.example.totastykotlin.domain.common

import jakarta.persistence.Column
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

abstract class BaseEntity protected constructor(
    @CreatedDate
    @Column(updatable = false)
    private val createdAt: LocalDateTime? = null,

    @LastModifiedDate
    private val updatedAt: LocalDateTime? = null,

    private var deletedAt: LocalDateTime? = null
) {
    fun softDelete() {
        this.deletedAt = LocalDateTime.now()
    }
}
