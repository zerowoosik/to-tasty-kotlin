package org.example.totastykotlin.domain.meeting.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.example.totastykotlin.domain.meeting.entity.MeetingParticipation

@Schema(description = "참가자 정보 응답")
class ParticipationResponse(

    @field:Schema(description = "멤버 Id", example = "1")
    val memberId: Long? = null,

    @field:Schema(description = "닉네임", example = "홍길동")
    val nickname: String? = null,

    @field:Schema(description = "프로필 이미지 URL", example = "https://example.com/profile.jpg")
    val profileImgUrl: String? = null,

    @field:JsonProperty("isHost")
    @field:Schema(description = "모임 주최자 여부", example = "true")
    val isHost: Boolean? = null,

) {
    companion object {
        fun from(participation: MeetingParticipation, hostMemberId: Long): ParticipationResponse {
            return ParticipationResponse(
                memberId = participation.member.id,
                nickname = participation.member.nickname,
                profileImgUrl = participation.member.profileImageUrl,
                isHost = (participation.member.id == hostMemberId)
            )
        }
    }
}