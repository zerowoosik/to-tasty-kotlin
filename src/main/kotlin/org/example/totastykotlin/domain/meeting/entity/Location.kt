package org.example.totastykotlin.domain.meeting.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import org.hibernate.annotations.Comment

@Embeddable
class Location(
    @Column(length = 20)
    @Comment("시/도")
    val sido: String,

    @Column(length = 200)
    @Comment("도로명 주소")
    val address: String,

    @Column(length = 100)
    @Comment("상세 주소")
    val detail: String? = null,
) {

    companion object {
        fun of(sido: String, address: String, detail: String): Location {
            return Location(sido = sido, address = address, detail = detail)
        }
    }
}