package org.example.totastykotlin.domain.meeting.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.tags.Tag
import org.example.totastykotlin.domain.meeting.enums.SortType
import org.example.totastykotlin.drink.enums.DrinkType
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestParam

@Tag(name = "모임 관련 API", description = "모임 생성, 조회, 수정, 삭제, 참가 관련 API")
interface MeetingApi {
    @Operation(
        summary = "모임 리스트 조회 (필터/정렬)",
        description = "필터링과 정렬 옵션을 적용하여 모임 리스트를 무한스크롤 방식으로 조회합니다.",
        parameters = [Parameter(
            name = "filter",
            description = "지역(시/도) 필터",
            example = "서울"
        ), Parameter(name = "drinkType", description = "음료 타입 필터", example = "WINE"), Parameter(
            name = "sort",
            description = "정렬 방식",
            example = "latest"
        ), Parameter(
            name = "memberId",
            description = "특정 회원이 참여한 모임만 조회 (선택사항)",
            example = "1"
        ), Parameter(
            name = "page",
            description = "페이지 번호 (1부터 시작)",
            example = "1",
            `in` = ParameterIn.QUERY
        ), Parameter(name = "size", description = "페이지 크기", example = "20", `in` = ParameterIn.QUERY)]
    )
    fun getMeetings(@RequestParam(required = false) filter: String?,
                    @RequestParam(required = false) drinkType: DrinkType?,
                    @RequestParam(required = false) sort: SortType?,
                    @RequestParam(required = false) memberId: Long?,
                    @Parameter(hidden = true) pageable: Pageable?
    ): ResponseEntity<*>
}