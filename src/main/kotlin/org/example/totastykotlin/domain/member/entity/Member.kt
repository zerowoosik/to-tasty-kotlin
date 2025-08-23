package org.example.totastykotlin.domain.member.entity

import jakarta.persistence.*
import org.example.totastykotlin.domain.common.BaseEntity
import org.example.totastykotlin.domain.member.enums.Gender
import org.hibernate.annotations.Comment
import java.time.LocalDate

@Entity
class Member(
    @Column(unique = true)
    var email: String,

    @Column(name = "social_id")
    @Comment("소셜 ID")
    var socialId: String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    var id: Long,

    @Column
    var nickname: String? = null,

    var birthdate: LocalDate? = null,

    var isAdultVerified: Boolean? = null,

    @Enumerated(EnumType.STRING)
    var gender: Gender? = null,

    var profileImageUrl: String? = null,

    @Column(name = "refresh_token")
    @Comment("리프레시 토큰")
    var refreshToken: String? = null,

    @Column(name = "interest")
    var interests: MutableList<String> = ArrayList()

) : BaseEntity()