package org.example.totastykotlin.domain.meeting.entity

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import lombok.Builder
import org.example.totastykotlin.domain.common.BaseEntity
import org.example.totastykotlin.domain.meeting.enums.MeetingStatus
import org.example.totastykotlin.domain.member.entity.Member
import org.example.totastykotlin.domain.tasting.entity.MeetingTasting
import org.example.totastykotlin.drink.enums.DrinkType
import org.hibernate.annotations.Comment
import java.time.LocalDateTime

@Entity
open class Meeting(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    @Comment("모임 주최자")
    val author: Member,

    @Column
    @Comment("참가비")
    var participationFee: Int,

    @Column
    @Comment("모임 시작 시간")
    var startAt: LocalDateTime,

    @Column
    @Comment("모집 마감 시간")
    var joinEndAt: LocalDateTime,

    @Column
    @Comment("최대 참가 인원")
    var maxParticipants: Int,

    @Column
    @Comment("최소 참가 인원")
    var minParticipants: Int,

    @Column(nullable = false)
    @Comment("모엠 제목")
    var title: String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meeting_id")
    val id: Long? = null,

    @Embedded
    @Comment("모임 장소")
    var location: Location? = null,

    @Comment("썸네일 이미지 URL")
    var thumbnailUrl: String? = null,

    @Column(columnDefinition = "TEXT")
    @Comment("모임 내용")
    var content: String? = null,

    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Comment("모임 상태")
    var status: MeetingStatus? = null,

    @Enumerated(EnumType.STRING)
    @Comment("주요 음료 타입")
    var drinkType: DrinkType? = null,

    @OneToMany(mappedBy = "meeting", cascade = [CascadeType.ALL], orphanRemoval = true)
    @Builder.Default
    val tastingList: MutableList<MeetingTasting> = ArrayList(),

    @OneToMany(mappedBy = "meeting", cascade = [CascadeType.ALL], orphanRemoval = true)
    @Builder.Default
    val participations: MutableList<MeetingParticipation> = ArrayList(),

    ) : BaseEntity() {

    fun updateMeeting(
        title: String, location: Location, participationFee: Int,
        startAt: LocalDateTime, joinEndAt: LocalDateTime, maxParticipants: Int,
        minParticipants: Int, thumbnailUrl: String, content: String, drinkType: DrinkType
    ) {
        this.title = title
        this.location = location
        this.participationFee = participationFee
        this.startAt = startAt
        this.joinEndAt = joinEndAt
        this.maxParticipants = maxParticipants
        this.minParticipants = minParticipants
        this.thumbnailUrl = thumbnailUrl
        this.content = content
        this.drinkType = drinkType
    }

    fun cancelMeeting() {
        this.status = MeetingStatus.CANCELED
        softDelete()
    }

    fun closeMeeting() {
        this.status = MeetingStatus.CLOSED
    }

    fun isAuthor(memberId: Long): Boolean {
        return this.author.id.equals(memberId)
    }

    fun canJoin(): Boolean {
        return this.status == MeetingStatus.OPEN && LocalDateTime.now().isBefore(this.joinEndAt)
    }

    fun canCancel(): Boolean {
        return this.status == MeetingStatus.OPEN
    }

    fun getCurrentParticipantCount(): Int {
        return participations.stream().filter { participation -> !participation!!.canceled }.count().toInt()
    }

    fun getTastingDrinkCount(): Int {
        return tastingList.stream().filter{tasting -> tasting.deletedAt == null}.count().toInt()
    }

    @JsonProperty("isWished")
    fun isWished(memberId: Long): Boolean {
        return false
    }

    @JsonProperty("isParticipated")
    fun isParticipated(memberId: Long): Boolean {
        return participations.stream()
            .anyMatch { participation -> participation?.member?.id?.equals(memberId) ?: false && !participation.canceled }
    }

    fun addParticipation(participation: MeetingParticipation) {
        this.participations.add(participation)
        participation.meeting = this
    }

    fun checkMaxParticipants() {
        if (getCurrentParticipantCount() >= (maxParticipants ?: 0)) {
            this.status = MeetingStatus.CLOSED
        }
    }
}