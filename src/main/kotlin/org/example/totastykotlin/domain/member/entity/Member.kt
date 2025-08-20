package org.example.totastykotlin.domain.member.entity

import jakarta.persistence.*
import org.example.totastykotlin.domain.common.BaseEntity
import org.example.totastykotlin.domain.member.enums.Gender
import org.hibernate.annotations.Comment
import java.time.LocalDate

@Entity
class Member(
    @Column(unique = true)
    private var email: String,

    @Column(name = "social_id")
    @Comment("소셜 ID")
    private var socialId: String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    var id: Long,

    @Column
    private var nickname: String? = null,

    private var birthdate: LocalDate? = null,

    private var isAdultVerified: Boolean? = null,

    @Enumerated(EnumType.STRING)
    private var gender: Gender? = null,

    private var profileImageUrl: String? = null,

    @Column(name = "refresh_token")
    @Comment("리프레시 토큰")
    private var refreshToken: String? = null,

    @Column(name = "interest")
    private var interests: MutableList<String?> = ArrayList()

) : BaseEntity()