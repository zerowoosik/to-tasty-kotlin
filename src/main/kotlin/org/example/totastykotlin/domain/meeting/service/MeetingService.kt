package org.example.totastykotlin.domain.meeting.service

import org.example.totastykotlin.domain.meeting.dto.response.MeetingListResponse
import org.example.totastykotlin.domain.meeting.entity.Meeting
import org.example.totastykotlin.domain.meeting.enums.SortType
import org.example.totastykotlin.domain.meeting.repository.MeetingRepository
import org.example.totastykotlin.drink.enums.DrinkType
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.stereotype.Service

@Service
class MeetingService(
    val meetingRepository: MeetingRepository,
) {
    fun getMeetings(
        sido: String?,
        drinkType: DrinkType?,
        sortType: SortType?,
        pageable: Pageable?
    ): Slice<MeetingListResponse> {
        val meetings: Slice<Meeting> = meetingRepository.findMeetingsWithFilter(sido, drinkType, sortType, pageable)
        return meetings.map { meeting -> MeetingListResponse.from(meeting, false, false) }
    }

//    fun getMeetings(sido : String, drinkType: DrinkType, sortType: SortType, pageable: Pageable, memberId: Long) : Slice<MeetingListResponse> {
//        val meetings: Slice<Meeting> = meetingRepository.findMeetingsWithFilter(sido, drinkType, sortType, pageable)
//
//        return meetings.map { meeting -> {
//            val isWished : Boolean = wish
//        } }
//    }
}