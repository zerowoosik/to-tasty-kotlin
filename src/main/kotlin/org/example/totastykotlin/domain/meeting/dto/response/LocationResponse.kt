package org.example.totastykotlin.domain.meeting.dto.response

import io.swagger.v3.oas.annotations.media.Schema
import org.example.totastykotlin.domain.meeting.entity.Location

@Schema(description = "위치 정보 응답")
class LocationResponse (

    @field:Schema(description = "시/도", example = "서울")
    val sido: String? = null,

    @field:Schema(description = "도로명 주소", example = "을지로 34-2")
    val address: String? = null,

    @field:Schema(description = "상세 주소", example = "3층 카페")
    val detail: String? = null,
) {
    companion object {
        fun from(location: Location?) : LocationResponse {
            return LocationResponse(sido = location?.sido, address = location?.address, detail = location?.detail)
        }
    }
}