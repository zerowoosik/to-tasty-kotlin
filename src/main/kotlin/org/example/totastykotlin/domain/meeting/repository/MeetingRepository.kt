package org.example.totastykotlin.domain.meeting.repository

import org.example.totastykotlin.domain.meeting.entity.Meeting
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.LocalDateTime
import java.util.*

interface MeetingRepository : JpaRepository<Meeting, Long>, MeetingRepositoryCustom {

    @Query("SELECT m FROM Meeting m " +
            "LEFT JOIN FETCH m.author " +
            "LEFT JOIN FETCH m.tastingList mt " +
            "LEFT JOIN FETCH mt.drink " +
            "WHERE m.id = :meetingId AND m.deletedAt IS NULL")
    fun findBydIdWithDetails(@Param("meetingId") meetingId: Long) : Optional<Meeting>


    @Query("SELECT m FROM Meeting m " +
            "WHERE m.author.id = :authorId AND m.deletedAt IS NULL")
    fun findByAuthorId(@Param("authorId") authorId: Long, pageable: Pageable): Slice<Meeting>



    @Query("SELECT m FROM Meeting m " +
            "WHERE m.status = org.example.totastykotlin.domain.meeting.enums.MeetingStatus.OPEN " +
            "AND m.joinEndAt < :currentTime AND m.deletedAt IS NULL")
    fun findExpiredOpenMeetings(@Param("currentTime") currentTime: LocalDateTime): MutableList<Meeting>

}