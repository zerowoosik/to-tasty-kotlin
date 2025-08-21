package org.example.totastykotlin.domain.meeting.repository

import org.example.totastykotlin.domain.meeting.entity.Meeting
import org.example.totastykotlin.domain.meeting.enums.MeetingStatus
import org.example.totastykotlin.domain.meeting.enums.SortType
import org.example.totastykotlin.drink.enums.DrinkType
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice

interface MeetingRepositoryCustom {
    fun findMeetingsWithFilter(sido: String?, drinkType: DrinkType?, sortType: SortType?, pageable: Pageable?) : Slice<Meeting>

    fun findMeetingsWithFilter(sido: String, drinkType: DrinkType, sortType: SortType, statusFilter: MeetingStatus, pageable: Pageable): Slice<Meeting>

    fun findMeetingsWithFilterByMemberId(sido: String, drinkType: DrinkType, sortType: SortType, pageable: Pageable, memberId: Long) : Slice<Meeting>
}