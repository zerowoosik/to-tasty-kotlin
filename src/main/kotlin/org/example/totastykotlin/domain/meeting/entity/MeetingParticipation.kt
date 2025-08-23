package org.example.totastykotlin.domain.meeting.entity

import jakarta.persistence.*
import org.example.totastykotlin.domain.common.BaseEntity
import org.example.totastykotlin.domain.member.entity.Member
import org.hibernate.annotations.Comment

@Entity
open class MeetingParticipation(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @Comment("참가자")
    val member: Member,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id")
    @Comment("모임")
    var meeting: Meeting,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meeting_participation_id")
    private val id: Long? = null,

    @Comment("참가 취소 여부")
    var canceled: Boolean = false

): BaseEntity() {
    companion object {
        fun create(member: Member, meeting: Meeting): MeetingParticipation {
            return MeetingParticipation(member = member, meeting = meeting)
        }
    }

    fun cancel() {
        this.canceled = true;
    }

}
