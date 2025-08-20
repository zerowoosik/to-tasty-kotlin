package org.example.totastykotlin.domain.meeting.enums

import org.springframework.context.annotation.Description

enum class MeetingStatus(
    private val value: String,
    private val description: String,
) {
    OPEN("open", "모집중"),
    CLOSED("closed", "마감"),
    CANCELED("canceled", "취소")
}