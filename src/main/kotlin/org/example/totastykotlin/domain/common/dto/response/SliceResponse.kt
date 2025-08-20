package org.example.totastykotlin.domain.common.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.data.domain.Slice

@Schema(description = "슬라이스 응답 DTO (무한스크롤용)")
class SliceResponse<T>(
    @field:Schema(description = "데이터 리스트")
    private val content: List<T>,

    @field:Schema(description = "페이지 정보")
    private val sliceInfo: SliceInfo,
) {
    companion object {
        fun <T> of(slice: Slice<T>) : SliceResponse<T> {
            return SliceResponse(slice.content, SliceInfo.of(slice))
        }
    }


    @Schema(description = "슬라이스 페이지 정보")
    class SliceInfo(
        @field:Schema(description = "현재 페이지 정보 (1부터 시작)", example = "1")
        private val currentPage: Int,

        @field:Schema(description = "페이지 크기", example = "20")
        private val size: Int,

        @field:Schema(description = "다음 페이지가 있는지 여부", example = "true")
        @field:JsonProperty("hasNext")
        private val hasNext: Boolean,
    ) {
        companion object {
            fun of(slice: Slice<*>): SliceInfo {
                return SliceInfo(
                    slice.number + 1,
                    slice.size,
                    slice.hasNext()
                )
            }
        }
    }
}