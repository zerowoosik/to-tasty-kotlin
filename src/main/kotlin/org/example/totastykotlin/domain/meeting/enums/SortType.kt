package org.example.totastykotlin.domain.meeting.enums

enum class SortType(
    private val value: String,
    private val description: String
) {

    LATEST("latest", "최신순"),
    POPULARITY_WISH("popularity-wish", "위시리스트 많은순"),
    COST_HIGH("costHigh", "가격 높은순"),
    COST_LOW("costLow", "가격 낮은순"),
    CLOSING_SOON("closingSoon", "마감 임박순"),
    CLOSED_RECENT("closedRecent", "최근 마감순");
}