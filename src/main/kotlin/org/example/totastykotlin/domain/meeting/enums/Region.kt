package org.example.totastykotlin.domain.meeting.enums

enum class Region(
    val value: String,
    val description: String
) {
    ALL("all", "전체"),
    SEOUL("서울", "서울"),
    GYEONGGI("경기도", "경기도"),
    INCHEON("인천", "인천"),
    GANGWON("강원도", "강원도"),
    CHUNGCHEONG("충청도", "충청도"),
    GYEONGSANG("경상도", "경상도"),
    JEOLLA("전라도", "전라도"),
    JEJU("제주도", "제주도")
}