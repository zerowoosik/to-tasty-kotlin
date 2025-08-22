package org.example.totastykotlin.domain.meeting.repository

import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import org.example.totastykotlin.domain.meeting.entity.Meeting
import org.example.totastykotlin.domain.meeting.entity.QMeeting.meeting
import org.example.totastykotlin.domain.meeting.enums.MeetingStatus
import org.example.totastykotlin.domain.meeting.enums.SortType
import org.example.totastykotlin.domain.meeting.enums.SortType.*
import org.example.totastykotlin.domain.member.entity.QMember.member
import org.example.totastykotlin.drink.enums.DrinkType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.domain.SliceImpl
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class MeetingRepositoryImpl @Autowired constructor(
    val queryFactory : JPAQueryFactory
) : MeetingRepositoryCustom{


    override fun findMeetingsWithFilter(
        sido: String?,
        drinkType: DrinkType?,
        sortType: SortType?,
        pageable: Pageable?
    ): Slice<Meeting> {
        return findMeetingsWithFilter(sido, drinkType, sortType, null, pageable)
    }

    override fun findMeetingsWithFilter(
        sido: String?,
        drinkType: DrinkType?,
        sortType: SortType?,
        statusFilter: MeetingStatus?,
        pageable: Pageable?
    ): Slice<Meeting> {

        // 1단계: Meeting + author만 조회
        val meetings: MutableList<Meeting> = queryFactory
            .selectFrom(meeting)
            .distinct()
            .leftJoin(meeting.author, member).fetchJoin()
            .where(
                meeting.deletedAt.isNull(),
                sidoFilter(sido),
                drinkTypeFilter(drinkType),
                statusFilter(statusFilter, sortType)
            )
            .orderBy(getOrderSpecifier(sortType))
            .offset(pageable!!.getOffset())
            .limit(pageable.getPageSize().toLong() + 1)
            .fetch()


        return SliceImpl(meetings, pageable, false)
    }

    override fun findMeetingsWithFilterByMemberId(
        sido: String,
        drinkType: DrinkType,
        sortType: SortType,
        pageable: Pageable,
        memberId: Long
    ): Slice<Meeting> {
        TODO("Not yet implemented")
    }

    private fun sidoFilter(sido: String?): BooleanExpression? {
        if (sido == null || sido.isEmpty() || "전체" == sido) {
            return null
        }
        return meeting.location.sido.eq(sido)
    }

    private fun drinkTypeFilter(drinkType: DrinkType?): BooleanExpression? {
        if (drinkType == null) {
            return null
        }
        return meeting.drinkType.eq(drinkType)
    }

    private fun statusFilter(statusFilter: MeetingStatus?, sortType: SortType?): BooleanExpression? {
        val now = LocalDateTime.now()
        val twentyFourHoursLater = now.plusHours(24)

        if (statusFilter != null) {
            return meeting.status.eq(statusFilter)
        }

        if (sortType === SortType.CLOSING_SOON) {
            return meeting.status.eq(MeetingStatus.OPEN)
                .and(meeting.joinEndAt.between(now, twentyFourHoursLater))
        }

        if (sortType === SortType.CLOSED_RECENT) {
            return meeting.status.eq(MeetingStatus.CLOSED)
        }

        return null
    }


    private fun getOrderSpecifier(sortType: SortType?): OrderSpecifier<*>? {
        if (sortType == null) {
            return meeting.createdAt.desc()
        }

        return when (sortType) {
            LATEST -> meeting.createdAt.desc()
            COST_HIGH -> meeting.participationFee.desc()
            COST_LOW -> meeting.participationFee.asc()
            POPULARITY_WISH -> meeting.createdAt.desc()
            CLOSING_SOON -> meeting.joinEndAt.asc()
            CLOSED_RECENT -> meeting.joinEndAt.desc()
        }
    }
}