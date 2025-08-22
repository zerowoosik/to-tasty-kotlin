package org.example.totastykotlin.domain.meeting.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.example.totastykotlin.domain.meeting.entity.Meeting
import java.time.LocalDateTime

class MeetingListResponse(
    @field:Schema(description = "모임 ID", example = "101")
    private val meetingId: Long? = null,

    @field:Schema(description = "모임 주최자 닉네임", example = "홍길동")
    private val meetingAuthor: String? = null,

    @field:Schema(description = "모임 제목", example = "우리 오늘 취해봐요~ 낭만에 취해요")
    private val meetingTitle: String? = null,

    @field:Schema(description = "모임 위치")
    private val location: LocationResponse? = null,

    @field:Schema(description = "참가비", example = "30000")
    private val participationFee: Int? = null,

    @field:Schema(description = "모임 시작 시간", example = "2024-05-20T10:30:00")
    private val startAt: LocalDateTime? = null,

    @field:Schema(description = "모집 마감 시간", example = "2024-05-19T23:59:00")
    private val joinEndAt: LocalDateTime? = null,

    @field:Schema(description = "최대 참가 인원", example = "5")
    private val maxParticipants: Int? = null,

    @field:Schema(description = "최소 참가 인원", example = "3")
    private val minParticipants: Int? = null,

    @field:Schema(description = "현재 참가 인원", example = "2")
    private val currentParticipants: Int? = null,

    @field:Schema(description = "시음 음료 개수", example = "3")
    private val tastingDrinkCount: Int? = null,

    @field:JsonProperty("isWished")
    @field:Schema(description = "위시리스트 추가 여부", example = "true")
    private val isWished: Boolean? = null,

    @field:Schema(description = "썸네일 URL", example = "https://example.com/image.jpg")
    private val thumbnailUrl: String? = null,

    @field:Schema(description = "모임 상태", example = "open")
    private val status: String? = null,

    @field:Schema(description = "대표 음료 타입", example = "WINE")
    private val drinkType: String? = null,

    @field:JsonProperty("isReviewed")
    @field:Schema(description = "리뷰 작성 여부", example = "false")
    private val isReviewed: Boolean? = null,

    @field:Schema(description = "참가자 목록")
    private val participation: MutableList<ParticipationResponse>? = null,
) {
    companion object {
        fun from(meeting: Meeting, isWished: Boolean, isReviewed: Boolean): MeetingListResponse {
            val drinkType: String? = if (meeting.drinkType != null) meeting.drinkType!!.name else null

            val participationList: MutableList<ParticipationResponse> = meeting.participations.stream().filter{participation -> !participation.canceled}
                .map{participation -> ParticipationResponse.from(participation, meeting.author.id) }
                .toList()

            return MeetingListResponse(
                meetingId = meeting.id,
                meetingAuthor = meeting.author.nickname,
                meetingTitle = meeting.title,
                location = LocationResponse.from(meeting.location),
                participationFee = meeting.participationFee,
                startAt = meeting.startAt,
                joinEndAt = meeting.joinEndAt,
                maxParticipants = meeting.maxParticipants,
                minParticipants = meeting.minParticipants,
                currentParticipants = meeting.getCurrentParticipantCount(),
                tastingDrinkCount = meeting.getTastingDrinkCount(),
                isWished = isWished,
                thumbnailUrl = meeting.thumbnailUrl,
                status = meeting.status?.value,
                drinkType = drinkType,
                isReviewed = isReviewed,
                participation = participationList
            )
        }

    }
}