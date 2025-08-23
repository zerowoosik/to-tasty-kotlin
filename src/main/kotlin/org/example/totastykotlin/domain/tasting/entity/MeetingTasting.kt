package org.example.totastykotlin.domain.tasting.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import org.example.totastykotlin.domain.common.BaseEntity
import org.example.totastykotlin.domain.meeting.entity.Meeting
import org.example.totastykotlin.drink.entity.Drink
import org.hibernate.annotations.Comment

@Entity
open class MeetingTasting(

    @Comment("음료 이름")
    val drinkName: String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meeting_tasting_id")
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id")
    @Comment("모임")
    var meeting: Meeting? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drink_id")
    @Comment("시음 음료")
    val drink: Drink? = null,

    @Comment("음료 이미지 URL")
    val drinkImageUrl: String? = null,


    ) : BaseEntity() {
        companion object {
            fun create(drink: Drink, drinkName: String, drinkImgUrl: String): MeetingTasting {
                return MeetingTasting(drink = drink, drinkName = drinkName, drinkImageUrl = drinkImgUrl)
            }

            fun createTasting(meeting: Meeting, drink: Drink) : MeetingTasting {
                return MeetingTasting(meeting = meeting, drink = drink, drinkName = drink.name, drinkImageUrl = drink.drinkImageUrl)
            }
        }
    }